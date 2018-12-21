package com.fangcang.b2b.thread;

import com.fangcang.b2b.response.HotelBaseInfoWithImagesResponseDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.hotelinfo.request.HotelBaseInfoRequestDTO;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.response.HotelBaseInfoRsponseDTO;
import com.fangcang.hotelinfo.response.HotelImageListResponseDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import com.fangcang.hotelinfo.service.ImageService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/3 17:29
 * @Description:
 */
@Slf4j
public class HotelBasicInfoThread implements Callable<HotelBaseInfoWithImagesResponseDTO> {

    private CountDownLatch countDownLatch;

    private HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO;

    private HotelBaseInfoWithImagesResponseDTO hotelBaseInfoWithImagesResponseDTO;

    private HotelInfoService hotelInfoService;

    private ImageService imageService;

    /**
     * 操作类型,1:查询基本信息 2:查询图片
     */
    private Integer operateType;

    public HotelBasicInfoThread(Integer operateType, CountDownLatch countDownLatch, HotelBaseInfoRequestDTO hotelBaseInfoRequestDTO,
                                HotelBaseInfoWithImagesResponseDTO hotelBaseInfoWithImagesResponseDTO,
                                HotelInfoService hotelInfoService, ImageService imageService) {
        this.operateType = operateType;
        this.countDownLatch = countDownLatch;
        this.hotelBaseInfoRequestDTO = hotelBaseInfoRequestDTO;
        this.hotelBaseInfoWithImagesResponseDTO = hotelBaseInfoWithImagesResponseDTO;
        this.hotelInfoService = hotelInfoService;
        this.imageService = imageService;
    }

    @Override
    public HotelBaseInfoWithImagesResponseDTO call() {
        try {
            Long startTime = System.currentTimeMillis();

            hotelBaseInfoWithImagesResponseDTO = new HotelBaseInfoWithImagesResponseDTO();

            if (operateType == 1) {
                ResponseDTO<HotelBaseInfoRsponseDTO> baseInfoResponseDTO = hotelInfoService.queryHotelInfoByHotelId(hotelBaseInfoRequestDTO);
                if (null != baseInfoResponseDTO && null != baseInfoResponseDTO.getModel()) {
                    HotelBaseInfoRsponseDTO baseInfo = baseInfoResponseDTO.getModel();
                    hotelBaseInfoWithImagesResponseDTO = PropertyCopyUtil.transfer(baseInfo, HotelBaseInfoWithImagesResponseDTO.class);
                }
            }
            if (operateType == 2) {
                ResponseDTO<HotelImageListResponseDTO> responseDTO = new ResponseDTO();
                HotelImageListResponseDTO hotelImageListResponseDTO = null;
                ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
                imageRequestDTO.setHotelId(hotelBaseInfoRequestDTO.getHotelId());
                responseDTO = imageService.queryHotelImageList(imageRequestDTO);
                if (null != responseDTO && null != responseDTO.getModel()) {
                    hotelImageListResponseDTO = responseDTO.getModel();
                    hotelBaseInfoWithImagesResponseDTO.setRoomTypeImageList(hotelImageListResponseDTO.getRoomTypeImageList());
                    hotelBaseInfoWithImagesResponseDTO.setImageTypeList(hotelImageListResponseDTO.getImageTypeList());
                    hotelBaseInfoWithImagesResponseDTO.setMeetingRoomImageList(hotelImageListResponseDTO.getMeetingRoomImageList());
                }

            }
            Long endTime = System.currentTimeMillis();
            log.info(Thread.currentThread().getName() + ": queryPriceInfoByHotelId end,total cost:" + (endTime - startTime));

        } catch (
                Exception e)

        {
            log.error("queryHotelBaseInfo has error", e);
        } finally

        {
            countDownLatch.countDown();
        }
        return hotelBaseInfoWithImagesResponseDTO;
    }
}

