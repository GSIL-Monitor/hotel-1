package com.travel.common.dto.finance.response;

import java.math.BigDecimal;
import java.util.Date;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 财务对账订单信息
 * @author : Zhiping Sun
 * @date : 2018年1月14日上午9:44:04
 */
@Data
public class BillOrderResponseDTO extends ModifyDTO {
	
	private static final long serialVersionUID = 6386281327751754853L;
	
	/**
	 * 订单id
	 */
	private Long orderId;
	
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 入住人
	 */
	private String guestName;
	
	/**
	 * 房间号码
	 */
	private String roomNumber;
	
	/**
	 * 房型id
	 */
	private Long roomTypeId;
	
	/**
	 * 房型名称
	 */
	private String roomTypeName;
	
	/**
	 * 间数
	 */
	private Integer roomCount;
	
	/**
	 * 晚数
	 */
	private Integer nightCount;
	
	/**
	 * 房费金额
	 */
	private BigDecimal roomPrice;
	
	/**
	 * 支付方式
	 */
	private Integer payMethod;
	
	/**
	 * 支付方式描述
	 */
	private String payMethodText;
	
	/**
	 * 应收金额
	 */
	private BigDecimal receiveAmount;
	
	/**
	 * 应付金额
	 */
	private BigDecimal payableAmount;
	
	/**
	 * 佣金金额
	 */
	private BigDecimal commission;
	
	/**
	 * 入住日期
	 */
	private Date checkInDate;
	
	/**
	 * 离店日期
	 */
	private Date checkOutDate;
	
	/**
	 * 售卖币种
	 */
	private String saleCurrency;
	
	/**
	 * 售卖币种描述
	 */
	private String saleCurrencyText;
	
	/**
	 * 低价币种
	 */
	private String baseCurrency;
	
	/**
	 * 低价币种描述
	 */
	private String baseCurrencyText;
	
	/**
	 * 客户对账状态
	 */
	private Integer orderBillStatus;
	
	/**
	 * 客户对账状态描述
	 */
	private String orderBillStatusText;
	
	/**
	 * 供应商对账状态
	 */
	private Integer supplyBillStatus;
	
	/**
	 * 供应商对账状态描述
	 */
	private String supplyBillStatusText;
	
}
