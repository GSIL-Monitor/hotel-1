package com.fangcang.hotelinfo.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.request.CommonUseRequestDTO;


public interface CommonHotelService {
	
	/**
	 * 判断是否常用酒店
	 * 
	 * @param commonUseRequestDTO
	 * @return
	 */
	public ResponseDTO  isCommonUseHotel(CommonUseRequestDTO commonUseRequestDTO);

}
