package com.travel.hotel.product.service.impl;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.query.QuotaDailyReportQueryDTO;
import com.travel.common.dto.product.request.QuotaDTO;
import com.travel.common.dto.product.response.DailyQuotaDTO;
import com.travel.common.dto.product.response.QuotaDailyReportResponseDTO;
import com.travel.common.dto.product.response.RoomTypeDTO;
import com.travel.common.enums.QuotaOperateEnum;
import com.travel.common.enums.ResultTypeEnum;
import com.travel.common.exception.ParameterException;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.order.dao.OrderDayPricePOMapper;
import com.travel.hotel.order.dao.OrderPOMapper;
import com.travel.hotel.order.dao.OrderQuotaLogPOMapper;
import com.travel.hotel.order.entity.po.OrderDayPricePO;
import com.travel.hotel.order.entity.po.OrderDayPricePOExample;
import com.travel.hotel.order.entity.po.OrderPO;
import com.travel.hotel.order.entity.po.OrderQuotaLogPO;
import com.travel.hotel.product.dao.QuotaPOMapper;
import com.travel.hotel.product.entity.po.QuotaPO;
import com.travel.hotel.product.entity.po.QuotaReportPO;
import com.travel.hotel.product.entity.po.ReserveQuotaPO;
import com.travel.hotel.product.service.PricePlanService;
import com.travel.hotel.product.service.QuotaService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/24.
 */
@Service
public class QuotaServiceImpl implements QuotaService {

    private static final Logger LOG = LoggerFactory.getLogger(QuotaServiceImpl.class);

    @Autowired
    private QuotaPOMapper quotaPOMapper;

    @Autowired
    private OrderQuotaLogPOMapper orderQuotaLogPOMapper;

    @Autowired
    private OrderDayPricePOMapper orderDayPricePOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private PricePlanService pricePlanService;

    @Override
    public int batchAddQuota(List<QuotaPO> quotaPOList) {
        if (CollectionUtils.isEmpty(quotaPOList)){
            return 0;
        }
        for (QuotaPO po : quotaPOList){
            quotaPOMapper.insertSelective(po);
        }

        return 1;
    }

    @Override
    public int deleteByAccountIdAndSaleDate(Long accountId, Date saleDate) {
        QuotaPO po = new QuotaPO();
        po.setAccountId(accountId);
        po.setSaleDate(saleDate);
        return quotaPOMapper.deleteByAccountIdAndSaleDate(po);
    }

