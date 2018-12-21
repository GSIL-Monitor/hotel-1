package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.ReserveQuotaPO;

import java.util.List;
import java.util.Map;

public interface ReserveQuotaPOMapper {
    int deleteByPrimaryKey(Long reserveId);

    int insert(ReserveQuotaPO record);

    int insertSelective(ReserveQuotaPO record);

    ReserveQuotaPO selectByPrimaryKey(Long reserveId);

    int updateByPrimaryKeySelective(ReserveQuotaPO record);

    int updateByPrimaryKey(ReserveQuotaPO record);

    List<ReserveQuotaPO> selectByCondition(ReserveQuotaPO reserveQuotaPO);

    List<ReserveQuotaPO> selectDaySumByPricePlan(Map param);

    int updateStatusToReserving(List<ReserveQuotaPO> list);


}