package com.travel.common.dto.finance.request;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 销账明细
 * @author : Zhiping Sun
 * @date : 2018年3月20日上午9:29:41
 */
@Data
public class CheckOutRequestDTO extends ModifyDTO {
	
	private static final long serialVersionUID = 6548513871411968956L;
	
	/**
	 * 订单每日售卖id
	 */
	private Long dayPriceId;
	
	/**
	 * 本次收/本次付
	 */
	private BigDecimal currentActualReceived;

}
