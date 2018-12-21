package com.fangcang.hotelinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.HotelFacilityDO;
import com.fangcang.hotelinfo.dto.HotelFacilityDTO;

public interface HotelFacilityMapper extends MyMapper<HotelFacilityDO>{
	/**
	 * 新增酒店设施
	 */
	public Integer inserHotelFacility(List<HotelFacilityDO> hotelFacilityDOList);
	/**
	 * 修改酒店设施	
	 */
	public Integer updatHotelFacility(HotelFacilityDO hotelFacilityDO);
	/**
	 * 删除酒店设施
	 */
	public Integer deleteHotelFacByHotelId(@Param("hotelId")Long hotelId);
	
}
