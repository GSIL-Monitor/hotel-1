package com.fangcang.order.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "o_order_finance")
public class OrderFinanceDO {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

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

    /**
     * 财务锁单状态，1：已锁定，2：未锁定
     */
    @Column(name = "finance_lock_status")
    private Byte financeLockStatus;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单编码
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 累计退款金额
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * 获取财务锁单状态，1：已锁定，2：未锁定
     *
     * @return finance_lock_status - 财务锁单状态，1：已锁定，2：未锁定
     */
    public Byte getFinanceLockStatus() {
        return financeLockStatus;
    }

    /**
     * 设置财务锁单状态，1：已锁定，2：未锁定
     *
     * @param financeLockStatus 财务锁单状态，1：已锁定，2：未锁定
     */
    public void setFinanceLockStatus(Byte financeLockStatus) {
        this.financeLockStatus = financeLockStatus;
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
     * 获取订单编码
     *
     * @return order_code - 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编码
     *
     * @param orderCode 订单编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取累计退款金额
     *
     * @return refund_amount - 累计退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置累计退款金额
     *
     * @param refundAmount 累计退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}