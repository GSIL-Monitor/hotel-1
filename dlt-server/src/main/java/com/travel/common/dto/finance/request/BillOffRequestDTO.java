package com.travel.common.dto.finance.request;

import com.travel.common.dto.ModifyDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : 对账请求参数
 * @author : Zhiping Sun
 * @date : 2018年1月15日上午11:19:26
 */
@Data
public class BillOffRequestDTO extends ModifyDTO {
	
	private static final long serialVersionUID = -826046022711940438L;
	
	/**
	 * 机构编码
	 */
	private String orgCode;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	
	/**
	 * 应收/应付
	 */
	private BigDecimal receivable;
	
	/**
	 * 本次收/本次付
	 */
	private BigDecimal billOffAmount;
	
	/**
	 * 销账凭证单号
	 */
	private String serialNumber;
	
	/**
	 * 银行账户
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
	 * 支付类型(1:预付;0:现付返佣)
	 */
	private Integer payType;
	
	/**
	 * 销账明细
	 */
	private List<Long> dayPriceIdList;

	private String dayPriceStr;
	
}
