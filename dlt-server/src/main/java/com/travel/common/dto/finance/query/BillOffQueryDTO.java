package com.travel.common.dto.finance.query;

import java.util.List;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 销账查询参数
 * @author : Zhiping Sun
 * @date : 2018年3月21日上午9:22:11
 */
@Data
public class BillOffQueryDTO extends GenericQueryDTO {
	
	private static final long serialVersionUID = 4778368395531258923L;
	
	/**
	 * 销账id
	 */
	private Long billOffId;
	
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
	
	/**
	 * 支付方式(1:预付;0:返佣)
	 */
	private Integer payType;
	
	/**
	 * 支付类型(prepay:预付;pay:现付返佣)
	 */
	private List<String> payMethodList;
	
	/**
	 * 支付类型(prepay:预付;pay:现付返佣)
	 */
	private String payMethod;
	
	/**
	 * 销账机构类型(F:分销商;S:供应商)
	 */
	private String orgType;

}
