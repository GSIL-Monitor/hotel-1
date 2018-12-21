package com.travel.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 报表应付
 * @author : Zhiping Sun
 * @date : 2018年4月23日 上午10:20:14
 */
@Data
public class Payable extends ModifyDTO {

	private static final long serialVersionUID = -1663725151974082928L;
	
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	
	/**
	 * 供应商名称
	 */
	private String supplyName;
	
	/**
	 * 入住日期
	 */
	private Date checkInDate;
	
	/**
	 * 离店日期
	 */
	private Date checkOutDate;
	
	/**
	 * 底价币种
	 */
	private String baseCurrency;
	
	/**
	 * 应付金额
	 */
	private BigDecimal payable;
	
	/**
	 * 利润
	 */
	private BigDecimal profit;

}
