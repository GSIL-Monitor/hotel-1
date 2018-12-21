package com.travel.hotel.order.service;

import com.travel.common.dto.ResultDTO;
import com.travel.common.dto.order.request.CreateManualOrderRequestDTO;
import com.travel.common.dto.order.request.CreateOrderRequestDTO;
import com.travel.common.dto.order.request.QueryOrderRequestDTO;
import com.travel.common.dto.order.response.CancelManualOrderResponseDTO;
import com.travel.common.dto.order.response.CancelOrderResponseDTO;
import com.travel.common.dto.order.response.CreateManualOrderResponseDTO;
import com.travel.common.dto.order.response.QueryOrderStateResponseDTO;

/**
 * 订单后台服务类
 * @author  2018/2/2
 */
public interface HotelManualOrderService {

    ResultDTO<CreateManualOrderResponseDTO> createManualOrder(CreateManualOrderRequestDTO createManualOrderRequestDTO);

    ResultDTO<CancelManualOrderResponseDTO> cancelManualOrder(String orderCode);

    ResultDTO<CreateManualOrderResponseDTO> modifyManualOrder(CreateManualOrderRequestDTO createManualOrderRequestDTO);
}
