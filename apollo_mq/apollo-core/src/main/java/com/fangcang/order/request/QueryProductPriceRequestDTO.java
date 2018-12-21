package com.fangcang.order.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class QueryProductPriceRequestDTO implements Serializable {
    private static final long serialVersionUID = -8979719692757984700L;

    /**
     * 价格计划id
     */
    @NotNull
    private Integer ratePlanId;
    /**
     * 入住日期
     */
    @NotBlank
    private String checkinDate;
    /**
     * 离店日期
     */
    @NotBlank
    private String checkoutDate;
    /**
     * 渠道编码，查那个渠道的价格
     */
    @NotBlank
    private String channelCode;
    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 是否团房订单
     */
    private Integer isGroupRoom;
}
