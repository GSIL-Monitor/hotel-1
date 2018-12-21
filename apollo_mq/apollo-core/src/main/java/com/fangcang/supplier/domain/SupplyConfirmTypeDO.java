package com.fangcang.supplier.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_supply_confirm_type")
public class SupplyConfirmTypeDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供应商ID
     */
    @Column(name = "supply_id")
    private Integer supplyId;

    /**
     * 确认方式
     */
    @Column(name = "send_type")
    private String sendType;

    private String week;

    @Column(name = "begin_time")
    private Integer beginTime;

    @Column(name = "end_time")
    private Integer endTime;

    @Column(name = "confirm_no")
    private String confirmNo;

    private String contact;

    @Column(name = "contact_phone")
    private String contactPhone;

    private String creator;

    @Column(name = "create_date")
    private Date createDate;

    private String modifier;

    @Column(name = "modify_date")
    private Date modifyDate;

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
     * 获取供应商ID
     *
     * @return supply_id - 供应商ID
     */
    public Integer getSupplyId() {
        return supplyId;
    }

    /**
     * 设置供应商ID
     *
     * @param supplyId 供应商ID
     */
    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    /**
     * 获取确认方式
     *
     * @return send_type - 确认方式
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * 设置确认方式
     *
     * @param sendType 确认方式
     */
    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    /**
     * @return week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @param week
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * @return begin_time
     */
    public Integer getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     */
    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return end_time
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * @return confirm_no
     */
    public String getConfirmNo() {
        return confirmNo;
    }

    /**
     * @param confirmNo
     */
    public void setConfirmNo(String confirmNo) {
        this.confirmNo = confirmNo;
    }

    /**
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return contact_phone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * @return modify_date
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}