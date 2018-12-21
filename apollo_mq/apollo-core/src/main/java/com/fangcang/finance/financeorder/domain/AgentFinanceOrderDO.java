package com.fangcang.finance.financeorder.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_agent_finance_order")
public class AgentFinanceOrderDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 工单号
     */
    @Column(name = "finance_code")
    private String financeCode;

    /**
     * 工单类型（）
     */
    @Column(name = "finance_type")
    private Integer financeType;

    /**
     * 工单状态（）
     */
    @Column(name = "finance_status")
    private Integer financeStatus;

    /**
     * 业务单号(订单号/账单号)
     */
    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "bill_name")
    private String billName;

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
     * 商家编码
     */
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
     * 获取工单号
     *
     * @return finance_code - 工单号
     */
    public String getFinanceCode() {
        return financeCode;
    }

    /**
     * 设置工单号
     *
     * @param financeCode 工单号
     */
    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    /**
     * 获取工单类型（）
     *
     * @return finance_type - 工单类型（）
     */
    public Integer getFinanceType() {
        return financeType;
    }

    /**
     * 设置工单类型（）
     *
     * @param financeType 工单类型（）
     */
    public void setFinanceType(Integer financeType) {
        this.financeType = financeType;
    }

    /**
     * 获取工单状态（）
     *
     * @return finance_status - 工单状态（）
     */
    public Integer getFinanceStatus() {
        return financeStatus;
    }

    /**
     * 设置工单状态（）
     *
     * @param financeStatus 工单状态（）
     */
    public void setFinanceStatus(Integer financeStatus) {
        this.financeStatus = financeStatus;
    }

    /**
     * 获取业务单号(订单号/账单号)
     *
     * @return order_code - 业务单号(订单号/账单号)
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置业务单号(订单号/账单号)
     *
     * @param orderCode 业务单号(订单号/账单号)
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * @return bill_name
     */
    public String getBillName() {
        return billName;
    }

    /**
     * @param billName
     */
    public void setBillName(String billName) {
        this.billName = billName;
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
     * 获取商家编码
     *
     * @return merchant_code - 商家编码
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * 设置商家编码
     *
     * @param merchantCode 商家编码
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