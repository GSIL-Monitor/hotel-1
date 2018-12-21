package com.travel.hotel.product.dao;

import com.travel.common.dto.product.request.PricePlanQueryDTO;
import com.travel.hotel.product.entity.PricePlanDTO;
import com.travel.hotel.product.entity.PricePlanRoom;
import com.travel.hotel.product.entity.po.PricePlanPO;

import java.util.List;

public interface PricePlanPOMapper {
    int deleteByPrimaryKey(Long priceplanId);

    int insert(PricePlanPO record);

    int insertSelective(PricePlanPO record);

    PricePlanPO selectByPrimaryKey(Long priceplanId);

    int updateByPrimaryKeySelective(PricePlanPO record);

    int updateByPrimaryKey(PricePlanPO record);
    
    /**
     * 查询价格计划和房型
     * @param pricePlanQuery
     * @return
     */
    public List<PricePlanRoom> queryPricePlanRoomByHotelId(PricePlanQueryDTO pricePlanQuery);

    public List<PricePlanDTO> queryPricePlanList(PricePlanPO pricePlanPO);
}