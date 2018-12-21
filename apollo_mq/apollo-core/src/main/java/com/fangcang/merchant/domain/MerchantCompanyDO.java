package com.fangcang.merchant.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_merchant_company")
public class MerchantCompanyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "merchant_code")
    private String merchantCode;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮件主题
     */
    private String emailSubject;

    /**
     * logo
     */
    @Column(name = "logo_url")
    private String logoUrl;

    /**
     * 印章
     */
    private String seal;

    /**
     * 适用供应商：0所有、1指定
     */
    @Column(name = "apply_supply")
    private Integer applySupply;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "modify_time")
    private Date modifyTime;

    private String modifier;

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
     * 获取公司名称
     *
     * @return company - 公司名称
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置公司名称
     *
     * @param company 公司名称
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取电话
     *
     * @return telephone - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取邮箱
     *
     * @return template - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    /**
     * 获取logo
     *
     * @return logo_url - logo
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 设置logo
     *
     * @param logoUrl logo
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * 获取印章
     *
     * @return seal - 印章
     */
    public String getSeal() {
        return seal;
    }

    /**
     * 设置印章
     *
     * @param seal 印章
     */
    public void setSeal(String seal) {
        this.seal = seal;
    }

    /**
     * 获取适用供应商：0所有、1指定
     *
     * @return apply_supply - 适用供应商：0所有、1指定
     */
    public Integer getApplySupply() {
        return applySupply;
    }

    /**
     * 设置适用供应商：0所有、1指定
     *
     * @param applySupply 适用供应商：0所有、1指定
     */
    public void setApplySupply(Integer applySupply) {
        this.applySupply = applySupply;
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
}