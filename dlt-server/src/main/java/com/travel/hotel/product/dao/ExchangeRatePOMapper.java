package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.ExchangeRatePO;
import com.travel.hotel.product.entity.po.ExchangeRatePOExample;
import com.travel.hotel.product.entity.po.ExchangeRatePOKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExchangeRatePOMapper {
    int countByExample(ExchangeRatePOExample example);

    int deleteByExample(ExchangeRatePOExample example);

    int deleteByPrimaryKey(ExchangeRatePOKey key);

    int insert(ExchangeRatePO record);

    int insertSelective(ExchangeRatePO record);

    List<ExchangeRatePO> selectByExample(ExchangeRatePOExample example);

    List<ExchangeRatePO> selectAll();

    ExchangeRatePO selectByPrimaryKey(ExchangeRatePOKey key);

    int updateByExampleSelective(@Param("record") ExchangeRatePO record, @Param("example") ExchangeRatePOExample example);

    int updateByExample(@Param("record") ExchangeRatePO record, @Param("example") ExchangeRatePOExample example);

    int updateByPrimaryKeySelective(ExchangeRatePO record);

    int updateByPrimaryKey(ExchangeRatePO record);
}