    @Override
    public synchronized String batchDeductQuota(List<QuotaDTO> quotaDTOList) {
        // 1. 扣配额前，先判断是否有预留配额，如果有预留配额，先从预留配额中扣除，不够的再从在售配额扣
        // 2. 预留配额和在售配额被扣的配额数，都需要加到已扣配额字段
        // 3. 下单时，如果房态有房可超，配额不足，不影响正常下单，下单完成后再扣配额，没有扣到就记录原因

        StringBuilder errorMsg = new StringBuilder("【扣配额】\n");
        for (QuotaDTO quotaDTO : quotaDTOList) {

            StringBuilder perPricePlanErrorMsg = new StringBuilder();
            perPricePlanErrorMsg.append("*将扣除产品【").append(quotaDTO.getPricePlanName()).append("】").append(DateUtil.dateToString(quotaDTO.getSaleDate())).append("日的配额").append(quotaDTO.getQuotaNum()).append("间，扣除详情：");

            ReserveQuotaPO reserveQuotaPO = null;
            try {
                reserveQuotaPO = pricePlanService.queryReserveQuota(quotaDTO.getPricePlanId(),
                        quotaDTO.getSaleDate(), quotaDTO.getDistributeCode());
            } catch (Exception e) {
                LOG.error("查询预留配额表失败， quotaDTO=" + quotaDTO, e);
                perPricePlanErrorMsg.append("查询预售配额失败，将直接扣在售配额；");
            }

            Boolean blnReserve = false;
            if (null == reserveQuotaPO || null == reserveQuotaPO.getReserveQuotaNum() || reserveQuotaPO.getReserveQuotaNum() <= 0) {
                // 如果查询不到预留配额、预留配额为空或小于0，则直接扣在售配额

                Boolean bln = this.deductOnSaleQuota(quotaDTO);
                perPricePlanErrorMsg.append("扣").append(quotaDTO.getQuotaNum()).append("个在售配额").append(bln ? "成功" : "失败").append(";");

                perPricePlanErrorMsg.append("\n");
                errorMsg.append(perPricePlanErrorMsg);
                continue;
            } else if (reserveQuotaPO.getReserveQuotaNum() >= quotaDTO.getQuotaNum()) {
                // 大于0，说明预留配额大于待扣配额数，直接扣预留配额，不需要再扣配额表的在售配额

                blnReserve = this.deductReserveQuota(quotaDTO, quotaDTO.getQuotaNum());
                perPricePlanErrorMsg.append("扣").append(quotaDTO.getQuotaNum()).append("个预留配额").append(blnReserve ? "成功" : "失败，将直接扣在售配额").append(";");

                // 如果预留配额扣失败，尝试扣在售配额
                if (!blnReserve) {
                    Boolean blnOnSale = this.deductOnSaleQuota(quotaDTO);
                    perPricePlanErrorMsg.append("扣").append(quotaDTO.getQuotaNum()).append("个在售配额").append(blnOnSale ? "成功" : "失败").append(";");
                }
            } else {
                // 预留配额扣除失败，或者不够扣，就继续扣在售配额
                blnReserve = this.deductReserveQuota(quotaDTO, reserveQuotaPO.getReserveQuotaNum());
                perPricePlanErrorMsg.append("扣").append(reserveQuotaPO.getReserveQuotaNum()).append("个预留配额").append(blnReserve ? "成功" : "失败，将直接扣在售配额").append(";");

                // 预留配额不够的，再从在售配额扣
                QuotaDTO onSaleQuotaDeduct = BeanCopy.copyProperties(quotaDTO, QuotaDTO.class);
                Integer quota = blnReserve ? quotaDTO.getQuotaNum() - reserveQuotaPO.getReserveQuotaNum() : quotaDTO.getQuotaNum();
                onSaleQuotaDeduct.setQuotaNum(quota);
                Boolean blnOnSale = this.deductOnSaleQuota(onSaleQuotaDeduct);
                perPricePlanErrorMsg.append("扣").append(quota).append("个在售配额").append(blnOnSale ? "成功" : "失败").append(";");
            }

            perPricePlanErrorMsg.append("\n");
            errorMsg.append(perPricePlanErrorMsg);
        }

        try {
            //扣配额，就记录减了多少个配额
            quotaDTOList.forEach(quotaDTO -> quotaDTO.setQuotaNum(0 - quotaDTO.getQuotaNum()));
            this.saveDeductOrRefundQuotaLog(quotaDTOList, QuotaOperateEnum.DEDUCT.key);
        } catch (Exception e) {
            LOG.error("添加扣配额日志失败", e);
        }
        return errorMsg.toString();
    }

    private Boolean deductOnSaleQuota(QuotaDTO quotaDTO) {
        try {
            List<QuotaDTO> list = new ArrayList<>();
            list.add(quotaDTO);
            quotaPOMapper.batchDeductQuota(list);
        } catch (Exception e) {
            LOG.error("扣在售配额失败，待扣除配额:" + quotaDTO, e);
            return false;
        }
        return true;
    }

    private Boolean deductReserveQuota(QuotaDTO quotaDTO, Integer deductQuotaNum) {
        try {
            pricePlanService.deductReserveQuota(quotaDTO.getPricePlanId(), quotaDTO.getSaleDate(), quotaDTO.getDistributeCode(), deductQuotaNum);

            // 更新已用配额
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("usedQuotaNum", deductQuotaNum);
            paramMap.put("pricePlanId", quotaDTO.getPricePlanId());
            paramMap.put("saleDate", quotaDTO.getSaleDate());
            quotaPOMapper.updateUsedQuota(paramMap);
        } catch (Exception e) {
            LOG.error("扣预留配额失败，待扣除配额:" + quotaDTO + ", 配额数：" + deductQuotaNum, e);
            return false;
        }
        return true;
    }

    @Override
    public void batchRefundQuota(List<QuotaDTO> quotaDTOList) {
        try {
            quotaPOMapper.batchRefundQuota(quotaDTOList);
            this.saveDeductOrRefundQuotaLog(quotaDTOList, QuotaOperateEnum.REFUND.key);
        } catch (Exception e) {
            LOG.error("批量退配额失败", e);
            throw new ServiceException(ResultTypeEnum.REFUND_PRODUCT_QUOTA_FAIL);
        }
    }

