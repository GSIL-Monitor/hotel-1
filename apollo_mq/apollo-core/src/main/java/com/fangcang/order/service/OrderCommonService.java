package com.fangcang.order.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.order.domain.OrderDO;
import com.fangcang.order.domain.OrderOperationLogDO;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.dto.OrderCheckDetailDTO;
import com.fangcang.order.dto.OrderMessageRedisCacheDTO;
import com.fangcang.order.dto.SupplyOrderCofirmMsgDTO;
import com.fangcang.order.dto.SupplyProductPriceDTO;
import com.fangcang.order.request.DeleteOrderMessageRequestDTO;
import com.fangcang.order.request.DeleteSupplyOrderCofirmMsgRequestDTO;
import com.fangcang.order.request.OrderCreditPayOrRefundRequestDTO;
import com.fangcang.order.request.OrderDebuctOrRetreatQuotaRequestDTO;
import com.fangcang.order.request.SupplyCreditPayOrReceiptRequestDTO;
import com.fangcang.order.response.AssemblyCreateOrderResponseDTO;
import com.fangcang.order.response.DeratePolicyResponseDTO;
import com.fangcang.order.response.OrderRedundancyInfoResponseDTO;
import com.fangcang.order.response.SupplyOrderPriceSumResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;
import com.fangcang.product.dto.ProductDailyDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 订单内部公共服务， 不对外开放
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
public interface OrderCommonService {

    /**
     * 获取订单冗余的字段信息
     *
     * @param orderId
     * @param supplyOrderDOS
     * @return
     */
    OrderRedundancyInfoResponseDTO getOrderRedundancyInfo(Integer orderId, List<SupplyOrderDO> supplyOrderDOS);

    /**
     * 获取产品每日价格map对象
     *
     * @param channelCode
     * @param rateplanId
     * @param checkinDate
     * @param checkoutDate
     * @param roomNum
     * @param isGroupRoom
     * @return
     */
    Map<String, SupplyProductPriceResponseDTO> getProductDailyPriceMap(String channelCode, Integer rateplanId, String checkinDate, String checkoutDate, Integer roomNum, Integer isGroupRoom);


    /**
     * 根据渠道编码获取产品对应渠道售价、底价
     *
     * @param channelCode
     * @param productDailyDTO
     * @param roomNum
     * @param isGroupRoom
     * @return
     */
    SupplyProductPriceDTO getSalePriceByChannelCode(String channelCode, ProductDailyDTO productDailyDTO, Integer roomNum, Integer isGroupRoom);

    /**
     * 下单
     *
     * @param assemblyOrderData
     * @return
     */
    Integer booking(AssemblyCreateOrderResponseDTO assemblyOrderData);

    /**
     * 异步扣散房配额，团房不扣配额
     *
     * @param requestDTO
     * @return
     */
    Future<ResponseDTO> debuctOrRetreatQuota(OrderDebuctOrRetreatQuotaRequestDTO requestDTO);

    /**
     * 根据每日价格， 推演对账明细
     *
     * @param roomNum
     * @param dayList
     * @return
     */
    List<OrderCheckDetailDTO> assemblyCheckDetail(Integer roomNum, List<SupplyProductPriceDTO> dayList);

    /**
     * 异步添加订单操作日志
     *
     * @param logDO
     */
    void addOperationLog(OrderOperationLogDO logDO);

    /**
     * 散房订单扣还供货单配额
     *
     * @param supplyStatus
     * @param supplyOrderDO
     * @param orderDO
     * @param operator
     */
    void debuctOrRetreatSupplyOrderQuota(Integer supplyStatus, SupplyOrderDO supplyOrderDO, OrderDO orderDO, String operator);

    /**
     * 供货单状态变化，重新计算供货单金额
     *
     * @param supplyOrderId
     * @param supplyStatusRequest
     * @return
     */
    SupplyOrderPriceSumResponseDTO calcSupplyOrderPriceSum(Integer supplyOrderId, Integer supplyStatusRequest);

    /**
     * 查供货单下所有减免政策
     *
     * @param supplyOrderId
     * @return
     */
    List<DeratePolicyResponseDTO> queryDeratePolicyList(Integer supplyOrderId);

    /**
     * 计算订单利润
     *
     * @param orderId
     * @param receivableAmount
     * @param supplyOrderDOS
     * @return
     */
    BigDecimal calcProfit(Integer orderId, BigDecimal receivableAmount, List<SupplyOrderDO> supplyOrderDOS);


    /**
     * 更新订单财务信息（单结订单）：支付状态、结算状态
     *
     * @param orderId
     * @param receivableAmount
     * @param settlementAmount
     * @param refundAmount
     * @return
     */
    ResponseDTO updateOrderFinanceInfo(Integer orderId, BigDecimal receivableAmount, BigDecimal settlementAmount, BigDecimal refundAmount);

    /**
     * 更新供货单财务信息（单结供货单）：支付状态、结算状态
     *
     * @param supplyOrderId
     * @param payableAmount
     * @param settlementAmount
     * @param receiptAmount
     * @return
     */
    ResponseDTO updateSupplyFinanceInfo(Integer supplyOrderId, BigDecimal payableAmount, BigDecimal settlementAmount, BigDecimal receiptAmount);

    /**
     * 非单结订单，挂账或退挂账
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO orderCreditPayOrRefund(OrderCreditPayOrRefundRequestDTO requestDTO);

    /**
     * 非单结供货单，挂账或退挂账
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO supplyCreditPayOrRefund(SupplyCreditPayOrReceiptRequestDTO requestDTO);

    /**
     * 缓存新单、修改申请、取消申请到Redis缓存
     *
     * @param requestDTO
     * @return
     */
    void saveOrderMessageToRedis(OrderMessageRedisCacheDTO requestDTO);

    /**
     * 查询指定商家Redis缓存的新单、修改申请、取消申请
     *
     * @param merchantCode
     * @return
     */
    ResponseDTO<List<OrderMessageRedisCacheDTO>> queryOrderMessageFromRedis(String merchantCode);

    /**
     * 删除订单缓存
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO deleteOrderMessageFromRedis(DeleteOrderMessageRequestDTO requestDTO);

    /**
     * 保存EBK确认订单的消息
     * @param supplyOrderCofirmMsgDTO
     * @return
     */
    void saveSupplyOrderCofirmMsgToRedis(SupplyOrderCofirmMsgDTO supplyOrderCofirmMsgDTO);

    ResponseDTO<List<SupplyOrderCofirmMsgDTO>> querySupplyOrderCofirmMsgFromRedis(String merchantCode);

    ResponseDTO deleteSupplyOrderCofirmMsgFromRedis(DeleteSupplyOrderCofirmMsgRequestDTO deleteSupplyOrderCofirmMsgRequestDTO);
}

