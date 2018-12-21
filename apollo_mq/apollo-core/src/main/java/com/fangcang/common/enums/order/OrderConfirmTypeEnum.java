package com.fangcang.common.enums.order;

import java.io.Serializable;

/**
 * 订单确认类型
 */
public enum OrderConfirmTypeEnum implements Serializable {

    ONLINE(1, "在线");

    public int key;

    public String value;

    private OrderConfirmTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(int key) {
        for (OrderConfirmTypeEnum attachTypeEnum : OrderConfirmTypeEnum.values()) {
            if (attachTypeEnum.key == key) {
                return attachTypeEnum.value;
            }
        }
        return null;
    }

}
