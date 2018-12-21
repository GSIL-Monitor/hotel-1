package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.SaleStatePO;
import com.travel.hotel.product.entity.po.SaleStatePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleStatePOMapper {
    int countByExample(SaleStatePOExample example);

    int deleteByExample(SaleStatePOExample example);

    int deleteByPrimaryKey(Long stateId);

    int insert(SaleStatePO record);

    int insertSelective(SaleStatePO record);

    List<SaleStatePO> selectByExample(SaleStatePOExample example);

    SaleStatePO selectByPrimaryKey(Long stateId);

    int updateByExampleSelective(@Param("record") SaleStatePO record, @Param("example") SaleStatePOExample example);

    int updateByExample(@Param("record") SaleStatePO record, @Param("example") SaleStatePOExample example);

    int updateByPrimaryKeySelective(SaleStatePO record);

    int updateByPrimaryKey(SaleStatePO record);

    List<SaleStatePO> selectByCondition(SaleStatePO saleStatePO);
}