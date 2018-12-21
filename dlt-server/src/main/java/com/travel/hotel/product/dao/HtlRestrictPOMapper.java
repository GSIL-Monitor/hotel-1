package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.HtlRestrictPO;

import java.util.List;
import java.util.Map;

public interface HtlRestrictPOMapper {
    int deleteByPrimaryKey(Long restrictId);

    int deleteByPricePlanIdAndSaleDate(HtlRestrictPO record);

    int insert(HtlRestrictPO record);

    int insertSelective(HtlRestrictPO record);

    HtlRestrictPO selectByPrimaryKey(Long restrictId);

    List<HtlRestrictPO> selectByCondition(Map paramMap);

    int updateByPrimaryKeySelective(HtlRestrictPO record);

    int updateByPrimaryKey(HtlRestrictPO record);
}