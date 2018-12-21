package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.QuotaAccountPO;

import java.util.List;

public interface QuotaAccountPOMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(QuotaAccountPO record);

    int insertSelective(QuotaAccountPO record);

    QuotaAccountPO selectByPrimaryKey(Long accountId);

    List<QuotaAccountPO> selectByRoomTypeId(Long roomTypeId);

    int updateByPrimaryKeySelective(QuotaAccountPO record);

    int updateByPrimaryKey(QuotaAccountPO record);
}