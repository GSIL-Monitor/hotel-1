package com.fangcang.supplier.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_supply_other_cost")
public class SupplyCostDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supply_id")
    private Integer supplyId;

    @Column(name = "cost_name")
    private String costName;

    @Column(name = "begin_use_date")
    private Date beginUseDate;

    @Column(name = "end_use_date")
    private Date endUseDate;

    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @Column(name = "is_active")
    private Integer isActive;

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
     * @return supply_id
     */
    public Integer getSupplyId() {
        return supplyId;
    }

    /**
     * @param supplyId
     */
    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    /**
     * @return cost_name
     */
    public String getCostName() {
        return costName;
    }

    /**
     * @param costName
     */
    public void setCostName(String costName) {
        this.costName = costName;
    }

    /**
     * @return begin_use_date
     */
    public Date getBeginUseDate() {
        return beginUseDate;
    }

    /**
     * @param beginUseDate
     */
    public void setBeginUseDate(Date beginUseDate) {
        this.beginUseDate = beginUseDate;
    }

    /**
     * @return end_use_date
     */
    public Date getEndUseDate() {
        return endUseDate;
    }

    /**
     * @param endUseDate
     */
    public void setEndUseDate(Date endUseDate) {
        this.endUseDate = endUseDate;
    }

    /**
     * @return cost_price
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return is_active
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * @param isActive
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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