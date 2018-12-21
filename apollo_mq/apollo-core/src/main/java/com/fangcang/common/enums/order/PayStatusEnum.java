package com.fangcang.common.enums.order;

/**
 * 支付状态
 *
 * @author zhanwang
 */
public enum PayStatusEnum {
    UN_PAID(1, "待支付"),
    PAID(2, "已支付"),
    CREDIT(3, "已挂账"),
    UN_CREDIT(4, "待挂账"),
    REFUND(5, "已退款"),
    REFUND_CREDIT(6, "已退账");

    public int key;
    public String value;

    PayStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (PayStatusEnum statusEnum : PayStatusEnum.values()) {
            if (key == statusEnum.key) {
                value = statusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (PayStatusEnum statusEnum : PayStatusEnum.values()) {
            if (statusEnum.value.equals(value)) {
                key = statusEnum.key;
                break;
            }
        }
        return key;
    }

    public static PayStatusEnum getEnumByKey(int key) {
        PayStatusEnum returnEnum = null;
        for (PayStatusEnum statusEnum : PayStatusEnum.values()) {
            if (key == statusEnum.key) {
                returnEnum = statusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
