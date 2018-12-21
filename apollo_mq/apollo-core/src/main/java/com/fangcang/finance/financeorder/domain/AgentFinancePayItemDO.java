package com.fangcang.finance.financeorder.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_agent_finance_pay_item")
public class AgentFinancePayItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "finance_order_id")
    private Integer financeOrderId;

    /**
     *合约iD
     */
    @Column(name = "contract_id")
    private Integer contractId;

    /**
     * 支付通道（）
     */
    private Integer passage;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 折合金额
     */
    @Column(name = "exchange_amount")
    private BigDecimal exchangeAmount;

    @Column(name = "exchange_currency")
    private Integer exchangeCurrency;

    @Column(name = "arrival_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date arrivalDate;

    /**
     * 凭证单号
     */
    @Column(name = "serial_no")
    private String serialNo;

    private String note;

    @Column(name = "org_bank_name")
    private String orgBankName;

    @Column(name = "org_bank_account")
    private String orgBankAccount;

    @Column(name = "merchant_bank_id")
    private Integer merchantBankId;

    @Column(name = "merchant_bank_name")
    private String merchantBankName;

    @Column(name = "merchant_bank_account")
    private String merchantBankAccount;

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
     * @return finance_order_id
     */
    public Integer getFinanceOrderId() {
        return financeOrderId;
    }

    /**
     * @param financeOrderId
     */
    public void setFinanceOrderId(Integer financeOrderId) {
        this.financeOrderId = financeOrderId;
    }

    /**
     * 获取支付通道（）
     *
     * @return passage - 支付通道（）
     */
    public Integer getPassage() {
        return passage;
    }

    /**
     * 设置支付通道（）
     *
     * @param passage 支付通道（）
     */
    public void setPassage(Integer passage) {
        this.passage = passage;
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
     * 获取币种
     *
     * @return currency - 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币种
     *
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取折合金额
     *
     * @return exchange_amount - 折合金额
     */
    public BigDecimal getExchangeAmount() {
        return exchangeAmount;
    }

    /**
     * 设置折合金额
     *
     * @param exchangeAmount 折合金额
     */
    public void setExchangeAmount(BigDecimal exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    /**
     * @return exchange_currency
     */
    public Integer getExchangeCurrency() {
        return exchangeCurrency;
    }

    /**
     * @param exchangeCurrency
     */
    public void setExchangeCurrency(Integer exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    /**
     * @return arrival_date
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @param arrivalDate
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * 获取凭证单号
     *
     * @return serial_no - 凭证单号
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 设置凭证单号
     *
     * @param serialNo 凭证单号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return org_bank_name
     */
    public String getOrgBankName() {
        return orgBankName;
    }

    /**
     * @param orgBankName
     */
    public void setOrgBankName(String orgBankName) {
        this.orgBankName = orgBankName;
    }

    /**
     * @return org_bank_account
     */
    public String getOrgBankAccount() {
        return orgBankAccount;
    }

    /**
     * @param orgBankAccount
     */
    public void setOrgBankAccount(String orgBankAccount) {
        this.orgBankAccount = orgBankAccount;
    }

    /**
     * @return merchant_bank_id
     */
    public Integer getMerchantBankId() {
        return merchantBankId;
    }

    /**
     * @param merchantBankId
     */
    public void setMerchantBankId(Integer merchantBankId) {
        this.merchantBankId = merchantBankId;
    }

    /**
     * @return merchant_bank_name
     */
    public String getMerchantBankName() {
        return merchantBankName;
    }

    /**
     * @param merchantBankName
     */
    public void setMerchantBankName(String merchantBankName) {
        this.merchantBankName = merchantBankName;
    }

    /**
     * @return merchant_bank_account
     */
    public String getMerchantBankAccount() {
        return merchantBankAccount;
    }

    /**
     * @param merchantBankAccount
     */
    public void setMerchantBankAccount(String merchantBankAccount) {
        this.merchantBankAccount = merchantBankAccount;
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