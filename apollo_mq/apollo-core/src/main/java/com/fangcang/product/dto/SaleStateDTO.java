package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class SaleStateDTO implements Serializable{

    private static final long serialVersionUID = 8995766376544250511L;
    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * B2B渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer b2bSaleState;

    /**
     * 携程渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer ctripSaleState;

    /**
     * 去哪儿渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer qunarSaleState;

    /**
     * 艺龙渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer elongSaleState;

    /**
     * 同程渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer tongchengSaleState;

    /**
     * 途牛渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer tuniuSaleState;

    /**
     * 新美大渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer xmdSaleState;

    /**
     * 京东渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer jdSaleState;

    /**
     * 淘宝渠道售卖状态(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer taobaoSaleState;

    /**
     * 去哪儿大B渠道是否上架(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer qunarB2BSaleState;

    /**
     * 去哪儿夜销渠道是否上架(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer qunarNgtSaleState;

    /**
     * 去哪儿美元渠道是否上架(-2 不变,-1 未开通,1 上架 ,0 下架)
     */
    private Integer qunarUsdSaleState;

    /**
     * 去哪儿子渠道是否上架(-1 未开通,0 下架,1 上架)
     */
    private Integer qunarSonSaleState;
}
