package com.fangcang.ebk.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
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
import com.fangcang.ebk.request.BookRequestDTO;
import com.fangcang.ebk.request.BookRequestItemDTO;
import com.fangcang.ebk.request.BookRequestItemPriceDTO;
import com.fangcang.ebk.request.CancelRequestDTO;
import com.fangcang.ebk.service.EbkRequestService;
import com.fangcang.order.request.EbkCallbackRequestDTO;
import com.fangcang.order.service.EbkCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EbkRequestServiceImpl implements EbkRequestService {

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
    private EbkRequestMapper ebkRequestMapper;

    @Autowired
    private EbkOrderLogMapper ebkOrderLogMapper;

    @Autowired
    private EbkCallService ebkCallService;

    @Transactional
    @Override
    public ResponseDTO sendBookRequest(BookRequestDTO requestDTO) {
        log.info("sendBookRequest param:"+requestDTO);
        //保存请求信息
        EbkRequestDO ebkRequestDO=new EbkRequestDO();
        BeanUtils.copyProperties(requestDTO,ebkRequestDO);
        ebkRequestDO.setRequestType(EbkRequestTypeEnum.BOOK_APPLY.key);
        ebkRequestDO.setRequestStatus(EbkRequestStatusEnum.UNTREATED.key);
        ebkRequestDO.setCreator(requestDTO.getOperator());
        ebkRequestDO.setCreateTime(new Date());
        ebkRequestMapper.insert(ebkRequestDO);

        EbkOrderDO ebkOrderParam=new EbkOrderDO();
        ebkOrderParam.setSupplyOrderCode(requestDTO.getSupplyOrderCode());
        EbkOrderDO ebkOrderDO=ebkOrderMapper.selectOne(ebkOrderParam);
        if (ebkOrderDO!=null){
            if (ebkOrderDO.getOrderStatus()!=EbkOrderStatusEnum.NEW.key){
                if (ebkOrderDO.getOrderStatus()==EbkOrderStatusEnum.CONFIRMED.key){
                    throw new ServiceException("订单已确认,不能再发预订单");
                }else if(ebkOrderDO.getOrderStatus()==EbkOrderStatusEnum.CANCELED.key){
                    throw new ServiceException("订单已取消,不能再发预订单");
                }
            }

            //如果该供货单号已经生成EBK订单，则先将原单保存快照
            saveEbkOrderSnap(ebkOrderDO);

            //更新订单信息
            BeanUtils.copyProperties(requestDTO,ebkOrderDO);
            ebkOrderDO.setEbkRequestId(ebkRequestDO.getId());
            //重置通知次数和上次通知时间
            ebkOrderDO.setNotifyCount(0);
            ebkOrderDO.setLastNotifyTime(DateUtil.stringToDate("2018-01-01"));
            ebkOrderMapper.updateByPrimaryKeySelective(ebkOrderDO);

            //清空原单明细
            EbkOrderItemDO ebkOrderItemParam=new EbkOrderItemDO();
            ebkOrderItemParam.setEbkOrderId(ebkOrderDO.getId());
            ebkOrderItemMapper.delete(ebkOrderItemParam);
            EbkOrderItemPriceDO ebkOrderItemPriceParam=new EbkOrderItemPriceDO();
            ebkOrderItemPriceParam.setEbkOrderId(ebkOrderDO.getId());
            ebkOrderItemPriceMapper.delete(ebkOrderItemPriceParam);
            //保存订单明细
            saveEbkOrderItem(ebkOrderDO.getId(),requestDTO);

            //先将重发预订单保存到快照中
            saveEbkOrderSnap(ebkRequestDO.getId(),requestDTO);

            //保存日志
            EbkOrderLogDO orderLogDO=new EbkOrderLogDO();
            orderLogDO.setContent("重发预订单，商家备注("+requestDTO.getMerchantRemark()+")");
            orderLogDO.setEbkOrderId(ebkOrderDO.getId());
            orderLogDO.setEbkRequestId(ebkRequestDO.getId());
            orderLogDO.setCreator(requestDTO.getOperator());
            orderLogDO.setCreateTime(new Date());
            ebkOrderLogMapper.insert(orderLogDO);
        }else{
            //创建ebk订单
            ebkOrderDO=new EbkOrderDO();
            BeanUtils.copyProperties(requestDTO,ebkOrderDO);
            ebkOrderDO.setOrderStatus(EbkOrderStatusEnum.NEW.key);
            ebkOrderDO.setEbkRequestId(ebkRequestDO.getId());
            ebkOrderDO.setNotifyCount(0);
            ebkOrderDO.setLastNotifyTime(DateUtil.stringToDate("2018-01-01"));
            ebkOrderDO.setCreator(requestDTO.getOperator());
            ebkOrderDO.setCreateTime(new Date());
            ebkOrderMapper.insert(ebkOrderDO);

            //保存订单明细
            saveEbkOrderItem(ebkOrderDO.getId(),requestDTO);

            //保存日志
            EbkOrderLogDO orderLogDO=new EbkOrderLogDO();
            orderLogDO.setContent("预订单");
            orderLogDO.setEbkOrderId(ebkOrderDO.getId());
            orderLogDO.setEbkRequestId(ebkRequestDO.getId());
            orderLogDO.setCreator(requestDTO.getOperator());
            orderLogDO.setCreateTime(new Date());
            ebkOrderLogMapper.insert(orderLogDO);
        }

        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Transactional
    @Override
    public ResponseDTO sendModifyRequest(BookRequestDTO requestDTO) {
        log.info("sendModifyRequest param:"+requestDTO);
        //保存请求信息
        EbkRequestDO ebkRequestDO=new EbkRequestDO();
        BeanUtils.copyProperties(requestDTO,ebkRequestDO);
        ebkRequestDO.setRequestType(EbkRequestTypeEnum.MODIFY_APPLY.key);
        ebkRequestDO.setRequestStatus(EbkRequestStatusEnum.UNTREATED.key);
        ebkRequestDO.setCreator(requestDTO.getOperator());
        ebkRequestDO.setCreateTime(new Date());
        ebkRequestMapper.insert(ebkRequestDO);

        EbkOrderDO ebkOrderParam=new EbkOrderDO();
        ebkOrderParam.setSupplyOrderCode(requestDTO.getSupplyOrderCode());
        EbkOrderDO ebkOrderDO=ebkOrderMapper.selectOne(ebkOrderParam);

        //如果上一个请求没保存快照，则生成快照
        EbkRequestDO lastEbkRequestDO=ebkRequestMapper.selectByPrimaryKey(ebkOrderDO.getEbkRequestId());
        if (lastEbkRequestDO.getExistSnap()==null || lastEbkRequestDO.getExistSnap()!=1){
            saveEbkOrderSnap(ebkOrderDO);
        }

        ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.MODIFY_APPLY.key);
        //更新订单状态和重置通知次数
        ebkOrderParam.setNotifyCount(0);
        ebkOrderParam.setLastNotifyTime(DateUtil.stringToDate("2018-01-01"));

        ebkOrderParam.setId(ebkOrderDO.getId());
        ebkOrderParam.setEbkRequestId(ebkRequestDO.getId());
        ebkOrderMapper.updateByPrimaryKeySelective(ebkOrderParam);

        //先将修改单保存到快照中
        saveEbkOrderSnap(ebkRequestDO.getId(),requestDTO);

        //保存日志
        EbkOrderLogDO orderLogDO=new EbkOrderLogDO();
        orderLogDO.setContent("修改申请单，商家备注("+requestDTO.getMerchantRemark()+")");
        orderLogDO.setEbkOrderId(ebkOrderDO.getId());
        orderLogDO.setEbkRequestId(ebkRequestDO.getId());
        orderLogDO.setCreator(requestDTO.getOperator());
        orderLogDO.setCreateTime(new Date());
        ebkOrderLogMapper.insert(orderLogDO);

        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Transactional
    @Override
    public ResponseDTO sendCancelRequest(CancelRequestDTO requestDTO) {
        log.info("sendCancelRequest param:"+requestDTO);
        //保存请求信息
        EbkRequestDO ebkRequestDO=new EbkRequestDO();
        BeanUtils.copyProperties(requestDTO,ebkRequestDO);
        ebkRequestDO.setRequestType(EbkRequestTypeEnum.CANCEL_APPLY.key);
        ebkRequestDO.setRequestStatus(EbkRequestStatusEnum.UNTREATED.key);
        ebkRequestDO.setCreator(requestDTO.getOperator());
        ebkRequestDO.setCreateTime(new Date());
        ebkRequestMapper.insert(ebkRequestDO);

        EbkOrderDO ebkOrderParam=new EbkOrderDO();
        ebkOrderParam.setSupplyOrderCode(requestDTO.getSupplyOrderCode());
        EbkOrderDO ebkOrderDO=ebkOrderMapper.selectOne(ebkOrderParam);

        if(ebkOrderDO.getOrderStatus()==EbkOrderStatusEnum.NEW.key){
            ebkRequestDO.setRequestStatus(EbkRequestStatusEnum.CONFIRMED.key);
            ebkRequestDO.setModifier("system");
            ebkRequestDO.setModifyTime(new Date());
            ebkRequestMapper.updateByPrimaryKeySelective(ebkRequestDO);

            ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CANCELED.key);
        }else{
            ebkOrderParam.setOrderStatus(EbkOrderStatusEnum.CANCEL_APPLY.key);
            //更新订单状态和重置通知次数
            ebkOrderParam.setNotifyCount(0);
            ebkOrderParam.setLastNotifyTime(DateUtil.stringToDate("2018-01-01"));
        }
        ebkOrderParam.setId(ebkOrderDO.getId());
        ebkOrderParam.setEbkRequestId(ebkRequestDO.getId());
        ebkOrderMapper.updateByPrimaryKeySelective(ebkOrderParam);

        //保存日志
        EbkOrderLogDO orderLogDO=new EbkOrderLogDO();
        orderLogDO.setContent("取消申请单，商家备注("+requestDTO.getMerchantRemark()+")");
        orderLogDO.setEbkOrderId(ebkOrderDO.getId());
        orderLogDO.setCreator(requestDTO.getOperator());
        orderLogDO.setCreateTime(new Date());
        ebkOrderLogMapper.insert(orderLogDO);

        //回写确认结果
        if (ebkOrderParam.getOrderStatus()==EbkOrderStatusEnum.CANCELED.key){
            EbkCallbackRequestDTO ebkCallbackRequestDTO=new EbkCallbackRequestDTO();
            ebkCallbackRequestDTO.setSupplyRequestId(requestDTO.getSupplyRequestId().intValue());
            ebkCallbackRequestDTO.setConfirmType(1);
            ebkCallbackRequestDTO.setConfirmName("system");
            ebkCallService.ebkCallback(ebkCallbackRequestDTO);
        }
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    /**
     * 保存订单明细
     * @param ebkOrderId
     * @param requestDTO
     */
    private void saveEbkOrderItem(Long ebkOrderId,BookRequestDTO requestDTO){
        List<EbkOrderItemDO> ebkOrderItemDOList=new ArrayList<>();
        List<EbkOrderItemPriceDO> ebkOrderItemPriceDOList=new ArrayList<>();
        Map<EbkOrderItemDO,List<EbkOrderItemPriceDO>> ebkOrderItemDOMap=new HashMap<>();
        for (BookRequestItemDTO bookRequestItemDTO:requestDTO.getItemDTOList()){
            EbkOrderItemDO ebkOrderItemDO=new EbkOrderItemDO();
            BeanUtils.copyProperties(bookRequestItemDTO,ebkOrderItemDO);
            if (bookRequestItemDTO.getCheckInDate()!=null
                    && bookRequestItemDTO.getCheckOutDate()!=null){
                ebkOrderItemDO.setNightNum((int)DateUtil.getDay(bookRequestItemDTO.getCheckInDate(),bookRequestItemDTO.getCheckOutDate()));
            }
            ebkOrderItemDO.setEbkOrderId(ebkOrderId);
            ebkOrderItemDO.setCreator(requestDTO.getOperator());
            ebkOrderItemDO.setCreateTime(new Date());
            ebkOrderItemDOList.add(ebkOrderItemDO);

            List<EbkOrderItemPriceDO> ebkOrderItemPriceDOListTemp=new ArrayList<>();
            if (bookRequestItemDTO.getItemPriceDTOList()!=null && bookRequestItemDTO.getItemPriceDTOList().size()>0){
                for (BookRequestItemPriceDTO bookRequestItemPriceDTO:bookRequestItemDTO.getItemPriceDTOList()){
                    EbkOrderItemPriceDO ebkOrderItemPriceDO=new EbkOrderItemPriceDO();
                    BeanUtils.copyProperties(bookRequestItemPriceDTO,ebkOrderItemPriceDO);
                    ebkOrderItemPriceDO.setEbkOrderId(ebkOrderId);
                    ebkOrderItemPriceDO.setCreator(requestDTO.getOperator());
                    ebkOrderItemPriceDO.setCreateTime(new Date());
                    ebkOrderItemPriceDOListTemp.add(ebkOrderItemPriceDO);
                    ebkOrderItemPriceDOList.add(ebkOrderItemPriceDO);
                }
            }
            ebkOrderItemDOMap.put(ebkOrderItemDO,ebkOrderItemPriceDOListTemp);
        }
        if (ebkOrderItemDOList.size()>0){
            ebkOrderItemMapper.insertList(ebkOrderItemDOList);
            if (ebkOrderItemPriceDOList.size()>0){
                for (Map.Entry<EbkOrderItemDO,List<EbkOrderItemPriceDO>> entry:ebkOrderItemDOMap.entrySet()){
                    for (EbkOrderItemPriceDO ebkOrderItemPriceDO:entry.getValue()){
                        ebkOrderItemPriceDO.setOrderItemId(entry.getKey().getId());
                    }
                }
                ebkOrderItemPriceMapper.insertList(ebkOrderItemPriceDOList);
            }
        }
    }

    /**
     * 保存订单快照
     * @param ebkRequestId
     * @param requestDTO
     */
    private void saveEbkOrderSnap(Long ebkRequestId,BookRequestDTO requestDTO){
        EbkRequestDO ebkRequestParam=new EbkRequestDO();
        ebkRequestParam.setId(ebkRequestId);
        ebkRequestParam.setExistSnap(1);
        ebkRequestMapper.updateByPrimaryKeySelective(ebkRequestParam);

        EbkOrderSnapDO ebkOrderSnapDO=new EbkOrderSnapDO();
        BeanUtils.copyProperties(requestDTO,ebkOrderSnapDO);
        ebkOrderSnapDO.setEbkRequestId(ebkRequestId);
        ebkOrderSnapMapper.insert(ebkOrderSnapDO);

        List<EbkOrderItemSnapDO> ebkOrderItemSnapDOList=new ArrayList<>();
        List<EbkOrderItemPriceSnapDO> ebkOrderItemPriceSnapDOList=new ArrayList<>();
        Map<EbkOrderItemSnapDO,List<EbkOrderItemPriceSnapDO>> ebkOrderItemSnapDOMap=new HashMap<>();
        for (BookRequestItemDTO bookRequestItemDTO:requestDTO.getItemDTOList()){
            EbkOrderItemSnapDO ebkOrderItemSnapDO=new EbkOrderItemSnapDO();
            BeanUtils.copyProperties(bookRequestItemDTO,ebkOrderItemSnapDO);
            if (bookRequestItemDTO.getCheckInDate()!=null
                    && bookRequestItemDTO.getCheckOutDate()!=null){
                ebkOrderItemSnapDO.setNightNum((int)DateUtil.getDay(bookRequestItemDTO.getCheckInDate(),bookRequestItemDTO.getCheckOutDate()));
            }
            ebkOrderItemSnapDO.setEbkOrderSnapId(ebkOrderSnapDO.getId());
            ebkOrderItemSnapDO.setCreator(requestDTO.getOperator());
            ebkOrderItemSnapDO.setCreateTime(new Date());
            ebkOrderItemSnapDOList.add(ebkOrderItemSnapDO);

            List<EbkOrderItemPriceSnapDO> ebkOrderItemPriceSnapDOListTemp=new ArrayList<>();
            if (bookRequestItemDTO.getItemPriceDTOList()!=null && bookRequestItemDTO.getItemPriceDTOList().size()>0){
                for (BookRequestItemPriceDTO bookRequestItemPriceDTO:bookRequestItemDTO.getItemPriceDTOList()){
                    EbkOrderItemPriceSnapDO ebkOrderItemPriceSnapDO=new EbkOrderItemPriceSnapDO();
                    BeanUtils.copyProperties(bookRequestItemPriceDTO,ebkOrderItemPriceSnapDO);
                    ebkOrderItemPriceSnapDO.setEbkOrderSnapId(ebkOrderSnapDO.getId());
                    ebkOrderItemPriceSnapDO.setCreator(requestDTO.getOperator());
                    ebkOrderItemPriceSnapDO.setCreateTime(new Date());
                    ebkOrderItemPriceSnapDOListTemp.add(ebkOrderItemPriceSnapDO);
                    ebkOrderItemPriceSnapDOList.add(ebkOrderItemPriceSnapDO);
                }
            }
            ebkOrderItemSnapDOMap.put(ebkOrderItemSnapDO,ebkOrderItemPriceSnapDOListTemp);
        }
        if (ebkOrderItemSnapDOList.size()>0){
            ebkOrderItemSnapMapper.insertList(ebkOrderItemSnapDOList);
            if (ebkOrderItemPriceSnapDOList.size()>0){
                for (Map.Entry<EbkOrderItemSnapDO,List<EbkOrderItemPriceSnapDO>> entry:ebkOrderItemSnapDOMap.entrySet()){
                    for (EbkOrderItemPriceSnapDO ebkOrderItemPriceSnapDO:entry.getValue()){
                        ebkOrderItemPriceSnapDO.setOrderItemId(entry.getKey().getId());
                    }
                }
                ebkOrderItemPriceSnapMapper.insertList(ebkOrderItemPriceSnapDOList);
            }
        }
    }

    /**
     * 保存订单快照
     * @param ebkOrderDO
     */
    private void saveEbkOrderSnap(EbkOrderDO ebkOrderDO){
        EbkRequestDO ebkRequestParam=new EbkRequestDO();
        ebkRequestParam.setId(ebkOrderDO.getEbkRequestId());
        ebkRequestParam.setExistSnap(1);
        ebkRequestMapper.updateByPrimaryKeySelective(ebkRequestParam);

        EbkOrderSnapDO ebkOrderSnapDO=new EbkOrderSnapDO();
        BeanUtils.copyProperties(ebkOrderDO,ebkOrderSnapDO);
        ebkOrderSnapDO.setId(null);
        ebkOrderSnapDO.setEbkRequestId(ebkOrderDO.getEbkRequestId());
        ebkOrderSnapMapper.insert(ebkOrderSnapDO);

        List<EbkOrderItemSnapDO> ebkOrderItemSnapDOList=new ArrayList<>();
        Map<Long,EbkOrderItemSnapDO> ebkOrderItemSnapDOMap=new HashMap<>();
        EbkOrderItemDO ebkOrderItemParam=new EbkOrderItemDO();
        ebkOrderItemParam.setEbkOrderId(ebkOrderDO.getId());
        List<EbkOrderItemDO> ebkOrderItemDOList=ebkOrderItemMapper.select(ebkOrderItemParam);
        for (EbkOrderItemDO ebkOrderItemDO:ebkOrderItemDOList){
            EbkOrderItemSnapDO ebkOrderItemSnapDO=new EbkOrderItemSnapDO();
            BeanUtils.copyProperties(ebkOrderItemDO,ebkOrderItemSnapDO);
            ebkOrderItemSnapDO.setId(null);
            ebkOrderItemSnapDO.setEbkOrderSnapId(ebkOrderSnapDO.getId());
            ebkOrderItemSnapDOList.add(ebkOrderItemSnapDO);
            ebkOrderItemSnapDOMap.put(ebkOrderItemDO.getId(),ebkOrderItemSnapDO);
        }
        if (ebkOrderItemSnapDOList.size()>0){
            ebkOrderItemSnapMapper.insertList(ebkOrderItemSnapDOList);

            List<EbkOrderItemPriceSnapDO> ebkOrderItemPriceSnapDOList=new ArrayList<>();
            for (Map.Entry<Long,EbkOrderItemSnapDO> itemEntry:ebkOrderItemSnapDOMap.entrySet()){
                EbkOrderItemPriceDO ebkOrderItemPriceParam=new EbkOrderItemPriceDO();
                ebkOrderItemPriceParam.setOrderItemId(itemEntry.getKey());
                List<EbkOrderItemPriceDO> ebkOrderItemPriceDOList=ebkOrderItemPriceMapper.select(ebkOrderItemPriceParam);
                if (ebkOrderItemPriceDOList.size()>0){
                    for (EbkOrderItemPriceDO ebkOrderItemPriceDO:ebkOrderItemPriceDOList){
                        EbkOrderItemPriceSnapDO ebkOrderItemPriceSnapDO=new EbkOrderItemPriceSnapDO();
                        BeanUtils.copyProperties(ebkOrderItemPriceDO,ebkOrderItemPriceSnapDO);
                        ebkOrderItemPriceSnapDO.setId(null);
                        ebkOrderItemPriceSnapDO.setEbkOrderSnapId(ebkOrderSnapDO.getId());
                        ebkOrderItemPriceSnapDO.setOrderItemId(itemEntry.getValue().getId());
                        ebkOrderItemPriceSnapDOList.add(ebkOrderItemPriceSnapDO);
                    }
                }
            }
            if (ebkOrderItemPriceSnapDOList.size()>0){
                ebkOrderItemPriceSnapMapper.insertList(ebkOrderItemPriceSnapDOList);
            }
        }
    }
}
