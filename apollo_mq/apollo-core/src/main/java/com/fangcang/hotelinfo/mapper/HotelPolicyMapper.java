package com.fangcang.hotelinfo.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.HotelPolicyDO;

public interface HotelPolicyMapper extends MyMapper<HotelPolicyDO>{
	/**
	 * 新增酒店政策
	 */
	public Integer insertHotelPolicy(HotelPolicyDO hotelPolicyDO);
	/**
	 * 修改酒店政策
	 */
	public Integer updateHotelPolicy(HotelPolicyDO hotelPolicyDO);
}
