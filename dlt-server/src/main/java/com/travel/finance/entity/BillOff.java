package com.travel.finance.entity;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 销账信息
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午3:24:25
 */
@Data
public class BillOff extends ModifyDTO {
	
	private static final long serialVersionUID = 8520308953591102016L;
	
	/**
	 * 销账id
	 */
	private Long billOffId;
	
	/**
	 * 销账凭证号
	 */
	private String serialNumber;
	
	/**
	 * 销账金额
	 */
	private BigDecimal billOffAmount;
	
	/**
	 * 机构编码(分销商编码/供应商编码)
	 */
	private String orgCode;
	
	/**
	 * 机构名称(分销商名称/供应商名称)
	 */
	private String orgName;
	
	/**
	 * 应收/应付
	 */
	private BigDecimal receivable;
	
	/**
	 * 状态(1:已反核销;0:未反核销)
	 */
	private Integer state;
	
	/**
	 * 银行账号
	 */
	private String bankNo;
	
	/**
	 * 开户行名称
	 */
	private String bankName;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 支付类型(1:预付; 0:现付返佣)
	 */
	private Integer payType;

}
