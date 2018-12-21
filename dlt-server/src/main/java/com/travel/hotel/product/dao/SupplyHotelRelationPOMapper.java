package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.SupplyHotelRelationPO;

import java.util.List;

public interface SupplyHotelRelationPOMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(SupplyHotelRelationPO record);

    int insertSelective(SupplyHotelRelationPO record);

    SupplyHotelRelationPO selectByPrimaryKey(Long relationId);

    List<SupplyHotelRelationPO> selectByCondition(SupplyHotelRelationPO po);

    int updateByPrimaryKeySelective(SupplyHotelRelationPO record);

    int updateByPrimaryKey(SupplyHotelRelationPO record);

//    SupplyHotelRelationPO selectByCondition(SupplyHotelRelationPO record);
}