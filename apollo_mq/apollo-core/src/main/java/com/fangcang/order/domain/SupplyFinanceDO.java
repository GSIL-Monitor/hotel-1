package com.fangcang.order.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "o_supply_finance")
public class SupplyFinanceDO {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供货单id
     */
    @Column(name = "supply_order_id")
    private Integer supplyOrderId;

    /**
     * 结算金额
     */
    @Column(name = "settlement_amount")
    private BigDecimal settlementAmount;

    /**
     * 结算状态：0未结算，1已结算
     */
    @Column(name = "settlement_status")
    private Byte settlementStatus;

    /**
     * 结算日期
     */
    @Column(name = "settlement_date")
    private Date settlementDate;

    /**
     * 对账状态：0新建，1可出账，2已纳入账单，3已对账
     */
    @Column(name = "account_status")
    private Byte accountStatus;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 供货单编码
     */
    @Column(name = "supply_order_code")
    private String supplyOrderCode;

    /**
     * 累计已收款金额
     */
    @Column(name = "receipt_amount")
    private BigDecimal receiptAmount;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取供货单id
     *
     * @return supply_order_id - 供货单id
     */
    public Integer getSupplyOrderId() {
        return supplyOrderId;
    }

    /**
     * 设置供货单id
     *
     * @param supplyOrderId 供货单id
     */
    public void setSupplyOrderId(Integer supplyOrderId) {
        this.supplyOrderId = supplyOrderId;
    }

    /**
     * 获取结算金额
     *
     * @return settlement_amount - 结算金额
     */
    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * 设置结算金额
     *
     * @param settlementAmount 结算金额
     */
    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Byte getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Byte settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    /**
     * 获取对账状态：0新建，1可出账，2已纳入账单，3已对账
     *
     * @return account_status - 对账状态：0新建，1可出账，2已纳入账单，3已对账
     */
    public Byte getAccountStatus() {
        return accountStatus;
    }

    /**
     * 设置对账状态：0新建，1可出账，2已纳入账单，3已对账
     *
     * @param accountStatus 对账状态：0新建，1可出账，2已纳入账单，3已对账
     */
    public void setAccountStatus(Byte accountStatus) {
        this.accountStatus = accountStatus;
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
     * 获取供货单编码
     *
     * @return supply_order_code - 供货单编码
     */
    public String getSupplyOrderCode() {
        return supplyOrderCode;
    }

    /**
     * 设置供货单编码
     *
     * @param supplyOrderCode 供货单编码
     */
    public void setSupplyOrderCode(String supplyOrderCode) {
        this.supplyOrderCode = supplyOrderCode;
    }

    /**
     * 获取累计已收款金额
     *
     * @return receipt_amount - 累计已收款金额
     */
    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    /**
     * 设置累计已收款金额
     *
     * @param receiptAmount 累计已收款金额
     */
    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}