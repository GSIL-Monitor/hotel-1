package com.fangcang.hotelinfo.mapper;

import java.util.List;

import com.fangcang.common.MyMapper;
import com.fangcang.hotelinfo.domain.ImageDO;
import com.fangcang.hotelinfo.dto.ImageInfoDTO;
import com.fangcang.hotelinfo.request.ImageQueryDTO;
import com.fangcang.hotelinfo.request.ImageRequestDTO;

public interface ImageMapper extends MyMapper<ImageDO> {
	/**
	 * 添加图片
	 */
	public Integer insertHotelImage(ImageDO imageDO);
	/**
	 * 修改图片
	 */
	public Integer updateHotelImage(ImageDO hotelImageDO);

	/**
	 * 删除酒店主图
	 */
	public Integer deleteImageById(Long imageId);


	/**
	 * 批量移动图片
	 * @param imageList
	 * @return
	 */
	public Integer  batchMoveImages(List<ImageInfoDTO> imageList);


	/**
	 * 动态查询
	 * @param imageQueryDTO
	 * @return
	 */
	public List<ImageDO> dynamicQueryImage(ImageQueryDTO imageQueryDTO);
	
	/**
	 * 删除图片
	 */
	public Integer deleteImage(ImageDO hotelImageDO);


	/**
	 * 上传主图
	 * @param imageRequestDTO
	 * @return
	 */
    public ImageDO uploadMainImage (ImageRequestDTO imageRequestDTO); 
    
    /**
     * 根据ID查询一个图片
     * @param imageId
     * @return
     */
    public ImageDO queryImageDO(Long imageId);
    
    /**
     * 设为默认图片
     * @param imageId
     * @return
     */
    public Integer updateDefaultImage(Long imageId);
    
    /**
     * 查询酒店下的图片列表
     * @param hotelid
     * @return
     */
    public List<ImageDO> getHotelImageList(Long hotelid);
    
    /**
	 * 批量修改图片
	 * @param images
	 * @return
	 */
	public  Integer  updateHotelImages(List<ImageDO> images);


	/**
	 * 批量新增图片
	 * @param imageDOS
	 */
	public void batchSaveHotelImage(List<ImageDO> imageDOS);

}
