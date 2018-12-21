package com.fangcang.hotelinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.HotelAdditionalDO;


/**
 * Created by ASUS on 2018/5/22.
 */
public interface HotelAdditionalMapper extends MyMapper<HotelAdditionalDO> {
	/**
	 * 添加酒店附加项信息
	 */
	public Integer inserHotelAdditional(List<HotelAdditionalDO> hotelAdditionalDOList);
	/**
	 * 删除附加项信息
	 */
	public Integer deleteHotelAdditional(HotelAdditionalDO hotelAdditionalDO);
}
