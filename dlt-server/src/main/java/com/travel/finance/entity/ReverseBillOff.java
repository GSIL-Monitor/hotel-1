package com.travel.finance.entity;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 反核销信息
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午3:33:27
 */
@Data
public class ReverseBillOff extends ModifyDTO {
	
	private static final long serialVersionUID = 742184844000257113L;
	
	/**
	 * 反核销id
	 */
	private Long reverseId;
	
	/**
	 * 销账id
	 */
	private Long billOffId;
	
	/**
	 * 反核销金额
	 */
	private BigDecimal reverseAmount;
	
	/**
	 * 销账凭证号
	 */
	private String serialNumber;
	
	/**
	 * 机构编码(分销商编码/供应商编码)
	 */
	private String orgCode;
	
	/**
	 * 机构名称(分销商名称/供应商名称)
	 */
	private String orgName;

}
