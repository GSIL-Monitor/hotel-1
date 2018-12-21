package com.fangcang.hotelinfo;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.hotelinfo.dto.ImageInfoDTO;
import com.fangcang.hotelinfo.dto.ImageTypeDTO;
import com.fangcang.hotelinfo.dto.RoomTypeImageDTO;
import com.fangcang.hotelinfo.request.DeleteRoomTypeRequestDTO;
import com.fangcang.hotelinfo.request.ImageRequestDTO;
import com.fangcang.hotelinfo.response.HotelImageListResponseDTO;
import com.fangcang.hotelinfo.response.ImageReponseDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 09:54
 * @Description:
 */

@RestController
@RequestMapping("/test/hotelinfo")
public class TestImageController {

    /**
     * uploadMainImage
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadMainImage")
    public ResponseDTO<ImageReponseDTO> uploadMainImage(MultipartFile file) {

        //获取上传图片的原始名
        String oriName = file.getOriginalFilename();
        //获取图片扩展名
        String extType = oriName.substring(oriName.lastIndexOf("."));
        //创建持久化文件名
        String picName = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss") + new Random().nextInt(1000) + extType;
        //存储路径
        String realPath = "~/download/pic";
        //映射路径
        String imageUrl = "http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg";

        ResponseDTO responseDTO = new ResponseDTO();
        //file.transferTo(new File(realPath));
        ImageReponseDTO imageReponseDTO = new ImageReponseDTO();
        imageReponseDTO.setImageUrl(imageUrl);
        imageReponseDTO.setRealPath(realPath);
        responseDTO.setModel(imageReponseDTO);
        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * uploadImage
     *
     * @param imageRequestDTO
     * @return
     */
    @RequestMapping(value = "/uploadImage")
    public ResponseDTO<ImageReponseDTO> uploadImage(ImageRequestDTO imageRequestDTO) {
        //存储路径
        String realPath = "~/download/pic";
        //映射路径
        String imageUrl = "http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg";

        ImageReponseDTO imageReponseDTO = new ImageReponseDTO();
        imageReponseDTO.setImageId(1000L);
        imageReponseDTO.setImageUrl(imageUrl);
        imageReponseDTO.setRealPath(realPath);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(imageReponseDTO);

        return responseDTO;
    }

    @RequestMapping(value = "/deleteImage")
    public ResponseDTO deleteImage(@RequestBody ImageRequestDTO imageRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }


    /**
     * 批量移动图片
     * batchMoveImages
     *
     * @param imageRequestDTO
     * @return
     */
    @RequestMapping(value = "/batchMoveImages")
    public ResponseDTO batchMoveImages(@RequestBody ImageRequestDTO imageRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;

    }

    /**
     * updateDefaultImage 设为默认图片
     *
     * @param imageRequestDTO
     * @return
     */
    @RequestMapping(value = "/updateDefaultImage")
    public ResponseDTO updateDefaultImage(@RequestBody ImageRequestDTO imageRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    /**
     * queryHotelImageList 查询酒店下的图片列表
     *
     * @param imageRequestDTO
     * @return
     */
    @RequestMapping(value = "/queryHotelImageList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<HotelImageListResponseDTO> queryHotelImageList(@RequestBody ImageRequestDTO imageRequestDTO) {

        //两个List
        HotelImageListResponseDTO hotelImageListResponseDTO = new HotelImageListResponseDTO();

        //图片类型列表
        List imageTypeDTOList = new ArrayList<ImageTypeDTO>();


        List<ImageTypeDTO> imageTypeDTOS = new ArrayList();

        List<RoomTypeImageDTO> roomTypeImageDTOS = new ArrayList();

        List<ImageInfoDTO> imageInfoDTOS = new ArrayList();

        for (int i = 0; i < 3; i++) {
            RoomTypeImageDTO roomTypeImageDTO = new RoomTypeImageDTO();

            ImageTypeDTO imageTypeDTO = new ImageTypeDTO();

            ImageInfoDTO imageInfoDTO = new ImageInfoDTO();

            imageInfoDTO.setHotelId(1000L + i);
            imageInfoDTO.setImageId(1000L + i);
            //统一为外观图,extID为酒店ID
            imageInfoDTO.setImageType(1);
            imageInfoDTO.setExtId(1000 + i);
            imageInfoDTO.setImageUrl("http://fcimage.fangcang.com/test02images/hotels/873/195873/201709271506477176812.jpg");
            imageInfoDTO.setRealPath("~/download/pic");
            //不为主图
            imageInfoDTO.setIsMainImage(0);
            imageInfoDTOS.add(imageInfoDTO);
            //床型 0.单床 1.大床  2.双床 3.三床  4.四床
            roomTypeImageDTO.setRoomTypeId(1L);
            roomTypeImageDTO.setRoomTypeName("大床");
            roomTypeImageDTO.setImageList(imageInfoDTOS);
            roomTypeImageDTOS.add(roomTypeImageDTO);

            imageTypeDTO.setImageType(i + 1);
            imageTypeDTO.setImageTypeName("图片类型名称" + i);
            imageTypeDTO.setImageList(imageInfoDTOS);
            imageTypeDTOS.add(imageTypeDTO);
        }
        hotelImageListResponseDTO.setImageTypeList(imageTypeDTOS);
        hotelImageListResponseDTO.setRoomTypeImageList(roomTypeImageDTOS);


        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(hotelImageListResponseDTO);
        return responseDTO;

    }
}
