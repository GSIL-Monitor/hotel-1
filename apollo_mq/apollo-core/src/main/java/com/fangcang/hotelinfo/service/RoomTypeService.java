package com.fangcang.hotelinfo.service;

import java.util.List;

import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.request.DeleteRoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeIdQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeSortDTO;
import com.fangcang.hotelinfo.response.RoomTypeIdRsponseDTO;
import com.fangcang.hotelinfo.response.RoomTypeRsponseDTO;


/**
 * 
 * @author ASUS
 *
 */
public interface RoomTypeService {
	
	/**
	 * 新增或者修改房型
	 * @param roomTypeRequestDTO
	 * @return
	 */
	
	public ResponseDTO saveOrUpdateRoomType(RoomTypeRequestDTO roomTypeRequestDTO);
	
	/**
     *根据酒店Id查询所有房型
     * @param RoomTypeQueryDTO
     * @return
     */
	public  ResponseDTO<RoomTypeRsponseDTO> queryRoomTypeInfoByHotelId(RoomTypeQueryDTO roomTypeQueryDTO);
	
	/**
	 * 根据酒店Id删除房型信息
	 * @param deleteRoomTypeRequestDTO
	 * @return
	 */
	public  ResponseDTO  deleteRoomType(DeleteRoomTypeRequestDTO deleteRoomTypeRequestDTO);

	/**
	 * 组装床型集合
	 */
	public void assembleBedTypeList(RoomTypeDO roomTypeDO, RoomTypeDTO roomTypeDTO);
	
	/**
	 * 设置房型排序
	 */
	public  ResponseDTO setRoomTypeSort(RoomTypeSortDTO roomTypeSortDTO );
	
	/**
	 * 批量添加房型
	 */
	public  ResponseDTO saveRoomTypes(List<RoomTypeDO> roomTypeDOs);
}
