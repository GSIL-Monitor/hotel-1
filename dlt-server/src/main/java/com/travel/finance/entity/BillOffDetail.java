package com.travel.finance.entity;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 销账明细
 * @author : Zhiping Sun
 * @date : 2018年3月19日下午8:20:42
 */
@Data
public class BillOffDetail extends ModifyDTO {
	
	private static final long serialVersionUID = -945703084789399614L;
	
	/**
	 * 销账明细id
	 */
	private Long billOffDetailId;
	
	/**
	 * 订单每日售卖id
	 */
	private Long dayPriceId;
	
	/**
	 * 销账id
	 */
	private Long billOffId;
	
	/**
	 * 机构编码(分销商编码/供应商编码)
	 */
	private String orgCode;
	
	/**
	 * 机构名称(分销商名称/供应商名称)
	 */
	private String orgName;
	
	/**
	 * 订单编码
	 */
	private String orderCode;
	
	/**
	 * 应收/应付
	 */
	private BigDecimal receivable;
	
	/**
	 * 已收/已付
	 */
	private BigDecimal actualReceived;
	
	/**
	 * 本次收/本次付
	 */
	private BigDecimal currentActualReceived;

}
