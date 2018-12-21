package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

/**
 * Created by ASUS on 2018/6/9.
 */
@Data
public class ImageQueryDTO extends BaseQueryConditionDTO {

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
    private Long extId;

    /**
     * 是否是主图(1是 0不是)
     */
    private Integer isMainImage;

}
