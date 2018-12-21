package com.fangcang.finance.bill.domain;

import com.fangcang.common.util.excel.ExcelColumn;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_fin_agent_bill_import")
public class AgentBillImportDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 导入批次号
     */
    @Column(name = "import_no")
    private Integer importNo;

    /**
     * 账单id
     */
    @Column(name = "bill_id")
    private Integer billId;

    /**
     * 渠道编码
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 客户单号
     */
    @Column(name = "customer_order_code")
    @ExcelColumn(name="客户单号",orderNum = "0")
    private String customerOrderCode;

    /**
     * 订单金额
     */
    @Column(name = "order_sum")
    @ExcelColumn(name="订单金额",orderNum = "1")
    private BigDecimal orderSum;

    @Column(name = "order_code")
    private String orderCode;

    /**
     * 是否匹配（1已匹配、2未匹配）
     */
    @Column(name = "is_match")
    private Integer isMatch;

    @Column(name = "fail_reason")
    private String failReason;

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
     * 获取导入批次号
     *
     * @return import_no - 导入批次号
     */
    public Integer getImportNo() {
        return importNo;
    }

    /**
     * 设置导入批次号
     *
     * @param importNo 导入批次号
     */
    public void setImportNo(Integer importNo) {
        this.importNo = importNo;
    }

    /**
     * 获取账单id
     *
     * @return bill_id - 账单id
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * 设置账单id
     *
     * @param billId 账单id
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * 获取渠道编码
     *
     * @return channel_code - 渠道编码
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道编码
     *
     * @param channelCode 渠道编码
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取客户单号
     *
     * @return customer_order_code - 客户单号
     */
    public String getCustomerOrderCode() {
        return customerOrderCode;
    }

    /**
     * 设置客户单号
     *
     * @param customerOrderCode 客户单号
     */
    public void setCustomerOrderCode(String customerOrderCode) {
        this.customerOrderCode = customerOrderCode;
    }

    /**
     * 获取订单金额
     *
     * @return order_sum - 订单金额
     */
    public BigDecimal getOrderSum() {
        return orderSum;
    }

    /**
     * 设置订单金额
     *
     * @param orderSum 订单金额
     */
    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    /**
     * @return order_code
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * @param orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取是否匹配（1已匹配、2未匹配）
     *
     * @return is_match - 是否匹配（1已匹配、2未匹配）
     */
    public Integer getIsMatch() {
        return isMatch;
    }

    /**
     * 设置是否匹配（1已匹配、2未匹配）
     *
     * @param isMatch 是否匹配（1已匹配、2未匹配）
     */
    public void setIsMatch(Integer isMatch) {
        this.isMatch = isMatch;
    }

    /**
     * @return fail_reason
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * @param failReason
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason;
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