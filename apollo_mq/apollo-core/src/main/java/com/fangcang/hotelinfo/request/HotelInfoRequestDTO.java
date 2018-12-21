package com.fangcang.hotelinfo.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.hotelinfo.dto.HotelAdditionalDTO;
import com.fangcang.hotelinfo.dto.HotelFacilityDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class


HotelInfoRequestDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -634213892614980616L;

	/**
	 * 操作类型 1新增  2修改
	 */
	private Integer operationType;

	/**
	 * 酒店ID
	 */
	 private Long hotelId;

	/**
	 * 酒店名称
	 */
	@NotNull(message = "hotelName不能为Null")
	private String hotelName;

	/**
	 * 国家编码
	 */
	private String country;

	/**
	 * 城市编码
	 */
	@NotNull(message = "cityCode不能为Null")
	private String cityCode;

	/**
	 * 城市名称
	 */
	@NotNull(message = "cityName不能为Null")
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
	 * 酒店主题
	 */
	private String[] theme;

	/**
	 * 主图ID
	 */
	private Long imageId;

	/**
	 * 酒店主图URL
	 */
	private String imageUrl;

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
	private String[] creditCard;

	/**
	 * 其他政策
	 */
	private String otherPolicy;
	
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
	 * 免房政策(1 全陪免半 2 8免半16兔1)
	 */
	private String[] freeRoomPolicy;

	/**
	 * 商家编码
	 */
	private String merchantCode;


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
