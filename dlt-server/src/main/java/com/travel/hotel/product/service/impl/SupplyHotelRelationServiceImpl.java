package com.travel.hotel.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.hotel.product.dao.SupplyHotelRelationPOMapper;
import com.travel.hotel.product.entity.po.SupplyHotelRelationPO;
import com.travel.hotel.product.service.SupplyHotelRelationService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 *   2018/1/10.
 */
@Service("supplyHotelRelationService")
public class SupplyHotelRelationServiceImpl implements SupplyHotelRelationService {

    @Autowired
    private SupplyHotelRelationPOMapper supplyHotelRelationPOMapper;

    @Override
    public int addRelation(SupplyHotelRelationPO po) {
        return supplyHotelRelationPOMapper.insert(po);
    }

    @Override
    public List<SupplyHotelRelationPO> queryByCondition(SupplyHotelRelationPO po) {
        return supplyHotelRelationPOMapper.selectByCondition(po);
    }

    @Override
    public SupplyHotelRelationPO queryByHotelIdAndSupplyCode(Long hotelId, String supplyCode) {
        SupplyHotelRelationPO queryPO = new SupplyHotelRelationPO();
        queryPO.setHotelId(hotelId);
        queryPO.setSupplyCode(supplyCode);
        List<SupplyHotelRelationPO> supplyHotelRelationPOList = this.queryByCondition(queryPO);
        if (CollectionUtils.isEmpty(supplyHotelRelationPOList)){
            return null;
        }
        return supplyHotelRelationPOList.get(0);
    }

    @Override
    public int updateByPrimaryKeySelective(SupplyHotelRelationPO record) {
        return supplyHotelRelationPOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SupplyHotelRelationPO selectByRelationId(Long relationId) {
        return supplyHotelRelationPOMapper.selectByPrimaryKey(relationId);
    }
}
