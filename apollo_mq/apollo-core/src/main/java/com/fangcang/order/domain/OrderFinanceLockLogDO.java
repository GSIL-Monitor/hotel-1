package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_finance_lock_log")
public class OrderFinanceLockLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单内容
     */
    private String content;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    @Column(name = "operator_time")
    private Date operatorTime;

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

    /**
     * 获取订单内容
     *
     * @return content - 订单内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置订单内容
     *
     * @param content 订单内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作时间
     *
     * @return operator_time - 操作时间
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     * 设置操作时间
     *
     * @param operatorTime 操作时间
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
}