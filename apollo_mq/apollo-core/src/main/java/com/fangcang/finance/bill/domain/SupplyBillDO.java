package com.fangcang.finance.bill.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_fin_supply_bill")
public class SupplyBillDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账单编码
     */
    @Column(name = "bill_code")
    private String billCode;

    /**
     * 账单名称
     */
    @Column(name = "bill_name")
    private String billName;

    /**
     * 账单状态
     */
    @Column(name = "bill_status")
    private Integer billStatus;

    /**
     * 机构编码
     */
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 机构名称
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 开始日期
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 是否自动对账（0手动，1自动）
     */
    @Column(name = "is_auto")
    private Integer isAuto;

    @Column(name = "merchant_code")
    private String merchantCode;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账单编码
     *
     * @return bill_code - 账单编码
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * 设置账单编码
     *
     * @param billCode 账单编码
     */
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    /**
     * 获取账单名称
     *
     * @return bill_name - 账单名称
     */
    public String getBillName() {
        return billName;
    }

    /**
     * 设置账单名称
     *
     * @param billName 账单名称
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * 获取账单状态
     *
     * @return bill_status - 账单状态
     */
    public Integer getBillStatus() {
        return billStatus;
    }

    /**
     * 设置账单状态
     *
     * @param billStatus 账单状态
     */
    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * 获取机构编码
     *
     * @return org_code - 机构编码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置机构编码
     *
     * @param orgCode 机构编码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取机构名称
     *
     * @return org_name - 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置机构名称
     *
     * @param orgName 机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取开始日期
     *
     * @return begin_date - 开始日期
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始日期
     *
     * @param beginDate 开始日期
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束日期
     *
     * @return end_date - 结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期
     *
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取是否自动对账（0手动，1自动）
     *
     * @return is_auto - 是否自动对账（0手动，1自动）
     */
    public Integer getIsAuto() {
        return isAuto;
    }

    /**
     * 设置是否自动对账（0手动，1自动）
     *
     * @param isAuto 是否自动对账（0手动，1自动）
     */
    public void setIsAuto(Integer isAuto) {
        this.isAuto = isAuto;
    }

    /**
     * @return merchant_code
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * @param merchantCode
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}