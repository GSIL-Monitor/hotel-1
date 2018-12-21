package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderOperationLogDTO implements Serializable {

    private static final long serialVersionUID = 1573686458870612855L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 订单编码
     */
    private Integer orderId;

    /**
     * 操作类型：1：订单信息，2：产品信息，3：附件
     */
    private Integer operationType;

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
    private String operateObject;

    /**
     * 操作时间
     */
    private String operateTime;

}