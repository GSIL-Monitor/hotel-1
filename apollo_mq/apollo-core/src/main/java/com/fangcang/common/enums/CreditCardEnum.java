package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/6/25.
 */
public enum CreditCardEnum {

    UNION_PAY(1, "国内发行银联卡"), VISA(2, "威士(VISA)"),MASTER(3, "万事达(Master)"),AMEX(4,  "运通(AMEX)"),
    DINERS_CLUB(5, "大来(Diners Club)"),EURO(6,  "Euro卡"),EURO_6000(7, "Euro 6000卡"),EC(8,  "EC借记卡"),
    VISA_DEBIT(9,  "威士电子借记卡"),MAESTRO(10,  "Maestro卡"),JESSE(11,  "吉士美卡");

    public int key;
    public String value;

    private CreditCardEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(CreditCardEnum creditCardSaasEnum : CreditCardEnum.values()) {
            if(creditCardSaasEnum.value .equals(value)) {
                key = creditCardSaasEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(CreditCardEnum creditCardSaasEnum : CreditCardEnum.values()) {
            if(creditCardSaasEnum.key == key) {
                value = creditCardSaasEnum.value;
                break;
            }
        }
        return value;
    }

    public static CreditCardEnum getEnumByKey(int key){
        CreditCardEnum CreditCardSaasEnum = null;
        for(CreditCardEnum creditCardSaasEnum : CreditCardSaasEnum.values()) {
            if(creditCardSaasEnum.key == key) {
                CreditCardSaasEnum = creditCardSaasEnum;
                break;
            }
        }
        return CreditCardSaasEnum;
    }
}
