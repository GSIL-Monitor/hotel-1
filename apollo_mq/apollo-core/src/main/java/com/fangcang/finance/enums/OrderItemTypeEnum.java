package com.fangcang.finance.enums;

public enum OrderItemTypeEnum {
    PRODUCT(1,"客房"),
    ADDITIONCHARGE(2,"附加项"),
    DERATEPOLICY(3,"减免政策");

    public int key;
    public String value;

    OrderItemTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (OrderItemTypeEnum orderItemTypeEnum : OrderItemTypeEnum.values()) {
            if (key == orderItemTypeEnum.key) {
                value = orderItemTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (OrderItemTypeEnum orderItemTypeEnum : OrderItemTypeEnum.values()) {
            if (orderItemTypeEnum.value.equals(value)) {
                key = orderItemTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static OrderItemTypeEnum getEnumByKey(int key) {
        OrderItemTypeEnum returnEnum = null;
        for (OrderItemTypeEnum orderItemTypeEnum : OrderItemTypeEnum.values()) {
            if (key == orderItemTypeEnum.key) {
                returnEnum = orderItemTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
