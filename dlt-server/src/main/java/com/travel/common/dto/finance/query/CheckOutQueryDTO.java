package com.travel.common.dto.finance.query;

import java.util.List;

import com.travel.common.dto.GenericQueryDTO;

/**
 * @Description : 机构出账查询参数
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午4:38:18
 */

public class CheckOutQueryDTO extends GenericQueryDTO {
	
	private static final long serialVersionUID = -6839783805852616349L;
	
	/**
	 * 订单每日售卖id
	 */
	private List<Long> dayPriceIdList;
	
	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 币种
	 */
	private String currency;
	
	/**
	 * 入住人
	 */
	private String guestName;
	
	/**
	 * 订单编码
	 */
	private String orderCode;
	
	/**
	 * 出账状态
	 */
	private Integer billState;
	
	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 部门编号
	 */
	private String deptNo;
	
	/**
	 * 开始日期
	 */
	private String beginDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	
	/**
	 * 日期类型(1:在住;2:入住;3:离店;4:预定)
	 */
	private Integer dateType;
	
	/**
	 * 尚欠是否大于0
	 */
	private boolean isGreaterZero = false;

	/**
	 * 订单状态(查询页面属性)
	 */
	private Integer orderState;
	
	/**
	 * 订单状态
	 */
	private List<String> orderStateList;

	/**
	 * 订单支付方式
	 * 0:现付;1:所有费用挂账/房费挂账杂费自理
	 */
	private Integer payType;
	
	/**
	 * 支付类型(prepay:预付;pay:现付返佣)
	 */
	private List<String> payMethodList;
	
	/**
	 * 支付类型(prepay:预付;pay:现付返佣)
	 */
	private String payMethod;

	public List<Long> getDayPriceIdList() {
		return dayPriceIdList;
	}

	public void setDayPriceIdList(List<Long> dayPriceIdList) {
		this.dayPriceIdList = dayPriceIdList;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getBillState() {
		return billState;
	}

	public void setBillState(Integer billState) {
		this.billState = billState;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	public boolean getIsGreaterZero() {
		return isGreaterZero;
	}

	public void setIsGreaterZero(boolean isGreaterZero) {
		this.isGreaterZero = isGreaterZero;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public List<String> getOrderStateList() {
		return orderStateList;
	}

	public void setOrderStateList(List<String> orderStateList) {
		this.orderStateList = orderStateList;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public List<String> getPayMethodList() {
		return payMethodList;
	}

	public void setPayMethodList(List<String> payMethodList) {
		this.payMethodList = payMethodList;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
}
