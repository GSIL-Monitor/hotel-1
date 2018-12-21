package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.RoomPO;

import java.util.List;

public interface RoomPOMapper {
    int deleteByPrimaryKey(Long roomTypeId);

    int insert(RoomPO record);

    int insertSelective(RoomPO record);

    RoomPO selectByPrimaryKey(Long roomTypeId);

    int updateByPrimaryKeySelective(RoomPO record);

    int updateByPrimaryKey(RoomPO record);

    List<RoomPO> selectByCondition(RoomPO record);

    List<RoomPO> selectByRoomIds(List<Long> roomIds);

}