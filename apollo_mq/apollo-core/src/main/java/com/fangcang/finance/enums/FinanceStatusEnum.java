package com.fangcang.finance.enums;

public enum FinanceStatusEnum {

    NEW(0,"新建"),
    CHECKOUT(1,"已确认"),
    CANCELED(2, "已取消");

    public int key;
    public String value;

    FinanceStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (FinanceStatusEnum financeStatusEnum : FinanceStatusEnum.values()) {
            if (key == financeStatusEnum.key) {
                value = financeStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (FinanceStatusEnum financeStatusEnum : FinanceStatusEnum.values()) {
            if (financeStatusEnum.value.equals(value)) {
                key = financeStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static FinanceStatusEnum getEnumByKey(int key) {
        FinanceStatusEnum returnEnum = null;
        for (FinanceStatusEnum financeStatusEnum : FinanceStatusEnum.values()) {
            if (key == financeStatusEnum.key) {
                returnEnum = financeStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
