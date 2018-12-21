package com.fangcang.common.enums.order;

/**
 * 供货单类型
 */
public enum SupplyTypeEnum {

    BOOK(1, "预订单"),
    RESEND(2, "重发预订单"),
    REVISE(3, "修改单"),
    CANCEL(4, "取消单");

    public int key;
    public String value;

    SupplyTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (SupplyTypeEnum statusEnum : SupplyTypeEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (SupplyTypeEnum statusEnum : SupplyTypeEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static SupplyTypeEnum getEnumByKey(int key) {
        SupplyTypeEnum returnEnum = null;
        for (SupplyTypeEnum statusEnum : SupplyTypeEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
