package com.fangcang.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ChannelTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.enums.order.OrderStatusEnum;
import com.fangcang.common.enums.order.SupplyStatusEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.HttpClientUtil;
import com.fangcang.common.util.OrderOperateConfig;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.URLSplitUtil;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.OrderRequestDO;
import com.fangcang.order.domain.OrderRequestPriceDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.domain.SupplyProductDO;
import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.mapper.OrderMapper;
import com.fangcang.order.mapper.OrderRequestMapper;
import com.fangcang.order.mapper.OrderRequestPriceMapper;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.order.mapper.SupplyProductMapper;
import com.fangcang.order.request.AddProductRequestDTO;
import com.fangcang.order.request.ChangeGuestRequestDTO;
import com.fangcang.order.request.ChangeProductRequestDTO;
import com.fangcang.order.request.DeleteProductRequestDTO;
import com.fangcang.order.request.DltOrderOperateRequestDTO;
import com.fangcang.order.request.OrderCancelRequestDTO;
import com.fangcang.order.request.OrderConfirmRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.OtaOrderOperateRequestDTO;
import com.fangcang.order.response.DltResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import com.fangcang.order.service.OrderCommonService;
import com.fangcang.order.service.OrderService;
import com.fangcang.order.service.OtaOrderHandleService;
import com.fangcang.order.service.SupplyOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : zhanwang
 * @date : 2018/8/2
 */
