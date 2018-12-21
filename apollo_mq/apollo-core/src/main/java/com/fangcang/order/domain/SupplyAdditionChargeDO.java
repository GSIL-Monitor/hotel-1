package com.fangcang.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "o_supply_addition_charge")
public class SupplyAdditionChargeDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 供货单id
     */
    @Column(name = "supply_order_id")
    private Integer supplyOrderId;

    /**
     * 附加项名称
     */
    private String name;

    /**
     * 附加项总底价
     */
    @Column(name = "base_price_sum")
    private BigDecimal basePriceSum;

    /**
     * 附加项总售价
     */
    @Column(name = "sale_price_sum")
    private BigDecimal salePriceSum;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 附加费类型：1附加项（ 加房/ 加早/其他）、2退改费
     */
    @Column(name = "addition_type")
    private Byte additionType;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 附加项底价
     */
    @Column(name = "base_price")
    private BigDecimal basePrice;

    /**
     * 附加项售价
     */
    @Column(name = "sale_price")
    private BigDecimal salePrice;

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
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * 获取附加项名称
     *
     * @return name - 附加项名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置附加项名称
     *
     * @param name 附加项名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取附加项总底价
     *
     * @return base_price_sum - 附加项总底价
     */
    public BigDecimal getBasePriceSum() {
        return basePriceSum;
    }

    /**
     * 设置附加项总底价
     *
     * @param basePriceSum 附加项总底价
     */
    public void setBasePriceSum(BigDecimal basePriceSum) {
        this.basePriceSum = basePriceSum;
    }

    /**
     * 获取附加项总售价
     *
     * @return sale_price_sum - 附加项总售价
     */
    public BigDecimal getSalePriceSum() {
        return salePriceSum;
    }

    /**
     * 设置附加项总售价
     *
     * @param salePriceSum 附加项总售价
     */
    public void setSalePriceSum(BigDecimal salePriceSum) {
        this.salePriceSum = salePriceSum;
    }

    /**
     * 获取购买数量
     *
     * @return num - 购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置购买数量
     *
     * @param num 购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取附加费类型：1附加项（ 加房/ 加早/其他）、2退改费
     *
     * @return addition_type - 附加费类型：1附加项（ 加房/ 加早/其他）、2退改费
     */
    public Byte getAdditionType() {
        return additionType;
    }

    /**
     * 设置附加费类型：1附加项（ 加房/ 加早/其他）、2退改费
     *
     * @param additionType 附加费类型：1附加项（ 加房/ 加早/其他）、2退改费
     */
    public void setAdditionType(Byte additionType) {
        this.additionType = additionType;
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
     * 获取天数
     *
     * @return days - 天数
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 设置天数
     *
     * @param days 天数
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 获取附加项底价
     *
     * @return base_price - 附加项底价
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * 设置附加项底价
     *
     * @param basePrice 附加项底价
     */
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * 获取附加项售价
     *
     * @return sale_price - 附加项售价
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * 设置附加项售价
     *
     * @param salePrice 附加项售价
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}