package com.fangcang.report.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HotelQuotaDTO implements Serializable {

	private static final long serialVersionUID = 673297805551393179L;
	/**
	 * 酒店ID
	 */
	private Long hotelId;

	/**
	 * 酒店名称
	 */
	private String hotelName;

	/**
	 * 房型明细
	 */
	private List<RoomQuotaDTO> roomQuotaDTOList;

	/**
	 * 酒店小计--每日总配额
	 */
	private List<QuotaDayDTO> hotelQuotaDayList;

	/**
	 * 酒店小计--剩余配额数
	 */
	private Integer quotaNum;

	/**
	 * 酒店小计--已用配额数
	 */
	private Integer usedQuotaNum;

	/**
	 * 酒店小计--总配额数
	 */
	private Integer allQuotaNum;

}
