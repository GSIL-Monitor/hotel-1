package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.HotelPO;

import java.util.List;


public interface HotelPOMapper {
    int deleteByPrimaryKey(HotelPO record);

    int insert(HotelPO record);

    int insertSelective(HotelPO record);

    HotelPO selectByPrimaryKey(Long hotelId);

    int updateByPrimaryKeySelective(HotelPO record);

    int updateByPrimaryKey(HotelPO record);

    /**
     * 根据条件查询酒店
     * @param po
     * @return
     */
    List<HotelPO> selectByCondition(HotelPO po);

}