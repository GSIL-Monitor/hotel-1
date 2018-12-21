package com.travel.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 报表应收
 * @author : Zhiping Sun
 * @date : 2018年4月23日 上午10:19:36
 */
@Data
public class Receivable extends ModifyDTO {

	private static final long serialVersionUID = -5790120226303037115L;
	
	/**
	 * 分销商编码
	 */
	private String agentCode;
	
	/**
	 * 分销商名称
	 */
	private String agentName;
	
	/**
	 * 入住日期
	 */
	private Date checkInDate;
	
	/**
	 * 离店日期
	 */
	private Date checkOutDate;
	
	/**
	 * 售卖币种
	 */
	private String saleCurrency;
	
	/**
	 * 应收金额
	 */
	private BigDecimal receivable;
	
	/**
	 * 应付金额
	 */
	private BigDecimal payable;
	
	/**
	 * 间数
	 */
	private Integer rooms;
	
	/**
	 * 利润
	 */
	private BigDecimal profit;

}
