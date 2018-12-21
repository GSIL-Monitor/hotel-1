package com.fangcang.common.enums.order;

/**
 * 供货单发送类型
 */
public enum SupplySendTypeEnum {

    EBK(1, "EBK"),EMAIL(2,"EMAIL"),FAX(3,"FAX"),PHONE(4,"PHONE"),WECHAT(5,"WECHAT"),QQ(6,"QQ");

    public int key;
    public String value;

    SupplySendTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (SupplySendTypeEnum statusEnum : SupplySendTypeEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (SupplySendTypeEnum statusEnum : SupplySendTypeEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static SupplySendTypeEnum getEnumByKey(int key) {
        SupplySendTypeEnum returnEnum = null;
        for (SupplySendTypeEnum statusEnum : SupplySendTypeEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