@Service
@Slf4j
public class OtaOrderHandleServiceImpl implements OtaOrderHandleService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private SupplyProductMapper supplyProductMapper;
    @Resource
    private OrderRequestMapper orderRequestMapper;
    @Resource
    private OrderRequestPriceMapper orderRequestPriceMapper;

    @Resource
    private SupplyOrderService supplyOrderService;
    @Resource
    private OrderCommonService orderCommonService;
    @Resource
    private OrderService orderService;

    @Autowired
    private OrderOperateConfig orderOperateConfig;

    /**
     * 检查ota订单能否操作
     *
     * @param requestDTO
     * @return
     */
    private ResponseDTO<Integer> checkOtaOrder(OtaOrderOperateRequestDTO requestDTO, List<OrderRequestDO> orderRequestDOS, OrderDO orderDO) {
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>(ResultCodeEnum.FAILURE.code);
        // 1. 校验订单能否操作
        Integer orderStatus = null;
        if (requestDTO.getSendType() == 1) {
            // 1.1 预订单，同意预订单订单变为已确认，拒绝预定订单变为已取消
            if (requestDTO.getHandleType() == 1) {
                orderStatus = OrderStatusEnum.TRADED.key;
            } else if (requestDTO.getHandleType() == 2) {
                orderStatus = OrderStatusEnum.CANCELED.key;
            }
        } else if (requestDTO.getSendType() == 2 && requestDTO.getHandleType() == 1) {
            // 1.2 检验修改申请参数，修改单订单状态不变
            if (CollectionUtils.isEmpty(orderRequestDOS)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("操作失败，没有未处理的修改申请！");
                return responseDTO;
            }
            OrderRequestDO orderRequestDO = orderRequestDOS.get(0);
            if (orderRequestDO.getRateplanId() == null || StringUtils.isEmpty(orderRequestDO.getCustomerOrderCode())
                    || orderRequestDO.getCheckinDate() == null || orderRequestDO.getCheckoutDate() == null
                    || orderRequestDO.getRoomNum() == null) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("操作失败，修改申请参数不正确！");
                return responseDTO;
            }
            OrderRequestPriceDO orderRequestPriceQuery = new OrderRequestPriceDO();
            orderRequestPriceQuery.setOrderRequestId(orderRequestDO.getId());
            List<OrderRequestPriceDO> orderRequestPriceDOS = orderRequestPriceMapper.select(orderRequestPriceQuery);
            if (CollectionUtils.isEmpty(orderRequestPriceDOS)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("操作失败，修改申请没有价格明细！");
                return responseDTO;
            }
        } else if (requestDTO.getSendType() == 3 && requestDTO.getHandleType() == 1) {
            // 1.3 取消单同意修改，订单变为已取消
            if (CollectionUtils.isEmpty(orderRequestDOS)) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("操作失败，没有未处理的取消申请！");
                return responseDTO;
            }
            orderStatus = OrderStatusEnum.CANCELED.key;
        }
        // 如果订单状态需要变化， 检查能否变化
        if (orderStatus != null) {
            if (orderDO.getOrderStatus().intValue() == OrderStatusEnum.CANCELED.key) {
                responseDTO.setResult(Constant.NO);
                responseDTO.setFailReason("订单已取消！");
                return responseDTO;
            }
            if (orderStatus == OrderStatusEnum.CANCELED.key) {
                // 检查供货单状态：没有已确认的供货单才能取消
                OrderDetailRequestDTO detailRequestDTO = new OrderDetailRequestDTO();
                detailRequestDTO.setOrderId(requestDTO.getOrderId());
                ResponseDTO<List<SupplyOrderDTO>> supplyListResponse = supplyOrderService.querySupplyList(detailRequestDTO);
                List<SupplyOrderDTO> supplyList = supplyListResponse.getModel();
                for (SupplyOrderDTO supplyOrderDTO : supplyList) {
                    if (supplyOrderDTO.getSupplyStatus() == SupplyStatusEnum.CONFIRMED.key) {
                        responseDTO.setResult(Constant.NO);
                        responseDTO.setFailReason("供货单（" + supplyOrderDTO.getSupplyOrderCode() + "）已确认，请先取消！");
                        return responseDTO;
                    }
                }
            }
        }
        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(orderStatus);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO otaOrderOperate(OtaOrderOperateRequestDTO requestDTO) {
        log.info("otaOrderOperate param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.FAILURE.code);

        // 1. 校验订单能否操作
        List<OrderRequestDO> orderRequestDOS = null;
        boolean isModifyOrCancelOrder = requestDTO.getSendType() == 2 || requestDTO.getSendType() == 3;
        if (isModifyOrCancelOrder) {
            OrderRequestDO requestQuery = new OrderRequestDO();
            requestQuery.setRequestType(requestDTO.getSendType() == 2 ? Byte.valueOf("2") : Byte.valueOf("1"));
            requestQuery.setOrderId(requestDTO.getOrderId());
            requestQuery.setHandleResult(Byte.valueOf("0"));
            orderRequestDOS = orderRequestMapper.select(requestQuery);
        }
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        ResponseDTO<Integer> checkResponseDTO = checkOtaOrder(requestDTO, orderRequestDOS, orderDO);
        if (checkResponseDTO.getResult().equals(Constant.NO)) {
            responseDTO.setResult(checkResponseDTO.getResult());
            responseDTO.setFailReason(checkResponseDTO.getFailReason());
            return responseDTO;
        }
        // 2. ota订单，调用ota订单确认接口
        boolean isInvokeOTA = CollectionUtils.isEmpty(orderRequestDOS)
                || orderRequestDOS.get(0).getOtaHandleResult() == null || orderRequestDOS.get(0).getOtaHandleResult() == 0;
        if (isInvokeOTA) {
            DltOrderOperateRequestDTO dltRequestDTO = assemblyOtaOrderOperate(requestDTO, orderRequestDOS);
            try {
                String message = JSON.toJSONString(dltRequestDTO);
                log.info("dlt orderOperate param:" + message);
                String url = URLSplitUtil.getOrderOperateUrl(orderOperateConfig);
                String responseStr = HttpClientUtil.postJson(url, message);
                log.info("dlt orderOperate response:" + responseStr);
                DltResponseDTO dltResponseDTO = JSON.parseObject(responseStr, DltResponseDTO.class);
                if (null == dltResponseDTO || ResultCodeEnum.FAILURE.code == dltResponseDTO.getIsSuccess()) {
                    responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                    responseDTO.setFailReason(null == dltResponseDTO ? null : dltResponseDTO.getFailureReason());
                } else if (ResultCodeEnum.SUCCESS.code == dltResponseDTO.getIsSuccess()) {
                    responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
                }
            } catch (IOException e) {
                log.error("dlt orderOperate error", e);
                responseDTO.setResult(ResultCodeEnum.FAILURE.code);
                responseDTO.setFailReason("操作异常");
            }
            if (responseDTO.getResult().equals(ResultCodeEnum.FAILURE.code)) {
                return responseDTO;
            }
        }

        // 3. 当有未处理的修改申请，如果同意申请：自动更新产品入离日期、间数、更新入住人
        if (!CollectionUtils.isEmpty(orderRequestDOS) && requestDTO.getSendType() == 2 && requestDTO.getHandleType() == 1) {
            OrderRequestDO orderRequestDO = orderRequestDOS.get(0);
            OrderRequestPriceDO orderRequestPriceQuery = new OrderRequestPriceDO();
            orderRequestPriceQuery.setOrderRequestId(orderRequestDO.getId());
            List<OrderRequestPriceDO> orderRequestPriceDOS = orderRequestPriceMapper.select(orderRequestPriceQuery);
            // 3.1 更新入住人
            if (!StringUtils.isEmpty(orderRequestDO.getGuestNames())) {
                List<String> guestList = StringUtil.stringToList(orderRequestDO.getGuestNames(), ",");
                ChangeGuestRequestDTO changeGuestRequestDTO = new ChangeGuestRequestDTO();
                changeGuestRequestDTO.setOrderId(orderRequestDO.getOrderId());
                changeGuestRequestDTO.setGuestList(guestList);
                changeGuestRequestDTO.setCreator(requestDTO.getCreator());
                changeGuestRequestDTO.setModifyTime(requestDTO.getModifyTime());
                orderService.changeGuest(changeGuestRequestDTO);
            }
            // 3.2. 更新客户单号
            if (!StringUtils.isEmpty(orderRequestDO.getCustomerOrderCode())) {
                OrderDO orderUpdate = new OrderDO();
                orderUpdate.setId(requestDTO.getOrderId());
                orderUpdate.setCustomerOrderCode(orderRequestDO.getCustomerOrderCode());
                orderMapper.updateByPrimaryKeySelective(orderUpdate);
            }
            // 3.3 更新产品信息
            SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
            supplyOrderQuery.setOrderId(orderRequestDO.getOrderId());
            List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
            SupplyProductDO supplyProductQuery = new SupplyProductDO();
            supplyProductQuery.setSupplyOrderId(supplyOrderDOS.get(0).getId());
            SupplyProductDO oldSupplyProductDO = supplyProductMapper.select(supplyProductQuery).get(0);
            // 查产品价格信息
            Map<String, SupplyProductPriceResponseDTO> productDailyPriceMap = orderCommonService.getProductDailyPriceMap(orderDO.getChannelCode(),
                    orderRequestDO.getRateplanId(), DateUtil.dateToString(orderRequestDO.getCheckinDate()), DateUtil.dateToString(orderRequestDO.getCheckoutDate()),
                    orderRequestDO.getRoomNum(), orderDO.getIsGroupRoom().intValue());
            List<SupplyProductPriceDTO> supplyProductPriceList = new ArrayList<>();
            for (OrderRequestPriceDO orderRequestPriceDO : orderRequestPriceDOS) {
                SupplyProductPriceDTO supplyProductPriceDTO = new SupplyProductPriceDTO();
                supplyProductPriceDTO.setSaleDate(DateUtil.dateToString(orderRequestPriceDO.getSaleDate()));
                supplyProductPriceDTO.setSalePrice(orderRequestPriceDO.getSalePrice());
                BigDecimal basePrice = productDailyPriceMap.get(supplyProductPriceDTO.getSaleDate()).getBasePrice();
                supplyProductPriceDTO.setBasePrice(basePrice == null ? BigDecimal.ZERO : basePrice);
                supplyProductPriceList.add(supplyProductPriceDTO);
            }
            if (orderRequestDO.getRateplanId().equals(oldSupplyProductDO.getRateplanId())) {
                // 3.3.1 如果价格计划没变，修改产品信息
                ChangeProductRequestDTO changeProductRequestDTO = new ChangeProductRequestDTO();
                changeProductRequestDTO.setSupplyProductId(oldSupplyProductDO.getId());
                changeProductRequestDTO.setCheckinDate(DateUtil.dateToString(orderRequestDO.getCheckinDate()));
                changeProductRequestDTO.setCheckoutDate(DateUtil.dateToString(orderRequestDO.getCheckoutDate()));
                changeProductRequestDTO.setRoomNum(orderRequestDO.getRoomNum());
                changeProductRequestDTO.setModifier(requestDTO.getModifier());
                changeProductRequestDTO.setModifyTime(requestDTO.getModifyTime());
                changeProductRequestDTO.setSupplyProductPriceList(supplyProductPriceList);
                supplyOrderService.changeProduct(changeProductRequestDTO);
            } else {
                // 3.3.2 换产品
                // 加产品
                AddProductRequestDTO addProductRequestDTO = new AddProductRequestDTO();
                addProductRequestDTO.setOrderId(orderRequestDO.getOrderId());
                addProductRequestDTO.setRatePlanId(orderRequestDO.getRateplanId());
                addProductRequestDTO.setRoomNum(orderRequestDO.getRoomNum());
                addProductRequestDTO.setCheckinDate(DateUtil.dateToString(orderRequestDO.getCheckinDate()));
                addProductRequestDTO.setCheckoutDate(DateUtil.dateToString(orderRequestDO.getCheckoutDate()));
                addProductRequestDTO.setModifier(requestDTO.getModifier());
                addProductRequestDTO.setModifyTime(requestDTO.getModifyTime());
                addProductRequestDTO.setSupplyProductPriceList(supplyProductPriceList);
                supplyOrderService.addProduct(addProductRequestDTO);
                // 刪产品
                DeleteProductRequestDTO deleteProductRequestDTO = new DeleteProductRequestDTO();
                deleteProductRequestDTO.setSupplyProductId(oldSupplyProductDO.getId());
                deleteProductRequestDTO.setModifier(requestDTO.getModifier());
                deleteProductRequestDTO.setModifyTime(requestDTO.getModifyTime());
                supplyOrderService.deleteProduct(deleteProductRequestDTO);
            }
        }
        // 4. 更新订单确认号
        if (requestDTO.getHandleType() == 3 && StringUtils.isNotEmpty(requestDTO.getConfirmNo())) {
            OrderDO orderUpdate = new OrderDO();
            orderUpdate.setId(requestDTO.getOrderId());
            orderUpdate.setConfirmNo(requestDTO.getConfirmNo());
            orderMapper.updateByPrimaryKeySelective(orderUpdate);
        }
        // 5. 确认或取消订单
        Integer orderStatus = checkResponseDTO.getModel();
        if (orderStatus != null) {
            if (orderStatus == OrderStatusEnum.TRADED.key) {
                OrderConfirmRequestDTO confirmRequestDTO = new OrderConfirmRequestDTO();
                confirmRequestDTO.setOrderId(requestDTO.getOrderId());
                confirmRequestDTO.setConfirmType(1);
                confirmRequestDTO.setModifier(requestDTO.getModifier());
                confirmRequestDTO.setModifyTime(requestDTO.getModifyTime());
                orderService.confirmOrder(confirmRequestDTO);
            } else if (orderStatus == OrderStatusEnum.CANCELED.key) {
                OrderCancelRequestDTO cancelRequestDTO = new OrderCancelRequestDTO();
                cancelRequestDTO.setOrderId(requestDTO.getOrderId());
                cancelRequestDTO.setCancelReason(requestDTO.getRefuseReason());
                cancelRequestDTO.setModifier(requestDTO.getModifier());
                cancelRequestDTO.setModifyTime(requestDTO.getModifyTime());
                orderService.cancelOrder(cancelRequestDTO);
            }
        }
        // 6. 更新订单申请状态
        if (isModifyOrCancelOrder && !CollectionUtils.isEmpty(orderRequestDOS)) {
            if (requestDTO.getHandleType() == 1 || requestDTO.getHandleType() == 2) {
                OrderRequestDO orderRequestUpdate = new OrderRequestDO();
                orderRequestUpdate.setHandleResult(requestDTO.getHandleType().byteValue());
                Example example = new Example(OrderRequestDO.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("orderId", requestDTO.getOrderId());
                criteria.andEqualTo("requestType", requestDTO.getSendType() == 2 ? 2 : 1);
                criteria.andEqualTo("handleResult", 0);
                orderRequestMapper.updateByExampleSelective(orderRequestUpdate, example);
            }
        }
        // 7. 记日志
        OrderOperationLogDO logDO = new OrderOperationLogDO();
        logDO.setOrderId(requestDTO.getOrderId());
        StringBuffer content = new StringBuffer();
        content.append("操作订单，发单类型：" + (requestDTO.getSendType() == 1 ? "预订单" : requestDTO.getSendType() == 2 ?
                "修改单" : "取消单") + "，操作类型：" + (requestDTO.getHandleType() == 1 ? "接受" : requestDTO.getHandleType() == 2 ?
                "拒绝" : requestDTO.getHandleType() == 3 ? "更改确认号" : "申请退订"));
        if (requestDTO.getConfirmType() != null) {
            content.append("，确认方式：" + (requestDTO.getConfirmType() == 1 ? "按入住姓名" : "按确认号"));
        }
        if (requestDTO.getRefuseType() != null) {
            content.append("，拒绝类型：" + (requestDTO.getRefuseType() == 1 ? "满房封首日" : requestDTO.getRefuseType() == 2 ?
                    "满房封订单入住日" : requestDTO.getRefuseType() == 3 ? "房价不对" : "其它原因"));
        }
        if (StringUtils.isNotEmpty(requestDTO.getRefuseReason())) {
            content.append("，拒绝原因：" + (requestDTO.getRefuseReason()));
        }
        if (StringUtils.isNotEmpty(requestDTO.getConfirmNo())) {
            content.append("，确认号：" + (requestDTO.getConfirmNo()));
        }
        if (requestDTO.getRefundAmount() != null) {
            content.append("，退款金额：" + requestDTO.getRefundAmount());
        }
        logDO.setContent(content.toString());
        logDO.setOperator(requestDTO.getCreator());
        logDO.setOperateTime(requestDTO.getModifyTime());
        orderCommonService.addOperationLog(logDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    /**
     * 组装ota订单操作请求对象
     *
     * @param requestDTO
     * @return
     */
    private DltOrderOperateRequestDTO assemblyOtaOrderOperate(OtaOrderOperateRequestDTO requestDTO, List<OrderRequestDO> orderRequestDOS) {
        OrderDO orderDO = orderMapper.selectByPrimaryKey(requestDTO.getOrderId());
        DltOrderOperateRequestDTO operateRequestDTO = new DltOrderOperateRequestDTO();

        operateRequestDTO.setMerchantCode(orderDO.getMerchantCode());
        if (requestDTO.getSendType() == 2 && requestDTO.getHandleType() == 1) {
            operateRequestDTO.setCustomerOrderCode(orderRequestDOS.get(0).getCustomerOrderCode());
        } else {
            operateRequestDTO.setCustomerOrderCode(orderDO.getCustomerOrderCode());
        }
        operateRequestDTO.setOrderState(orderDO.getOrderStatus() == 1 ? "new" : orderDO.getOrderStatus() == 2 ?
                "processing" : orderDO.getOrderStatus() == 3 ? "confirmed" : "canceled");
        operateRequestDTO.setChannelCode(orderDO.getChannelCode());
        operateRequestDTO.setOrderCode(requestDTO.getOrderId());

        /**
         * ctrip开头渠道操作类型：
         * 0-接受（安排）,
         * 1-拒绝,
         * 2-更改确认号,
         * 11-接受取消,
         * 12-拒绝取消,
         * qunar渠道操作类型：
         * 14-有房,
         * 15-无房,
         * 16-申请退订,
         * 17-同意退订,
         * 18-拒绝退订
         */
        Integer operateType = null;
        if (StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.CTRIP.key)
                || StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.CTRIP_B2B.key)
                || StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.CTRIP_CHANNEL_A.key)) {
            if (requestDTO.getSendType() == 1 || requestDTO.getSendType() == 2) {
                if (requestDTO.getHandleType() == 1) {
                    operateType = 0;
                } else if (requestDTO.getHandleType() == 2) {
                    operateType = 1;
                } else if (requestDTO.getHandleType() == 3) {
                    operateType = 2;
                }
            } else if (requestDTO.getSendType() == 3) {
                if (requestDTO.getHandleType() == 1) {
                    operateType = 11;
                } else if (requestDTO.getHandleType() == 2) {
                    operateType = 12;
                }
            }
        } else if (StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.QUNAR.key)
                || StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.QUNAR_B2B.key)
                || StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.QUNAR_NGT.key)
                || StringUtils.equals(orderDO.getChannelCode(), ChannelTypeEnum.QUNAR_USD.key)) {
            if (requestDTO.getSendType() == 1 || requestDTO.getSendType() == 2) {
                if (requestDTO.getHandleType() == 1) {
                    operateType = 14;
                } else if (requestDTO.getHandleType() == 2) {
                    operateType = 15;
                } else if (requestDTO.getHandleType() == 4) {
                    operateType = 16;
                }
            } else if (requestDTO.getSendType() == 3) {
                if (requestDTO.getHandleType() == 1) {
                    operateType = 17;
                } else if (requestDTO.getHandleType() == 2) {
                    operateType = 18;
                }
            }
        }
        operateRequestDTO.setOperateType(operateType);
        operateRequestDTO.setConfirmType(requestDTO.getConfirmType());
        operateRequestDTO.setConfirmNo(requestDTO.getConfirmNo());
        operateRequestDTO.setRefuseType(requestDTO.getRefuseType());
        operateRequestDTO.setRefuseReason(requestDTO.getRefuseReason());
        operateRequestDTO.setRefundAmount(requestDTO.getRefundAmount());
        operateRequestDTO.setOperator(requestDTO.getModifier());
        return operateRequestDTO;
    }
}
