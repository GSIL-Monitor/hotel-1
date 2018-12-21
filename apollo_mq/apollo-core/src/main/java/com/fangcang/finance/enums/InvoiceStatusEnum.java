package com.fangcang.finance.enums;

public enum InvoiceStatusEnum {

    UN_FINISH(0,"未完善"),
    FINISH(1,"已开票"),
    NOT_POSTAL(2,"未寄出"),
    HAS_POSTAL(3,"已寄出"),
    NOT_TAKE_AWAY(4,"未取走"),
    HAS_TAKE_AWAY(5,"已取走");

    public int key;
    public String value;

    InvoiceStatusEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (InvoiceStatusEnum invoiceStatusEnum : InvoiceStatusEnum.values()) {
            if (key == invoiceStatusEnum.key) {
                value = invoiceStatusEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (InvoiceStatusEnum invoiceStatusEnum : InvoiceStatusEnum.values()) {
            if (invoiceStatusEnum.value.equals(value)) {
                key = invoiceStatusEnum.key;
                break;
            }
        }
        return key;
    }

    public static InvoiceStatusEnum getEnumByKey(int key) {
        InvoiceStatusEnum returnEnum = null;
        for (InvoiceStatusEnum invoiceStatusEnum : InvoiceStatusEnum.values()) {
            if (key == invoiceStatusEnum.key) {
                returnEnum = invoiceStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
