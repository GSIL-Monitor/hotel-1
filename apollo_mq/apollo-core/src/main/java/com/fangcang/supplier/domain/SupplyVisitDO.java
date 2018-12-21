package com.fangcang.supplier.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 供应商拜访记录管理
 * @Author: yanming.li@fangcang.com
 * @CreateDate: 2018/5/31 20:34
 */
@Data
public class SupplyVisitDO {


    private Long supplyVisitId;

    private Long supplyId;

    /**
     * 商家编码
     */
    private String supplyCode;

    /**
     * 拜访时间
     */
    private Date visitTime;

    /**
     * 拜访内容
     */
    private String visitContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 拜访人
     */
    private String visitor;

    /**
     * 供应商名称(拜访记录表没有该字段)
     */
    private String supplyName;

}