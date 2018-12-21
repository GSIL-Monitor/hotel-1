package com.fangcang.ebk.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SupplyHotelResponseDTO implements Serializable {
    private static final long serialVersionUID = -4402047097946015563L;

    /**
     * 酒店列表
     */
    private List<SupplyHotelDTO> hotelList;
}
