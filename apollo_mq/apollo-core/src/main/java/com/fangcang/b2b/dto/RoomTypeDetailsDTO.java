package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 15:09
 * @Description: 房型类型详情DTO
 */
@Data
public class RoomTypeDetailsDTO implements Serializable {

    private static final long serialVersionUID = 356034871263317572L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 面积
     */
    private String area;

    /**
     * 床型描述
     */
    private String bedDescription;

    /**
     * 起价
     */
    private BigDecimal startPrice;

    /**
     * 展示币种
     */
    private String showCurrency;

    /**
     * 房型主图
     */
    private String imageUrl;

    /**
     * 产品详情List
     */
    private List<ProductDetailsDTO> productDetails;
}
