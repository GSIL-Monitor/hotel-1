package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/17.
 */
public enum PayMethodEnum {

    PAY(1, "pay", "现付(有佣金)"), PRE_PAY(2, "pre_pay", "预付"),PAY_NOCOMISSION(3,"pay_nocommission","现付(无佣金)");

    public int key;
    public String code;
    public String value;

    private PayMethodEnum(int key, String code, String value) {
        this.key = key;
        this.code = code;
        this.value = value;
    }

    public static String getCodeByKey(int key) {
        for(PayMethodEnum payMethodEnum : PayMethodEnum.values()) {
            if(payMethodEnum.key == key) {
                return payMethodEnum.code;
            }
        }
        return null;
    }

    public static String getValueByKey(int key) {
        for(PayMethodEnum payMethodEnum : PayMethodEnum.values()) {
            if(payMethodEnum.key == key) {
                return payMethodEnum.value;
            }
        }
        return null;
    }

    public static String getValueByCode(String code) {
        for(PayMethodEnum payMethodEnum : PayMethodEnum.values()) {
            if(payMethodEnum.code .equals( code)) {
                return payMethodEnum.value;
            }
        }
        return null;
    }

    public static PayMethodEnum  getEnumByKey(int key){
        PayMethodEnum payMethodEnum = null;
        for(PayMethodEnum payMethod : PayMethodEnum.values()) {
            if(payMethod.key == key) {
                payMethodEnum = payMethod;
                break;
            }
        }
        return payMethodEnum;
    }

    public String getValue() {
        return value;
    }
}
