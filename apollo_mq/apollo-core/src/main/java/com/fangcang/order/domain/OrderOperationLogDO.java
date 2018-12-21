package com.fangcang.order.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "o_order_operation_log")
public class OrderOperationLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单编码
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作对象
     */
    @Column(name = "operate_object")
    private String operateObject;

    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

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
     * 获取订单编码
     *
     * @return order_id - 订单编码
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编码
     *
     * @param orderId 订单编码
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取操作内容
     *
     * @return content - 操作内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置操作内容
     *
     * @param content 操作内容
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
     * 获取操作对象
     *
     * @return operate_object - 操作对象
     */
    public String getOperateObject() {
        return operateObject;
    }

    /**
     * 设置操作对象
     *
     * @param operateObject 操作对象
     */
    public void setOperateObject(String operateObject) {
        this.operateObject = operateObject;
    }

    /**
     * 获取操作时间
     *
     * @return operate_time - 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}