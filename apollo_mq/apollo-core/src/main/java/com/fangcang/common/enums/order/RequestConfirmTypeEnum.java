package com.fangcang.common.enums.order;

/**
 * 供货请求确认类型
 */
public enum RequestConfirmTypeEnum {

    UN_HANDLE(0, "未处理"),
    CONFIRMED(1, "已确认"),
    REFUSED(2, "已拒绝");

    public int key;
    public String value;

    RequestConfirmTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (RequestConfirmTypeEnum statusEnum : RequestConfirmTypeEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (RequestConfirmTypeEnum statusEnum : RequestConfirmTypeEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static RequestConfirmTypeEnum getEnumByKey(int key) {
        RequestConfirmTypeEnum returnEnum = null;
        for (RequestConfirmTypeEnum statusEnum : RequestConfirmTypeEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
