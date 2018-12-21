package com.fangcang.ebk.enums;

public enum EbkOrderStatusEnum {

    NEW(1, "新建"),
    MODIFY_APPLY(2, "修改申请"),
    CANCEL_APPLY(3, "取消申请"),
    CONFIRMED(4, "已确认"),
    CANCELED(5, "已取消");

    public int key;
    public String value;

    EbkOrderStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (EbkOrderStatusEnum ebkOrderStatusEnum : EbkOrderStatusEnum.values()) {
            if (key == ebkOrderStatusEnum.key) {
                value = ebkOrderStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (EbkOrderStatusEnum ebkOrderStatusEnum : EbkOrderStatusEnum.values()) {
            if (ebkOrderStatusEnum.value.equals(value)) {
                key = ebkOrderStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static EbkOrderStatusEnum getEnumByKey(int key) {
        EbkOrderStatusEnum returnEnum = null;
        for (EbkOrderStatusEnum ebkOrderStatusEnum : EbkOrderStatusEnum.values()) {
            if (key == ebkOrderStatusEnum.key) {
                returnEnum = ebkOrderStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
