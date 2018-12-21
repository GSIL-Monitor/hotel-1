package com.travel.hotel.dlt.service;


import com.travel.channel.dto.request.HotelOrderOperateRequestDTO;
import com.travel.channel.dto.response.HotelOrderOperateResponseDTO;

/**
 *   2018/2/28.
 */
public interface DltHotelOrderOperateService {

    HotelOrderOperateResponseDTO operateOrder(HotelOrderOperateRequestDTO requestDTO);
}
