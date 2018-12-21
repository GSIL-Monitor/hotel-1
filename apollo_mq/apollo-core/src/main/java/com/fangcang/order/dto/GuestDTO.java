package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 入住人
 */
@Data
public class GuestDTO implements Serializable {
    private static final long serialVersionUID = -9199599483822133562L;

    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 入住人名称
     */
    private String guestName;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 手机
     */
    private String phone;

    /**
     * 证件类型
     */
    private Byte idType;

    /**
     * 证件号码
     */
    private String idNumber;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 名
     */
    private String firstName;

    /**
     * 姓
     */
    private String lastName;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}