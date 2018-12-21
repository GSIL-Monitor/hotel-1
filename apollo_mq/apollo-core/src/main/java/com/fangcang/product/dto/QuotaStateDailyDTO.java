package com.fangcang.product.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class QuotaStateDailyDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -8060437708613573667L;

    /**
     * 售卖日期
     */
    private Date saleDate;

    /**
     * 剩余配额数
     */
    private Integer quotaNum;

    /**
     * 总配额数
     */
    private Integer allQuotaNum;

    /**
     * 是否可超(1可超  0不可超)
     */
    private Integer overDraft;

    /**
     * 房态(0关房  1开房)
     */
    private Integer quotaState;
}
