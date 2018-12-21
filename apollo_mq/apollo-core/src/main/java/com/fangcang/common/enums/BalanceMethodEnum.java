package com.fangcang.common.enums;

import java.io.Serializable;

/**
 * 结算方式
 */
public enum BalanceMethodEnum implements Serializable {

    MONTH(1, "月结"), HALFMONTH(2, "半月结"), WEEK(3, "周结"), SINGLE(4, "单结"), DAY(5, "日结");

    public int key;
    public String value;

    private BalanceMethodEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (BalanceMethodEnum balanceMethodEnum : BalanceMethodEnum.values()) {
            if (balanceMethodEnum.value == value) {
                key = balanceMethodEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (BalanceMethodEnum balanceMethodEnum : BalanceMethodEnum.values()) {
            if (balanceMethodEnum.key == key) {
                value = balanceMethodEnum.value;
                break;
            }
        }
        return value;
    }

    public static BalanceMethodEnum getEnumByKey(int key) {
        BalanceMethodEnum settlementMethod = null;
        for (BalanceMethodEnum balanceMethodEnum : BalanceMethodEnum.values()) {
            if (balanceMethodEnum.key == key) {
                settlementMethod = balanceMethodEnum;
                break;
            }
        }
        return settlementMethod;
    }

}
