package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.util.Date;

@Data
public class QuotaLogDO extends BaseDO {

    private Long id;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 星期
     */
    private String weeks;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 配额操作类型
     */
    private Integer operateType;

    /**
     * 配额数量
     */
    private Integer quotaNum;

    /**
     * 是否可超
     */
    private Integer overDraft;

    /**
     * 房态
     */
    private Integer quotaState;

}