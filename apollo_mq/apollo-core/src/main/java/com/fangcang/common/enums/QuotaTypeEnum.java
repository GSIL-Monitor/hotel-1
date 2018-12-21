package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/16.
 */
public enum QuotaTypeEnum {
    CONTRACT_ROOM(1,"合约房"),QUOTA_ROOM(2,"配额房"),PRIVATE_ROOM_ONE(3,"包房一"),PRIVATE_ROOM_TWO(4,"包房二");

    public int key;
    public String value;

    private QuotaTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(QuotaTypeEnum quotaTypeEnum : QuotaTypeEnum.values()) {
            if(quotaTypeEnum.value .equals(value)) {
                key = quotaTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(QuotaTypeEnum quotaTypeEnum : QuotaTypeEnum.values()) {
            if(quotaTypeEnum.key == key) {
                value = quotaTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static QuotaTypeEnum  getEnumByKey(int key){
        QuotaTypeEnum quotaTypeEnum = null;
        for(QuotaTypeEnum quotaType : QuotaTypeEnum.values()) {
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
