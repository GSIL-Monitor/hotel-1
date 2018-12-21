package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/29.
 */
@Data
public class DateSegmentQuotaDTO implements Serializable {

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
     * 房态设置(-1不变  0关房  1开房)
     */
    private Integer quotaState;

    /**
     * 配额的操作类型(1 增加 , 2减少, 3设置为)
     */
    private Integer operateType;

    /**
     * 配额数量
     */
    private Integer quotaNum;

    /**
     * 是否可超(1可超  0不可超  -1 不变)
     */
    private Integer overDraft;

}
