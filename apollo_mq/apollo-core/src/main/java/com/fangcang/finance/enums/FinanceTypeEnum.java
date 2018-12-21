package com.fangcang.finance.enums;

public enum FinanceTypeEnum {

    ORDERPAY(1,"订单支付"),
    ORDERREFUND(2,"订单退款"),
    SUPPLIERORDERPAY(3,"供货单支付"),
    SUPPLIERORDERFUND(4,"供货单退款"),

    AGENTBILLSATTLE(5,"分销商账单结算"),
    SUPPLIERBILLSATTLE(6,"供应商账单结算");

    public int key;
    public String value;

    FinanceTypeEnum(int key, String name) {
        this.key = key;
        this.value = name;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (FinanceTypeEnum financeTypeEnum : FinanceTypeEnum.values()) {
            if (key == financeTypeEnum.key) {
                value = financeTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (FinanceTypeEnum financeTypeEnum : FinanceTypeEnum.values()) {
            if (financeTypeEnum.value.equals(value)) {
                key = financeTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static FinanceTypeEnum getEnumByKey(int key) {
        FinanceTypeEnum returnEnum = null;
        for (FinanceTypeEnum financeTypeEnum : FinanceTypeEnum.values()) {
            if (key == financeTypeEnum.key) {
                returnEnum = financeTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
