package com.fangcang.finance.enums;

public enum PassageEnum {

    CREDIT(1,"挂账"),
    CASH(2,"现金余额"),
    WXPAY(3,"微信支付"),
    OFFLINE(4,"线下转账"),
    PREPAY(5,"预付款");

    public int key;
    public String value;

    PassageEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (PassageEnum passageEnum : PassageEnum.values()) {
            if (key == passageEnum.key) {
                value = passageEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (PassageEnum passageEnum : PassageEnum.values()) {
            if (passageEnum.value.equals(value)) {
                key = passageEnum.key;
                break;
            }
        }
        return key;
    }

    public static PassageEnum getEnumByKey(int key) {
        PassageEnum returnEnum = null;
        for (PassageEnum passageEnum : PassageEnum.values()) {
            if (key == passageEnum.key) {
                returnEnum = passageEnum;
                break;
            }
        }
        return returnEnum;
    }
}
