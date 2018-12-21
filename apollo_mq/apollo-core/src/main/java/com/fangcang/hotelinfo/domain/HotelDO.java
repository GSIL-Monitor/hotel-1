package com.fangcang.hotelinfo.domain;




import com.fangcang.common.BaseDO;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
@Table(name = "t_hotel")
@Data
public class HotelDO extends BaseDO implements Serializable{

	private static final long serialVersionUID = -3439010827747190721L;
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
     * 国家
     */
    private String country;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;   
    /**
     * 商业区编码
     */
    private String businessCode;
    /**
     * 酒店地址
     */
    private String hotelAddress;
    /**
     * 酒店英文地址
     */
    private String engHotelAddress;
    /**
     * 酒店星级
     */
    private Integer hotelStar;
    /**
     * 酒店主题
     */
    private String theme;
    /**
     * 酒店电话
     */
    private String phone;
    /**
     * 酒店传真
     */
    private String fax;
    /**
     * 房间总数
     */
    private Integer roomTotalNum;  
    /**
     * 装修时间
     */
    private String decorationDate; 
    /**
     * 开业时间
     */
    private String openingDate; 
    /**
     * 酒店介绍
     */
    private String introduction;
    /**
     * 是否有效(1有效 0无效)
     */
    private Integer isActive;                         
    /**
     * 取消政策
     */
    private String cancelPolicy;
    /**
     * 附加项包装类
     */
    private List<HotelAdditionalDO> additionalList;
    /**
     * 酒店设施包装类
     */
    private List<HotelFacilityDO> facilityList;
    /**
     * 酒店政策包装类
     */
    private HotelPolicyDO hotelPolicyDO;
    /**
     * 图片包装类
     */
    private ImageDO imagedo;
    /**
     * 房型组合包装类
     */
    private List<RoomTypeDO> roomTypeList;
    /**
     * 查询商家常用酒店包装类
     */
    private CommonHotelDO commonHotelDO;
    /**
     * 是否常用
     */
    private Integer isCommonUsed;
    /**
	 * 免房政策(1 全陪免半 2 8免半16兔1)
	 */
	private String freeRoomPolicy;

    /**
     * 商家编码
     */
	private String merchantCode;

    /**
     * 会议室列表
     */
	private List<MeetingRoomDO> meetingRoomDOList;


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
