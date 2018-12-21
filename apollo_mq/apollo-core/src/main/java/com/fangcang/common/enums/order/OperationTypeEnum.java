package com.fangcang.common.enums.order;

/**
 * 备注类型
 */
public enum OperationTypeEnum {

    ORDER(1, "订单信息"),
    PRODUCT(2, "产品信息"),
    ATTACH(3, "附件");

    public int key;
    public String value;

    OperationTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (OperationTypeEnum statusEnum : OperationTypeEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (OperationTypeEnum statusEnum : OperationTypeEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static OperationTypeEnum getEnumByKey(int key) {
        OperationTypeEnum returnEnum = null;
        for (OperationTypeEnum statusEnum : OperationTypeEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
