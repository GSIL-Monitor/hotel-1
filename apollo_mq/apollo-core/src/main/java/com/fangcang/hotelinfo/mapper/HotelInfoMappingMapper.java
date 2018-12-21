package com.fangcang.hotelinfo.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.HotelMappingDO;

public interface HotelInfoMappingMapper extends MyMapper<HotelMappingDO> {

	/**
	 * 新增酒店映射
	 * @param hotelMappingDO
	 */
	public void insertHotelMapping(HotelMappingDO hotelMappingDO);

}
