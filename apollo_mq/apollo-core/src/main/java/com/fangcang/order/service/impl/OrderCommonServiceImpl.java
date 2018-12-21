package com.fangcang.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fangcang.agent.request.QueryOrderDeductCreditDTO;
import com.fangcang.agent.response.OrderDeductCreditDTO;
import com.fangcang.agent.service.AgentService;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.PayStatusEnum;
import com.fangcang.common.enums.order.SupplyStatusEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.finance.enums.AccountStatusEnum;
import com.fangcang.finance.remote.CreditRemote;
import com.fangcang.finance.remote.request.CreditOrderPayDTO;
import com.fangcang.message.weixin.cache.redis.util.RedisUtil;
import com.fangcang.order.domain.DeratePolicyDO;
import com.fangcang.order.domain.DeratePolicyPriceDO;
import com.fangcang.order.domain.GuestDO;
import com.fangcang.order.domain.OrderCheckDetailDO;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderFinanceDO;
import com.fangcang.order.domain.OrderNoteDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.SupplyAdditionChargeDO;
import com.fangcang.order.domain.SupplyFinanceDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.domain.SupplyProductDO;
import com.fangcang.order.domain.SupplyProductPriceDO;
import com.fangcang.order.dto.DeratePolicyPriceDTO;
import com.fangcang.order.dto.OrderCheckDetailDTO;
import com.fangcang.order.dto.OrderMessageRedisCacheDTO;
import com.fangcang.order.dto.SupplyOrderCofirmMsgDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.mapper.DeratePolicyMapper;
import com.fangcang.order.mapper.DeratePolicyPriceMapper;
import com.fangcang.order.mapper.GuestMapper;
import com.fangcang.order.mapper.OrderCheckDetailMapper;
import com.fangcang.order.mapper.OrderFinanceMapper;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.mapper.OrderNoteMapper;
import com.fangcang.order.mapper.OrderOperationLogMapper;
import com.fangcang.order.mapper.SupplyAdditionChargeMapper;
import com.fangcang.order.mapper.SupplyFinanceMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.mapper.SupplyProductMapper;
import com.fangcang.order.mapper.SupplyProductPriceMapper;
import com.fangcang.order.request.DeleteOrderMessageRequestDTO;
import com.fangcang.order.request.DeleteSupplyOrderCofirmMsgRequestDTO;
import com.fangcang.order.request.OrderCreditPayOrRefundRequestDTO;
import com.fangcang.order.request.OrderDebuctOrRetreatQuotaRequestDTO;
import com.fangcang.order.request.SupplyCreditPayOrReceiptRequestDTO;
import com.fangcang.order.response.AssemblyCreateOrderResponseDTO;
import com.fangcang.order.response.DeratePolicyResponseDTO;
import com.fangcang.order.response.OrderRedundancyInfoResponseDTO;
import com.fangcang.order.response.SupplyOrderPriceSumResponseDTO;
import com.fangcang.order.response.SupplyOrderResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import com.fangcang.order.response.SupplyProductResponseDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.product.dto.ProductDailyDTO;
import com.fangcang.product.dto.QuotaStateDailyDTO;
import com.fangcang.product.request.DebuctOrRetreatQuotaRequestDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.response.DebuctOrRetreatQuotaResponseDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;
import com.fangcang.product.service.PriceInfoService;
import com.fangcang.product.service.QuotaStateService;
import com.fangcang.supplier.request.QuerySupplyOrderDeductCreditDTO;
import com.fangcang.supplier.response.SupplyOrderDeductCreditDTO;
import com.fangcang.supplier.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 订单内部公共服务， 不对外开放
 *
 * @author : zhanwang
 * @date : 2018/6/2
 */
