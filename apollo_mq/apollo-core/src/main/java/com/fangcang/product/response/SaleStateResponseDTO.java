package com.fangcang.product.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SaleStateResponseDTO extends BaseDTO implements Serializable{

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * B2B渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer b2bSaleState;

    /**
     * 携程渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer ctripSaleState;

    /**
     * 去哪儿渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer qunarSaleState;

    /**
     * 艺龙渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer elongSaleState;

    /**
     * 同程渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer tongchengSaleState;

    /**
     * 途牛渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer tuniuSaleState;

    /**
     * 新美大渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer xmdSaleState;

    /**
     * 京东渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer jdSaleState;

    /**
     * 淘宝渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer taobaoSaleState;

    /**
     * 去哪儿大B渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer qunarB2BSaleState;

    /**
     * 去哪儿夜销渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer qunarNgtSaleState;

    /**
     * 去哪儿美元渠道是否上架(0下架 , 1上架 ,-1未开通,2 不展示)
     */
    private Integer qunarUsdSaleState;

    /**
     * 去哪儿子渠道是否上架(-1 未开通,0 下架,1 上架)
     */
    private Integer qunarSonSaleState;

    /**
     * 是否上架(1上架  0下架)
     */
    private Integer isOnSale;
}
