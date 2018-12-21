package com.travel.finance.entity;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 单天出账信息
 * @author : Zhiping Sun
 * @date : 2018年3月15日下午5:04:27
 */
@Data
public class DayCheckOut extends ModifyDTO {

	private static final long serialVersionUID = -4635401279147406863L;

	private String title = "单天小结";
	
	/**
	 * 售卖日期
	 */
	private String saleDate;
	
	/**
	 * 间数
	 */
	private Integer rooms;
	
	/**
	 * 应收/应付
	 */
	private BigDecimal receivable;
	
	/**
	 * 已收/已付
	 */
	private BigDecimal actualReceived;
	
	/**
	 * 尚欠
	 */
	private BigDecimal unreceivable;
}
