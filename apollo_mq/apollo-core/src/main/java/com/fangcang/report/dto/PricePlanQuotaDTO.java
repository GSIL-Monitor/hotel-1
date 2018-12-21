package com.fangcang.report.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PricePlanQuotaDTO implements Serializable {

	/**
	 * 供应商编码
	 */
	private String supplierCode;

	/**
	 * 供应商名称
	 */
	private String supplierName;

	/**
	 * 酒店ID
	 */
	private Long hotelId;

	/**
	 * 酒店名称
	 */
	private String hotelName;

	/**
	 * 房型ID
	 */
	private Long roomTypeId;

	/**
	 * 房型名称
	 */
	private String roomTypeName;

	/**
	 * 价格计划ID
	 */
	private Long pricePlanId;

	/**
	 * 价格计划名称
	 */
	private String pricePlanName;

	/**
	 * 配额账号ID
	 */
	private Long quotaAccountId;

	/**
	 * 是否有效
	 */
	private Integer isActive;

	/**
	 * 配额类型
	 */
	private Integer quotaType;

	/**
	 * 床型
	 */
	private String bedType;

	/**
	 * 床型中文
	 */
	private String bedTypeStr;

	/**
	 * 商家编码
	 */
	private String merchantCode;

	/**
	 * 每日明细
	 */
	private List<QuotaDayDTO> quotaDayList;

	/**
	 * 总计--剩余配额数
	 */
	private Integer quotaNum;

	/**
	 * 总计--已用配额数
	 */
	private Integer usedQuotaNum;

	/**
	 * 总计--总配额数
	 */
	private Integer allQuotaNum;

	/**
	 * 是否共享
	 */
	private Integer isShare;
}
