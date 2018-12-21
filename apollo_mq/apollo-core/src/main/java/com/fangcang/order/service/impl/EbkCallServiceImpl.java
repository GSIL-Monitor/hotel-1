package com.fangcang.order.service.impl;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.order.NoteTypeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.SupplyConfirmResultEnum;
import com.fangcang.common.enums.order.SupplyStatusEnum;
import com.fangcang.common.enums.order.SupplyTypeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderNoteDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.domain.SupplyProductDO;
import com.fangcang.order.domain.SupplyRequestDO;
import com.fangcang.order.dto.SupplyOrderCofirmMsgDTO;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.mapper.OrderNoteMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.mapper.SupplyProductMapper;
import com.fangcang.order.mapper.SupplyRequestMapper;
import com.fangcang.order.request.EbkCallbackRequestDTO;
import com.fangcang.order.request.OrderCreditPayOrRefundRequestDTO;
import com.fangcang.order.request.SupplyCreditPayOrReceiptRequestDTO;
import com.fangcang.order.response.SupplyOrderPriceSumResponseDTO;
import com.fangcang.order.service.EbkCallService;
import com.fangcang.order.service.OrderCommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * EBK回调服务
 *
 * @author : zhanwang
 * @date : 2018/6/8
 */
@Service
@Slf4j
public class EbkCallServiceImpl implements EbkCallService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private SupplyRequestMapper supplyRequestMapper;
    @Resource
    private OrderNoteMapper orderNoteMapper;
    @Resource
    private SupplyProductMapper supplyProductMapper;

    @Resource
    private OrderCommonService orderCommonService;


    @Override
    @Transactional
    @Async
    public ResponseDTO ebkCallback(EbkCallbackRequestDTO requestDTO) {
        log.info("ebkCallback param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新供货请求状态
        SupplyRequestDO supplyRequestUpdate = new SupplyRequestDO();
        supplyRequestUpdate.setId(requestDTO.getSupplyRequestId());
        supplyRequestUpdate.setThisConfirmType(Byte.valueOf(requestDTO.getConfirmType() + ""));
        supplyRequestUpdate.setThisConfirmName(requestDTO.getConfirmName());
        supplyRequestUpdate.setThisConfirmNo(requestDTO.getConfirmNo());
        supplyRequestUpdate.setRefuseReason(requestDTO.getRefuseReason());
        supplyRequestUpdate.setNote(requestDTO.getNote());
        supplyRequestUpdate.setModifier(requestDTO.getConfirmName());
        supplyRequestUpdate.setModifyTime(DateUtil.getCurrentDate());
        supplyRequestMapper.updateByPrimaryKeySelective(supplyRequestUpdate);
        // 2. 更新供货单状态
        SupplyRequestDO supplyRequestDO = supplyRequestMapper.selectByPrimaryKey(requestDTO.getSupplyRequestId());
        SupplyOrderDO supplyOrderDO = supplyOrderMapper.selectByPrimaryKey(supplyRequestDO.getSupplyOrderId());
        OrderDO orderDO = orderMapper.selectByPrimaryKey(supplyOrderDO.getOrderId());
        // 如果发预订单，EBK确认供货单即为已确认，EBK拒绝供货单变为不确认
        // 如果发取消单，EBK确认供货单即为已取消，EBK拒绝供货单状态不变
        // 如果发修改单，EBK确认供货单状态不变，EBK拒绝供货单状态不变
        StringBuffer logContent = new StringBuffer();
        Integer supplyStatus = null;
        String confirmResult = null;
        if (requestDTO.getConfirmType() == 1) {
            if (supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.BOOK.key
                    || supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.RESEND.key) {
                supplyStatus = SupplyStatusEnum.CONFIRMED.key;
                logContent.append("同意预订");
                confirmResult = SupplyConfirmResultEnum.BOOK_YES.key;
            } else if (supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.CANCEL.key) {
                supplyStatus = SupplyStatusEnum.UN_CONFIRM.key;
                logContent.append("同意取消");
                confirmResult = SupplyConfirmResultEnum.CANCEL_YES.key;
            } else if (supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.REVISE.key) {
                logContent.append("同意修改");
                confirmResult = SupplyConfirmResultEnum.MODIFY_YES.key;
            }
        } else if (requestDTO.getConfirmType() == 2) {
            if (supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.BOOK.key
                    || supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.RESEND.key) {
                supplyStatus = SupplyStatusEnum.UN_CONFIRM.key;
                logContent.append("拒绝预订");
                confirmResult = SupplyConfirmResultEnum.BOOK_NO.key;
            } else if (supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.CANCEL.key) {
                logContent.append("拒绝取消");
                confirmResult = SupplyConfirmResultEnum.CANCEL_NO.key;
            } else if (supplyRequestDO.getSupplyType().intValue() == SupplyTypeEnum.REVISE.key) {
                logContent.append("拒绝修改");
                confirmResult = SupplyConfirmResultEnum.MODIFY_NO.key;
            }
        }
        BigDecimal supplySalePriceSum = null;
        if (supplyStatus != null) {
            // 供货单状态变化，重新计算供货单金额
            SupplyOrderPriceSumResponseDTO priceSumResponseDTO = orderCommonService.calcSupplyOrderPriceSum(supplyRequestDO.getSupplyOrderId(), supplyStatus);
            BigDecimal supplyBasePriceSum = priceSumResponseDTO.getSupplyBasePriceSum();
            supplySalePriceSum = priceSumResponseDTO.getSupplySalePriceSum();
            SupplyOrderDO supplyOrderUpdate = new SupplyOrderDO();
            supplyOrderUpdate.setId(supplyRequestDO.getSupplyOrderId());
            supplyOrderUpdate.setSupplyStatus(Byte.valueOf(supplyStatus + ""));
            supplyOrderUpdate.setConfirmNo(requestDTO.getConfirmNo());
            supplyOrderUpdate.setConfirmName(requestDTO.getConfirmName());
            supplyOrderUpdate.setRefuseReason(requestDTO.getRefuseReason());
            // 供货单金额已变化
            if (supplyBasePriceSum != null) {
                supplyOrderUpdate.setSupplySum(supplyBasePriceSum);
                supplyOrderUpdate.setPayableAmount(supplyBasePriceSum);
                supplyOrderUpdate.setSalePriceSum(supplySalePriceSum);
            }
            supplyOrderUpdate.setModifier(requestDTO.getConfirmName());
            supplyOrderUpdate.setModifyTime(DateUtil.getCurrentDate());
            supplyOrderMapper.updateByPrimaryKeySelective(supplyOrderUpdate);
            // 3. 更新单结供货单财务信息：支付状态、结算状态
            if (supplyBasePriceSum != null) {
                if (supplyOrderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key) {
                    orderCommonService.updateSupplyFinanceInfo(supplyOrderDO.getId(), supplyOrderUpdate.getPayableAmount(), null, null);
                }
            }
            // 4. 非单结供货单，挂账或退挂账
            if (supplyOrderDO.getBalanceMethod().intValue() != BalanceMethodEnum.SINGLE.key) {
                SupplyCreditPayOrReceiptRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(supplyOrderDO, SupplyCreditPayOrReceiptRequestDTO.class);
                creditRequestDTO.setSupplyOrderId(supplyOrderDO.getId());
                creditRequestDTO.setPayableAmount(supplyBasePriceSum != null ? supplyOrderUpdate.getPayableAmount() : supplyOrderDO.getPayableAmount());
                creditRequestDTO.setPayAmount(supplyOrderDO.getPayableAmount());
                creditRequestDTO.setOperateType(supplyStatus == 3 ? 1 : 2);
                creditRequestDTO.setOperator(requestDTO.getConfirmName());
                creditRequestDTO.setMerchantCode(orderDO.getMerchantCode());
                creditRequestDTO.setSupplyStatus(supplyStatus);
                orderCommonService.supplyCreditPayOrRefund(creditRequestDTO);
            }
        }
        // 5. 更新订单冗余字段： 供货单状态、供货单确认号、订单总金额
        SupplyOrderDO supplyOrderListQuery = new SupplyOrderDO();
        supplyOrderListQuery.setOrderId(supplyOrderDO.getOrderId());
        List<SupplyOrderDO> supplyOrderDOList = supplyOrderMapper.select(supplyOrderListQuery);
        Set<String> supplyStatusSet = new HashSet<>();
        Set<String> supplyConfirmNoSet = new HashSet<>();
        BigDecimal orderSum = BigDecimal.ZERO;
        Integer firstActiveSupplyOrder = null;
        for (SupplyOrderDO supplyOrder : supplyOrderDOList) {
            supplyStatusSet.add(supplyOrder.getSupplyStatus() + "");
            if (!StringUtils.isEmpty(supplyOrder.getConfirmNo())) {
                supplyConfirmNoSet.add(supplyOrder.getConfirmNo());
            }
            orderSum = orderSum.add(supplyOrder.getSalePriceSum());
            if (firstActiveSupplyOrder == null && supplyOrder.getSupplyStatus().intValue() != SupplyStatusEnum.UN_CONFIRM.key) {
                firstActiveSupplyOrder = supplyOrder.getId();
            }
        }
        OrderDO orderUpdate = new OrderDO();
        orderUpdate.setId(supplyOrderDO.getOrderId());
        orderUpdate.setSupplyStatus(StringUtil.listToSQLString(supplyStatusSet));
        orderUpdate.setSupplyConfirmNo(StringUtil.listToSQLString(supplyConfirmNoSet));
        // 供货单金额已变化
        if (supplySalePriceSum != null) {
            orderUpdate.setOrderSum(orderSum);
            orderUpdate.setReceivableAmount(orderSum);
            orderUpdate.setProfit(orderCommonService.calcProfit(supplyOrderDO.getOrderId(), orderSum, supplyOrderDOList));
        }
        // 更新订单表产品冗余字段：为第一个供货单的第一个产品的入离日期、间数、产品名称
        if (firstActiveSupplyOrder != null) {
            SupplyProductDO firstProductQuery = new SupplyProductDO();
            firstProductQuery.setSupplyOrderId(firstActiveSupplyOrder);
            SupplyProductDO firstProduct = supplyProductMapper.select(firstProductQuery).get(0);
            orderUpdate.setCheckoutDate(firstProduct.getCheckoutDate());
            orderUpdate.setCheckinDate(firstProduct.getCheckinDate());
            orderUpdate.setRoomNum(firstProduct.getRoomNum());
            orderUpdate.setRateplanName(firstProduct.getRateplanName());
            orderUpdate.setBreakfastType(firstProduct.getBreakfastType());
        }
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
        // 6. 更新单结订单财务信息：支付状态、结算状态
        if (supplySalePriceSum != null) {
            if (orderDO.getBalanceMethod().intValue() == BalanceMethodEnum.SINGLE.key) {
                orderCommonService.updateOrderFinanceInfo(orderDO.getId(), orderUpdate.getReceivableAmount(), null, null);
            } else {
                // 6. 非单结、已确认订单自动挂账
                if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.TRADED.key) {
                    OrderCreditPayOrRefundRequestDTO creditRequestDTO = PropertyCopyUtil.transfer(orderDO, OrderCreditPayOrRefundRequestDTO.class);
                    creditRequestDTO.setReceivableAmount(orderUpdate.getReceivableAmount());
                    creditRequestDTO.setPayAmount(orderDO.getReceivableAmount());
                    creditRequestDTO.setOperateType(3);
                    creditRequestDTO.setOperator(requestDTO.getConfirmName());
                    creditRequestDTO.setOrderId(orderDO.getId());
                    orderCommonService.orderCreditPayOrRefund(creditRequestDTO);
                }
            }
        }
        // 7. 散房订单， 调用产品模块， 退扣配额
        if (supplyStatus != null) {
            orderCommonService.debuctOrRetreatSupplyOrderQuota(supplyStatus, supplyOrderDO, orderDO, requestDTO.getConfirmName());
        }
        // 8. 添加供应商给商家备注
        if (!StringUtils.isEmpty(requestDTO.getNote())) {
            OrderNoteDO noteInsert = new OrderNoteDO();
            noteInsert.setOrderId(supplyOrderDO.getOrderId());
            noteInsert.setCreator(requestDTO.getConfirmName());
            noteInsert.setCreateTime(DateUtil.getCurrentDate());
            noteInsert.setNote(requestDTO.getNote());
            noteInsert.setNoteObject(orderDO.getMerchantName());
            noteInsert.setNoteType(Byte.valueOf(NoteTypeEnum.SUPPLY_NOTE.key + ""));
            orderNoteMapper.insert(noteInsert);
        }
        // 9. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(supplyOrderDO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("供应商EBK回传结果，供货单：" + supplyOrderDO.getSupplyOrderCode()
                + ", 发" + SupplyTypeEnum.getValueByKey(supplyRequestDO.getSupplyType())
                + ", 供应商" + logContent.toString());
        if (StringUtils.isNotEmpty(requestDTO.getConfirmNo())) {
            content.append("，确认号：" + requestDTO.getConfirmNo());
        }
        if (StringUtils.isNotEmpty(requestDTO.getRefuseReason())) {
            content.append("，拒绝原因：" + requestDTO.getRefuseReason());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getConfirmName());
        logDO.setOperateTime(DateUtil.getCurrentDate());
        orderCommonService.addOperationLog(logDO);


        //10、发送消息。消息存入缓存中
        SupplyOrderCofirmMsgDTO supplyOrderCofirmMsgDTO = new SupplyOrderCofirmMsgDTO();
        supplyOrderCofirmMsgDTO.setMerchantCode(orderDO.getMerchantCode());
        supplyOrderCofirmMsgDTO.setSupplyCode(supplyOrderDO.getSupplyCode());
        supplyOrderCofirmMsgDTO.setOrderId(orderDO.getId());
        supplyOrderCofirmMsgDTO.setOrderCode(orderDO.getOrderCode());
        supplyOrderCofirmMsgDTO.setRefuseReason(requestDTO.getRefuseReason());
        supplyOrderCofirmMsgDTO.setSupplyName(supplyOrderDO.getSupplyName());
        supplyOrderCofirmMsgDTO.setConfirmResult(confirmResult);
        orderCommonService.saveSupplyOrderCofirmMsgToRedis(supplyOrderCofirmMsgDTO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }


}
