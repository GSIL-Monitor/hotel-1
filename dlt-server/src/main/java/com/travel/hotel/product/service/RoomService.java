package com.travel.hotel.product.service;

import java.util.List;
import java.util.Map;

import com.travel.common.dto.PaginationDTO;
import com.travel.hotel.product.entity.po.RoomPO;

/**
 *   2018/1/11.
 */
public interface RoomService {
    /**
     * 分页查询房型列表
     * @param queryPO
     * @return
     */
    public PaginationDTO<RoomPO> queryRoomListByCondition(RoomPO queryPO);

    /**
     * 根据酒店ID查询房型列表
     * @param hotelId
     * @return
     */
    public List<RoomPO> queryRoomByHotelId(Long hotelId);
    
    /**
     * key=hotelId
     * value=hotelName
     * @return
     */
    public Map<Long,String> queryHotelMap();

    /**
     * 保存房型
     * @param po
     * @return
     */
    public int addRoom(RoomPO po);

    public RoomPO queryRoomById(Long roomId);

    public int updateRoom(RoomPO po);

    public int delRoom(RoomPO po);

    /**
     * 选择酒店后，房型下拉联想框
     * @param hotelId
     * @return
     */
    public String autoComplete(Long hotelId);

}
