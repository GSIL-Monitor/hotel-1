package com.fangcang.hotelinfo.response;

import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.dto.HotelFacilityDTO;
import com.fangcang.hotelinfo.dto.RoomTypeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HotelBaseInfoRsponseDTO implements Serializable {
	private static final long serialVersionUID = 2131158468867800328L;
	
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
     private String fax;
     
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
      * 房间主题 
      */
     private String[] theme;
     
     /**
      * 酒店主题URL
      * 
      */
     private String imageUrl;
     
     /**
      *酒店主题真实路径
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
     * 其他政策
     */
    private String otherPolicy;
     
     /**
      * 入住时间
      */
     private String checkInTime;
     
     /**
      * 退房时间
      */
     private String checkOutTime;
     
     /**
      * 是否允许携带宠物
      * 0允许 1 不允许
      */
     private Integer pet;
     
     /**
      * 信用卡
      */
     private String[] creditCard;
     
     /**
      * 酒店地址
      */
     private String hotelAddress;
     
     /**
      * 酒店英文地址
      */
     private String engHotelAddress;
     
     /**
      * 房型
      */
     private List<RoomTypeDTO> roomTypeList;
     
     /**
      * 附加项
      */
     private List<HotelAdditionalDTO> additionalList;
     /**
      * 酒店设施包装类
      */
     private List<HotelFacilityDTO> facilityList;
     /**
      * 免房政策
      */
     private String[] freeRoomPolicy;
     /**
      * 主图Id
      */
     private Long imageId;


    /**********************/
    /**
     * 内部备注：产品备注
     */
    private String productRemark;
    /**
     * 内部备注：订单备注
     */
    private String orderRemark;
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
