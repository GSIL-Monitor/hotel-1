package com.fangcang.ebk.enums;

public enum EbkRequestStatusEnum {

    UNTREATED(0, "未处理"),
    CONFIRMED(1, "同意"),
    REFUSED(2, "拒绝");

    public int key;
    public String value;

    EbkRequestStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (EbkRequestStatusEnum ebkRequestStatusEnum : EbkRequestStatusEnum.values()) {
            if (key == ebkRequestStatusEnum.key) {
                value = ebkRequestStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (EbkRequestStatusEnum ebkRequestStatusEnum : EbkRequestStatusEnum.values()) {
            if (ebkRequestStatusEnum.value.equals(value)) {
                key = ebkRequestStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static EbkRequestStatusEnum getEnumByKey(int key) {
        EbkRequestStatusEnum returnEnum = null;
        for (EbkRequestStatusEnum ebkRequestStatusEnum : EbkRequestStatusEnum.values()) {
            if (key == ebkRequestStatusEnum.key) {
                returnEnum = ebkRequestStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
