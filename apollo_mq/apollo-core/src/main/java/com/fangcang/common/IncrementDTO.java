package com.fangcang.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/6/20.
 */
@Data
public class IncrementDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = 5036496181608718590L;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 商家酒店Id
     */
    private Long mHotelId;

    /**
     * 商家房型Id
     */
    private Long mRoomTypeId;

    /**
     *商家产品Id
     */
    private Long mRatePlanId;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 是否有增量
     */
    private Boolean hasIncrement;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;
}
