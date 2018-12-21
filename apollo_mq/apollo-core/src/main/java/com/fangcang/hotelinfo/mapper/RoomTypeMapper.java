package com.fangcang.hotelinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.request.DeleteRoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeIdQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;

/**
 * 
 * @author ASUS
 */

public interface RoomTypeMapper extends MyMapper<RoomTypeDO> {
	/**
	 * 添加房型
	 * @param roomTypeDO
	 * @return
	 */
	public  Integer insertRoomType(RoomTypeDO roomTypeDO);
	
	/**
	 * 修改房型
	 * @param roomTypeDO
	 * @return
	 */
	 public  Integer updateRoomType(RoomTypeDO roomTypeDO);
	
	/**
	 * 根据酒店id查询所有房型
	 * @param roomTypeQueryDTO
	 * @return
	 */
	
	public List<RoomTypeDO> queryRoomTypeInfoByHotelId(RoomTypeQueryDTO roomTypeQueryDTO);
	
	/**
	 * 根据房型Id查询房型信息
	 * @param roomTypeIdQueryDTO
	 * @return
	 */
    public  RoomTypeDO queryRoomTypeInfoById(Long roomTypeId);
    
    /**
     * 删除房型
     * @param deleteRoomTypeRequestDTO
     * @return
     */
    public  Integer  deleteRoomType(DeleteRoomTypeRequestDTO deleteRoomTypeRequestDTO);
    
    /**
     * 批量添加房型
     * @param roomTypeDOs
     * @return
     */
    public void insertRoomTypes(List<RoomTypeDO> roomTypeDOs);
}
