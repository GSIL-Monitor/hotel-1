package com.travel.finance.entity;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 机构出账信息
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午3:02:27
 */
@Data
public class OrgCheckOut extends ModifyDTO {
	
	private static final long serialVersionUID = -6890817992367096224L;
	
	/**
	 * 机构编码(分销商编码/供应商编码)
	 */
	private String orgCode;
	
	/**
	 * 机构名称(分销商名称/供应商名称)
	 */
	private String orgName;
	
	/**
	 * 币种
	 */
	private String currency;
	
	/**
	 * 应收/应付
	 */
	private BigDecimal receivable;
	
	/**
	 * 已标记出账应收/应付
	 */
	private BigDecimal checkoutAmount;
	
	/**
	 * 已收/已付
	 */
	private BigDecimal actualReceived;
	
	/**
	 * 未收/未付
	 */
	private BigDecimal unreceivable;

}
