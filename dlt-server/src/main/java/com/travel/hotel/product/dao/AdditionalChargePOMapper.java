package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.AdditionalChargePO;

import java.util.List;

public interface AdditionalChargePOMapper {
    int deleteByPrimaryKey(Long chargeId);

    int insert(AdditionalChargePO record);

    int insertSelective(AdditionalChargePO record);

    AdditionalChargePO selectByPrimaryKey(Long chargeId);

    int updateByPrimaryKeySelective(AdditionalChargePO record);

    int updateByPrimaryKey(AdditionalChargePO record);
    
    /**
     * 根据酒店id查询附加费
     * @param hotelId
     * @return
     */
    List<AdditionalChargePO> queryAdditionalChargeByHotelId(Long hotelId);
}