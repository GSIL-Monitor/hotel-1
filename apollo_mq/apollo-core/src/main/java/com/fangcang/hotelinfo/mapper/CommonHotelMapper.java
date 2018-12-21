package com.fangcang.hotelinfo.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.CommonHotelDO;
import com.fangcang.hotelinfo.request.CommonUsedHotelRequestDTO;

public interface CommonHotelMapper extends MyMapper<CommonHotelDO> {
	
	/**
	 * 添加常用酒店
	 * @param commonHotelDO
	 * @return
	 */
	public Integer insertCommonHotel(CommonHotelDO commonHotelDO);
	
	
	/**
	 * 删除常用酒店
	 * @param
	 * @return
	 */
	public Integer  deleteCommonHotel(CommonHotelDO commonHotelDO);
	

}
