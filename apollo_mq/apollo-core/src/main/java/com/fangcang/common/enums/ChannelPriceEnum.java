package com.fangcang.common.enums;

/**
 * Created by Vinney on 2018/5/17.
 */
public enum ChannelPriceEnum {

    BASE_PRICE(1,"basePrice","散房底价"),GROUP_BASE_PRICE(2,"groupBasePrice","团房底价"),B2B_SALE_PRICE(3,"b2bSalePrice","B2B散房售价"),
    GROUP_SALE_PRICE(4,"groupSalePrice","B2B团房售价"),CTRIP_SALE_PRICE(5,"ctripSalePrice","携程售价"),QUNAR_SALE_PRICE(6,"qunarSalePrice","去哪儿售价"),
    ELONG_SALE_PRICE(7,"elongSalePrice","艺龙售价"),TONGCHENG_SALE_PRICE(8,"tongchengSalePrice","同程售价"),TUNIU_SALE_PRICE(9,"tuniuSalePrice","途牛售价"),
    XMD_SALE_PRICE(10,"xmdSalePrice","新美大售价"),JD_SALE_PRICE(11,"jdSalePrice","京东售价"),TAOBAO_SALE_PRICE(12,"taobaoSalePrice","淘宝售价"),
    QUNAR_B2B_SALE_PRICE(13,"qunarB2BSalePrice","去哪儿大B散房售价"),QUNAR_NGT_SALE_PRICE(14,"qunarNgtSalePrice","去哪儿夜宵售价"),
    QUNAR_USD_SALE_PRICE(15,"qunarUsdSalePrice","去哪儿美元售价"),NO_BASE_PRICE(16,"noBasePrice","无基本价"),QUNAR_SON_SALE_PRICE(17,"qunarSonSalePrice","去哪儿子售价");

    public Integer key;

    public String enPriceName;

    public String priceName;

    ChannelPriceEnum(Integer key,String enPriceName, String priceName) {
        this.key = key;
        this.enPriceName = enPriceName;
        this.priceName = priceName;
    }
}
