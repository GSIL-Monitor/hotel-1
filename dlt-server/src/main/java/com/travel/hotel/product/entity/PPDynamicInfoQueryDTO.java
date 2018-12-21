package com.travel.hotel.product.entity;

import java.io.Serializable;
import java.util.Date;
//
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;

public class PPDynamicInfoQueryDTO  implements Serializable {
	/**
	 * 酒店id
	 */
	private Long hotelId;
	/**
	 * 房型id
	 */
	private Long roomTypeId;
	/**
	 * 产品id(同一酒店下的)
	 */
	//private List<Long> pricePlanIds;
	/**
	 * 商家编码
	 */
	private String merchantCode;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 产品供应方式，默认查询商家自签
	 */
	private Integer supplyWay=1;
	/**
	 * 产品类型
	 */
	private Integer payMethod;
	/**
	 * 开始日期
	 */
	private Date beginDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	
	private int currentPage;
	
	private int pageSize;
	
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
/*	public List<Long> getPricePlanIds() {
		return pricePlanIds;
	}
	public void setPricePlanIds(List<Long> pricePlanIds) {
		this.pricePlanIds = pricePlanIds;
	}*/
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public Integer getSupplyWay() {
		return supplyWay;
	}
	public void setSupplyWay(Integer supplyWay) {
		this.supplyWay = supplyWay;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PPDynamicInfoQueryDTO{" +
				"hotelId=" + hotelId +
				", roomTypeId=" + roomTypeId +
				", merchantCode='" + merchantCode + '\'' +
				", supplierCode='" + supplierCode + '\'' +
				", supplierName='" + supplierName + '\'' +
				", supplyWay=" + supplyWay +
				", payMethod=" + payMethod +
				", beginDate=" + beginDate +
				", endDate=" + endDate +
				", currentPage=" + currentPage +
				", pageSize=" + pageSize +
				'}';
	}
}
