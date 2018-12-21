package com.travel.common.dto.order.request;

import java.math.BigDecimal;
import java.util.Date;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 订单附加项信息
 * @author : Zhiping Sun
 * @date : 2018年1月30日下午2:21:33
 */
@Data
public class OrderAdditionalRequestDTO extends ModifyDTO {

	private static final long serialVersionUID = 7338448091141959404L;
	
	private Long dayPriceId;
	
	private Long opId;
	
	private Long chargeId;
	
	private String chargeName;
	
	private Date saleDate;
	
	private Integer count;
	
	private BigDecimal salePrice;
	
	private BigDecimal basePrice;
	
	private BigDecimal rate;

}
