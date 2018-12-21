package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/6/1.
 */
public enum ShowSaleStateEnum {

    B2B("B2B","b2bSaleState"),CTRIP("ctrip","ctripSaleState"),
    ELONG("elong","elongSaleState"),TONGCHENG("tongcheng","tongchengSaleState"),
    TUNIU("tuniu","tuniuSaleState"),XMD("xmd","xmdSaleState"),JD("jd","jdSaleState"),
    QUNAR("qunar","qunarSaleState"),QUNAR_B2B("qunar_B2B","qunarB2BSaleState"),
    QUNAR_NGT("qunar_ngt","qunarNgtSaleState"),QUNAR_USD("qunar_usd","qunarUsdSaleState"),QUNAR_SON("qunar_son","qunarSonSaleState"),TAOBAO("taobao","taobaoSaleState");

    private ShowSaleStateEnum(String channelCode,String saleStateName){
        this.channelCode=channelCode;
        this.saleStateName=saleStateName;
    }

    public String channelCode;

    public String saleStateName;
}
