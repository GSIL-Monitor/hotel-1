package com.fangcang.hotelinfo.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.UploadResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ImageTypeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.exception.ServiceException;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.FTPClientForFangcang;
import com.fangcang.common.util.FileUploadUtil;
import com.fangcang.common.util.MyConfig;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.UploadFileConfig;
import com.fangcang.hotelinfo.domain.ImageDO;
import com.fangcang.hotelinfo.domain.MeetingRoomDO;
import com.fangcang.hotelinfo.domain.RoomTypeDO;
import com.fangcang.hotelinfo.dto.ImageInfoDTO;
import com.fangcang.hotelinfo.dto.ImageTypeDTO;
import com.fangcang.hotelinfo.dto.MeetingRoomImageDTO;
import com.fangcang.hotelinfo.dto.RoomTypeImageDTO;
import com.fangcang.hotelinfo.mapper.ImageMapper;
import com.fangcang.hotelinfo.mapper.MeetingRoomMapper;
import com.fangcang.hotelinfo.mapper.RoomTypeMapper;
import com.fangcang.hotelinfo.request.ImageQueryDTO;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.request.MeetingRoomListQueryDTO;
import com.fangcang.hotelinfo.request.RoomTypeQueryDTO;
import com.fangcang.hotelinfo.response.HotelImageListResponseDTO;
import com.fangcang.hotelinfo.response.ImageReponseDTO;
import com.fangcang.hotelinfo.service.ImageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ASUS
 *
 */
