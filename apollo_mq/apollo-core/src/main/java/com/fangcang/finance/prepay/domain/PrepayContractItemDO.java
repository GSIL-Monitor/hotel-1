package com.fangcang.finance.prepay.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_prepay_contract_item")
public class PrepayContractItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 合约id
     */
    @Column(name = "contract_id")
    private Integer contractId;

    /**
     * 收付款类型（1收款,2付款）
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 备注
     */
    private String note;

    /**
     * 对象类型，1账单，2供货单
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 类型1为账单编码，类型2为供货单编码
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 类型1为账单名称
     */
    @Column(name = "bill_name")
    private String billName;

    /**
     * 财务工单编码
     */
    @Column(name = "finance_code")
    private String financeCode;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

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
     * 获取合约id
     *
     * @return contract_id - 合约id
     */
    public Integer getContractId() {
        return contractId;
    }

    /**
     * 设置合约id
     *
     * @param contractId 合约id
     */
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    /**
     * 获取收付款类型（1收款,2付款）
     *
     * @return type - 收付款类型（1收款,2付款）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置收付款类型（1收款,2付款）
     *
     * @param type 收付款类型（1收款,2付款）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取对象类型，1账单，2订单
     *
     * @return order_type - 对象类型，1账单，2订单
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置对象类型，1账单，2订单
     *
     * @param orderType 对象类型，1账单，2订单
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取类型1为账单编码，类型2为订单id
     *
     * @return order_code - 类型1为账单编码，类型2为订单id
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置类型1为账单编码，类型2为订单id
     *
     * @param orderCode 类型1为账单编码，类型2为订单id
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取类型1为账单名称
     *
     * @return bill_name - 类型1为账单名称
     */
    public String getBillName() {
        return billName;
    }

    /**
     * 设置类型1为账单名称
     *
     * @param billName 类型1为账单名称
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }

    /**
     * 获取财务工单编码
     *
     * @return finance_code - 财务工单编码
     */
    public String getFinanceCode() {
        return financeCode;
    }

    /**
     * 设置财务工单编码
     *
     * @param financeCode 财务工单编码
     */
    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
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
}