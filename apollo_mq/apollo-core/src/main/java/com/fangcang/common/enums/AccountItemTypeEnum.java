package com.fangcang.common.enums;

import java.io.Serializable;

public enum AccountItemTypeEnum implements Serializable {

    TRANSFER_IN(1, "转入"), TRANSFER_OUT(2, "转出"), CHECKOUT(3, "结算");

    public int key;
    public String value;

    private AccountItemTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (AccountItemTypeEnum accountItemTypeEnum : AccountItemTypeEnum.values()) {
            if (accountItemTypeEnum.value == value) {
                key = accountItemTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (AccountItemTypeEnum accountItemTypeEnum : AccountItemTypeEnum.values()) {
            if (accountItemTypeEnum.key == key) {
                value = accountItemTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static AccountItemTypeEnum getEnumByKey(int key) {
        AccountItemTypeEnum settlementMethod = null;
        for (AccountItemTypeEnum accountItemTypeEnum : AccountItemTypeEnum.values()) {
            if (accountItemTypeEnum.key == key) {
                settlementMethod = accountItemTypeEnum;
                break;
            }
        }
        return settlementMethod;
    }
}
