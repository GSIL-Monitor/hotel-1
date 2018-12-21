package com.fangcang.hotelinfo.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.hotelinfo.dto.SimpleHotelDTO;
import lombok.Data;

@Data
public class CommonUsedHotelResponseDTO implements Serializable{
	 
	private static final long serialVersionUID = -4402047097946015563L;

	/**
	 * 酒店列表
	 */
	private List<SimpleHotelDTO> hotelList;
	
}
