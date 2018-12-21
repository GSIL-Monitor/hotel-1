package com.fangcang.finance.financeorder.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_pay_item")
public class PayItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     */
    @Column(name = "pay_item_type")
    private Integer payItemType;

    /**
     * 关联id
     */
    @Column(name = "relation_id")
    private Integer relationId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 折合商家本币金额
     */
    @Column(name = "exchange_amount")
    private BigDecimal exchangeAmount;

    /**
     * 商家本币币种
     */
    @Column(name = "exchange_currency")
    private String exchangeCurrency;

    /**
     * 到账时间
     */
    @Column(name = "arrival_date")
    private Date arrivalDate;

    /**
     * 凭证单号
     */
    @Column(name = "serial_no")
    private String serialNo;

    /**
     * 备注
     */
    private String note;

    /**
     * 供应商开户行
     */
    @Column(name = "org_bank_name")
    private String orgBankName;

    /**
     * 供应商银行账户
     */
    @Column(name = "org_bank_account")
    private String orgBankAccount;

    /**
     * 商家银行卡id
     */
    @Column(name = "merchant_bank_id")
    private Integer merchantBankId;

    /**
     * 商家开户行
     */
    @Column(name = "merchant_bank_name")
    private String merchantBankName;

    /**
     * 商家银行账户
     */
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
     * 获取类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     *
     * @return pay_item_type - 类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     */
    public Integer getPayItemType() {
        return payItemType;
    }

    /**
     * 设置类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     *
     * @param payItemType 类型：1分销商现金,2分销商押金,3供应商现金,4供应商押金
     */
    public void setPayItemType(Integer payItemType) {
        this.payItemType = payItemType;
    }

    /**
     * 获取关联id
     *
     * @return relation_id - 关联id
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * 设置关联id
     *
     * @param relationId 关联id
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
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
     * 获取折合商家本币金额
     *
     * @return exchange_amount - 折合商家本币金额
     */
    public BigDecimal getExchangeAmount() {
        return exchangeAmount;
    }

    /**
     * 设置折合商家本币金额
     *
     * @param exchangeAmount 折合商家本币金额
     */
    public void setExchangeAmount(BigDecimal exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    /**
     * 获取商家本币币种
     *
     * @return exchange_currency - 商家本币币种
     */
    public String getExchangeCurrency() {
        return exchangeCurrency;
    }

    /**
     * 设置商家本币币种
     *
     * @param exchangeCurrency 商家本币币种
     */
    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    /**
     * 获取到账时间
     *
     * @return arrival_date - 到账时间
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * 设置到账时间
     *
     * @param arrivalDate 到账时间
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
     * 获取供应商开户行
     *
     * @return org_bank_name - 供应商开户行
     */
    public String getOrgBankName() {
        return orgBankName;
    }

    /**
     * 设置供应商开户行
     *
     * @param orgBankName 供应商开户行
     */
    public void setOrgBankName(String orgBankName) {
        this.orgBankName = orgBankName;
    }

    /**
     * 获取供应商银行账户
     *
     * @return org_bank_account - 供应商银行账户
     */
    public String getOrgBankAccount() {
        return orgBankAccount;
    }

    /**
     * 设置供应商银行账户
     *
     * @param orgBankAccount 供应商银行账户
     */
    public void setOrgBankAccount(String orgBankAccount) {
        this.orgBankAccount = orgBankAccount;
    }

    /**
     * 获取商家银行卡id
     *
     * @return merchant_bank_id - 商家银行卡id
     */
    public Integer getMerchantBankId() {
        return merchantBankId;
    }

    /**
     * 设置商家银行卡id
     *
     * @param merchantBankId 商家银行卡id
     */
    public void setMerchantBankId(Integer merchantBankId) {
        this.merchantBankId = merchantBankId;
    }

    /**
     * 获取商家开户行
     *
     * @return merchant_bank_name - 商家开户行
     */
    public String getMerchantBankName() {
        return merchantBankName;
    }

    /**
     * 设置商家开户行
     *
     * @param merchantBankName 商家开户行
     */
    public void setMerchantBankName(String merchantBankName) {
        this.merchantBankName = merchantBankName;
    }

    /**
     * 获取商家银行账户
     *
     * @return merchant_bank_account - 商家银行账户
     */
    public String getMerchantBankAccount() {
        return merchantBankAccount;
    }

    /**
     * 设置商家银行账户
     *
     * @param merchantBankAccount 商家银行账户
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