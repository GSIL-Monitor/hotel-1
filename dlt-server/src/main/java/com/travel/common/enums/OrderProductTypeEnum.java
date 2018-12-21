package com.travel.common.enums;

/**
 *   2018/1/17.
 */
public enum OrderProductTypeEnum {

    ROOM("room","房费"), ADDITIONAL("additional","杂费");

    public String code;

    public String desc;

    OrderProductTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderProductTypeEnum getEnumByCode(String code) {
        for (OrderProductTypeEnum pte : OrderProductTypeEnum.values()) {
            if (pte.code.equals(code)) {
                return pte;
            }
        }
        return null;
    }

    public static String getDescByCode(String code) {
        String des = "";
        for (OrderProductTypeEnum pte : OrderProductTypeEnum.values()) {
            if (pte.code.equals(code)) {
                des = pte.desc;
                break;
            }
        }
        return des;
    }
}
