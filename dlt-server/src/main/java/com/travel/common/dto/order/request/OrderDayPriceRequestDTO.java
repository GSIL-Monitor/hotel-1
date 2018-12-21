package com.travel.common.dto.order.request;

import java.math.BigDecimal;
import java.util.Date;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 每日价格
 * @author : Zhiping Sun
 * @date : 2018年1月30日上午11:58:53
 */
@Data
public class OrderDayPriceRequestDTO extends ModifyDTO {
	
	private static final long serialVersionUID = -2016199973660373717L;
	
	private Long dayPriceId;

    private Long opId;
	
	private Long pricePlanId;
	
	private String bedType;
	
	private String breakFast;
	
	private Integer roomNum;
	
	private Date saleDate;
	
	private BigDecimal rate;
	
	private BigDecimal salePrice;
	
	private BigDecimal basePrice;
	
}