@Service
@Slf4j
public class OrderCommonServiceImpl implements OrderCommonService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private SupplyProductMapper supplyProductMapper;
    @Resource
    private SupplyAdditionChargeMapper additionChargeMapper;
    @Resource
    private GuestMapper guestMapper;
    @Resource
    private OrderCheckDetailMapper checkDetailMapper;
    @Resource
    private SupplyProductPriceMapper productPriceMapper;
    @Resource
    private OrderOperationLogMapper logMapper;
    @Resource
    private DeratePolicyMapper deratePolicyMapper;
    @Resource
    private DeratePolicyPriceMapper deratePolicyPriceMapper;
    @Resource
    private OrderFinanceMapper orderFinanceMapper;
    @Resource
    private SupplyFinanceMapper supplyFinanceMapper;

    @Resource
    private QuotaStateService quotaStateService;
    @Resource
    private PriceInfoService priceInfoService;
    @Resource
    private CreditRemote creditRemote;
    @Resource
    private AgentService agentService;
    @Resource
    private SupplyService supplyService;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private OrderNoteMapper orderNoteMapper;

    @Override
    public OrderRedundancyInfoResponseDTO getOrderRedundancyInfo(Integer orderId, List<SupplyOrderDO> supplyOrderDOS) {
        OrderRedundancyInfoResponseDTO responseDTO = new OrderRedundancyInfoResponseDTO();
        if (orderId == null) {
            return responseDTO;
        }
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            SupplyOrderDO supplyOrderListQuery = new SupplyOrderDO();
            supplyOrderListQuery.setOrderId(orderId);
            supplyOrderDOS = supplyOrderMapper.select(supplyOrderListQuery);
        }
        Set<String> roomtypeNameSet = new HashSet<>();
        Set<String> supplyOrderCodeSet = new HashSet<>();
        Set<String> supplyStatusSet = new HashSet<>();
        Set<String> supplyCodeSet = new HashSet<>();
        Set<String> supplyConfirmNoSet = new HashSet<>();
        for (SupplyOrderDO supplyOrder : supplyOrderDOS) {
            supplyOrderCodeSet.add(supplyOrder.getSupplyOrderCode());
            supplyStatusSet.add(supplyOrder.getSupplyStatus().toString());
            supplyCodeSet.add(supplyOrder.getSupplyCode());
            if (!StringUtils.isEmpty(supplyOrder.getConfirmNo())) {
                supplyConfirmNoSet.add(supplyOrder.getConfirmNo());
            }
            SupplyProductDO supplyProductQuery = new SupplyProductDO();
            supplyProductQuery.setSupplyOrderId(supplyOrder.getId());
            List<SupplyProductDO> supplyProductDOList = supplyProductMapper.select(supplyProductQuery);
            for (SupplyProductDO productDO : supplyProductDOList) {
                roomtypeNameSet.add(productDO.getRoomTypeName());
            }
        }
        responseDTO.setRoomtypeNameSet(roomtypeNameSet);
        responseDTO.setSupplyOrderCodeSet(supplyOrderCodeSet);
        responseDTO.setSupplyStatusSet(supplyStatusSet);
        responseDTO.setSupplyCodeSet(supplyCodeSet);
        responseDTO.setSupplyConfirmNoSet(supplyConfirmNoSet);
        return responseDTO;
    }

    @Override
    public Map<String, SupplyProductPriceResponseDTO> getProductDailyPriceMap(String channelCode, Integer rateplanId, String checkinDate, String checkoutDate, Integer roomNum, Integer isGroupRoom) {
        Map<String, SupplyProductPriceResponseDTO> productPriceMap = new HashMap<>(16);
        // 1. 调用产品模块，查产品最新价格
        ProductDailyInfoQueryDTO productDailyInfoQuery = new ProductDailyInfoQueryDTO();
        productDailyInfoQuery.setPricePlanId(rateplanId.longValue());
        productDailyInfoQuery.setStartDate(DateUtil.stringToDate(checkinDate));
        productDailyInfoQuery.setEndDate(DateUtil.stringToDate(checkoutDate));
        ResponseDTO<List<ProductDailyInfoResponseDTO>> productInfoResponse = priceInfoService.queryProductDailyInfo(productDailyInfoQuery);
        if (ResultCodeEnum.SUCCESS.code == productInfoResponse.getResult()
                && !CollectionUtils.isEmpty(productInfoResponse.getModel())) {
            List<ProductDailyDTO> productDailyList = productInfoResponse.getModel().get(0).getProductDailyList();
            for (ProductDailyDTO productDailyDTO : productDailyList) {
                SupplyProductPriceResponseDTO productPrice = new SupplyProductPriceResponseDTO();
                String saleDate = DateUtil.dateToString(productDailyDTO.getSaleDate());
                productPrice.setSaleDate(saleDate);
                productPrice.setQuotaNum(productDailyDTO.getQuotaNum());
                SupplyProductPriceDTO priceDTO = getSalePriceByChannelCode(channelCode, productDailyDTO, roomNum, isGroupRoom);
                productPrice.setBasePrice(priceDTO.getBasePrice());
                productPrice.setSalePrice(priceDTO.getSalePrice());
                productPrice.setOverDraft(productDailyDTO.getOverDraft());
                productPrice.setQuotaState(productDailyDTO.getQuotaState());
                productPriceMap.put(saleDate, productPrice);
            }
        }

        return productPriceMap;
    }

    /**
     * 根据渠道编码获取产品对应渠道售价
     *
     * @param channelCode
     * @param productDailyDTO
     * @return
     */
    @Override
    public SupplyProductPriceDTO getSalePriceByChannelCode(String channelCode, ProductDailyDTO productDailyDTO, Integer roomNum, Integer isGroupRoom) {
        SupplyProductPriceDTO priceDTO = new SupplyProductPriceDTO();

        // 团房订单，数量大于5， 取团房价
        if (isGroupRoom == 1 && roomNum != null && roomNum >= 5 && productDailyDTO.getGroupSalePrice() != null) {
            priceDTO.setSalePrice(productDailyDTO.getGroupSalePrice());
            priceDTO.setBasePrice(productDailyDTO.getGroupBasePrice());
            return priceDTO;
        }
        BigDecimal salePrice = null;
        if (channelCode.equals(ChannelTypeEnum.B2B.key)) {
            salePrice = productDailyDTO.getB2bSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.CTRIP.key)) {
            salePrice = productDailyDTO.getCtripSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.ELONG.key)) {
            salePrice = productDailyDTO.getElongSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.TONGCHENG.key)) {
            salePrice = productDailyDTO.getTongchengSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.TUNIU.key)) {
            salePrice = productDailyDTO.getTuniuSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.XMD.key)) {
            salePrice = productDailyDTO.getXmdSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.JD.key)) {
            salePrice = productDailyDTO.getJdSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.QUNAR.key)) {
            salePrice = productDailyDTO.getQunarSalePrice();
//        } else if (channelCode.equals(ChannelTypeEnum.QUNAR_B2B.key)) {
//            salePrice = productDailyDTO.getQunarB2BSalePrice();
//        } else if (channelCode.equals(ChannelTypeEnum.QUNAR_NGT.key)) {
//            salePrice = productDailyDTO.getQunarNgtSalePrice();
//        } else if (channelCode.equals(ChannelTypeEnum.QUNAR_USD.key)) {
//            salePrice = productDailyDTO.getQunarUsdSalePrice();
        } else if (channelCode.equals(ChannelTypeEnum.TAOBAO.key)) {
            salePrice = productDailyDTO.getTaobaoSalePrice();
        }
        priceDTO.setBasePrice(productDailyDTO.getBasePrice());
        priceDTO.setSalePrice(salePrice);
        return priceDTO;
    }

    @Override
    @Transactional
    public Integer booking(AssemblyCreateOrderResponseDTO assemblyOrderData) {
        log.info("组装后的下单参数，booking param：{}", assemblyOrderData);
        // 1. 插入订单表
        OrderDO orderDO = PropertyCopyUtil.transfer(assemblyOrderData, OrderDO.class);
        orderMapper.insert(orderDO);
        int orderId = orderDO.getId();
        // 2. 插入订单财务表
        OrderFinanceDO orderFinanceDO = PropertyCopyUtil.transfer(assemblyOrderData.getOrderFinanceDTO(), OrderFinanceDO.class);
        orderFinanceDO.setOrderId(orderId);
        OrderDO orderNew = orderMapper.selectByPrimaryKey(orderId);
        orderFinanceDO.setOrderCode(orderNew.getOrderCode());
        orderFinanceMapper.insert(orderFinanceDO);
        assemblyOrderData.setOrderId(orderId);
        assemblyOrderData.setOrderCode(orderNew.getOrderCode());
        // 3. 插入入住人表
        List<GuestDO> guestDOList = PropertyCopyUtil.transferArray(assemblyOrderData.getGuestList(), GuestDO.class);
        for (GuestDO guestDO : guestDOList) {
            guestDO.setOrderId(orderId);
        }
        guestMapper.insertList(guestDOList);
        // 4. 插入供货单表
        for (SupplyOrderResponseDTO supplyOrderDTO : assemblyOrderData.getSupplyOrderList()) {
            SupplyOrderDO supplyOrderDO = PropertyCopyUtil.transfer(supplyOrderDTO, SupplyOrderDO.class);
            supplyOrderDO.setOrderId(orderId);
            supplyOrderMapper.insert(supplyOrderDO);
            int supplyOrderId = supplyOrderDO.getId();
            // 5. 插入供货单财务表
            SupplyFinanceDO supplyFinanceDO = PropertyCopyUtil.transfer(supplyOrderDTO.getSupplyFinanceDTO(), SupplyFinanceDO.class);
            supplyFinanceDO.setSupplyOrderId(supplyOrderId);
            SupplyOrderDO supplyOrderNew = supplyOrderMapper.selectByPrimaryKey(supplyOrderId);
            supplyFinanceDO.setSupplyOrderCode(supplyOrderNew.getSupplyOrderCode());
            supplyFinanceMapper.insert(supplyFinanceDO);
            // 6. 插入供货产品表
            for (SupplyProductResponseDTO supplyProductDTO : supplyOrderDTO.getSupplyProductList()) {
                SupplyProductDO supplyProductDO = PropertyCopyUtil.transfer(supplyProductDTO, SupplyProductDO.class);
                supplyProductDO.setSupplyOrderId(supplyOrderId);
                supplyProductDTO.setSupplyOrderId(supplyOrderId);
                supplyProductMapper.insert(supplyProductDO);
                int supplyProductId = supplyProductDO.getId();
                // 7. 插入供货价格表
                List<SupplyProductPriceDO> productPriceDOList = PropertyCopyUtil.transferArray(supplyProductDTO.getProductPriceDTOList(), SupplyProductPriceDO.class);
                for (SupplyProductPriceDO productPriceDO : productPriceDOList) {
                    productPriceDO.setSupplyProductId(supplyProductId);
                }
                productPriceMapper.insertList(productPriceDOList);
                // 8. 插入对账明细表
                List<OrderCheckDetailDO> checkDetailDOList = PropertyCopyUtil.transferArray(supplyProductDTO.getCheckDetailDTOList(), OrderCheckDetailDO.class);
                for (OrderCheckDetailDO checkDetailDO : checkDetailDOList) {
                    checkDetailDO.setOderId(orderId);
                    checkDetailDO.setSupplyOrderId(supplyOrderId);
                    checkDetailDO.setSupplyProductId(supplyProductId);
                    checkDetailDO.setCreator(assemblyOrderData.getCreator());
                    checkDetailDO.setCreateTime(DateUtil.stringToDateWithHms(assemblyOrderData.getCreateTime()));
                }
                checkDetailMapper.insertList(checkDetailDOList);
            }
            // 9. 插入供货附加项表
            if (!CollectionUtils.isEmpty(supplyOrderDTO.getSupplyAdditionChargeList())) {
                List<SupplyAdditionChargeDO> additionChargeDOList = PropertyCopyUtil.transferArray(supplyOrderDTO.getSupplyAdditionChargeList(), SupplyAdditionChargeDO.class);
                for (SupplyAdditionChargeDO additionChargeDO : additionChargeDOList) {
                    additionChargeDO.setOrderId(orderId);
                    additionChargeDO.setSupplyOrderId(supplyOrderId);
                }
                additionChargeMapper.insertList(additionChargeDOList);
            }
            // 10. 插入减免政策表
            if (!CollectionUtils.isEmpty(supplyOrderDTO.getDerateList())) {
                for (DeratePolicyResponseDTO deratePolicyDTO : supplyOrderDTO.getDerateList()) {
                    DeratePolicyDO deratePolicyDO = PropertyCopyUtil.transfer(deratePolicyDTO, DeratePolicyDO.class);
                    deratePolicyDO.setOrderId(orderId);
                    deratePolicyDO.setSupplyOrderId(supplyOrderId);
                    deratePolicyMapper.insert(deratePolicyDO);
                    int deratePolicyId = deratePolicyDO.getId();
                    if (!CollectionUtils.isEmpty(deratePolicyDTO.getDayList())) {
                        List<DeratePolicyPriceDO> deratePolicyPriceDOList = PropertyCopyUtil.transferArray(deratePolicyDTO.getDayList(), DeratePolicyPriceDO.class);
                        for (DeratePolicyPriceDO deratePolicyPriceDO : deratePolicyPriceDOList) {
                            deratePolicyPriceDO.setDeratePolicyId(deratePolicyId);
                        }
                        deratePolicyPriceMapper.insertList(deratePolicyPriceDOList);
                    }
                }
            }
        }

        // 11. 更新订单冗余字段：订单房型、供货单编码、供货单状态、供应商编码
        OrderRedundancyInfoResponseDTO orderRedundancyInfo = getOrderRedundancyInfo(orderId, null);
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(orderId);
        orderUpdate.setRoomTypeNames(StringUtil.listToSQLString(orderRedundancyInfo.getRoomtypeNameSet()));
        orderUpdate.setSupplyOrderCodes(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyOrderCodeSet()));
        orderUpdate.setSupplyStatus(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyStatusSet()));
        orderUpdate.setSupplyCodes(StringUtil.listToSQLString(orderRedundancyInfo.getSupplyCodeSet()));
        orderMapper.updateByPrimaryKeySelective(orderUpdate);

        //12保存分销商备注
        if(StringUtil.isValidString(assemblyOrderData.getNote())){
            OrderNoteDO orderNoteDO = new OrderNoteDO();
            orderNoteDO.setOrderId(orderId);
            orderNoteDO.setNoteType((byte)1);
            orderNoteDO.setNote(assemblyOrderData.getNote());
            orderNoteDO.setNoteObject(assemblyOrderData.getMerchantName());
            orderNoteDO.setCreator(assemblyOrderData.getAgentName());
            orderNoteDO.setCreateTime(DateUtil.stringToDateWithHms(assemblyOrderData.getCreateTime()));
            orderNoteMapper.insert(orderNoteDO);
        }
        return orderId;
    }

    @Override
    @Async
    public Future<ResponseDTO> debuctOrRetreatQuota(OrderDebuctOrRetreatQuotaRequestDTO requestDTO) {
        String quotaTypeStr = requestDTO.getDebuctQuotaType() == 1 ? "扣配额" : "退配额";
        log.info("异步{}，debuctOrRetreatQuota param: {}", quotaTypeStr, requestDTO);
        Future<ResponseDTO> future;
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            boolean productIsExist = requestDTO.getRatePlanId() != null && requestDTO.getRatePlanId() > 0 && requestDTO.getQuotaAccountId() != null;
            if (productIsExist && !CollectionUtils.isEmpty(requestDTO.getDateList())) {
                DebuctOrRetreatQuotaRequestDTO debuctOrRetreatQuotaReq = new DebuctOrRetreatQuotaRequestDTO();
                debuctOrRetreatQuotaReq.setOrderCode(requestDTO.getOrderCode());
                debuctOrRetreatQuotaReq.setPricePlanId(requestDTO.getRatePlanId().longValue());
                debuctOrRetreatQuotaReq.setQuotaAccountId(requestDTO.getQuotaAccountId().longValue());
                List<QuotaStateDailyDTO> quotaStateDailyList = new ArrayList<>();
                for (String saleDate : requestDTO.getDateList()) {
                    QuotaStateDailyDTO dailyDTO = new QuotaStateDailyDTO();
                    dailyDTO.setSaleDate(DateUtil.stringToDate(saleDate));
                    dailyDTO.setQuotaNum(requestDTO.getRoomNum());
                    quotaStateDailyList.add(dailyDTO);
                }
                debuctOrRetreatQuotaReq.setQuotaStateDailyList(quotaStateDailyList);
                // 配额类型(1 扣配额,2 退配额)
                debuctOrRetreatQuotaReq.setQuotaType(requestDTO.getDebuctQuotaType());
                debuctOrRetreatQuotaReq.setSupplyOrderCode(requestDTO.getSupplyOrderCode());
                debuctOrRetreatQuotaReq.setSupplyOrderId(requestDTO.getSupplyOrderId().longValue());
                debuctOrRetreatQuotaReq.setStartDate(requestDTO.getStartDate());
                debuctOrRetreatQuotaReq.setEndDate(requestDTO.getEndDate());
                debuctOrRetreatQuotaReq.setMerchantCode(requestDTO.getMerchantCode());
                log.info("调{}接口，quotaStateService.debuctOrRetreatQuota param: {}", quotaTypeStr, debuctOrRetreatQuotaReq);
                ResponseDTO<DebuctOrRetreatQuotaResponseDTO> debuctResponse = quotaStateService.debuctOrRetreatQuota(debuctOrRetreatQuotaReq);
                responseDTO.setResult(debuctResponse.getResult());
                responseDTO.setFailReason(debuctResponse.getFailReason());
                log.info("调{}接口，quotaStateService.debuctOrRetreatQuota response: {}", quotaTypeStr, debuctResponse);
            } else {
                responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
            }
            future = new AsyncResult<>(responseDTO);
        } catch (Exception e) {
            log.error("扣" + quotaTypeStr + "异常，debuctOrRetreatQuota exception", e);
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("扣还配额异常");
            future = new AsyncResult<>(responseDTO);
        }

        // 2. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("订单" + quotaTypeStr + "（价格计划id："
                + requestDTO.getRatePlanId() + "，配额帐号id：" + requestDTO.getQuotaAccountId() + "，入住日期："
                + DateUtil.dateToString(requestDTO.getStartDate()) + "，离店日期："
                + DateUtil.dateToString(requestDTO.getEndDate()) + "，间数："
                + requestDTO.getRoomNum() + "），" + quotaTypeStr + "结果：" + (responseDTO.getResult().equals(ResultCodeEnum.SUCCESS.code) ? "全部成功" : "全部失败"));
        if (responseDTO.getResult().equals(ResultCodeEnum.FAILURE.code)) {
            content.append("，失败原因：" + responseDTO.getFailReason());
        }
        logDO.setOperator("system");
        logDO.setOperateTime(DateUtil.getCurrentDate());
        logDO.setContent(content.toString());
        addOperationLog(logDO);
        return future;
    }

    @Override
    public List<OrderCheckDetailDTO> assemblyCheckDetail(Integer roomNum, List<SupplyProductPriceDTO> dayList) {
        // 根据每日价格， 推演对账明细
        LinkedList<OrderCheckDetailDTO> checkDetailDTOList = new LinkedList<>();
        for (int i = 0; i < dayList.size(); i++) {
            SupplyProductPriceDTO dayDTO = dayList.get(i);
            if (i == 0) {
                // 第一个日期段
                OrderCheckDetailDTO checkDetailDTO = new OrderCheckDetailDTO();
                checkDetailDTO.setCheckinDate(dayDTO.getSaleDate());
                checkDetailDTO.setBasePrice(dayDTO.getBasePrice());
                checkDetailDTO.setRoomNum(roomNum);
                checkDetailDTOList.add(checkDetailDTO);
            } else {
                OrderCheckDetailDTO lastCheckDetailDTO = checkDetailDTOList.getLast();
                if (lastCheckDetailDTO.getBasePrice().compareTo(dayDTO.getBasePrice()) != 0) {
                    lastCheckDetailDTO.setCheckoutDate(dayDTO.getSaleDate());
                    // 价格不相等，新建一个日期段
                    OrderCheckDetailDTO checkDetailDTO = new OrderCheckDetailDTO();
                    checkDetailDTO.setCheckinDate(dayDTO.getSaleDate());
                    checkDetailDTO.setBasePrice(dayDTO.getBasePrice());
                    checkDetailDTO.setRoomNum(roomNum);
                    checkDetailDTOList.add(checkDetailDTO);
                }
            }
            // 最后一天
            if (i == dayList.size() - 1) {
                Date afterDate = DateUtil.getAfterDate(DateUtil.stringToDate(dayDTO.getSaleDate()));
                checkDetailDTOList.getLast().setCheckoutDate(DateUtil.dateToString(afterDate));
            }
        }

        return checkDetailDTOList;
    }

    @Override
    @Async
    public void addOperationLog(OrderOperationLogDO logDO) {
        if (logDO.getOrderId() == null && StringUtils.isEmpty(logDO.getContent())) {
            return;
        }
        logMapper.insert(logDO);
    }

    /**
     * 扣还供货单配额
     *
     * @param supplyStatus
     * @param supplyOrderDO
     * @param orderDO
     */
    @Override
    public void debuctOrRetreatSupplyOrderQuota(Integer supplyStatus, SupplyOrderDO supplyOrderDO, OrderDO orderDO, String operator) {
        if (Constant.NO.equals(orderDO.getIsGroupRoom().intValue())) {
            SupplyProductDO supplyProductQuery = new SupplyProductDO();
            supplyProductQuery.setSupplyOrderId(supplyOrderDO.getId());
            SupplyProductDO supplyProductDO = supplyProductMapper.select(supplyProductQuery).get(0);
            boolean productIsExist = supplyProductDO.getRateplanId() != null && supplyProductDO.getRateplanId() > 0;
            if (productIsExist) {
                SupplyProductPriceDO productPriceQuery = new SupplyProductPriceDO();
                productPriceQuery.setSupplyProductId(supplyProductDO.getId());
                List<SupplyProductPriceDO> productPriceDOList = productPriceMapper.select(productPriceQuery);
                List<String> newDateList = new ArrayList<>();
                for (SupplyProductPriceDO productPriceDO : productPriceDOList) {
                    newDateList.add(DateUtil.dateToString(productPriceDO.getSaleDate()));
                }
                /**
                 *  退配额场景：
                 *  未发单、已发单、已确认---> 不确认，退配额
                 *
                 *  扣配额场景：
                 *  未发单、已发单---> 已确认， 不扣配额
                 *  不确认---> 已确认，扣配额
                 *
                 *  其他：
                 *  供货单状态不变，不退扣配额
                 */
                if (supplyOrderDO.getSupplyStatus().intValue() != SupplyStatusEnum.UN_CONFIRM.key
                        && supplyStatus == SupplyStatusEnum.UN_CONFIRM.key) {
                    // 供货单由未发单、已发单、已确认变为不确认，退散房配额
                    OrderDebuctOrRetreatQuotaRequestDTO oldDebuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
                    oldDebuctRequestDTO.setDebuctQuotaType(2);
                    oldDebuctRequestDTO.setOrderCode(orderDO.getOrderCode());
                    oldDebuctRequestDTO.setDateList(newDateList);
                    oldDebuctRequestDTO.setQuotaAccountId(supplyProductDO.getQuotaAccountId());
                    oldDebuctRequestDTO.setRatePlanId(supplyProductDO.getRateplanId());
                    oldDebuctRequestDTO.setRoomNum(supplyProductDO.getRoomNum());
                    oldDebuctRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
                    oldDebuctRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
                    oldDebuctRequestDTO.setStartDate(supplyProductDO.getCheckinDate());
                    oldDebuctRequestDTO.setEndDate(supplyProductDO.getCheckoutDate());
                    oldDebuctRequestDTO.setMerchantCode(orderDO.getMerchantCode());
                    oldDebuctRequestDTO.setOrderId(orderDO.getId());
                    debuctOrRetreatQuota(oldDebuctRequestDTO);
                } else if (supplyOrderDO.getSupplyStatus().intValue() == SupplyStatusEnum.UN_CONFIRM.key
                        && supplyStatus == SupplyStatusEnum.CONFIRMED.key) {
                    // 供货单由不确认变为已确认，扣散房配额
                    OrderDebuctOrRetreatQuotaRequestDTO debuctRequestDTO = new OrderDebuctOrRetreatQuotaRequestDTO();
                    debuctRequestDTO.setDebuctQuotaType(1);
                    debuctRequestDTO.setOrderCode(orderDO.getOrderCode());
                    debuctRequestDTO.setDateList(newDateList);
                    debuctRequestDTO.setQuotaAccountId(supplyProductDO.getQuotaAccountId());
                    debuctRequestDTO.setRatePlanId(supplyProductDO.getRateplanId());
                    debuctRequestDTO.setRoomNum(supplyProductDO.getRoomNum());
                    debuctRequestDTO.setSupplyOrderCode(supplyOrderDO.getSupplyOrderCode());
                    debuctRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
                    debuctRequestDTO.setStartDate(supplyProductDO.getCheckinDate());
                    debuctRequestDTO.setEndDate(supplyProductDO.getCheckoutDate());
                    debuctRequestDTO.setMerchantCode(orderDO.getMerchantCode());
                    debuctRequestDTO.setOrderId(orderDO.getId());
                    debuctOrRetreatQuota(debuctRequestDTO);
                }
            }
        }
    }

    @Override
    public SupplyOrderPriceSumResponseDTO calcSupplyOrderPriceSum(Integer supplyOrderId, Integer supplyStatusRequest) {
        /**
         * 1.1 如果供货单由不确认改为已确认， 重新计算供货单金额，重新计算订单金额
         * 1.2 如果供货单由未发单、已发单、已确认改为不确认， 供货单金额改为0，重新计算订单金额
         * 1.3 其他，供货单金额不变
         */
        SupplyOrderPriceSumResponseDTO responseDTO = new SupplyOrderPriceSumResponseDTO();
        BigDecimal supplyBasePriceSum = null;
        BigDecimal supplySalePriceSum = null;
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(supplyOrderId);
        if (supplyOrderDO.getSupplyStatus().intValue() == SupplyStatusEnum.UN_CONFIRM.key
                && supplyStatusRequest == SupplyStatusEnum.CONFIRMED.key) {
            supplyBasePriceSum = BigDecimal.ZERO;
            supplySalePriceSum = BigDecimal.ZERO;
            // 查产品
            SupplyProductDO supplyProductQuery = new SupplyProductDO();
            supplyProductQuery.setSupplyOrderId(supplyOrderDO.getId());
            List<SupplyProductDO> supplyProductDOList = supplyProductMapper.select(supplyProductQuery);
            for (SupplyProductDO supplyProductDO : supplyProductDOList) {
                supplyBasePriceSum = supplyBasePriceSum.add(supplyProductDO.getBasePriceSum());
                supplySalePriceSum = supplySalePriceSum.add(supplyProductDO.getSalePriceSum());
            }
            // 查附加项
            SupplyAdditionChargeDO additionChargeQuery = new SupplyAdditionChargeDO();
            additionChargeQuery.setSupplyOrderId(supplyOrderDO.getId());
            List<SupplyAdditionChargeDO> additionChargeDOList = additionChargeMapper.select(additionChargeQuery);
            if (!CollectionUtils.isEmpty(additionChargeDOList)) {
                for (SupplyAdditionChargeDO additionChargeDO : additionChargeDOList) {
                    supplyBasePriceSum = supplyBasePriceSum.add(additionChargeDO.getBasePriceSum());
                    supplySalePriceSum = supplySalePriceSum.add(additionChargeDO.getSalePriceSum());
                }
            }

        } else if (supplyOrderDO.getSupplyStatus().intValue() != SupplyStatusEnum.UN_CONFIRM.key
                && supplyStatusRequest == SupplyStatusEnum.UN_CONFIRM.key) {
            supplyBasePriceSum = BigDecimal.ZERO;
            supplySalePriceSum = BigDecimal.ZERO;
        }
        responseDTO.setSupplyBasePriceSum(supplyBasePriceSum);
        responseDTO.setSupplySalePriceSum(supplySalePriceSum);
        return responseDTO;
    }

    @Override
    public List<DeratePolicyResponseDTO> queryDeratePolicyList(Integer supplyOrderId) {
        DeratePolicyDO deratePolicyQuery = new DeratePolicyDO();
        deratePolicyQuery.setSupplyOrderId(supplyOrderId);
        List<DeratePolicyDO> deratePolicyDOList = deratePolicyMapper.select(deratePolicyQuery);
        List<DeratePolicyResponseDTO> deratePolicyDTOList = null;
        if (!CollectionUtils.isEmpty(deratePolicyDOList)) {
            deratePolicyDTOList = PropertyCopyUtil.transferArray(deratePolicyDOList, DeratePolicyResponseDTO.class);
            for (DeratePolicyResponseDTO deratePolicyResponseDTO : deratePolicyDTOList) {
                DeratePolicyPriceDO deratePolicyPriceQuery = new DeratePolicyPriceDO();
                deratePolicyPriceQuery.setDeratePolicyId(deratePolicyResponseDTO.getId());
                List<DeratePolicyPriceDO> policyPriceDOList = deratePolicyPriceMapper.select(deratePolicyPriceQuery);
                if (!CollectionUtils.isEmpty(policyPriceDOList)) {
                    List<DeratePolicyPriceDTO> policyPriceDTOList = new ArrayList<>();
                    for (DeratePolicyPriceDO deratePolicyPriceDO : policyPriceDOList) {
                        DeratePolicyPriceDTO deratePolicyPriceDTO = PropertyCopyUtil.transfer(deratePolicyPriceDO, DeratePolicyPriceDTO.class);
                        deratePolicyPriceDTO.setSaleDate(DateUtil.dateToString(deratePolicyPriceDO.getSaleDate()));
                        policyPriceDTOList.add(deratePolicyPriceDTO);
                    }
                    deratePolicyResponseDTO.setDayList(policyPriceDTOList);
                }
            }
        }
        return deratePolicyDTOList;
    }

    @Override
    public BigDecimal calcProfit(Integer orderId, BigDecimal receivableAmount, List<SupplyOrderDO> supplyOrderDOS) {
        if (orderId == null || receivableAmount == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal payableAmount = BigDecimal.ZERO;
        BigDecimal profit = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(supplyOrderDOS)) {
            SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
            supplyOrderQuery.setOrderId(orderId);
            supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
        }
        for (SupplyOrderDO supplyOrderDO : supplyOrderDOS) {
            payableAmount = payableAmount.add(supplyOrderDO.getPayableAmount());
        }
        profit = profit.add(receivableAmount.subtract(payableAmount));
        return profit;
    }

    @Override
    @Transactional
    public ResponseDTO updateOrderFinanceInfo(Integer orderId, BigDecimal receivableAmount, BigDecimal settlementAmount, BigDecimal refundAmount) {
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 如果结算金额为空，则查DB中的结算金额
        OrderFinanceDO orderFinanceQuery = new OrderFinanceDO();
        orderFinanceQuery.setOrderId(orderId);
        OrderFinanceDO orderFinanceDO = orderFinanceMapper.select(orderFinanceQuery).get(0);
        if (settlementAmount == null) {
            settlementAmount = orderFinanceDO.getSettlementAmount();
            refundAmount = orderFinanceDO.getRefundAmount();
        }
        if (refundAmount == null) {
            refundAmount = BigDecimal.ZERO;
        }
        /**
         * 计算订单支付状态
         * 当应收金额>0, 如果应收=实收，支付状态为已支付
         *             如果应收>实收，支付状态为未支付
         *             如果应收<实收，支付状态为未支付
         * 当应收金额=0, 如果退款金额>0，支付状态为已退款
         *             如果退款金额=0，实收金额>0，支付状态为已支付
         *             如果退款金额=0，实收金额=0，支付状态为未支付
         */
        Integer payStatus = null;
        if (receivableAmount.compareTo(BigDecimal.ZERO) > 0) {
            if (receivableAmount.compareTo(settlementAmount) == 0) {
                payStatus = PayStatusEnum.PAID.key;
            } else if (receivableAmount.compareTo(settlementAmount) > 0) {
                payStatus = PayStatusEnum.UN_PAID.key;
            } else {
                payStatus = PayStatusEnum.UN_PAID.key;
            }
        } else if (receivableAmount.compareTo(BigDecimal.ZERO) == 0) {
            if (refundAmount.compareTo(BigDecimal.ZERO) > 0) {
                payStatus = PayStatusEnum.REFUND.key;
            } else if (refundAmount.compareTo(BigDecimal.ZERO) == 0 && settlementAmount.compareTo(BigDecimal.ZERO) > 0) {
                payStatus = PayStatusEnum.PAID.key;
            } else if (refundAmount.compareTo(BigDecimal.ZERO) == 0 && settlementAmount.compareTo(BigDecimal.ZERO) == 0) {
                payStatus = PayStatusEnum.UN_PAID.key;
            }
        }
        if (payStatus != null) {
            // 2. 更改订单财务表，结算金额、结算状态（0未结算，1已结算）
            int settlementStatus = 0;
            Date settlementDate=null;
            if (payStatus == PayStatusEnum.UN_PAID.key) {
                settlementStatus = 0;
            } else if (payStatus == PayStatusEnum.PAID.key) {
                settlementStatus = 1;
                settlementDate=new Date();
            } else if (payStatus == PayStatusEnum.REFUND.key) {
                settlementStatus = 1;
                settlementDate=new Date();
            }
            OrderFinanceDO orderFinanceUpdate = new OrderFinanceDO();
            orderFinanceUpdate.setSettlementAmount(settlementAmount);
            orderFinanceUpdate.setSettlementStatus(Byte.valueOf(settlementStatus + ""));
            orderFinanceUpdate.setSettlementDate(settlementDate);
            orderFinanceUpdate.setRefundAmount(refundAmount);
            //已对账的订单，如果结算状态变更为未结算，则将对账状态改为可出账
            if (settlementStatus==0 && orderFinanceDO.getAccountStatus()== AccountStatusEnum.CHECKED.key){
                orderFinanceUpdate.setAccountStatus(Byte.valueOf(AccountStatusEnum.CANCHECK.key+""));
            }

            Example example = new Example(OrderFinanceDO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orderId", orderId);
            orderFinanceMapper.updateByExampleSelective(orderFinanceUpdate, example);

            // 3. 更新订单表，支付状态
            OrderDO orderUpdate = new OrderDO();
            orderUpdate.setId(orderId);
            orderUpdate.setPayStatus(Byte.valueOf(payStatus + ""));
            orderMapper.updateByPrimaryKeySelective(orderUpdate);
        }
        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO updateSupplyFinanceInfo(Integer supplyOrderId, BigDecimal payableAmount, BigDecimal settlementAmount, BigDecimal receiptAmount) {
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 如果结算金额为空，则查DB中的结算金额
        SupplyFinanceDO supplyFinanceQuery = new SupplyFinanceDO();
        supplyFinanceQuery.setSupplyOrderId(supplyOrderId);
        SupplyFinanceDO supplyFinanceDO = supplyFinanceMapper.select(supplyFinanceQuery).get(0);
        if (settlementAmount == null) {
            settlementAmount = supplyFinanceDO.getSettlementAmount();
            receiptAmount = supplyFinanceDO.getReceiptAmount();
        }
        if (receiptAmount == null) {
            receiptAmount = BigDecimal.ZERO;
        }
        /**
         * 计算供货单支付状态
         * 当应付金额>0, 如果应付=实付，支付状态为已支付
         *             如果应付>实付，支付状态为未支付
         *             如果应付<实付，支付状态为未支付
         * 当应付金额=0, 如果收款金额>0，支付状态为已退款
         *             如果收款金额=0，实付金额>0，支付状态为已支付
         *             如果收款金额=0，实付金额=0，支付状态为未支付
         */
        Integer payStatus = null;
        if (payableAmount.compareTo(BigDecimal.ZERO) > 0) {
            if (payableAmount.compareTo(settlementAmount) > 0) {
                payStatus = PayStatusEnum.UN_PAID.key;
            } else if (payableAmount.compareTo(settlementAmount) == 0) {
                payStatus = PayStatusEnum.PAID.key;
            } else if (payableAmount.compareTo(settlementAmount) < 0) {
                payStatus = PayStatusEnum.UN_PAID.key;
            }
        } else if (payableAmount.compareTo(BigDecimal.ZERO) == 0) {
            if (receiptAmount.compareTo(BigDecimal.ZERO) > 0) {
                payStatus = PayStatusEnum.REFUND.key;
            } else if (receiptAmount.compareTo(BigDecimal.ZERO) == 0 && settlementAmount.compareTo(BigDecimal.ZERO) > 0) {
                payStatus = PayStatusEnum.PAID.key;
            } else if (receiptAmount.compareTo(BigDecimal.ZERO) == 0 && settlementAmount.compareTo(BigDecimal.ZERO) == 0) {
                payStatus = PayStatusEnum.UN_PAID.key;
            }
        }
        if (payStatus != null) {
            // 2. 更改供货单财务表，结算金额、结算状态（对账状态：0新建，1可出账，2已纳入账单，3已结算）
            int settlementStatus = 0;
            Date settlementDate=null;
            if (payStatus == PayStatusEnum.UN_PAID.key) {
                settlementStatus = 0;
            } else if (payStatus == PayStatusEnum.PAID.key || payStatus == PayStatusEnum.REFUND.key) {
                settlementStatus = 1;
                settlementDate=new Date();
            }
            SupplyFinanceDO supplyFinanceUpdate = new SupplyFinanceDO();
            supplyFinanceUpdate.setSettlementAmount(settlementAmount);
            supplyFinanceUpdate.setSettlementStatus(Byte.valueOf(settlementStatus + ""));
            supplyFinanceUpdate.setSettlementDate(settlementDate);
            supplyFinanceUpdate.setReceiptAmount(receiptAmount);
            //已对账的订单，如果结算状态变更为未结算，则将对账状态改为可出账
            if (settlementStatus==0 && supplyFinanceDO.getAccountStatus()== AccountStatusEnum.CHECKED.key){
                supplyFinanceUpdate.setAccountStatus(Byte.valueOf(AccountStatusEnum.CANCHECK.key+""));
            }

            Example example = new Example(SupplyFinanceDO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("supplyOrderId", supplyOrderId);
            supplyFinanceMapper.updateByExampleSelective(supplyFinanceUpdate, example);

            // 3. 更新供货单表，支付状态
            SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
            supplyOrderUpdate.setId(supplyOrderId);
            supplyOrderUpdate.setPayStatus(Byte.valueOf(payStatus + ""));
            supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        }
        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO orderCreditPayOrRefund(OrderCreditPayOrRefundRequestDTO requestDTO) {
        log.info("orderCreditPayOrRefund param：{}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 校验
        if (requestDTO.getOrderStatus() == OrderStatusEnum.NEWORDER.key || requestDTO.getOrderStatus() == OrderStatusEnum.PROCESSING.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单状态是新建或处理中，无需挂账");
            return responseDTO;
        }
        // 2. 订单挂账/退挂账
        /**
         * 当操作类型是确认订单，需要查已挂账金额
         * 当操作类型是取消订单，需要查已挂账金额
         * 当操作类型是其他，订单为未挂账，需要查已挂账金额
         */
        boolean isQueryCreditAmount = requestDTO.getOperateType() == 1 || requestDTO.getOperateType() == 2
                || (requestDTO.getOperateType() == 3 && requestDTO.getPayStatus() == PayStatusEnum.UN_CREDIT.key);
        if (isQueryCreditAmount) {
            QueryOrderDeductCreditDTO creditDTO = new QueryOrderDeductCreditDTO();
            creditDTO.setOrderCode(requestDTO.getOrderCode());
            OrderDeductCreditDTO orderDeductCreditDTO = agentService.queryOrderDeductCredit(creditDTO);
            requestDTO.setPayAmount((orderDeductCreditDTO == null || orderDeductCreditDTO.getDeductAmount() == null) ? BigDecimal.ZERO : orderDeductCreditDTO.getDeductAmount());
        }
        // 获取挂账金额 = 应收金额-已挂账金额
        BigDecimal amount = requestDTO.getReceivableAmount().subtract(requestDTO.getPayAmount());
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("订单挂账金额为0，无需挂账");
            return responseDTO;
        }
        Integer creditType = amount.compareTo(BigDecimal.ZERO) > 0 ? 1 : 2;
        CreditOrderPayDTO creditRequestDTO = new CreditOrderPayDTO();
        creditRequestDTO.setOrgCode(requestDTO.getAgentCode());
        creditRequestDTO.setOrgName(requestDTO.getAgentName());
        creditRequestDTO.setMerchantCode(requestDTO.getMerchantCode());
        creditRequestDTO.setOrderCode(requestDTO.getOrderCode());
        creditRequestDTO.setCurrency(requestDTO.getSaleCurrency());
        creditRequestDTO.setAmount(amount.abs());
        creditRequestDTO.setOperator(requestDTO.getOperator());
        log.info("orderPay or orderRefund param：{}", requestDTO);
        if (creditType == 1) {
            responseDTO = creditRemote.orderPay(creditRequestDTO);
        } else {
            responseDTO = creditRemote.orderRefund(creditRequestDTO);
        }
        log.info("orderPay or orderRefund response：{}", responseDTO);

        // 3. 更新订单支付状态
        /**
         *  月结订单，计算订单支付状态：
         *  已确认订单， 当应收金额>0，如果挂账成功，支付状态为已挂账
         *                        如果挂账失败，支付状态为未挂账，可以点击确认客人重新挂账
         *             当应收金额=0， 支付状态为已退账
         *  已取消订单， 支付状态为已退账
         */
        Integer payStatus = null;
        if (requestDTO.getOrderStatus() == OrderStatusEnum.TRADED.key) {
            if (requestDTO.getReceivableAmount().compareTo(BigDecimal.ZERO) > 0) {
                if (responseDTO.getResult().equals(Constant.YES)) {
                    payStatus = PayStatusEnum.CREDIT.key;
                } else {
                    payStatus = PayStatusEnum.UN_CREDIT.key;
                }
            } else if (requestDTO.getReceivableAmount().compareTo(BigDecimal.ZERO) == 0) {
                payStatus = PayStatusEnum.REFUND_CREDIT.key;
            }
        } else if (requestDTO.getOrderStatus() == OrderStatusEnum.CANCELED.key) {
            payStatus = PayStatusEnum.REFUND_CREDIT.key;
        }
        if (payStatus != null) {
            OrderDO orderUpdate = new OrderDO();
            orderUpdate.setId(requestDTO.getOrderId());
            orderUpdate.setPayStatus(Byte.valueOf(payStatus + ""));
            orderMapper.updateByPrimaryKeySelective(orderUpdate);
        }

        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("订单" + (creditType == 1 ? "挂账" : "退挂账") + "（分销商：" + requestDTO.getAgentName()
                + "，金额：" + creditRequestDTO.getAmount() + "），操作结果：" + (responseDTO.getResult().equals(Constant.YES) ? "成功" : "失败"));
        if (responseDTO.getResult().equals(Constant.NO)) {
            content.append("，失败原因：" + responseDTO.getFailReason());
        }
        logDO.setOperator(requestDTO.getOperator());
        logDO.setOperateTime(DateUtil.getCurrentDate());
        logDO.setContent(content.toString());
        addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }


    @Override
    @Transactional
    public ResponseDTO supplyCreditPayOrRefund(SupplyCreditPayOrReceiptRequestDTO requestDTO) {
        log.info("supplyCreditPayOrRefund param：{}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 校验
        if (requestDTO.getSupplyStatus() == SupplyStatusEnum.UN_SEND.key || requestDTO.getSupplyStatus() == SupplyStatusEnum.SENT.key) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单状态是未发单或已发单，无需挂账");
            return responseDTO;
        }
        // 2. 供货单挂账/退挂账
        /**
         * 当操作类型是确认供货单，需要查已挂账金额
         * 当操作类型是不确认供货单，需要查已挂账金额
         * 当操作类型是其他，供货单为未挂账，需要查已挂账金额
         */
        boolean isQueryCreditAmount = requestDTO.getOperateType() == 1 || requestDTO.getOperateType() == 2
                || (requestDTO.getOperateType() == 3 && requestDTO.getPayStatus() == PayStatusEnum.UN_CREDIT.key);
        if (isQueryCreditAmount) {
            QuerySupplyOrderDeductCreditDTO creditRequestDTO = new QuerySupplyOrderDeductCreditDTO();
            creditRequestDTO.setOrderCode(requestDTO.getSupplyOrderCode());
            SupplyOrderDeductCreditDTO supplyOrderDeductCreditDTO = supplyService.querySupplyOrderDeductCredit(creditRequestDTO);
            requestDTO.setPayAmount((supplyOrderDeductCreditDTO == null || supplyOrderDeductCreditDTO.getDeductAmount() == null) ? BigDecimal.ZERO : supplyOrderDeductCreditDTO.getDeductAmount());
        }
        // 获取挂账金额 = 应付金额-已挂账金额
        BigDecimal amount = requestDTO.getPayableAmount().subtract(requestDTO.getPayAmount());
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("供货单挂账金额为0，无需挂账");
            return responseDTO;
        }
        Integer creditType = amount.compareTo(BigDecimal.ZERO) > 0 ? 1 : 2;
        CreditOrderPayDTO creditRequestDTO = new CreditOrderPayDTO();
        creditRequestDTO.setOrgCode(requestDTO.getSupplyCode());
        creditRequestDTO.setOrgName(requestDTO.getSupplyName());
        creditRequestDTO.setMerchantCode(requestDTO.getMerchantCode());
        creditRequestDTO.setOrderCode(requestDTO.getSupplyOrderCode());
        creditRequestDTO.setCurrency(requestDTO.getBaseCurrency());
        creditRequestDTO.setAmount(amount.abs());
        creditRequestDTO.setOperator(requestDTO.getOperator());
        log.info("supplierOrderPay or supplierOrderRefund param：{}", requestDTO);
        if (creditType == 1) {
            responseDTO = creditRemote.supplierOrderPay(creditRequestDTO);
        } else {
            responseDTO = creditRemote.supplierOrderRefund(creditRequestDTO);
        }
        log.info("supplierOrderPay or supplierOrderRefund response：{}", responseDTO);

        // 3. 更新供货单支付状态
        /**
         *  月结供货单，计算供货单支付状态：
         *  已确认供货单， 当应付金额>0，如果挂账成功，支付状态为已挂账
         *                          如果挂账失败，支付状态为未挂账，可以点击确认客人重新挂账
         *              当应付金额=0， 支付状态为已退账
         *  不确认供货单， 支付状态为已退账
         */
        Integer payStatus = null;
        if (requestDTO.getSupplyStatus() == SupplyStatusEnum.CONFIRMED.key) {
            if (requestDTO.getPayableAmount().compareTo(BigDecimal.ZERO) > 0) {
                if (responseDTO.getResult().equals(Constant.YES)) {
                    payStatus = PayStatusEnum.CREDIT.key;
                } else {
                    payStatus = PayStatusEnum.UN_CREDIT.key;
                }
            } else if (requestDTO.getPayableAmount().compareTo(BigDecimal.ZERO) == 0) {
                payStatus = PayStatusEnum.REFUND_CREDIT.key;
            }
        } else if (requestDTO.getSupplyStatus() == SupplyStatusEnum.UN_CONFIRM.key) {
            payStatus = PayStatusEnum.REFUND_CREDIT.key;
        }
        if (payStatus != null) {
            SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
            supplyOrderUpdate.setId(requestDTO.getSupplyOrderId());
            supplyOrderUpdate.setPayStatus(Byte.valueOf(payStatus + ""));
            supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
        }

        // 4. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("供货单" + (creditType == 1 ? "挂账" : "退挂账") + "（供应商：" + requestDTO.getSupplyName()
                + "，金额：" + creditRequestDTO.getAmount() + "），操作结果：" + (responseDTO.getResult().equals(Constant.YES) ? "成功" : "失败"));
        if (responseDTO.getResult().equals(Constant.NO)) {
            content.append("，失败原因：" + responseDTO.getFailReason());
        }
        logDO.setOperator(requestDTO.getOperator());
        logDO.setOperateTime(DateUtil.getCurrentDate());
        logDO.setContent(content.toString());
        addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Async
    public void saveOrderMessageToRedis(OrderMessageRedisCacheDTO requestDTO) {
        String key = Constant.ORDER_MESSAGE_KEY_PREFIX + requestDTO.getMerchantCode() + ":" + requestDTO.getOrderType() + ":" + requestDTO.getOrderCode();
        requestDTO.setCreateTime(System.currentTimeMillis());
        String value = JSON.toJSONString(requestDTO);
        redisUtil.saveDataInCache(key, value, 60 * 60);
    }

    @Override
    public ResponseDTO<List<OrderMessageRedisCacheDTO>> queryOrderMessageFromRedis(String merchantCode) {
        ResponseDTO<List<OrderMessageRedisCacheDTO>> responseDTO = new ResponseDTO<>();
        Set<String> keys = redisUtil.scanDataInCache(Constant.ORDER_MESSAGE_KEY_PREFIX + merchantCode + ":*", 1000);
        if (!CollectionUtils.isEmpty(keys)) {
            List<OrderMessageRedisCacheDTO> messageList = new ArrayList<>();
            for (String key : keys) {
                String value = redisUtil.queryDataInCache(key);
                OrderMessageRedisCacheDTO orderMessage = JSONObject.parseObject(value, OrderMessageRedisCacheDTO.class);
                messageList.add(orderMessage);
            }
            responseDTO.setModel(messageList);
            messageList.sort((o1, o2) -> {
                Long i = o2.getCreateTime() - o1.getCreateTime();
                return i.intValue();
            });
        }
        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteOrderMessageFromRedis(DeleteOrderMessageRequestDTO requestDTO) {
        if (!CollectionUtils.isEmpty(requestDTO.getOrderMessageList())) {
            for (OrderMessageRedisCacheDTO orderMessage : requestDTO.getOrderMessageList()) {
                redisUtil.delDataInCache(Constant.ORDER_MESSAGE_KEY_PREFIX + requestDTO.getMerchantCode() + ":" + orderMessage.getOrderType() + ":" + orderMessage.getOrderCode());
            }
        }
        return new ResponseDTO(Constant.YES);
    }

    @Override
    @Async
    public void saveSupplyOrderCofirmMsgToRedis(SupplyOrderCofirmMsgDTO supplyOrderCofirmMsgDTO) {
        String key = Constant.SUPPLY_ORDER_CONFIRM_MESSAGE_KEY_PREFIX + supplyOrderCofirmMsgDTO.getMerchantCode() +":" + supplyOrderCofirmMsgDTO.getOrderId();
        supplyOrderCofirmMsgDTO.setCreateTime(System.currentTimeMillis());
        String value = JSON.toJSONString(supplyOrderCofirmMsgDTO);
        log.info("saveSupplyOrderCofirmMsgToRedis:key={},value={}",key,value);
        redisUtil.saveDataInCache(key, value, 60 * 60);
    }

    @Override
    public ResponseDTO<List<SupplyOrderCofirmMsgDTO>> querySupplyOrderCofirmMsgFromRedis(String merchantCode) {
        ResponseDTO<List<SupplyOrderCofirmMsgDTO>> responseDTO = new ResponseDTO<>();
        Set<String> keys = redisUtil.scanDataInCache(Constant.SUPPLY_ORDER_CONFIRM_MESSAGE_KEY_PREFIX + merchantCode + ":*", 1000);
        if (!CollectionUtils.isEmpty(keys)) {
            List<SupplyOrderCofirmMsgDTO> messageList = new ArrayList<>();
            for (String key : keys) {
                String value = redisUtil.queryDataInCache(key);
                SupplyOrderCofirmMsgDTO orderMessage = JSONObject.parseObject(value, SupplyOrderCofirmMsgDTO.class);
                messageList.add(orderMessage);
            }
            responseDTO.setModel(messageList);
            messageList.sort((o1, o2) -> {
                Long i = o2.getCreateTime() - o1.getCreateTime();
                return i.intValue();
            });
        }
        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteSupplyOrderCofirmMsgFromRedis(DeleteSupplyOrderCofirmMsgRequestDTO deleteSupplyOrderCofirmMsgRequestDTO) {
        List<SupplyOrderCofirmMsgDTO> messageList = deleteSupplyOrderCofirmMsgRequestDTO.getSupplyOrderCofirmMsgDTOList();
        if (!CollectionUtils.isEmpty(messageList)) {
            for (SupplyOrderCofirmMsgDTO orderMessage : messageList) {
                redisUtil.delDataInCache(Constant.SUPPLY_ORDER_CONFIRM_MESSAGE_KEY_PREFIX + deleteSupplyOrderCofirmMsgRequestDTO.getMerchantCode() + ":" + orderMessage.getOrderId());
            }
        }
        return new ResponseDTO(ErrorCodeEnum.SUCCESS);
    }
}

