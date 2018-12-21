package com.fangcang.common.enums.order;

/**
 * 备注类型
 */
public enum NoteTypeEnum {

    AGENT_NOTE(1, "分销商备注"),
    SUPPLY_NOTE(2, "供应商备注"),
    INNER_NOTE(3, "内部备注");

    public int key;
    public String value;

    NoteTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (NoteTypeEnum statusEnum : NoteTypeEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (NoteTypeEnum statusEnum : NoteTypeEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static NoteTypeEnum getEnumByKey(int key) {
        NoteTypeEnum returnEnum = null;
        for (NoteTypeEnum statusEnum : NoteTypeEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
