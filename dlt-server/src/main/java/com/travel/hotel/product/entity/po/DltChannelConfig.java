package com.travel.hotel.product.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  m_ 2018/6/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DltChannelConfig {

    private String channelCode;

    private String channelName;

    private String merchantCode;

    private String merchantName;

    private String fieldName;

    private String fieldValue;

    private String remark;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;
}
