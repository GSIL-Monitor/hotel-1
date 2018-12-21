package com.fangcang.ebk.enums;

public enum EbkRequestTypeEnum {

    BOOK_APPLY(1, "预订申请"),
    MODIFY_APPLY(2, "修改申请"),
    CANCEL_APPLY(3, "取消申请");

    public int key;
    public String value;

    EbkRequestTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (EbkRequestTypeEnum ebkRequestTypeEnum : EbkRequestTypeEnum.values()) {
            if (key == ebkRequestTypeEnum.key) {
                value = ebkRequestTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (EbkRequestTypeEnum ebkRequestTypeEnum : EbkRequestTypeEnum.values()) {
            if (ebkRequestTypeEnum.value.equals(value)) {
                key = ebkRequestTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static EbkRequestTypeEnum getEnumByKey(int key) {
        EbkRequestTypeEnum returnEnum = null;
        for (EbkRequestTypeEnum ebkRequestTypeEnum : EbkRequestTypeEnum.values()) {
            if (key == ebkRequestTypeEnum.key) {
                returnEnum = ebkRequestTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
