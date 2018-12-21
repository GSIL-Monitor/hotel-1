package com.fangcang.hotelinfo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.fangcang.common.BaseDO;

import java.io.Serializable;

/**
 * @Description: 图片Entity
 * @Author: yanming.li@fangcang.com
 * @CreateDate: 2018/5/22 14:54
 */
@Data
public class ImageDO  extends BaseDO implements Serializable  {


    private static final long serialVersionUID = 2072573893981245499L;
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
     * 是否主图（也可为房型设置主图）
     * 1:是主图 0：非主图，默认为0
     */
    private Integer isMainImage;

    /**
     * 原图
     */
    private String imageUrl;

    /**
     * 大图URL
     */
    private String imageLargeUrl;

    /**
     * 中图URL
     */
    private String imageMiddleUrl;

    /**
     * 小图URL
     */
    private String imageSmallUrl;

    /**
     * 服务器图片地址
     */
    private String realPath;


}