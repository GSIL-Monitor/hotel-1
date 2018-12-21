package com.travel.common.dto.finance.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.travel.common.dto.ModifyDTO;
import com.travel.common.validate.AddValidate;
import com.travel.common.validate.ModifyValidate;

import lombok.Data;

/**
 * @Description : 订单申诉信息
 * @author : Zhiping Sun
 * @date : 2018年1月17日下午2:14:01
 */
@Data
public class OrderAppealRequestDTO extends ModifyDTO {
	
	private static final long serialVersionUID = 4505807745136765512L;
	
	/**
	 * 申诉id
	 */
	@NotNull(message = "申诉id不能为空", groups = { ModifyValidate.class })
	private Long appealId;
	
	/**
	 * 订单号码
	 */
	@NotBlank(message = "申诉订单号不能为空", groups = { AddValidate.class, ModifyValidate.class })
	private String orderCode;
	
	/**
	 * 申诉状态
	 */
	private String status;
	
	/**
	 * 申诉理由
	 */
	@NotBlank(message = "申诉理由不能为空", groups = { AddValidate.class, ModifyValidate.class })
	private String applyReason;
	
	/**
	 * 申诉处理人
	 */
	private String handler;

	/**
	 * 申诉处理时间
	 */
    private Date handleTime;
    
    /**
     * 备注
     */
    private String mark;
	
}
