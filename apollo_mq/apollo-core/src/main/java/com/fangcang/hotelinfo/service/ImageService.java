package com.fangcang.hotelinfo.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.response.HotelImageListResponseDTO;
import com.fangcang.hotelinfo.response.ImageReponseDTO;

public interface ImageService {
	
	/**
	 * 设为默认图片
	 * @param imageRequestDTO
	 * @return
	 */
	public ResponseDTO updateDefaultImage (ImageRequestDTO imageRequestDTO );
	
	/**
	 * 查询酒店下的图片列表
	 * @param imageRequestDTO
	 * @return
	 */
   public  ResponseDTO<HotelImageListResponseDTO> queryHotelImageList(ImageRequestDTO imageRequestDTO);
   
   /**
    * 批量移动图片
    * @param imageRequestDTO
    * @return
    */
   public  ResponseDTO batchMoveImages(ImageRequestDTO imageRequestDTO);
   /**
    * 上传酒店主图
    */
   public ResponseDTO<ImageReponseDTO> uploadMainImage(MultipartFile file);

   
   /**
    * 图片管理上传图片
    */
    public ResponseDTO<ImageReponseDTO> uploadImage(MultipartFile file,ImageRequestDTO imageRequestDTO);

   /**
    * 删除图片
    */
   public ResponseDTO deleteImage(ImageRequestDTO imageRequestDTO);

}
