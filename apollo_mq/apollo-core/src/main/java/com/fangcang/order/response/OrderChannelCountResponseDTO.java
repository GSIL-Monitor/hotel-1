package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class OrderChannelCountResponseDTO implements Serializable {


    private static final long serialVersionUID = 6062838308871055395L;
    /**
     * 渠道编码: ctrip:携程，elong:艺龙，tongcheng:同程，tuniu:途牛，xmd:新美大，jd:京东，qunar:去哪儿TTS，qunar_B2B:去哪儿大B，qunar_ngt:去哪儿夜销，qunar_usd:去哪儿美元，taobao:淘宝
     */
    private String channelCode;
    /**
     * 订单数量
     */
    private Integer counts;

}
