package com.fangcang.hotelinfo.saas.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/6/25.
 */
@Data
public class HotelSaasDTO implements Serializable {
    private static final long serialVersionUID = -4994369413909654499L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 酒店英文名称
     */
    private String engHotelName;
    /**
     * 酒店电话
     */
    private String telePhone;

    /***
     * 酒店集团下的品牌,对应htl_info.t_brand表ID
     */
    private String hotelBrand ;
    /**酒店集团 对应htl_info.t_brand表ID**/
    private String hotelGroupCode ;
    private String hotelGroupName ;
    /**
     * 酒店品牌
     */
    private String hotelBrandName;
    /**
     * 酒店星级
     */
    private String hotelStar;

    /**
     * 国家编码
     */
    private String countryCode;

    private String countryName;

    /**
     * 省份编码
     */
    private String provinceCode;

    private String provinceName;

    /**
     * 城市编码
     */
    private String cityCode;

    private  String cityName;

    /**
     * 商业区编码
     */
    private String businessCode;

    private String businessName;

    /**
     * 城区编码
     */
    private String districtCode;

    private String districtName;

    /**
     * 是否有效（1有效，0无效）
     */
    private String isActive;
    /**主图*/
    private String mainImage;

    /**
     * 酒店地址
     */
    private String hotelAddress;

    /**
     * 按条件查询出来的酒店图片数
     */
    private int imgNum;

    private String hotelCode;
    /**
     * 层高
     */
    private String layerHigh;

    /**
     * 房间总数
     */
    private String layerCount;

    /**
     * 酒店网址
     */
    private String webSite;



    /**
     * 酒店英文地址
     */
    private String engHotelAddress;

    /**
     * 开业时间
     */
    private Date praciceDate;

    /**
     * 装修时间
     */
    private Date fitmentDate;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 酒店传真号
     */
    private String fax;

    /**
     * 支持的信用卡 文本形式提供
     */
    private String creditCard;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 描述
     */
    private String description;

    /**
     * 酒店规定入住时间
     */
    private String checkInTime;

    /**
     * 酒店规定退房时间
     */
    private String checkOutTime;
    //酒店品牌,对应htl_info.t_brand表ID
    private String hotelBrandCode;

    /**
     * 酒店主题
     */
    private String hotelTheme;

    /**
     * 无烟楼层
     */
    private String noSmokingFloor;

    /**
     * 酒店是否全部禁烟
     */
    private Integer allNoSmoking;

    private String createName;

    private Date createTime;

    private String modifyName;

    private Date modifyTime;

    /**
     * 经度 (未使用)
     */
    private Double longitude;

    /**
     * 纬度 (未使用)
     */
    private Double latitude;

    private String accepCustom;

    /**
     * 申请状态
     */
    private Integer applyStatus;

    /**
     * 审核原因
     */
    private String cause;

    /**
     * 审核操作人
     */
    private String auditName;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 商家名字
     */
    private String merchantName;


    /**
     * 房型信息 查询用
     */
    private List<RoomTypeSaasDTO> roomTypeDTOList;
    /**
     * 会议室信息  查询用
     */
    private List<MeetingTypeInfoDTO> meetingInfoDTOList;

    /**酒店所持有的特征,HotelFeatureList将取代 酒店设置列表hotelExtraList**/
    private List<HotelFeatureDTO> hotelFeatureList ;
    /**地图位置对象**/
    private MapPositionDTO mapPositionDTO ;
    /**地图位置对象的主健ID**/
    private Long positionId ;
    /**
     * 酒店图片列表
     */
    private List<HotelImgDTO> hotelImgList;

    private String meetingActive;

    private String roomACTIVE;
    /**临时保存是否为管理员操作**/
    private boolean isPlatformManager;
    /**临时保存操作类型 1修改、2审核；修改又分管理员修改和商家修改**/
    private Integer operationType ;
}
