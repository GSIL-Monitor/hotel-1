package com.fangcang.hotelinfo.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 11:20
 * @Description:
 */
@Data
public class ImageInfoDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = 1744727873462932455L;

    /**
     * 图片ID
     */
    private Long imageId;
    /**
     * 酒店ID
     */
    private Long hotelId;
    /**
     * 1.外观图 2.房型图 3.大堂图 4.设施图 5.宴会厅 6.会议厅 7.其它
     */
    private Integer imageType;

    /**
     * 当图片类型为房型时，对应房型ID
     * 当图片类型为宴会厅时，对应宴会厅ID
     * 如果是外观图、内景图、其他图，则此ID存酒店ID
     */
    private Integer extId;

    /**
     * 1:是主图 0：非主图，默认为0
     */
    private Integer isMainImage;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 服务器图片地址
     */
    private String realPath;

}
