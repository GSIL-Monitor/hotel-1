package com.travel.common.dto.finance.query;

import java.util.Date;
import java.util.List;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 每日小结明细查询参数
 * @author : Zhiping Sun
 * @date : 2018年3月19日上午11:19:28
 */
@Data
public class OrgCheckOutDetailQueryDTO extends GenericQueryDTO {
	
	private static final long serialVersionUID = -241289750851654283L;
	
	/**
	 * 订单编码集合
	 */
	private List<String> orderCodeList;
	
	/**
	 * 售卖日期
	 */
	private Date saleDate;

}
