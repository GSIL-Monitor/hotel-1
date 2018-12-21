package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class DateDTO implements Serializable{
    private static final long serialVersionUID = -3317766981271418617L;

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
     * 售卖日期
     */
    private Date saleDate;
}
