package com.fangcang.finance.enums;

public enum InvoiceTypeEnum {

    GENERAL(1,"普通发票"),
    PROFESSION(2,"专业发票"),
    ELECTRONIC(3,"电子发票");

    public int key;
    public String value;

    InvoiceTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (InvoiceTypeEnum invoiceTypeEnum : InvoiceTypeEnum.values()) {
            if (key == invoiceTypeEnum.key) {
                value = invoiceTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (InvoiceTypeEnum invoiceTypeEnum : InvoiceTypeEnum.values()) {
            if (invoiceTypeEnum.value.equals(value)) {
                key = invoiceTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static InvoiceTypeEnum getEnumByKey(int key) {
        InvoiceTypeEnum returnEnum = null;
        for (InvoiceTypeEnum invoiceTypeEnum : InvoiceTypeEnum.values()) {
            if (key == invoiceTypeEnum.key) {
                returnEnum = invoiceTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
