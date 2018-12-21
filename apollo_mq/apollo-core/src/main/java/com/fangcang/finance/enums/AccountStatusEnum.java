package com.fangcang.finance.enums;

public enum AccountStatusEnum {

    NEW(0,"新建"),
    CANCHECK(1,"可出账"),
    HOLD(2,"已纳入账单"),
    CHECKED(3,"已对账");

    public int key;
    public String value;

    AccountStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (AccountStatusEnum accountStatusEnum : AccountStatusEnum.values()) {
            if (key == accountStatusEnum.key) {
                value = accountStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (AccountStatusEnum accountStatusEnum : AccountStatusEnum.values()) {
            if (accountStatusEnum.value.equals(value)) {
                key = accountStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static AccountStatusEnum getEnumByKey(int key) {
        AccountStatusEnum returnEnum = null;
        for (AccountStatusEnum accountStatusEnum : AccountStatusEnum.values()) {
            if (key == accountStatusEnum.key) {
                returnEnum = accountStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
