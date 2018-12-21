package com.fangcang.finance.bill.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_supply_bill_currency")
public class SupplyBillCurrencyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

    @Column(name = "paidin_amount")
    private BigDecimal paidinAmount;

    @Column(name = "notice_amount")
    private BigDecimal noticeAmount;

    private String currency;

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
     * @return bill_id
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * @param billId
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * @return receivable_amount
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * @param receivableAmount
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * @return paidin_amount
     */
    public BigDecimal getPaidinAmount() {
        return paidinAmount;
    }

    /**
     * @param paidinAmount
     */
    public void setPaidinAmount(BigDecimal paidinAmount) {
        this.paidinAmount = paidinAmount;
    }

    /**
     * @return notice_amount
     */
    public BigDecimal getNoticeAmount() {
        return noticeAmount;
    }

    /**
     * @param noticeAmount
     */
    public void setNoticeAmount(BigDecimal noticeAmount) {
        this.noticeAmount = noticeAmount;
    }

    /**
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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