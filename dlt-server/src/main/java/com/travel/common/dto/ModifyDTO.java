package com.travel.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 通用对象
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月30日下午3:00:50
 */
@Data
public class ModifyDTO implements Serializable {
	
	private static final long serialVersionUID = 4896763053125411891L;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;

}
