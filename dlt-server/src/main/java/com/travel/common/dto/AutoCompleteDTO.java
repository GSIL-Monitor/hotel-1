package com.travel.common.dto;

import java.math.BigDecimal;

/**
 *   2018/1/14.
 */
public class AutoCompleteDTO {
    /**
     * 下拉框展示的名称
     */
    private String label;

    /**
     * 提交到后台的值
     */
    private String value;
    
    /**
     * 分销商信用额度
     */
    private BigDecimal amount;
    
    /**
     * 供应商编码
     */
    private String supplyCode;
    
    /**
     * 供应商名称
     */
    private String supplyName;
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

}
