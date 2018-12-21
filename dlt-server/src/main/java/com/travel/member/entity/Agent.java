package com.travel.member.entity;

import com.travel.common.dto.ModifyDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 分销商信息
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月30日下午2:51:23
 */
@Data
public class Agent extends ModifyDTO {

	private static final long serialVersionUID = -9185097541160253023L;
	
	/**
	 * 分销商id
	 */
	private Long agentId;

	/**
	 * 分销商编码
	 */
	private String agentCode;
	
	/**
	 * 分销商名称
	 */
	private String agentName;
	
	/**
	 * 分销商地址
	 */
	private String address;
	
	/**
	 * 分销商联系电话
	 */
	private String tel;
	
	/**
	 * 分销商传真号码
	 */
	private String fax;
	
	/**
	 * 分销商结算方式
	 */
	private String settlement;
	
	/**
	 * 分销商邮箱
	 */
	private String email;
	
	/**
	 * 分销商联系人
	 */
	private String contacts;
	
	/**
	 * 分销商是否有效
	 */
	private Integer isActive;
	
	/**
	 * 分销商信用额度
	 */
	private BigDecimal creditLine;
	
	/**
	 * 分销商已用信用额度
	 */
	private BigDecimal usedCreditAmount;
	
	/**
	 * 是否人民币结算(1：是；0：否)
	 */
	private Integer settlementRMB;
}
