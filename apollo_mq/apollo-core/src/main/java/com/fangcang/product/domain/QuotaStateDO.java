package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import com.fangcang.product.dto.DateDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class QuotaStateDO extends BaseDO implements Serializable{

    /**
     * 配额账号
     */
    private Long quotaAccountId;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;

    /**
     * 售卖日期
     */
    private Date saleDate;

    /**
     * 房态设置(0关房  1开房)
     */
    private Integer quotaState;

    /**
     * 配额数量
     */
    private Integer quotaNum;

    /**
     * 已用配额数
     */
    private Integer usedQuotaNum;

    /**
     * 配额总数
     */
    private Integer allQuotaNum;

    /**
     * 是否可超(1可超  0不可超 )
     */
    private Integer overDraft;

    /**
     * 配额的操作类型(1 增加 , 2减少, 3设置为)
     */
    private Integer operateType;

}
