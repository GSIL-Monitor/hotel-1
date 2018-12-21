package com.fangcang.hotelinfo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 15:07
 * @Description:
 */
@Data
public class ImageTypeDTO implements Serializable {

    /**
     * 1.外观图 2.房型图 3.大堂图 4.设施图 5.宴会厅 6.会议厅 7.其它
     */
    private Integer imageType;

    /**
     * 图片类型名称
     */
    private String imageTypeName;

    /**
     * 图片列表信息
     */
    private List<ImageInfoDTO> imageList;
}
