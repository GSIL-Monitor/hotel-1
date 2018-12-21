package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/16.
 */
public enum QuotaOperateTypeEnum {
    ADD(1,"增加"),DECREASE(2,"减少"),SET_TO(3,"设置为");

    public int key;
    public String value;

    private QuotaOperateTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(QuotaOperateTypeEnum quotaTypeEnum : QuotaOperateTypeEnum.values()) {
            if(quotaTypeEnum.value .equals(value)) {
                key = quotaTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(QuotaOperateTypeEnum quotaTypeEnum : QuotaOperateTypeEnum.values()) {
            if(quotaTypeEnum.key == key) {
                value = quotaTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static QuotaOperateTypeEnum getEnumByKey(int key){
        QuotaOperateTypeEnum quotaTypeEnum = null;
        for(QuotaOperateTypeEnum quotaType : QuotaOperateTypeEnum.values()) {
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
