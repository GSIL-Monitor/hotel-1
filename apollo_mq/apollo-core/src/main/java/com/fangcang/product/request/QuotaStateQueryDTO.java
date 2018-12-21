package com.fangcang.product.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class QuotaStateQueryDTO implements Serializable{

    private static final long serialVersionUID = 3289104351748705084L;
    /**
     * 配额账户ID
     */
    @NotNull(message = "quotaAccountId不能为Null")
    private Long quotaAccountId;

    private Long pricePlanId;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 价格计划IDS
     */
    private String pricePlanIds;
}
