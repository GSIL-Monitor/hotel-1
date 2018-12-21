package com.travel.hotel.order.service;

import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.order.request.CancelOrderRequestDTO;
import com.travel.common.dto.order.request.CreateOrderRequestDTO;
import com.travel.common.dto.order.request.PreBookingRequestDTO;
import com.travel.common.dto.order.request.QueryOrderRequestDTO;
import com.travel.common.dto.order.response.CancelOrderResponseDTO;
import com.travel.common.dto.order.response.CreateOrderResponseDTO;
import com.travel.common.dto.order.response.PreBookingResponseDTO;
import com.travel.common.dto.order.response.QueryOrderStateResponseDTO;

/**
 * 订单后台服务类
 * @author  2018/1/10
 */
public interface HotelOrderService {

    ResultDTO<CreateOrderResponseDTO> createOrder(CreateOrderRequestDTO createOrderRequestDTO);

    ResultDTO<QueryOrderStateResponseDTO> queryOrderState(QueryOrderRequestDTO queryOrderRequestDTO);

    ResultDTO<CancelOrderResponseDTO> cancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO);

    ResultDTO<CreateOrderResponseDTO> modifyOrder(CreateOrderRequestDTO createOrderRequestDTO);
}
