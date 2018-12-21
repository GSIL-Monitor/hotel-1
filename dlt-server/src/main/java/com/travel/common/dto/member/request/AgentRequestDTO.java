package com.travel.common.dto.member.request;

import com.travel.common.dto.ModifyDTO;
import com.travel.common.validate.ModifyValidate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description 分销商信息
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日下午3:00:54
 */
@Data
public class AgentRequestDTO extends ModifyDTO {

	private static final long serialVersionUID = 5670478984612179533L;

	/**
	 * 分销商id
	 */
	@NotNull(message = "分销商id不能为空", groups = { ModifyValidate.class })
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
	 * 挂账金额
	 */
	private BigDecimal creditAmount;
	
	/**
	 * 是否人民币结算
	 */
	private Integer settlementRMB;

}
