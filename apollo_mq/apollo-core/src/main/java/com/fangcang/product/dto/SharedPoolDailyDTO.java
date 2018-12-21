package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SharedPoolDailyDTO implements Serializable {

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
     * 配额数
     */
    private Integer quotaNum;

    /**
     * 是否可超(1可超  0不可超)
     */
    private Integer overDraft;

    /**
     * 房态(0关房  1开房)
     */
    private Integer quotaState;
}
