package com.fangcang.hotelinfo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class HotelDTO implements Serializable {
    private static final long serialVersionUID = 2984492042699443520L;

    /**
     * 酒店ID
     */
    private Long hotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 酒店英文名称
     */
    private String engHotelName;
    /**
     * 商业区编码
     */
    private String businessCode;
    /**
     * 商业区名称
     */
    private String businessName;
    /**
     * 酒店星级
     */
    private Integer hotelStar;
    /**
     * 酒店电话
     */
    private String phone;
    /**
     * 酒店传真
     */
    private Integer fax;
    /**
     * 开业时间
     */
    private String openingDate;
    /**
     * 装修时间
     */
    private String decorationDate;
    /**
     * 房间总数
     */
    private Integer roomTotalNum;
    /**
     * 酒店主题
     */
    private String theme;
    /**
     * 酒店主图URL
     */
    private Date hotelImageUrl;
    /**
     * 酒店主图真实路径
     */
    private String realPath;
    /**
     * 酒店介绍
     */
    private String introduction;
    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 是否有效(1有效 0无效)
     */
    private Integer isActive;

    /**
     * 酒店设施
     */
    private List<HotelFacilityDTO> facilityList;
    /**
     * 酒店附加项
     */
    private List<HotelAdditionalDTO> additionalList;

    /**
     * 酒店地址
     */
   private String hotelAddress;

    /**
     * 酒店英文地址
     */
    private String engHotelAddress;

    /**
     * 退房时间
     */
    private String checkOutTime;

    /**
     * 入住时间
     */
    private String checkInTime;

    /**
     * 宠物政策(1 允许携带宠物，0 不允许携带宠物)
     */
    private Integer pet;

    /**
     * 接受的信用卡,多个逗号分隔(
     * 1:国内发行银联卡   2:威士(VISA)  3:万事达(Master) 4运通(AMEX)
     * 5大来(Diners Club) 6 Euro卡  7 Euro 6000卡  8EC借记卡
     * 9 威士电子借记卡 10Maestro卡  11吉士美卡)
     */
    private String creditCard;

    /**
     * 其他政策
     */
    private String otherPolicy;
}
