package com.travel.hotel.dlt.enums;

/**
 * 订单状态
 */
public enum OrderStatusEnum {

    NEWORDER(1, "新建"),
    PROCESSING(2, "处理中"),
    TRADED(3, "已确认"),
    CANCELED(4, "已取消");

    public int key;
    public String value;

    OrderStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (key == orderStatusEnum.key) {
                value = orderStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.value.equals(value)) {
                key = orderStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static OrderStatusEnum getEnumByKey(int key) {
        OrderStatusEnum returnEnum = null;
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (key == orderStatusEnum.key) {
                returnEnum = orderStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
