package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/16.
 */
public enum PriceOperateTypeEnum {
    ADD(1,"增加"),DECREASE(2,"减少"),MULTIPLICATION(3,"乘法"),SET_TO(4,"设置为");

    public int key;
    public String value;

    private PriceOperateTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(PriceOperateTypeEnum quotaTypeEnum : PriceOperateTypeEnum.values()) {
            if(quotaTypeEnum.value .equals(value)) {
                key = quotaTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(PriceOperateTypeEnum quotaTypeEnum : PriceOperateTypeEnum.values()) {
            if(quotaTypeEnum.key == key) {
                value = quotaTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static PriceOperateTypeEnum getEnumByKey(int key){
        PriceOperateTypeEnum quotaTypeEnum = null;
        for(PriceOperateTypeEnum quotaType : PriceOperateTypeEnum.values()) {
            if(quotaType.key == key) {
                quotaTypeEnum = quotaType;
                break;
            }
        }
        return quotaTypeEnum;
    }

    public String getValue() {
        return value;
    }

}
