package com.fangcang.hotelinfo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fangcang.common.BaseDO;
import com.fangcang.common.constant.Constant;

import lombok.Data;

@Data
public class HotelAdditionalDTO extends BaseDO implements Serializable{

	private static final long serialVersionUID = 7304655781461554741L;

	/**
	 * 附加项ID
	 */
	private Long additionalId;

	/**
	 * 附加项类型(1.加早  2.加床  3.其他)
	 */
	private Integer additionalType;
	/**
	 * 附加项类型
	 */
	private String additionalName;
	/**
	 * 附加项价格
	 */
	private BigDecimal additionalPrice;
	/**
	 * 商家编码
	 */
	private String merchantCode;

	/**
	 * 是否勾选 (1勾选  0不勾选)
	 */
	private Integer isSelected;

}