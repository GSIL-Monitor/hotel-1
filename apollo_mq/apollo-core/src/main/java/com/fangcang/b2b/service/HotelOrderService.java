package com.fangcang.b2b.service;

import com.fangcang.agent.dto.AgentUserDTO;
import com.fangcang.b2b.request.ApplyCancelOrderRequestDTO;
import com.fangcang.b2b.request.BookOrderRequestDTO;
import com.fangcang.b2b.request.HotelChangeGuideRequestDTO;
import com.fangcang.b2b.request.HotelOrderDetailRequestDTO;
import com.fangcang.b2b.request.HotelOrderListRequestDTO;
import com.fangcang.b2b.request.PreBookRequestDTO;
import com.fangcang.b2b.response.BookOrderResponseDTO;
import com.fangcang.b2b.response.GetOfflinePayResponseDTO;
import com.fangcang.b2b.response.HotelOrderDetailResponseDTO;
import com.fangcang.b2b.response.OrderListResponseDTO;
import com.fangcang.b2b.response.PreBookResponseDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;

/**
 * Created by ASUS on 2018/7/4.
 */
public interface HotelOrderService {

    /**
     * 试预订
     *
     * @param preBookRequestDTO
     * @return
     */
    public ResponseDTO<PreBookResponseDTO> prebook(PreBookRequestDTO preBookRequestDTO, AgentUserDTO agentUserDTO);

    /**
     * 下单
     * @param bookOrderRequestDTO
     * @param agentUserDTO
     * @return
     */
    public ResponseDTO<BookOrderResponseDTO> book(BookOrderRequestDTO bookOrderRequestDTO, AgentUserDTO agentUserDTO);

    /**
     * 获取线下支付
     *
     * @param merchantId
     * @return
     */
    public ResponseDTO<GetOfflinePayResponseDTO> getOfflinePay(Long merchantId);

    /**
     * 申请取消订单
     * @param applyCancelOrderRequestDTO
     * @return
     */
    public ResponseDTO applyCancelOrder(ApplyCancelOrderRequestDTO applyCancelOrderRequestDTO);

    /**
     *订单列表
     * @param orderListRequestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<OrderListResponseDTO>> queryOrderList(HotelOrderListRequestDTO orderListRequestDTO,AgentUserDTO agentUserDTO);

    /**
     * 查询订单详情
     * @param hotelOrderDetailRequestDTO
     * @param agentUserDTO
     * @return
     */
    public ResponseDTO<HotelOrderDetailResponseDTO> queryOrderDetail(HotelOrderDetailRequestDTO hotelOrderDetailRequestDTO, AgentUserDTO agentUserDTO);

    /**
     * 录入导游
     * @param hotelChangeGuideRequestDTO
     * @return
     */
    public ResponseDTO changeGuide(HotelChangeGuideRequestDTO hotelChangeGuideRequestDTO);
}
