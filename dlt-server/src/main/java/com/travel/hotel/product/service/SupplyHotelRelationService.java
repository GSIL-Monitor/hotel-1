package com.travel.hotel.product.service;

import com.travel.hotel.product.entity.po.SupplyHotelRelationPO;

import java.util.List;

/**
 *   2018/1/10.
 */
public interface SupplyHotelRelationService {

    public int addRelation(SupplyHotelRelationPO po);

    public List<SupplyHotelRelationPO> queryByCondition(SupplyHotelRelationPO po);

    public SupplyHotelRelationPO queryByHotelIdAndSupplyCode(Long hotelId, String supplyCode);

    int updateByPrimaryKeySelective(SupplyHotelRelationPO record);

    SupplyHotelRelationPO selectByRelationId(Long relationId);
}
