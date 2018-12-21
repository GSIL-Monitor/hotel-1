package com.fangcang.b2b.response;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 14:15
 * @Description: 酒店列表ResponseDTO
 */
@Data
public class QueryHotelListRsponseDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = -656410046410606204L;

    /**
     * 酒店id
     */
    private Long hotelId;

    /**
     * 中文名称
     */
    private String chineseName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 酒店地址
     */
    private String chineseAddress;

    /**
     * 酒店英文地址
     */
    private String englishAddress;

    /**
     * 酒店星级  10：二星以下    15：经济型   20：二星   25：舒适型   30：三星  35：高档型   40：四星   45：豪华型  50：五星
     */
    private Integer hotelStar;

    /**
     * 起价
     */
    private BigDecimal startPrice;
    /**
     * 展示币种
     */
    private String showCurrency;
    /**
     * 主图
     */
    private String imgUrl;


}
