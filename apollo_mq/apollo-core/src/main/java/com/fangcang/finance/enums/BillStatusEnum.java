package com.fangcang.finance.enums;

public enum BillStatusEnum {

    NEW(1,"对账中"),
    CHECKED(2,"已对账"),
    WAITCHECKOUT(3,"待结算"),
    CHECKOUT(4,"已结算"),
    CANCELED(5, "已取消");

    public int key;
    public String value;

    BillStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (BillStatusEnum billStatusEnum : BillStatusEnum.values()) {
            if (key == billStatusEnum.key) {
                value = billStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (BillStatusEnum billStatusEnum : BillStatusEnum.values()) {
            if (billStatusEnum.value.equals(value)) {
                key = billStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static BillStatusEnum getEnumByKey(int key) {
        BillStatusEnum returnEnum = null;
        for (BillStatusEnum billStatusEnum : BillStatusEnum.values()) {
            if (key == billStatusEnum.key) {
                returnEnum = billStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
