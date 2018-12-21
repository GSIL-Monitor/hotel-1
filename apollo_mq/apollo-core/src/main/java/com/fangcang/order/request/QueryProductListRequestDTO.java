package com.fangcang.order.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class QueryProductListRequestDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 2159918650750463174L;

    /**
     * 酒店id
     */
    private Integer hotelId;
    /**
     * 供应商编码
     */
    private String supplyCode;
    /**
     * 房型id
     */
    private Integer roomTypeId;
    /**
     * 入住日期
     */
    private String checkinDate;
    /**
     * 离店日期
     */
    private String checkoutDate;
    /**
     * 渠道编码，查那个渠道的价格
     */
    private String channelCode;
    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 间数
     */
    private Integer roomNum;
}
