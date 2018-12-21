package com.fangcang.supplier.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SupplyConfirmTypeDTO implements Serializable{

    private static final long serialVersionUID = 2907033226378435413L;

    /**
     * 确认方式ID
     */
    private Integer id;

    /**
     * 供应商ID
     */
    private Integer supplyId;

    /**
     * 发送方式
     * EBK(1, "EBK"),EMAIL(2,"EMAIL"),FAX(3,"FAX"),PHONE(4,"PHONE"),WECHAT(5,"WECHAT"),QQ(6,"QQ");
     */
    private String sendType;

    /**
     * 星期，逗号
     */
    private String week;

    /**
     * 开始时间：800
     */
    private Integer beginTime;

    /**
     * 结束时间：1800
     */
    private Integer endTime;

    /**
     * 确认号
     */
    private String confirmNo;

    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String contactPhone;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;
}