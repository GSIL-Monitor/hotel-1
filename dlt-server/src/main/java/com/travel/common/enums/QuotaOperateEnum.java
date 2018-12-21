package com.travel.common.enums;

/**
 * 扣退配额枚举
 *   2018/3/22.
 */
public enum QuotaOperateEnum {

    DEDUCT("deduct","扣配额"),
    REFUND("refund","退配额");

    public String key;
    public String value;

    QuotaOperateEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(String key) {
        String value = null;
        QuotaOperateEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            QuotaOperateEnum roomStateEnum = arr$[i$];
            if (roomStateEnum.key.equals(key)) {
                value = roomStateEnum.value;
                break;
            }
        }

        return value;
    }

    public static QuotaOperateEnum getEnumByKey(String key) {
        QuotaOperateEnum roomStateEnum = null;
        QuotaOperateEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            QuotaOperateEnum bedType = arr$[i$];
            if (bedType.key.equals(key)) {
                roomStateEnum = bedType;
                break;
            }
        }

        return roomStateEnum;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
