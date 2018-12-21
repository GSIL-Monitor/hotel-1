package com.travel.common.enums;

/**
 *   2018/1/10.
 */
public enum DictionaryEnum {

    STAR("star","酒店星级"),CITY("city","城市"),BED_TYPE("bedType","床型描述"),CURRENCY("currency","币种")
    ,QUOTA_TYPE("quotaType","采购类型"),WEEKEND("weekend","周末的定义"),CHARGE_TYPE("charge_type","杂费类型"),
    MERCHANT("merchant","商家配置"),DLT_INTERFACE_INFO("dlt_interface_info","代理通接口信息");

    public String dataType;
    public String descrip;

    DictionaryEnum(String dataType, String descrip) {
        this.dataType = dataType;
        this.descrip = descrip;
    }
}
