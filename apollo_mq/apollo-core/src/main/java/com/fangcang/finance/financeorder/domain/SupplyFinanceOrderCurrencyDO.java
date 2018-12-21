package com.fangcang.finance.financeorder.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_supply_finance_order_currency")
public class SupplyFinanceOrderCurrencyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "finance_order_id")
    private Integer financeOrderId;

    /**
     * 币种
     */
    private String currency;

    /**
     * 应收金额
     */
    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

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
     * 获取应收金额
     *
     * @return receivable_amount - 应收金额
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 设置应收金额
     *
     * @param receivableAmount 应收金额
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
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