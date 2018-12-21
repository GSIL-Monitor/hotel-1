package com.fangcang.hotelinfo.mapper;



import java.util.List;

import com.fangcang.common.MyMapper;
import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.hotelinfo.request.CommonUsedHotelRequestDTO;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.HotelListQueryDTO;
import com.fangcang.hotelinfo.response.HotelInfoResponseDTO;

public interface HotelMapper extends MyMapper<HotelDO>{
	/**
	 * 添加酒店基本信息
	 */
	public Integer insertHotel(HotelDO hotelDO);

	/**
	 * 获得酒店列表
	 * @param hotelListQueryDTO
	 * @return
	 */
	public List<HotelDO> getHotelList(HotelListQueryDTO hotelListQueryDTO);
	
	/**
	 * 查询酒店基本信息
	 */
	public HotelDO queryHotelInfoByHotelId(HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO );
	
	/**
	 * 修改酒店基本信息
	 */
	public Integer updateHotel(HotelDO hotelDO);
	
	/**
	 * 查询商家常用酒店
	 */
	public List<HotelDO> queryCommonUsedHotel(CommonUsedHotelRequestDTO commonUsedHotelRequestDTO);
}
