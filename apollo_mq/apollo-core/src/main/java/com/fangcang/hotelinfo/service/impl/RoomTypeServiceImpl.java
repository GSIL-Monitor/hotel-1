package com.fangcang.hotelinfo.service.impl;

import com.fangcang.common.IncrementDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.BedTypeEnum;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ImageTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.URLSplitUtil;
import com.fangcang.hotelinfo.domain.ImageDO;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.dto.BedTypeDTO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import com.fangcang.hotelinfo.mapper.ImageMapper;
import com.fangcang.hotelinfo.mapper.RoomTypeMapper;
import com.fangcang.hotelinfo.request.DeleteRoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.RoomTypeSortDTO;
import com.fangcang.hotelinfo.response.RoomTypeRsponseDTO;
import com.fangcang.hotelinfo.service.RoomTypeService;
import com.fangcang.product.service.IncrementService;
import com.fangcang.product.thread.IncrementThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/22.
 */
@Service
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeMapper roomTypeMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Resource(name = "incrementExecutor")
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	private IncrementConfig incrementConfig;

	@Autowired
	private IncrementService incrementService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO saveOrUpdateRoomType(RoomTypeRequestDTO roomTypeRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			// 房型基本信息
			RoomTypeDO roomTypeDO = null;
			roomTypeDO = PropertyCopyUtil.transfer(roomTypeRequestDTO, RoomTypeDO.class);
			roomTypeDO.setIsActive(1);
			if (!CollectionUtils.isEmpty(roomTypeRequestDTO.getBedTypeList())) {
				StringBuilder stringBuilder = new StringBuilder();
				StringBuilder stringBuilderBed = new StringBuilder();
				for (BedTypeDTO bedTypeDTO : roomTypeRequestDTO.getBedTypeList()) {
					stringBuilder.append(BedTypeEnum.getValueByKey(bedTypeDTO.getBedType())).append("(")
							.append(bedTypeDTO.getLength()).append("m").append("*").append(bedTypeDTO.getWide())
							.append("m").append(";").append(bedTypeDTO.getNum()).append("张").append(")").append(",");
					stringBuilderBed.append(bedTypeDTO.getBedType()).append(",");
				}
				String bedDescription = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
				String bedType = stringBuilderBed.toString().substring(0, stringBuilderBed.toString().length() - 1);
				roomTypeDO.setBedDescription(bedDescription);
				roomTypeDO.setBedType(bedType);
			}
			if (null == roomTypeRequestDTO.getSort()) {
				roomTypeDO.setSort(99);
			}
			// 房型主图
			ImageDO imageDO = new ImageDO();
			imageDO.setImageId(roomTypeRequestDTO.getImageId());
			imageDO.setImageUrl(roomTypeRequestDTO.getImageUrl());
			imageDO.setRealPath(roomTypeRequestDTO.getRealPath());
			imageDO.setHotelId(roomTypeRequestDTO.getHotelId());
			imageDO.setIsMainImage(1);
			imageDO.setImageType(ImageTypeEnum.ROOMVIEW.key);
			imageDO.setCreator(roomTypeRequestDTO.getCreator());
			imageDO.setModifier(roomTypeRequestDTO.getModifier());
			if (null == roomTypeRequestDTO.getRoomTypeId()) {

				roomTypeMapper.insertRoomType(roomTypeDO);
				imageDO.setExtId(roomTypeDO.getRoomTypeId());

				imageMapper.insertHotelImage(imageDO);
			} else if (null != roomTypeRequestDTO.getRoomTypeId()) {
				// 修改房型基本信息
				roomTypeMapper.updateRoomType(roomTypeDO);
				// 修改房型图片主图和真实路径
				if (null != imageDO && imageDO.getImageId() != null) {
					imageMapper.updateHotelImage(imageDO);
				}else if(null != imageDO && imageDO.getImageId() == null){
					imageDO.setExtId(roomTypeRequestDTO.getRoomTypeId());
					imageMapper.insertHotelImage(imageDO);
				}
			}
			responseDTO.setModel(roomTypeDO.getRoomTypeId());
			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
		} catch (Exception e) {
			log.error("saveOrUpdateRoomType has error", e);
			throw new ServiceException("saveOrUpdateRoomType has error", e);
		}
		return responseDTO;
	}

	/**
	 * 根据酒店ID查房型
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ResponseDTO<RoomTypeRsponseDTO> queryRoomTypeInfoByHotelId(RoomTypeQueryDTO roomTypeQueryDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			if (null == roomTypeQueryDTO
					|| (null == roomTypeQueryDTO.getHotelId() && null == roomTypeQueryDTO.getRoomTypeId())) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
				return responseDTO;
			}
			RoomTypeRsponseDTO roomTypeRsponseDTO = new RoomTypeRsponseDTO();
			roomTypeQueryDTO.setIsActive(1);
			List<RoomTypeDO> roomTypeDOs = roomTypeMapper.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
			RoomTypeDTO roomTypeDTO = null;
			if (!CollectionUtils.isEmpty(roomTypeDOs)) {
				List<RoomTypeDTO> roomTypeList = new ArrayList<RoomTypeDTO>();
				for (RoomTypeDO roomTypeDO : roomTypeDOs) {
					roomTypeDTO = PropertyCopyUtil.transfer(roomTypeDO, RoomTypeDTO.class);
					if (null != roomTypeDO && null != roomTypeDO.getImagedo()) {
						roomTypeDTO.setImageUrl(roomTypeDO.getImagedo().getImageUrl());
						roomTypeDTO.setRealPath(roomTypeDO.getImagedo().getRealPath());
						roomTypeDTO.setImageId(roomTypeDO.getImagedo().getImageId());
					}
					// 单床(1.8m*2m;1张),单床(1.8m*2m;1张)
					assembleBedTypeList(roomTypeDO, roomTypeDTO);
					roomTypeList.add(roomTypeDTO);
				}
				roomTypeRsponseDTO.setRoomTypeList(roomTypeList);
				responseDTO.setModel(roomTypeRsponseDTO);
			}
			return responseDTO;
		} catch (Exception e) {
			log.error("queryRoomTypeInfoByHotelId", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	public void assembleBedTypeList(RoomTypeDO roomTypeDO, RoomTypeDTO roomTypeDTO) {

		List<BedTypeDTO> bedTypeDTOS = new ArrayList<>();

		// 单床(1.8m*2m;1张),单床(1.8m*2m;1张)
		if (null != roomTypeDO && null != roomTypeDTO && StringUtil.isValidString(roomTypeDO.getBedDescription())) {

			String[] bedDescriptionArray = roomTypeDO.getBedDescription().split(",");
			BedTypeDTO bedTypeDTO = null;
			for (String bedDescription : bedDescriptionArray) {
				bedTypeDTO = new BedTypeDTO();
				String bedTypeName = bedDescription.substring(0, bedDescription.indexOf("("));
				bedTypeDTO.setBedType(BedTypeEnum.getKeyByValue(bedTypeName));
				bedTypeDTO.setLength(
						bedDescription.substring(bedDescription.indexOf("(") + 1, bedDescription.indexOf("m")));
				bedTypeDTO.setWide(
						bedDescription.substring(bedDescription.indexOf("*") + 1, bedDescription.lastIndexOf("m")));
				String sum = bedDescription.substring(bedDescription.indexOf(";") + 1, bedDescription.length() - 2);
				if (StringUtil.isValidString(sum) && !"null".equalsIgnoreCase(sum)) {
					bedTypeDTO.setNum(Integer.valueOf(sum));
				} else {
					bedTypeDTO.setNum(0);
				}
				bedTypeDTOS.add(bedTypeDTO);
			}
		}
		roomTypeDTO.setBedTypeList(bedTypeDTOS);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO deleteRoomType(DeleteRoomTypeRequestDTO deleteRoomTypeRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
			roomTypeQueryDTO.setRoomTypeId(deleteRoomTypeRequestDTO.getRoomTypeId());
			ResponseDTO<RoomTypeRsponseDTO> response = this.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);
			if (null == response || ResultCodeEnum.FAILURE.code == response.getResult()
					|| null == response.getModel()) {
				log.error("Query roomType info has error.");
				throw new ServiceException("Query roomType info has error.");
			}
			RoomTypeDO roomTypeDO = new RoomTypeDO();
			roomTypeDO = PropertyCopyUtil.transfer(deleteRoomTypeRequestDTO, RoomTypeDO.class);
			roomTypeDO.setIsActive(0);
			roomTypeMapper.updateRoomType(roomTypeDO);
			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);

			// 推送增量
			RoomTypeRsponseDTO roomTypeRsponseDTO = response.getModel();
			RoomTypeDTO roomTypeDTO = roomTypeRsponseDTO.getRoomTypeList().get(0);
			IncrementDTO incrementDTO = new IncrementDTO();
			incrementDTO.setMerchantCode(deleteRoomTypeRequestDTO.getMerchantCode());
			incrementDTO.setMHotelId(roomTypeDTO.getHotelId());
			incrementDTO.setMRoomTypeId(roomTypeDTO.getRoomTypeId());
			List<IncrementDTO> incrementDTOList = new ArrayList<>();
			incrementDTOList.add(incrementDTO);

			String url = URLSplitUtil.getUrl(incrementConfig);
			IncrementThread incrementThread = new IncrementThread(incrementDTOList, url, incrementService);
			threadPoolTaskExecutor.execute(incrementThread);
		} catch (Exception e) {
			log.error("deleteRoomType", e);
			throw new ServiceException("deleteRoomType has error", e);
		}
		return responseDTO;
	}

	/**
	 * 设置排序字段
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ResponseDTO setRoomTypeSort(RoomTypeSortDTO roomTypeSortDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			if (null == roomTypeSortDTO || null == roomTypeSortDTO.getRoomTypeId()
					|| null == roomTypeSortDTO.getSort()) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
				return responseDTO;
			}
			RoomTypeDO roomTypeDO = PropertyCopyUtil.transfer(roomTypeSortDTO, RoomTypeDO.class);
			roomTypeMapper.updateRoomType(roomTypeDO);

		} catch (Exception e) {
			log.error("deleteRoomType", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 批量添加房型
	 */
	@Override
	public ResponseDTO saveRoomTypes(List<RoomTypeDO> roomTypeDOs) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		List<RoomTypeQueryDTO> roomTypeQueryDTOs = null;
		try {
			if (null == roomTypeDOs) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
				return responseDTO;
			}
			roomTypeMapper.insertRoomTypes(roomTypeDOs);
			for (RoomTypeDO RoomTypeDO : roomTypeDOs) {
				RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
				roomTypeQueryDTO.setRoomTypeId(RoomTypeDO.getRoomTypeId());
				roomTypeQueryDTOs.add(roomTypeQueryDTO);

			}
			responseDTO.setModel(roomTypeQueryDTOs);
		} catch (Exception e) {
			log.error("saveRoomTypes", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}

		return responseDTO;
	}

}
