package com.fangcang.common.enums.B2B;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 16:35
 * @Description: 订房方式
 */
public enum BookTypeEnum {

    RESERVATION(1, "预订"),
    PARTIALLYDEFINABLE(2, "部分可订"),
    NOT_RESERVATION(3,"不可订");

    public int key;
    public String value;

    private BookTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (BookTypeEnum bookTypeEnum : BookTypeEnum.values()) {
            if (key == bookTypeEnum.key) {
                value = bookTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (BookTypeEnum bookTypeEnum : BookTypeEnum.values()) {
            if (bookTypeEnum.value.equals(value)) {
                key = bookTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static BookTypeEnum getEnumByKey(int key) {
        BookTypeEnum returnEnum = null;
        for (BookTypeEnum bookTypeEnum : BookTypeEnum.values()) {
            if (key == bookTypeEnum.key) {
                returnEnum = bookTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
