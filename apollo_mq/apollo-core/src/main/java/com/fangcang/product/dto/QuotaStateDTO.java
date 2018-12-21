package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class QuotaStateDTO implements Serializable{

    /**
     * 配额账号
     */
    private Long quotaAccountId;

    /**
     * 售卖日期
     */
    private Date saleDate;

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
