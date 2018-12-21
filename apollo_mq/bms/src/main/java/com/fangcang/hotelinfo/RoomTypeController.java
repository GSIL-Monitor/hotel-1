package com.fangcang.hotelinfo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.dto.BedTypeDTO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.request.DeleteRoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeIdQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeSortDTO;
import com.fangcang.hotelinfo.response.RoomTypeIdRsponseDTO;
import com.fangcang.hotelinfo.response.RoomTypeRsponseDTO;
import com.fangcang.hotelinfo.service.RoomTypeService;
import com.fangcang.merchant.dto.UserDTO;

/**
 * 
 * @author ASUS
 *
 */

@Slf4j
@RestController
@RequestMapping("/hotelinfo")
public class RoomTypeController extends BaseController {

	@Autowired
	private RoomTypeService roomTypeService;

	/**
	 * 增加或修改房型
	 * 
	 * @param roomTypeRequestDTO
	 * @return
	 */

	@RequestMapping(value = "/saveOrUpdateRoomType", method = RequestMethod.POST, produces = {
			" application/json;charset=UTF-8" })
	public ResponseDTO saveOrUpdateRoomType(@RequestBody RoomTypeRequestDTO roomTypeRequestDTO) {
		log.debug("enter 保存房型：{}", JSON.toJSONString(roomTypeRequestDTO));
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			roomTypeRequestDTO.setCreator(super.getFullName());
			roomTypeRequestDTO.setModifier(super.getFullName());
			responseDTO = roomTypeService.saveOrUpdateRoomType(roomTypeRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 根据酒店ID查询房型信息
	 * 
	 * @param roomTypeQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/queryRoomTypeInfoByHotelId", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseDTO<RoomTypeRsponseDTO> queryRoomTypeInfoByHotelId(
			@RequestBody @Valid RoomTypeQueryDTO roomTypeQueryDTO) {
		return roomTypeService.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
	}

	/**
	 * 根据房型ID查询房型信息
	 * 
	 * @param roomTypeIdQueryDTO
	 * @return
	 */
	@RequestMapping(value = "/queryRoomTypeInfoById", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseDTO<RoomTypeIdRsponseDTO> queryRoomTypeInfoById(
			@RequestBody @Valid RoomTypeIdQueryDTO roomTypeIdQueryDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
		roomTypeQueryDTO.setRoomTypeId(roomTypeIdQueryDTO.getRoomTypeId());
		ResponseDTO<RoomTypeRsponseDTO> roomTypeIdRsponseDTOList = roomTypeService
				.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
		if (null != roomTypeIdRsponseDTOList && ResultCodeEnum.SUCCESS.code == roomTypeIdRsponseDTOList.getResult()) {
			RoomTypeRsponseDTO roomTypeRsponseDTO = roomTypeIdRsponseDTOList.getModel();
			if (null != roomTypeRsponseDTO && !CollectionUtils.isEmpty(roomTypeRsponseDTO.getRoomTypeList())) {
				RoomTypeDTO roomTypeDTO = roomTypeRsponseDTO.getRoomTypeList().get(0);
				RoomTypeIdRsponseDTO roomTypeIdRsponseDTO = null;
				roomTypeIdRsponseDTO = PropertyCopyUtil.transfer(roomTypeDTO, RoomTypeIdRsponseDTO.class);
				responseDTO.setModel(roomTypeIdRsponseDTO);
			}
		}
		return responseDTO;
	}

	/**
	 * 根据房型Id删除房型
	 * 
	 * @param deleteRoomTypeRequestDTO
	 * @return
	 */

	@RequestMapping(value = "/deleteRoomType", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseDTO deleteRoomType(@RequestBody @Valid DeleteRoomTypeRequestDTO deleteRoomTypeRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			UserDTO userDTO = super.getCacheUser();
			deleteRoomTypeRequestDTO.setCreator(super.getFullName());
			deleteRoomTypeRequestDTO.setMerchantCode(userDTO.getMerchantCode());
			responseDTO = roomTypeService.deleteRoomType(deleteRoomTypeRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 设置房型排序
	 *
	 * @param roomTypeRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/setRoomTypeSort", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseDTO setRoomTypeSort(@RequestBody @Valid RoomTypeSortDTO roomTypeSortDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			UserDTO userDTO = super.getCacheUser();
			userDTO.setModifier(super.getFullName());
			responseDTO = roomTypeService.setRoomTypeSort(roomTypeSortDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
     /**
      * 批量添加房型
      * @return
      */
	@RequestMapping(value = "/saveRoomTypes(", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public ResponseDTO saveRoomTypes(@RequestBody @Valid RoomTypeRsponseDTO roomTypeRsponseDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<RoomTypeDTO> roomTypeList = roomTypeRsponseDTO.getRoomTypeList();
		List<RoomTypeDO> roomTypeDOs = null;
		roomTypeDOs = PropertyCopyUtil.transferArray(roomTypeList, RoomTypeDO.class );
		try {
			UserDTO userDTO = super.getCacheUser();
			userDTO.setModifier(super.getFullName());
			
			responseDTO = roomTypeService.saveRoomTypes(roomTypeDOs);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
}