    @Override
    public void refundQuota(String orderCode) {
        try {
            OrderPO orderPO = orderPOMapper.selectByOrderCode(orderCode);
            if (null == orderPO) {
                LOG.error("取消的订单不存在");
                throw new ServiceException(ResultTypeEnum.ORDER_NOT_EXISTS);
            }

            OrderDayPricePOExample example = new OrderDayPricePOExample();
            example.createCriteria().andOrderCodeEqualTo(orderCode);
            List<OrderDayPricePO> orderDayPricePOList = orderDayPricePOMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(orderDayPricePOList)) {
                LOG.error("该订单每日价格配额信息不存在");
                throw new ServiceException(ResultTypeEnum.ORDER_DAY_PRICE_NOT_EXISTS);
            }

            List<QuotaDTO> quotaDTOList = new ArrayList<>();
            /** 退配额 */
            for (OrderDayPricePO odpp : orderDayPricePOList) {
                QuotaDTO qd = new QuotaDTO();
                qd.setSaleDate(odpp.getSaleDate());
                qd.setPricePlanId(odpp.getPriceplanId());
                qd.setQuotaNum(odpp.getRooms());
                qd.setOrderCode(orderCode);
                qd.setDistributeCode(orderPO.getAgentCode());
                quotaDTOList.add(qd);
            }

            this.batchRefundQuota(quotaDTOList);
        } catch (Exception e) {
            LOG.error("批量退配额失败", e);
            throw new ServiceException(ResultTypeEnum.REFUND_PRODUCT_QUOTA_FAIL);
        }
    }

    private void saveDeductOrRefundQuotaLog(List<QuotaDTO> quotaDTOList, String opType) {

        try {
            for (QuotaDTO qd : quotaDTOList) {
                OrderQuotaLogPO oqlp = new OrderQuotaLogPO();
                oqlp.setSaleDate(qd.getSaleDate());
                oqlp.setPriceplanId(qd.getPricePlanId().toString());
                oqlp.setQuotaNum(qd.getQuotaNum());
                oqlp.setOrderCode(qd.getOrderCode());
                oqlp.setOpType(opType);
                orderQuotaLogPOMapper.insertSelective(oqlp);
            }
        } catch (Exception e) {
            LOG.error("批量记录扣/退配额日志失败", e);
            throw new ServiceException(ResultTypeEnum.RECORD_DEDUCT_REFUND_QUOTA_FAIL);
        }
    }

    @Override
    public List<QuotaPO> queryByCondition(Map<String, Object> paramMap) {
        return quotaPOMapper.selectByCondition(paramMap);
    }

    @Override
    public int updateQuotaById(QuotaPO po) {
        return quotaPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public int batchUpdateQuotaById(List<QuotaPO> poList) {
        poList.forEach(po -> quotaPOMapper.updateByQuotaIdSelective(po));
        return 1;
    }

    @Override
    public int updateByAccountIdSelective(QuotaPO po) {
        if (po.getAccountId() == null){
            LOG.error("配额账号为空,{}",po);
            throw new ParameterException("配额账号为空");
        }
        return quotaPOMapper.updateByAccountIdSelective(po);
    }

    /**
     * 只适合，新增预留配额时使用。如果是减少预留配额不能用这个
     * @param po
     * @return
     */
    @Override
    public int addReserveQuota(QuotaPO po) {
        return quotaPOMapper.addReserveQuota(po);
    }

    @Override
    public PaginationDTO<QuotaDailyReportResponseDTO> queryQuotaReportForPage(QuotaDailyReportQueryDTO quotaDailyReportQueryDTO) {
        return null;
    }

    @Override
    public QuotaDailyReportResponseDTO queryQuotaReport(QuotaDailyReportQueryDTO quotaDailyReportQueryDTO) {
        List<QuotaReportPO> reportPOList = quotaPOMapper.queryQuotaReport(quotaDailyReportQueryDTO);

        if (CollectionUtils.isEmpty(reportPOList)){
            return  null;
        }

        List<Date> dateList = DateUtil.getDateList(DateUtil.stringToDate(quotaDailyReportQueryDTO.getBeginDate()),DateUtil.stringToDate(quotaDailyReportQueryDTO.getEndDate()));


        //按照房型分类，有几个房型就分组为几个List

        Map<Long,String> roomNameMap = new HashMap<>();
        Map<Long,List<QuotaReportPO>> quotaReportPOMap = new HashMap<>();
        List<QuotaReportPO> oneRoomQuotaList = null;
        for(QuotaReportPO quotaReportPO : reportPOList){
            if (quotaReportPOMap.containsKey(quotaReportPO.getRoomTypeId())){
                quotaReportPOMap.get(quotaReportPO.getRoomTypeId()).add(quotaReportPO);
            }else{
                oneRoomQuotaList = new ArrayList<>();
                oneRoomQuotaList.add(quotaReportPO);
                quotaReportPOMap.put(quotaReportPO.getRoomTypeId(),oneRoomQuotaList);
                roomNameMap.put(quotaReportPO.getRoomTypeId(),quotaReportPO.getRoomName());
            }
        }

        if (null == quotaReportPOMap){
            return null;
        }

        QuotaDailyReportResponseDTO responseDTO = new QuotaDailyReportResponseDTO();
        //获取酒店名称
        String hotelName = reportPOList.get(0).getHotelName();
        Long hotelId = reportPOList.get(0).getHotelId();
        responseDTO.setHotelId(hotelId);
        responseDTO.setHotelName(hotelName);
        responseDTO.setDateList(dateList);

        List<RoomTypeDTO> roomTypeDTOList = new ArrayList<>();
        for(Map.Entry<Long,List<QuotaReportPO>> entry : quotaReportPOMap.entrySet()){
            RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
            roomTypeDTO.setRoomTypeId(entry.getKey());
            roomTypeDTO.setRoomTypeName(roomNameMap.get(entry.getKey()));
            List<DailyQuotaDTO> dailyQuotaList =BeanCopy.listCopy(entry.getValue(), DailyQuotaDTO.class);
            //与日期集合做差集。补充没有的日期进去

            roomTypeDTO.setTotalDailyQuotaList(dailyQuotaList);
            roomTypeDTOList.add(roomTypeDTO);
        }
        responseDTO.setRoomTypeDTOList(roomTypeDTOList);

        return responseDTO;
    }



//
//    /**
//     * （下单时）调整已用配额（增加、减少），在售配额需要变更（减少，增加）
//     * @param quotaPO
//     * @param quotaOperateEnum
//     */
//    @Override
//    public void modifyUsedQuotaNum(QuotaPO quotaPO, QuotaOperateEnum quotaOperateEnum) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("accountId", quotaPO.getAccountId());
//        map.put("saleDate", quotaPO.getSaleDate());
//        List<QuotaPO> quotaPOList = quotaPOMapper.selectByCondition(map);
//        if (CollectionUtils.isEmpty(quotaPOList)) {
//            LOG.error("调整配额失败，无当天配额记录， quotaPO: " + quotaPO);
//            throw new DaoException("调整配额失败，无当天配额记录");
//        }
//
//        QuotaPO ownQuotaPO = quotaPOList.get(0);
//        // 扣配额
//        if (QuotaOperateEnum.DEDUCT.key.equals(quotaOperateEnum.key)) {
//            if (ownQuotaPO.getQuotaNum() < quotaPO.getQuotaNum()) {
//                LOG.error("扣配额失败，配额不满足， ownQuotaPO: " + ownQuotaPO + ",  quotaPO: " + quotaPO);
//                throw new DaoException("扣配额失败，配额不满足");
//            }
//
//            try {
//                // 在售配额扣减，已用配额增加
//                ownQuotaPO.setQuotaNum(ownQuotaPO.getQuotaNum() - quotaPO.getQuotaNum());
//                ownQuotaPO.setUsedQuotaNum(ownQuotaPO.getUsedQuotaNum() + quotaPO.getQuotaNum());
//                quotaPOMapper.updateByPrimaryKeySelective(ownQuotaPO);
//            } catch (Exception e) {
//                LOG.error("扣配额失败", e);
//                throw new DaoException("扣配额失败");
//            }
//        } else if (QuotaOperateEnum.INCREASE.key.equals(quotaOperateEnum.key)){
//            // 增加配额
//            if (ownQuotaPO.getUsedQuotaNum() < quotaPO.getQuotaNum()) {
//                LOG.error("扣配额失败，配额不满足， ownQuotaPO: " + ownQuotaPO + ",  quotaPO: " + quotaPO);
//                throw new DaoException("扣配额失败，配额不满足");
//            }
//
//            try {
//                // 在售配额扣减，已用配额增加
//                ownQuotaPO.setQuotaNum(ownQuotaPO.getQuotaNum() - quotaPO.getQuotaNum());
//                ownQuotaPO.setUsedQuotaNum(ownQuotaPO.getUsedQuotaNum() + quotaPO.getQuotaNum());
//                quotaPOMapper.updateByPrimaryKeySelective(ownQuotaPO);
//            } catch (Exception e) {
//                LOG.error("扣配额失败", e);
//                throw new DaoException("扣配额失败");
//            }
//        }
//
//
//    }
//
//    @Override
//    public void batchModifyUsedQuotaNum(List<QuotaPO> quotaPOList, QuotaOperateEnum quotaOperateEnum) {
//
//    }
}
