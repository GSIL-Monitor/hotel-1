package com.fangcang.report.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoomQuotaDTO implements Serializable {

	/**
	 * 房型ID
	 */
	private Long roomTypeId;

	/**
	 * 房型名称
	 */
	private String roomTypeName;

	/**
	 * 价格计划明细
	 */
	private List<PricePlanQuotaDTO> pricePlanQuotaList;

	/**
	 * 房型小计--每日总配额
	 */
	private List<QuotaDayDTO> roomQuotaDayList;

	/**
	 * 房型小计--剩余配额数
	 */
	private Integer quotaNum;

	/**
	 * 房型小计--已用配额数
	 */
	private Integer usedQuotaNum;

	/**
	 * 房型小计--总配额数
	 */
	private Integer allQuotaNum;
}
