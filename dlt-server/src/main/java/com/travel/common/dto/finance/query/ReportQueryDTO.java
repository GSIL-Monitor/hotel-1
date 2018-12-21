package com.travel.common.dto.finance.query;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 报表查询基础类
 * @author : Zhiping Sun
 * @date : 2018年4月23日 上午10:27:58
 */
@Data
public class ReportQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = -926758428250251486L;
	
	/**
	 * 机构编码
	 */
	private String orgCode;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	
	/**
	 * 日期类型
	 * 1:居住日期
	 * 2:入住日期
	 * 3:离店日期
	 * 4:预定日期
	 */
	private Integer dateType;
	
	/**
	 * 开始日期
	 */
	private String beginDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	

}
