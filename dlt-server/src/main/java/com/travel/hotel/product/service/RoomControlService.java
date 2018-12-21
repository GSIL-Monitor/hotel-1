package com.travel.hotel.product.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.request.DayRoomControlDTO;
import com.travel.common.dto.product.request.RoomControlRequestDTO;
import com.travel.common.dto.product.response.RoomControlResponseDTO;

/**
 *   2018/1/26.
 */
public interface RoomControlService {
    public PaginationDTO<RoomControlResponseDTO> queryRoomControlList(RoomControlRequestDTO requestDTO);

    int saveDayPriceAndQuota(DayRoomControlDTO dayRoomControlDTO);
}
