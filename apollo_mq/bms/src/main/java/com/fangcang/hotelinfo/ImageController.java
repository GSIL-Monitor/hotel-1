package com.fangcang.hotelinfo;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import com.fangcang.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ErrorCodeEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.response.HotelImageListResponseDTO;
import com.fangcang.hotelinfo.response.ImageReponseDTO;
import com.fangcang.hotelinfo.service.ImageService;

@RestController
@RequestMapping("/hotelinfo")
public class ImageController extends BaseController {

	@Autowired
	private ImageService imageService;

	/**
	 * updateDefaultImage 设为默认图片
	 *
	 * @param imageRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/updateDefaultImage", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseDTO updateDefaultImage(@RequestBody ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			imageRequestDTO.setModifier(super.getFullName());
			responseDTO = imageService.updateDefaultImage(imageRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);

		}
		return responseDTO;
	}

	/**
	 * queryHotelImageList 查询酒店下的图片列表
	 *
	 * @param imageRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/queryHotelImageList", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseDTO<HotelImageListResponseDTO> queryHotelImageList(@RequestBody ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = imageService.queryHotelImageList(imageRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 批量移动图片 batchMoveImages
	 *
	 * @param imageRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/batchMoveImages")
	public ResponseDTO batchMoveImages(@RequestBody ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {

			// imageRequestDTO.setModifier(super.s);
			responseDTO = imageService.batchMoveImages(imageRequestDTO);

			imageRequestDTO.setCreator(super.getFullName());
			imageRequestDTO.setModifier(super.getFullName());
			responseDTO = imageService.batchMoveImages(imageRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;

	}

	/**
	 * 上传酒店主图
	 * 
	 * @param hotelMainImageRequestDTO
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadMainImage")
	public ResponseDTO<ImageReponseDTO> uploadMainImage(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO = imageService.uploadMainImage(file);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 图片管理上传图片
	 *
	 * @param imageRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/uploadImage")
	public ResponseDTO<ImageReponseDTO> uploadImage(@RequestParam(required = false) MultipartFile file,
			ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			imageRequestDTO.setCreator(super.getFullName());
			responseDTO = imageService.uploadImage(file, imageRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}

	/**
	 * 删除图片
	 * 
	 * @param imageRequestDTO
	 * @return
	 */
	@RequestMapping(value = "/deleteImage")
	public ResponseDTO deleteImage(@RequestBody ImageRequestDTO imageRequestDTO) {
		ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
		try {
			responseDTO = imageService.deleteImage(imageRequestDTO);
		} catch (Exception e) {
			responseDTO.setResult(ResultCodeEnum.FAILURE.code);
			responseDTO.setFailCode(ErrorCodeEnum.SYSTEM_EXCEPTION.errorCode);
			responseDTO.setFailReason(ErrorCodeEnum.SYSTEM_EXCEPTION.errorDesc);
		}
		return responseDTO;
	}
}