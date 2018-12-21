package com.fangcang.common.enums.order;

/**
 * 供货单状态
 */
public enum SupplyStatusEnum {

    UN_SEND(1, "未发单"),
    SENT(2, "已发单"),
    CONFIRMED(3, "已确认"),
    UN_CONFIRM(4, "不确认");

    public int key;
    public String value;

    SupplyStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (SupplyStatusEnum statusEnum : SupplyStatusEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (SupplyStatusEnum statusEnum : SupplyStatusEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static SupplyStatusEnum getEnumByKey(int key) {
        SupplyStatusEnum returnEnum = null;
        for (SupplyStatusEnum statusEnum : SupplyStatusEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
