package com.travel.common.dto.finance.query;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 订单锁单查询类
 * @author : Zhiping Sun
 * @date : 2018年1月22日下午2:35:20
 */
@Data
public class OrderLockQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = 3773191268893277079L;
	
	private String orderCode;
	
	private Integer financeLockStatus;

}
