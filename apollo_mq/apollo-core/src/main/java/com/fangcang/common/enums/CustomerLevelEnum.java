package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/30.
 */
public enum CustomerLevelEnum {

    COMMONLY(1, "一般"), SILVER(2, "白银"), GOLD(3, "黄金"), PLATINUM(4, "铂金"), DIAMONDS(5, "钻石");

    public int key;
    public String value;

    private CustomerLevelEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (CustomerLevelEnum customerLevelEnum : CustomerLevelEnum.values()) {
            if (customerLevelEnum.value == value) {
                key = customerLevelEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (CustomerLevelEnum customerLevelEnum : CustomerLevelEnum.values()) {
            if (customerLevelEnum.key == key) {
                value = customerLevelEnum.value;
                break;
            }
        }
        return value;
    }

    public static CustomerLevelEnum getEnumByKey(int key) {
        CustomerLevelEnum customerLevelEnum = null;
        for (CustomerLevelEnum c : CustomerLevelEnum.values()) {
            if (c.key == key) {
                customerLevelEnum = c;
                break;
            }
        }
        return customerLevelEnum;
    }
}
