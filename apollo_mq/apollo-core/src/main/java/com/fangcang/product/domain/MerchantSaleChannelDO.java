package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;


@Data
public class MerchantSaleChannelDO extends BaseDO{
    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 是否开通(0未开通  1已开通)
     */
    private Integer isOpen;

    /**
     * 渠道币种
     */
    private String channelCurrency;


}