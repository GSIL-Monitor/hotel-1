package com.fangcang.order.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.order.dto.OrderDTO;
import com.fangcang.order.dto.OrderMessageRedisCacheDTO;
import com.fangcang.order.dto.OrderRequestDTO;
import com.fangcang.order.dto.SupplyOrderCofirmMsgDTO;
import com.fangcang.order.request.AddAbatementOrderDTO;
import com.fangcang.order.request.AddAttachRequestDTO;
import com.fangcang.order.request.AddNoteRequestDTO;
import com.fangcang.order.request.AddOrderReqRequestDTO;
import com.fangcang.order.request.ChangeAgentRequestDTO;
import com.fangcang.order.request.ChangeBalanceMethodRequestDTO;
import com.fangcang.order.request.ChangeChannelOrderCodeRequestDTO;
import com.fangcang.order.request.ChangeGuestRequestDTO;
import com.fangcang.order.request.ChangeGuideRequestDTO;
import com.fangcang.order.request.ChangeSpecialReqeustDTO;
import com.fangcang.order.request.CountsRequestDTO;
import com.fangcang.order.request.CreateOrderRequestDTO;
import com.fangcang.order.request.DeleteAttachRequestDTO;
import com.fangcang.order.request.DeleteOrderMessageRequestDTO;
import com.fangcang.order.request.DeleteSupplyOrderCofirmMsgRequestDTO;
import com.fangcang.order.request.HandleOrderReqRequestDTO;
import com.fangcang.order.request.LockOrderRequestDTO;
import com.fangcang.order.request.NotifyAgentRequestDTO;
import com.fangcang.order.request.OrderCancelRequestDTO;
import com.fangcang.order.request.OrderConfirmRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.OrderListRequestDTO;
import com.fangcang.order.request.UpdateOrderExceptionAmountDTO;
import com.fangcang.order.response.OrderCountResponseDTO;
import com.fangcang.order.response.OrderStatisticsDTO;
import com.fangcang.order.response.QueryChannelListResponseDTO;

import java.util.List;

/**
 * 订单服务
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
public interface OrderService {

    /**
     * 订单列表
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<OrderDTO> orderList(OrderListRequestDTO requestDTO);

    /**
     * 查询订单统计信息
     * @param merchantCode
     * @return
     */
    OrderStatisticsDTO queryOrderStatistics(String merchantCode,String operator);

    /**
     * 设置订单异常金额
     * @return
     */
    ResponseDTO updateOrderExceptionAmount(UpdateOrderExceptionAmountDTO request);

    /**
     * 确认订单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO confirmOrder(OrderConfirmRequestDTO requestDTO);

    /**
     * 取消订单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO cancelOrder(OrderCancelRequestDTO requestDTO);

    /**
     * 修改分销商
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeAgent(ChangeAgentRequestDTO requestDTO);

    /**
     * 修改渠道单号
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeChannelOrderCode(ChangeChannelOrderCodeRequestDTO requestDTO);

    /**
     * 修改结算方式
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeBalanceMethod(ChangeBalanceMethodRequestDTO requestDTO);

    /**
     * 修改入住人
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeGuest(ChangeGuestRequestDTO requestDTO);

    /**
     * 修改特殊要求
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeSpecialRequest(ChangeSpecialReqeustDTO requestDTO);

    /**
     * 修改团号及向导信息
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeGuide(ChangeGuideRequestDTO requestDTO);

    /**
     * 试预定
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO preBooking(CreateOrderRequestDTO requestDTO);

    /**
     * 下单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO create(CreateOrderRequestDTO requestDTO);

    /**
     * 下手工单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addManualOrder(CreateOrderRequestDTO requestDTO);

    /**
     * 创建核销单
     */
    public ResponseDTO addAbatementOrder(AddAbatementOrderDTO requestDTO);

    /**
     * 处理订单申请
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO handleOrderRequest(HandleOrderReqRequestDTO requestDTO);

    /**
     * 查询订单申请通知
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<OrderRequestDTO>> queryOrderRequestList(OrderDetailRequestDTO requestDTO);

    /**
     * 查询渠道列表接口
     *
     * @return
     */
    ResponseDTO<List<QueryChannelListResponseDTO>> queryChannelList();

    /**
     * 催客人
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO notifyAgent(NotifyAgentRequestDTO requestDTO);

    /**
     * 统计订单数
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<OrderCountResponseDTO>> counts(CountsRequestDTO requestDTO);

    /**
     * 添加附件
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addAttach(AddAttachRequestDTO requestDTO);

    /**
     * 删除附件
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO deleteAttach(DeleteAttachRequestDTO requestDTO);

    /**
     * 添加备注
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addNote(AddNoteRequestDTO requestDTO);

    /**
     * 接单
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO handleOrder(OrderDetailRequestDTO requestDTO);


    /**
     * 加解锁
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO lockOrder(LockOrderRequestDTO requestDTO);

    /**
     * 添加订单申请
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addOrderRequest(AddOrderReqRequestDTO requestDTO);

    /**
     * 查询商家Redis缓存的新单、修改申请、取消申请
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
