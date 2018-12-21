package com.fangcang.finance.invoice.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_agent_invoice")
public class AgentInvoiceDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商家编码
     */
    @Column(name = "merchant_code")
    private String merchantCode;

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
     * 开票方式：1按订单开票2按账单开票
     */
    @Column(name = "invoice_method")
    private Integer invoiceMethod;

    /**
     * 发票名称
     */
    @Column(name = "invoice_name")
    private String invoiceName;

    /**
     * 发票类型：1普通发票2专业发票3电子发票
     */
    @Column(name = "invoice_type")
    private Integer invoiceType;

    /**
     * 发票代码
     */
    @Column(name = "invoice_code")
    private String invoiceCode;

    /**
     * 发票抬头
     */
    private String title;

    /**
     * 发票内容
     */
    private String content;

    /**
     * 纳税人识别号
     */
    private String identifier;

    /**
     * 币种
     */
    private String currency;

    /**
     * 发票金额
     */
    private BigDecimal amount;

    /**
     * 开票日期
     */
    @Column(name = "invoice_date")
    private Date invoiceDate;

    /**
     * 发票状态
     */
    @Column(name = "invoice_status")
    private Integer invoiceStatus;

    /**
     * 备注
     */
    private String note;

    /**
     * 注册地址
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 公司电话
     */
    @Column(name = "company_telephone")
    private String companyTelephone;

    /**
     * 开户银行
     */
    @Column(name = "company_bank_name")
    private String companyBankName;

    /**
     * 取票方式:1邮寄2上门自提
     */
    @Column(name = "postal_type")
    private Integer postalType;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    @Column(name = "contact_telephone")
    private String contactTelephone;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 详情地址
     */
    private String address;

    /**
     * 快递公司
     */
    @Column(name = "express_company")
    private String expressCompany;

    /**
     * 快递单号
     */
    @Column(name = "express_no")
    private String expressNo;

    /**
     * 取票时间
     */
    @Column(name = "postal_date")
    private Date postalDate;

    /**
     * 是否有效
     */
    @Column(name = "is_invalid")
    private Integer isInvalid;

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
     * 获取开票方式：1按订单开票2按账单开票
     *
     * @return invoice_method - 开票方式：1按订单开票2按账单开票
     */
    public Integer getInvoiceMethod() {
        return invoiceMethod;
    }

    /**
     * 设置开票方式：1按订单开票2按账单开票
     *
     * @param invoiceMethod 开票方式：1按订单开票2按账单开票
     */
    public void setInvoiceMethod(Integer invoiceMethod) {
        this.invoiceMethod = invoiceMethod;
    }

    /**
     * 获取发票名称
     *
     * @return invoice_name - 发票名称
     */
    public String getInvoiceName() {
        return invoiceName;
    }

    /**
     * 设置发票名称
     *
     * @param invoiceName 发票名称
     */
    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    /**
     * 获取发票类型：1普通发票2专业发票3电子发票
     *
     * @return invoice_type - 发票类型：1普通发票2专业发票3电子发票
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 设置发票类型：1普通发票2专业发票3电子发票
     *
     * @param invoiceType 发票类型：1普通发票2专业发票3电子发票
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 获取发票代码
     *
     * @return invoice_code - 发票代码
     */
    public String getInvoiceCode() {
        return invoiceCode;
    }

    /**
     * 设置发票代码
     *
     * @param invoiceCode 发票代码
     */
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    /**
     * 获取发票抬头
     *
     * @return title - 发票抬头
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置发票抬头
     *
     * @param title 发票抬头
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取发票内容
     *
     * @return content - 发票内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置发票内容
     *
     * @param content 发票内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取纳税人识别号
     *
     * @return identifier - 纳税人识别号
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * 设置纳税人识别号
     *
     * @param identifier 纳税人识别号
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
     * 获取发票金额
     *
     * @return amount - 发票金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置发票金额
     *
     * @param amount 发票金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取开票日期
     *
     * @return invoice_date - 开票日期
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * 设置开票日期
     *
     * @param invoiceDate 开票日期
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * 获取发票状态
     *
     * @return invoice_status - 发票状态
     */
    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 设置发票状态
     *
     * @param invoiceStatus 发票状态
     */
    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
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
     * 获取注册地址
     *
     * @return company_address - 注册地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置注册地址
     *
     * @param companyAddress 注册地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取公司电话
     *
     * @return company_telephone - 公司电话
     */
    public String getCompanyTelephone() {
        return companyTelephone;
    }

    /**
     * 设置公司电话
     *
     * @param companyTelephone 公司电话
     */
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    /**
     * 获取开户银行
     *
     * @return company_bank_name - 开户银行
     */
    public String getCompanyBankName() {
        return companyBankName;
    }

    /**
     * 设置开户银行
     *
     * @param companyBankName 开户银行
     */
    public void setCompanyBankName(String companyBankName) {
        this.companyBankName = companyBankName;
    }

    /**
     * 获取取票方式:1邮寄2上门自提
     *
     * @return postal_type - 取票方式:1邮寄2上门自提
     */
    public Integer getPostalType() {
        return postalType;
    }

    /**
     * 设置取票方式:1邮寄2上门自提
     *
     * @param postalType 取票方式:1邮寄2上门自提
     */
    public void setPostalType(Integer postalType) {
        this.postalType = postalType;
    }

    /**
     * 获取联系人
     *
     * @return contacter - 联系人
     */
    public String getContacter() {
        return contacter;
    }

    /**
     * 设置联系人
     *
     * @param contacter 联系人
     */
    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    /**
     * 获取联系电话
     *
     * @return contact_telephone - 联系电话
     */
    public String getContactTelephone() {
        return contactTelephone;
    }

    /**
     * 设置联系电话
     *
     * @param contactTelephone 联系电话
     */
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取详情地址
     *
     * @return address - 详情地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详情地址
     *
     * @param address 详情地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取快递公司
     *
     * @return express_company - 快递公司
     */
    public String getExpressCompany() {
        return expressCompany;
    }

    /**
     * 设置快递公司
     *
     * @param expressCompany 快递公司
     */
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    /**
     * 获取快递单号
     *
     * @return express_no - 快递单号
     */
    public String getExpressNo() {
        return expressNo;
    }

    /**
     * 设置快递单号
     *
     * @param expressNo 快递单号
     */
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    /**
     * 获取取票时间
     *
     * @return postal_date - 取票时间
     */
    public Date getPostalDate() {
        return postalDate;
    }

    /**
     * 设置取票时间
     *
     * @param postalDate 取票时间
     */
    public void setPostalDate(Date postalDate) {
        this.postalDate = postalDate;
    }

    /**
     * 获取是否有效
     *
     * @return is_invalid - 是否有效
     */
    public Integer getIsInvalid() {
        return isInvalid;
    }

    /**
     * 设置是否有效
     *
     * @param isInvalid 是否有效
     */
    public void setIsInvalid(Integer isInvalid) {
        this.isInvalid = isInvalid;
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