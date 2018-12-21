package com.fangcang.order.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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