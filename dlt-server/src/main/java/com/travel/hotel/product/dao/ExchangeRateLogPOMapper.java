package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.ExchangeRateLogPO;

import java.util.List;

public interface ExchangeRateLogPOMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(ExchangeRateLogPO record);

    int insertSelective(ExchangeRateLogPO record);

    ExchangeRateLogPO selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(ExchangeRateLogPO record);

    int updateByPrimaryKey(ExchangeRateLogPO record);

    List<ExchangeRateLogPO> selectByCondition(ExchangeRateLogPO record);
}