@Service
@Slf4j
@EnableConfigurationProperties(MyConfig.class)
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private RoomTypeMapper roomTypeMapper;

	@Autowired
	private MeetingRoomMapper meetingRoomMapper;

	private String separator = File.separator;

	@Autowired
	private UploadFileConfig uploadFileConfig;

	/**
	 * 将图片设为默认图片
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO updateDefaultImage(ImageRequestDTO imageRequestDTO) {
		 ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			if (null == imageRequestDTO || null == imageRequestDTO.getImageId()
					|| null == imageRequestDTO.getImageType() || null == imageRequestDTO.getHotelId()
					|| null == imageRequestDTO.getExtId()) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
				return responseDTO;
			}
			// 查询主图是否存在
			ImageQueryDTO imageQueryDTO = new ImageQueryDTO();
			imageQueryDTO.setImageType(imageRequestDTO.getImageType());
			imageQueryDTO.setHotelId(imageRequestDTO.getHotelId());
			imageQueryDTO.setExtId(imageRequestDTO.getExtId());
			imageQueryDTO.setIsMainImage(1);
			List<ImageDO> imageDOS = imageMapper.dynamicQueryImage(imageQueryDTO);

			if (!CollectionUtils.isEmpty(imageDOS) && imageDOS.size() >= 1) {
				for (ImageDO imageDO : imageDOS) {
					imageDO.setIsMainImage(0);
					imageDO.setModifier(imageRequestDTO.getModifier());
				}
			}
			if (null == imageDOS) {
				imageDOS = new ArrayList<>();
			}
			ImageDO imageDO = new ImageDO();
			imageDO.setImageId(imageRequestDTO.getImageId());
			imageDO.setIsMainImage(1);
			imageDO.setModifier(imageRequestDTO.getModifier());
			imageDOS.add(imageDO);
			imageMapper.updateHotelImages(imageDOS);
			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
		} catch (Exception e) {
			log.error("updateDefaultImage", e);
			throw new ServiceException("updateDefaultImage", e);
		}
		return responseDTO;
	}

	/**
	 * 查询酒店下的图片列表
	 */
	@Override
	public ResponseDTO<HotelImageListResponseDTO> queryHotelImageList(ImageRequestDTO imageRequestDTO) {
		 ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		if (null == imageRequestDTO || null == imageRequestDTO.getHotelId()) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
			return responseDTO;
		}

		HotelImageListResponseDTO hotelImageListResponseDTO = new HotelImageListResponseDTO();
		try {
			// 根据酒店ID找到所有的图片
			List<ImageDO> imagelist = imageMapper.getHotelImageList(imageRequestDTO.getHotelId());
			// 根据酒店ID找到所有房型
			RoomTypeQueryDTO roomTypeQueryDTO = new RoomTypeQueryDTO();
			roomTypeQueryDTO.setHotelId(imageRequestDTO.getHotelId());
			roomTypeQueryDTO.setIsActive(1);
			List<RoomTypeDO> roomTypeDOs = roomTypeMapper.queryRoomTypeInfoByHotelId(roomTypeQueryDTO);

			Map<Long, RoomTypeDO> roomTypeDBMap = new HashMap<>();
			for (RoomTypeDO roomTypeDO : roomTypeDOs) {
				roomTypeDBMap.put(roomTypeDO.getRoomTypeId(), roomTypeDO);
			}
			// 根据酒店ID找到所有的会议室
			MeetingRoomListQueryDTO meetingRoomListQueryDTO = new MeetingRoomListQueryDTO();

			meetingRoomListQueryDTO.setHotelId(imageRequestDTO.getHotelId());

			List<MeetingRoomDO> meetingRoomDOs = meetingRoomMapper.queryMeetingRoomList(meetingRoomListQueryDTO);

			Map<Long, MeetingRoomDO> meetingRoomDBMap = new HashMap<>();

			for (MeetingRoomDO meetingRoomDO : meetingRoomDOs) {
				meetingRoomDBMap.put(meetingRoomDO.getMeetingId(), meetingRoomDO);
			}
			// roomTypeId
			Map<Long, RoomTypeImageDTO> roomTypeMap = new HashMap<>();
			// imageType
			Map<Integer, ImageTypeDTO> imageTypeMap = new HashMap<>();
			// meetingId
			Map<Long, MeetingRoomImageDTO> meetingRoomMap = new HashMap<>();
			if (null != imagelist) {
				for (ImageDO imageDO : imagelist) {
					if (ImageTypeEnum.ROOMVIEW.key == imageDO.getImageType()) {
						// 房型图
						ImageInfoDTO imageInfoDTO = PropertyCopyUtil.transfer(imageDO, ImageInfoDTO.class);
						if (roomTypeMap.containsKey(imageDO.getExtId())) {
							roomTypeMap.get(imageDO.getExtId()).getImageList().add(imageInfoDTO);
						} else {
							RoomTypeDO roomTypeDO = roomTypeDBMap.get(imageDO.getExtId());
							if (null != roomTypeDO) {
								RoomTypeImageDTO roomTypeImageDTO = PropertyCopyUtil.transfer(roomTypeDO,
										RoomTypeImageDTO.class);
								List<ImageInfoDTO> imageList = new ArrayList<>();
								imageList.add(imageInfoDTO);
								roomTypeImageDTO.setImageList(imageList);
								roomTypeMap.put(imageDO.getExtId(), roomTypeImageDTO);
							}
						}
					} else if (ImageTypeEnum.MEET.key == imageDO.getImageType()) {
						// 会议厅
						ImageInfoDTO imageInfoDTO = PropertyCopyUtil.transfer(imageDO, ImageInfoDTO.class);
						if (meetingRoomMap.containsKey(imageDO.getExtId())) {
							meetingRoomMap.get(imageDO.getExtId()).getImageList().add(imageInfoDTO);
						} else {
							MeetingRoomDO meetingRoomDO = meetingRoomDBMap.get(imageDO.getExtId());
							if (null != meetingRoomDO) {
								MeetingRoomImageDTO meetingRoomImageDTO = PropertyCopyUtil.transfer(meetingRoomDO,
										MeetingRoomImageDTO.class);
								List<ImageInfoDTO> imageList = new ArrayList<>();
								imageList.add(imageInfoDTO);
								meetingRoomImageDTO.setImageList(imageList);
								meetingRoomMap.put(imageDO.getExtId(), meetingRoomImageDTO);

							}
						}

					} else if (ImageTypeEnum.OUTVIEW.key == imageDO.getImageType()
							|| ImageTypeEnum.INVIEW.key == imageDO.getImageType()
							|| ImageTypeEnum.OTHERVIEW.key == imageDO.getImageType()) {
						// 外观图 内景图 其他图片
						ImageInfoDTO imageInfoDTO = PropertyCopyUtil.transfer(imageDO, ImageInfoDTO.class);
						if (imageTypeMap.containsKey(imageDO.getImageType())) {
							imageTypeMap.get(imageDO.getImageType()).getImageList().add(imageInfoDTO);
						} else {
							List<ImageInfoDTO> imageInDTOs = new ArrayList<>();
							imageInDTOs.add(imageInfoDTO);
							ImageTypeDTO imageTypeDTO = new ImageTypeDTO();
							imageTypeDTO.setImageTypeName(ImageTypeEnum.getValueByKey(imageDO.getImageType()));
							imageTypeDTO.setImageType(imageDO.getImageType());
							imageTypeDTO.setImageList(imageInDTOs);
							imageTypeMap.put(imageDO.getImageType(), imageTypeDTO);
						}
					}
				}
				List<RoomTypeImageDTO> roomTypeImageList = new ArrayList<RoomTypeImageDTO>();
				for (Map.Entry<Long, RoomTypeImageDTO> entry : roomTypeMap.entrySet()) {
					roomTypeImageList.add(entry.getValue());
				}
				List<ImageTypeDTO> imageTypeList = new ArrayList<ImageTypeDTO>();
				for (Map.Entry<Integer, ImageTypeDTO> entry : imageTypeMap.entrySet()) {
					imageTypeList.add(entry.getValue());
				}
				List<MeetingRoomImageDTO> meetingRoomlist = new ArrayList<MeetingRoomImageDTO>();
				for (Map.Entry<Long, MeetingRoomImageDTO> entry : meetingRoomMap.entrySet()) {
					meetingRoomlist.add(entry.getValue());
				}

				hotelImageListResponseDTO.setImageTypeList(imageTypeList);
				hotelImageListResponseDTO.setRoomTypeImageList(roomTypeImageList);
				hotelImageListResponseDTO.setMeetingRoomImageList(meetingRoomlist);
				responseDTO.setModel(hotelImageListResponseDTO);
			}
		} catch (Exception e) {
			log.error("queryHotelImageList", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 批量移动图片
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO batchMoveImages(ImageRequestDTO imageRequestDTO) {
		 ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {

			imageMapper.batchMoveImages(imageRequestDTO.getImageList());

			if (null == imageRequestDTO || CollectionUtils.isEmpty(imageRequestDTO.getImageList())) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
				return responseDTO;
			}
			List<ImageInfoDTO> imageList = imageRequestDTO.getImageList();
			for (ImageInfoDTO imageInfoDTO : imageList) {
				imageInfoDTO.setCreator(imageRequestDTO.getCreator());
				imageInfoDTO.setModifier(imageRequestDTO.getModifier());
			}
			imageMapper.batchMoveImages(imageRequestDTO.getImageList());

			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
		} catch (Exception e) {
			log.error("batchMoveImages", e);
			throw new ServiceException("batchMoveImages", e);
		}
		return responseDTO;
	}

	/**
	 * 上传酒店主图
	 */
	@SuppressWarnings("deprecation")
	@Override
	public ResponseDTO<ImageReponseDTO> uploadMainImage(MultipartFile file) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
						
		try {
			// 获取上传图片的原始名
			String oriName = file.getOriginalFilename();
			// 获取图片扩展名
			String extType = oriName.substring(oriName.lastIndexOf(".") + 1);
			boolean allow = false;
			String[] imagesType = Constant.IMAGE_ALLOW_TYPES;
			for (String imageType : imagesType) {
				if (imageType.equalsIgnoreCase(extType)) {
					allow = true;
					break;
				}
			}
			if (!allow) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorDesc);
				return responseDTO;
			}
						
			FileUploadUtil fileUploadUtil = new FileUploadUtil();
			ResponseDTO<UploadResponseDTO> result = fileUploadUtil.addFile(file, Constant.IMAGE_PATH, null,uploadFileConfig);
			if(result.getResult() == ResultCodeEnum.SUCCESS.code && null != result.getModel()){
				ImageReponseDTO imageReponseDTO = new ImageReponseDTO();
				imageReponseDTO.setImageUrl(result.getModel().getFileUrl());
				imageReponseDTO.setRealPath(result.getModel().getFilePath());
				responseDTO.setModel(imageReponseDTO);
				responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
			}else{
				log.error("Upload hotel image has error," + result.getFailReason());
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
			}
			
		} catch (Exception e) {
			log.error("uploadMainImage has error", e);
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		} 
		return responseDTO;
	}

	/**
	 * 删除酒店主图
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO deleteImage(ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			if (null != imageRequestDTO.getImageId()) {
				ImageDO image = new ImageDO();
				image.setImageId(imageRequestDTO.getImageId());
				imageMapper.deleteImageById(image.getImageId());
			}
			if(null != imageRequestDTO.getRealPath()){
				String realPath = imageRequestDTO.getRealPath();
				String realFileName = realPath.trim().substring(realPath.lastIndexOf("/")+1);
				FileUploadUtil fileUploadUtil = new FileUploadUtil();			 
				fileUploadUtil.deleteFile(Constant.IMAGE_PATH, realFileName,uploadFileConfig);
			}										
		} catch (Exception e) {
			log.error("deleteImage", e);
			throw new ServiceException("deleteImage has error", e);
		}
		return responseDTO;
	}

	/**
	 * 图片管理上传图片
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDTO<ImageReponseDTO> uploadImage(MultipartFile file, ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		

		if (null == file || null == imageRequestDTO || null == imageRequestDTO.getImageType()
				|| null == imageRequestDTO.getHotelId() || null == imageRequestDTO.getExtId()) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.INVALID_INPUTPARAM.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.INVALID_INPUTPARAM.errorDesc);
			return responseDTO;
		}
	
		try {
			// 获取上传图片的原始名
			String oriName = file.getOriginalFilename();
			// 获取图片扩展名
			String extType = oriName.substring(oriName.lastIndexOf(".") + 1);
			boolean allow = false;
			String[] imagesType = Constant.IMAGE_ALLOW_TYPES;
			for (String imageType : imagesType) {
				if (imageType.equalsIgnoreCase(extType)) {
					allow = true;
					break;
				}
			}
			if (!allow) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorDesc);
				return responseDTO;
			}
			String fileDir = Constant.IMAGE_PATH;
			ResponseDTO<UploadResponseDTO> result = FileUploadUtil.addFile(file, Constant.IMAGE_PATH, null,uploadFileConfig);
			if (result.getResult() != ResultCodeEnum.SUCCESS.code) {
				responseDTO.setResult(ResultCodeEnum.FAILURE.code);
				responseDTO.setFailCode(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorCode);
				responseDTO.setFailReason(ErrorCodeEnum.INVALID_IMAGE_TYPE.errorDesc);
				return responseDTO;
			}
			// 将图片信息保存到数据库
			ImageDO imageDO = new ImageDO();
			imageDO.setCreator(imageRequestDTO.getCreator());
			imageDO.setImageUrl(result.getModel().getFileUrl());
			imageDO.setRealPath(result.getModel().getFilePath());
			imageDO.setIsMainImage(0);
			imageDO.setHotelId(imageRequestDTO.getHotelId());
			imageDO.setImageType(imageRequestDTO.getImageType());
			imageDO.setExtId(imageRequestDTO.getExtId());
			imageMapper.insertHotelImage(imageDO);

			ImageReponseDTO imageReponseDTO = new ImageReponseDTO();
			imageReponseDTO.setImageUrl(imageDO.getImageUrl());
			imageReponseDTO.setRealPath(imageDO.getRealPath());
			imageReponseDTO.setImageId(imageDO.getImageId());
			responseDTO.setModel(imageReponseDTO);
			responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
		} catch (Exception e) {
			log.error("uploadImage has error", e);
			throw new ServiceException("uploadImage has error", e);
		}

		return responseDTO;
	}

}
