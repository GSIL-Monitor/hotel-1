package com.travel.common.dto.member.response;

import com.travel.common.dto.ModifyDTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 分销商信息
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日下午3:15:21
 */
@Data
public class AgentResponseDTO extends ModifyDTO {

	private static final long serialVersionUID = 55153295740963733L;

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
	 * 分销商结算方式描述
	 */
	private String settlementText;
	
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
	 * 分销商是否有效描述
	 */
	private String isActiveText;
	
	/**
	 * 分销商信用额度
	 */
	private BigDecimal creditLine;
	
	/**
	 * 分销商已用信用额度
	 */
	private BigDecimal usedCreditAmount;
	
	/**
	 * 分销商剩余信用额度
	 */
	private BigDecimal surplus;
	
	/**
	 * 是否人民币结算
	 */
	private Integer settlementRMB;
	
	/**
	 * 是否人民币结算描述
	 */
	private String settlementRMBText;
}
