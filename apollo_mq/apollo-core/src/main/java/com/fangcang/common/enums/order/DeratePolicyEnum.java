package com.fangcang.common.enums.order;

/**
 * 减免政策
 */
public enum DeratePolicyEnum {
    WHOLE_HALF(1, "全陪免半"),
    EIGHT_HALF_OR_SIXTEEN_ONE(2, "8免半16免1");

    public int key;
    public String value;

    DeratePolicyEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (DeratePolicyEnum statusEnum : DeratePolicyEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (DeratePolicyEnum statusEnum : DeratePolicyEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static DeratePolicyEnum getEnumByKey(int key) {
        DeratePolicyEnum returnEnum = null;
        for (DeratePolicyEnum statusEnum : DeratePolicyEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
