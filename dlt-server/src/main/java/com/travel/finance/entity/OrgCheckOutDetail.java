package com.travel.finance.entity;

import com.travel.common.dto.ModifyDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : 机构出账明细
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午3:08:42
 */
@Data
public class OrgCheckOutDetail extends ModifyDTO {
	
	private static final long serialVersionUID = -6124527647777155856L;
	
	/**
	 * 订单每日售卖id
	 */
	private Long dayPriceId;
	
	/**
	 * 价格计划id
	 */
	private Long pricePlanId;
	
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	/**
	 * 机构编码(分销商编码/供应商编码)
	 */
	private String orgCode;
	
	/**
	 * 机构名称(分销商名称/供应商名称)
	 */
	private String orgName;
	
	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 房型id
	 */
	private Long roomTypeId;
	
	/**
	 * 房型名称
	 */
	private String roomTypeName;
	
	/**
	 * 订单状态
	 */
	private String orderState;
	
	/**
	 * 入住人姓名
	 */
	private String guestName;
	
	/**
	 * 房号
	 */
	private String roomNo;
	
	/**
	 * 入住日期
	 */
	private String checkInDate;
	
	/**
	 * 离店日期
	 */
	private String checkOutDate;
	
	/**
	 * 间数
	 */
	private Integer rooms;
	
	/**
	 * 支付方式
	 */
	private String payMethod;
	
	/**
	 * 房费/杂费
	 */
	private BigDecimal roomPrice;
	
	/**
	 * 应收/应付
	 */
	private BigDecimal receivable;
	
	/**
	 * 已收/已付
	 */
	private BigDecimal actualReceived;
	
	/**
	 * 未收/未付
	 */
	private BigDecimal unreceivable;
	
	/**
	 * 本次已收/已付
	 */
	private BigDecimal currentActualReceived;
	
	/**
	 * 订单创建人
	 */
	private String orderCreator;
	
	/**
	 * 销账凭证号
	 */
	private String serialNumber;
	
	/**
	 * 客户订单对账状态
	 */
	private Integer orderBillStatus;
	
	/**
	 * 客户对账人
	 */
	private String orderBillUser;
	
	/**
	 * 供应商订单对账状态
	 */
	private Integer supplyOrderBillStatus;
	
	/**
	 * 供应商对账人
	 */
	private String supplyBillUser;

	/**
	 * 回传单号
	 */
	private List<String> attachUrl;
}
