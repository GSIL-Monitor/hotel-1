package com.fangcang.report.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class QuotaDayDTO implements Serializable{

	private static final long serialVersionUID = -7620872466602476173L;

	/**
	 * 价格计划ID
	 */
	private Long pricePlanId;

	/**
	 * 配额账号ID
	 */
	private Long quotaAccountId;

	/**
	 * 售卖日期
	 */
	private Date saleDate;

	/**
	 * 已用配额数
	 */
	private Integer usedQuotaNum;

	/**
	 * 总配额数
	 */
	private Integer allQuotaNum;

	/**
	 * 剩余配额数
	 */
	private Integer quotaNum;

}
