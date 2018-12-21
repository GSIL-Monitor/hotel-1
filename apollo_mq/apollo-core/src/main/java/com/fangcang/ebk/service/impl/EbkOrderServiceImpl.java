package com.fangcang.ebk.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.ebk.domain.EbkOrderDO;
import com.fangcang.ebk.domain.EbkOrderItemDO;
import com.fangcang.ebk.domain.EbkOrderItemPriceDO;
import com.fangcang.ebk.domain.EbkOrderItemPriceSnapDO;
import com.fangcang.ebk.domain.EbkOrderItemSnapDO;
import com.fangcang.ebk.domain.EbkOrderLogDO;
import com.fangcang.ebk.domain.EbkOrderSnapDO;
import com.fangcang.ebk.domain.EbkRequestDO;
import com.fangcang.ebk.enums.EbkOrderStatusEnum;
import com.fangcang.ebk.enums.EbkRequestStatusEnum;
import com.fangcang.ebk.enums.EbkRequestTypeEnum;
import com.fangcang.ebk.mapper.EbkOrderItemMapper;
import com.fangcang.ebk.mapper.EbkOrderItemPriceMapper;
import com.fangcang.ebk.mapper.EbkOrderItemPriceSnapMapper;
import com.fangcang.ebk.mapper.EbkOrderItemSnapMapper;
import com.fangcang.ebk.mapper.EbkOrderLogMapper;
import com.fangcang.ebk.mapper.EbkOrderMapper;
import com.fangcang.ebk.mapper.EbkOrderSnapMapper;
import com.fangcang.ebk.mapper.EbkRequestMapper;
import com.fangcang.ebk.request.LockRequestDTO;
import com.fangcang.ebk.request.QueryEbkOrderDTO;
import com.fangcang.ebk.request.UpdateConfirmNoDTO;
import com.fangcang.ebk.request.UpdateConfirmResultDTO;
import com.fangcang.ebk.response.EbkNotifyDTO;
import com.fangcang.ebk.response.EbkOrderDTO;
import com.fangcang.ebk.response.EbkOrderProductDTO;
import com.fangcang.ebk.response.EbkOrderSimpleDTO;
import com.fangcang.ebk.response.EbkOrderSnapDTO;
import com.fangcang.ebk.response.EbkOrderStatisticsDTO;
import com.fangcang.ebk.service.EbkOrderService;
import com.fangcang.order.request.EbkCallbackRequestDTO;
import com.fangcang.order.service.EbkCallService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EbkOrderServiceImpl implements EbkOrderService {

    @Autowired
    private EbkOrderMapper ebkOrderMapper;

    @Autowired
    private EbkOrderItemMapper ebkOrderItemMapper;

    @Autowired
    private EbkOrderItemPriceMapper ebkOrderItemPriceMapper;

    @Autowired
    private EbkOrderSnapMapper ebkOrderSnapMapper;

    @Autowired
    private EbkOrderItemSnapMapper ebkOrderItemSnapMapper;

    @Autowired
    private EbkOrderItemPriceSnapMapper ebkOrderItemPriceSnapMapper;

    @Autowired
    private EbkOrderLogMapper ebkOrderLogMapper;

    @Autowired
    private EbkRequestMapper ebkRequestMapper;

    @Autowired
    private EbkCallService ebkCallService;

    @Override
    public PaginationSupportDTO<EbkOrderSimpleDTO> queryOrderList(QueryEbkOrderDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<EbkOrderSimpleDTO> list =ebkOrderMapper.queryOrderList(requestDTO);
        PageInfo<EbkOrderSimpleDTO> page = new PageInfo<EbkOrderSimpleDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public EbkOrderStatisticsDTO queryOrderStatistics(String supplyCode) {
        Example example = new Example(EbkOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("supplyCode", supplyCode);
        criteria.andIn("orderStatus", Arrays.asList(1,2,3));
        int unTreatedCount=ebkOrderMapper.selectCountByExample(example);

        EbkOrderDO ebkOrderParam=new EbkOrderDO();
        ebkOrderParam.setCheckInDate(DateUtil.stringToDate(DateUtil.dateToString(new Date())));
        ebkOrderParam.setSupplyCode(supplyCode);
        int todayCheckInCount=ebkOrderMapper.selectCount(ebkOrderParam);
        return new EbkOrderStatisticsDTO(unTreatedCount,todayCheckInCount);
    }

    @Override
    public EbkOrderDTO queryOrderDetail(String supplyOrderCode) {
        EbkOrderDTO ebkOrderDTO=ebkOrderMapper.queryOrderDetail(supplyOrderCode);
        ebkOrderDTO.setOriginalOrderSum(ebkOrderDTO.getOrderSum());
        if(ebkOrderDTO.getDerateList()!=null && ebkOrderDTO.getDerateList().size()>0){
            for (EbkOrderProductDTO productDTO:ebkOrderDTO.getDerateList()){
                ebkOrderDTO.setOriginalOrderSum(ebkOrderDTO.getOriginalOrderSum().subtract(productDTO.getSalePriceSum()));
            }
        }
        return ebkOrderDTO;
    }

    @Override
    public EbkOrderSnapDTO queryModifyRequestDetail(Long requestId) {
        EbkOrderSnapDTO ebkOrderSnapDTO=ebkOrderSnapMapper.queryModifyRequestDetail(requestId);
        ebkOrderSnapDTO.setOriginalOrderSum(ebkOrderSnapDTO.getOrderSum());
        if(ebkOrderSnapDTO.getDerateList()!=null && ebkOrderSnapDTO.getDerateList().size()>0){
            for (EbkOrderProductDTO productDTO:ebkOrderSnapDTO.getDerateList()){
                ebkOrderSnapDTO.setOriginalOrderSum(ebkOrderSnapDTO.getOriginalOrderSum().subtract(productDTO.getSalePriceSum()));
            }
        }
        return ebkOrderSnapDTO;
    }

    @Transactional
    @Override
    public ResponseDTO confirmRequest(UpdateConfirmResultDTO requestDTO) {
        EbkRequestDO ebkRequestDO=ebkRequestMapper.selectByPrimaryKey(requestDTO.getRequestId());
        EbkOrderDO ebkOrderParam=new EbkOrderDO();
        ebkOrderParam.setSupplyOrderCode(ebkRequestDO.getSupplyOrderCode());
        EbkOrderDO ebkOrderDO=ebkOrderMapper.selectOne(ebkOrderParam);
        if (ebkOrderDO.getOrderStatus()==EbkOrderStatusEnum.CANCELED.key){
            return new ResponseDTO(ErrorCodeEnum.EBK_ORDER_CANCELED);
        }
        if (requestDTO.getRequestId().compareTo(ebkOrderDO.getEbkRequestId())!=0){
            return new ResponseDTO(ErrorCodeEnum.EBK_REBOOK);
        }

        //保存确认结果
        ebkRequestDO.setRequestStatus(requestDTO.getConfirmResult());
        ebkRequestDO.setConfirmNo(requestDTO.getConfirmNo());
        ebkRequestDO.setRefuseReason(requestDTO.getRefuseReason());
        ebkRequestDO.setSupplyRemark(requestDTO.getRemark());
        ebkRequestDO.setModifier(requestDTO.getOperator());
        ebkRequestDO.setModifyTime(new Date());
        ebkRequestMapper.updateByPrimaryKeySelective(ebkRequestDO);

        //更新订单状态
        ebkOrderParam.setId(ebkOrderDO.getId());
        if (ebkRequestDO.getRequestType()==EbkRequestTypeEnum.BOOK_APPLY.key){
            if (ebkRequestDO.getRequestStatus()==EbkRequestStatusEnum.CONFIRMED.key){
                ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CONFIRMED.key);
                ebkOrderParam.setConfirmNo(ebkRequestDO.getConfirmNo());
            }else if(ebkRequestDO.getRequestStatus()==EbkRequestStatusEnum.REFUSED.key){
                ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CANCELED.key);
            }
        }else if(ebkRequestDO.getRequestType()==EbkRequestTypeEnum.CANCEL_APPLY.key){
            if (ebkRequestDO.getRequestStatus()==EbkRequestStatusEnum.CONFIRMED.key){
                ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CANCELED.key);
            }else{
                ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CONFIRMED.key);
            }
        }else if(ebkRequestDO.getRequestType()==EbkRequestTypeEnum.MODIFY_APPLY.key){
            if (ebkRequestDO.getRequestStatus()==EbkRequestStatusEnum.CONFIRMED.key){
                //查询出修改单内容
                EbkOrderSnapDO ebkOrderSnapParam=new EbkOrderSnapDO();
                ebkOrderSnapParam.setEbkRequestId(requestDTO.getRequestId());
                List<EbkOrderSnapDO> ebkOrderSnapDOList=ebkOrderSnapMapper.select(ebkOrderSnapParam);

                //删除原单明细
                EbkOrderItemDO ebkOrderItemParam=new EbkOrderItemDO();
                ebkOrderItemParam.setEbkOrderId(ebkOrderDO.getId());
                ebkOrderItemMapper.delete(ebkOrderItemParam);
                EbkOrderItemPriceDO ebkOrderItemPriceParam=new EbkOrderItemPriceDO();
                ebkOrderItemPriceParam.setEbkOrderId(ebkOrderDO.getId());
                ebkOrderItemPriceMapper.delete(ebkOrderItemPriceParam);

                //更新订单内容
                EbkOrderDO ebkOrderModifyDO=new EbkOrderDO();
                BeanUtils.copyProperties(ebkOrderSnapDOList.get(0),ebkOrderModifyDO);
                ebkOrderModifyDO.setId(ebkOrderDO.getId());
                ebkOrderMapper.updateByPrimaryKeySelective(ebkOrderModifyDO);

                List<EbkOrderItemDO> ebkOrderItemModifyDOList=new ArrayList<>();
                Map<Long,EbkOrderItemDO> ebkOrderItemModifyDOMap=new HashMap<>();
                EbkOrderItemSnapDO ebkOrderItemSnapModifyParam=new EbkOrderItemSnapDO();
                ebkOrderItemSnapModifyParam.setEbkOrderSnapId(ebkOrderSnapDOList.get(0).getId());
                List<EbkOrderItemSnapDO> ebkOrderItemSnapModifyDOList=ebkOrderItemSnapMapper.select(ebkOrderItemSnapModifyParam);
                for (EbkOrderItemSnapDO ebkOrderItemSnapModifyDO:ebkOrderItemSnapModifyDOList){
                    EbkOrderItemDO ebkOrderItemModifyDO=new EbkOrderItemDO();
                    BeanUtils.copyProperties(ebkOrderItemSnapModifyDO,ebkOrderItemModifyDO);
                    ebkOrderItemModifyDO.setId(null);
                    ebkOrderItemModifyDO.setEbkOrderId(ebkOrderDO.getId());
                    ebkOrderItemModifyDOList.add(ebkOrderItemModifyDO);
                    ebkOrderItemModifyDOMap.put(ebkOrderItemSnapModifyDO.getId(),ebkOrderItemModifyDO);
                }
                if (ebkOrderItemModifyDOList.size()>0){
                    ebkOrderItemMapper.insertList(ebkOrderItemModifyDOList);

                    List<EbkOrderItemPriceDO> ebkOrderItemPriceModifyDOList=new ArrayList<>();
                    for (Map.Entry<Long,EbkOrderItemDO> itemEntry:ebkOrderItemModifyDOMap.entrySet()){
                        EbkOrderItemPriceSnapDO ebkOrderItemPriceSnapModifyParam=new EbkOrderItemPriceSnapDO();
                        ebkOrderItemPriceSnapModifyParam.setOrderItemId(itemEntry.getKey());
                        List<EbkOrderItemPriceSnapDO> ebkOrderItemPriceSnapModifyDOList=ebkOrderItemPriceSnapMapper.select(ebkOrderItemPriceSnapModifyParam);
                        if (ebkOrderItemPriceSnapModifyDOList.size()>0){
                            for (EbkOrderItemPriceSnapDO ebkOrderItemPriceSnapModifyDO:ebkOrderItemPriceSnapModifyDOList){
                                EbkOrderItemPriceDO ebkOrderItemPriceModifyDO=new EbkOrderItemPriceDO();
                                BeanUtils.copyProperties(ebkOrderItemPriceSnapModifyDO,ebkOrderItemPriceModifyDO);
                                ebkOrderItemPriceModifyDO.setId(null);
                                ebkOrderItemPriceModifyDO.setEbkOrderId(ebkOrderDO.getId());
                                ebkOrderItemPriceModifyDO.setOrderItemId(itemEntry.getValue().getId());
                                ebkOrderItemPriceModifyDOList.add(ebkOrderItemPriceModifyDO);
                            }
                        }
                    }
                    if (ebkOrderItemPriceModifyDOList.size()>0){
                        ebkOrderItemPriceMapper.insertList(ebkOrderItemPriceModifyDOList);
                    }
                }
            }
            ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CONFIRMED.key);
        }
        if (!StringUtil.isValidString(ebkOrderDO.getBelongUser())){
            ebkOrderParam.setBelongUser(requestDTO.getOperator());
            ebkOrderParam.setBelongName(requestDTO.getOperatorName());
        }
        ebkOrderMapper.updateByPrimaryKeySelective(ebkOrderParam);

        StringBuilder logContent=new StringBuilder();
        logContent.append(EbkRequestStatusEnum.getValueByKey(requestDTO.getConfirmResult())).append("了商家")
                .append(EbkRequestTypeEnum.getValueByKey(ebkRequestDO.getRequestType()));
        if (StringUtil.isValidString(requestDTO.getConfirmNo())){
            logContent.append(",确认号:").append(requestDTO.getConfirmNo());
        }
        if (StringUtil.isValidString(requestDTO.getRefuseReason())){
            logContent.append(",原因:").append(requestDTO.getRefuseReason());
        }
        if (StringUtil.isValidString(requestDTO.getRemark())){
            logContent.append(",备注:").append(requestDTO.getRemark());
        }

        //保存日志
        EbkOrderLogDO orderLogDO=new EbkOrderLogDO();
        orderLogDO.setContent(logContent.toString());
        orderLogDO.setEbkOrderId(ebkOrderDO.getId());
        orderLogDO.setCreator(requestDTO.getOperatorName()+"("+requestDTO.getOperator()+")");
        orderLogDO.setCreateTime(new Date());
        ebkOrderLogMapper.insert(orderLogDO);

        //回调订单接口
        EbkCallbackRequestDTO ebkCallbackRequestDTO=new EbkCallbackRequestDTO();
        ebkCallbackRequestDTO.setSupplyRequestId(ebkRequestDO.getSupplyRequestId().intValue());
        ebkCallbackRequestDTO.setConfirmType(requestDTO.getConfirmResult());
        ebkCallbackRequestDTO.setConfirmName(requestDTO.getOperatorName()+"("+requestDTO.getOperator()+")");
        ebkCallbackRequestDTO.setConfirmNo(requestDTO.getConfirmNo());
        ebkCallbackRequestDTO.setRefuseReason(requestDTO.getRefuseReason());
        ebkCallbackRequestDTO.setNote(requestDTO.getRemark());
        ebkCallService.ebkCallback(ebkCallbackRequestDTO);

        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Transactional
    @Override
    public ResponseDTO modifyConfirmNo(UpdateConfirmNoDTO requestDTO) {
        EbkOrderDO ebkOrderParam=new EbkOrderDO();
        ebkOrderParam.setSupplyOrderCode(requestDTO.getSupplyOrderCode());
        EbkOrderDO ebkOrderDO=ebkOrderMapper.selectOne(ebkOrderParam);

        ebkOrderParam.setConfirmNo(requestDTO.getConfirmNo());
        Example example = new Example(EbkOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("supplyOrderCode", requestDTO.getSupplyOrderCode());
        ebkOrderMapper.updateByExampleSelective(ebkOrderParam,example);

        //保存日志
        EbkOrderLogDO orderLogDO=new EbkOrderLogDO();
        orderLogDO.setContent("修改确认号："+(ebkOrderDO.getConfirmNo()==null?"":ebkOrderDO.getConfirmNo())+"改为"+requestDTO.getConfirmNo());
        orderLogDO.setEbkOrderId(ebkOrderDO.getId());
        orderLogDO.setCreator(requestDTO.getOperatorName()+"("+requestDTO.getOperator()+")");
        orderLogDO.setCreateTime(new Date());
        ebkOrderLogMapper.insert(orderLogDO);

        //回调订单接口，修改确认号
        Example example1 = new Example(EbkRequestDO.class);
        example1.setOrderByClause("create_time desc");
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("supplyOrderCode", requestDTO.getSupplyOrderCode());
        criteria1.andEqualTo("requestType",EbkRequestTypeEnum.BOOK_APPLY.key);
        List<EbkRequestDO> ebkRequestDOList=ebkRequestMapper.selectByExample(example1);

        EbkCallbackRequestDTO ebkCallbackRequestDTO=new EbkCallbackRequestDTO();
        ebkCallbackRequestDTO.setSupplyRequestId(ebkRequestDOList.get(0).getSupplyRequestId().intValue());
        ebkCallbackRequestDTO.setConfirmType(1);
        ebkCallbackRequestDTO.setConfirmName(requestDTO.getOperatorName()+"("+requestDTO.getOperator()+")");
        ebkCallbackRequestDTO.setConfirmNo(requestDTO.getConfirmNo());
        ebkCallbackRequestDTO.setNote("修改确认号："+(ebkOrderDO.getConfirmNo()==null?"":ebkOrderDO.getConfirmNo())+"改为"+requestDTO.getConfirmNo());
        ebkCallService.ebkCallback(ebkCallbackRequestDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Transactional
    @Override
    public ResponseDTO lock(LockRequestDTO requestDTO) {
        int result=ebkOrderMapper.lock(requestDTO);
        if (result>0){
            return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        }else{
            return new ResponseDTO(ResultCodeEnum.FAILURE.code);
        }
    }

    @Transactional
    @Override
    public ResponseDTO unlock(LockRequestDTO requestDTO) {
        int result=ebkOrderMapper.unlock(requestDTO);
        if (result>0){
            return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        }else{
            return new ResponseDTO(ResultCodeEnum.FAILURE.code);
        }
    }

    @Transactional
    @Override
    public List<EbkNotifyDTO> queryNotify(String supplyCode) {
        List<EbkNotifyDTO> ebkNotifyDTOList=new ArrayList<>();
        List<Long> idList=new ArrayList<>();

        Example example = new Example(EbkOrderDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("supplyCode", supplyCode);
        criteria.andIn("orderStatus",Arrays.asList(1,2,3));
        criteria.andLessThan("notifyCount",3);
        criteria.andLessThanOrEqualTo("lastNotifyTime",DateUtil.getDate(new Date(),0,0,-5));
        List<EbkOrderDO> ebkOrderDOList=ebkOrderMapper.selectByExample(example);
        for (EbkOrderDO ebkOrderDO:ebkOrderDOList){
            idList.add(ebkOrderDO.getId());

            EbkNotifyDTO ebkNotifyDTO=new EbkNotifyDTO();
            ebkNotifyDTO.setSupplyOrderCode(ebkOrderDO.getSupplyOrderCode());
            StringBuilder content=new StringBuilder("商家");
            content.append("【").append(ebkOrderDO.getMerchantName()).append("】")
                    .append(" 提交了一张 ")
                    .append(DateUtil.dateToString(ebkOrderDO.getCheckInDate())).append("入住")
                    .append(ebkOrderDO.getHotelName()).append(" ").append(ebkOrderDO.getRoomtypeName()).append(" ")
                    .append(ebkOrderDO.getRoomNum()).append("间 的");
            switch (ebkOrderDO.getOrderStatus()){
                case 1:content.append("【预订单】");break;
                case 2:content.append("【修改单】");break;
                case 3:content.append("【取消单】");
            }
            content.append("，主人~赶紧处理吧~");
            ebkNotifyDTO.setContent(content.toString());
            ebkNotifyDTOList.add(ebkNotifyDTO);
        }
        if (idList.size()>0){
            ebkOrderMapper.updateNotifyCount(idList);
        }
        return ebkNotifyDTOList;
    }
}
