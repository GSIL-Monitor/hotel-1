package com.fangcang.common.enums.saas;

/**
 * Created by ASUS on 2018/6/25.
 */
public enum  CreditCardSaasEnum {

    UNION_PAY(5001, "国内发行银联卡"), VISA(5002, "威士(VISA)"),MASTER(5003, "万事达(Master)"),AMEX(5004,  "运通(AMEX)"),
    DINERS_CLUB(5005, "大来(Diners Club)"),EURO(5006,  "Euro卡"),EURO_6000(5007, "Euro 6000卡"),EC(5008,  "EC借记卡"),
    VISA_DEBIT(5009,  "威士电子借记卡"),MAESTRO(5010,  "Maestro卡"),JESSE(5011,  "吉士美卡");

    public int key;
    public String value;

    private CreditCardSaasEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(CreditCardSaasEnum creditCardSaasEnum : CreditCardSaasEnum.values()) {
            if(creditCardSaasEnum.value .equals(value)) {
                key = creditCardSaasEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(CreditCardSaasEnum creditCardSaasEnum : CreditCardSaasEnum.values()) {
            if(creditCardSaasEnum.key == key) {
                value = creditCardSaasEnum.value;
                break;
            }
        }
        return value;
    }

    public static CreditCardSaasEnum  getEnumByKey(int key){
        CreditCardSaasEnum CreditCardSaasEnum = null;
        for(CreditCardSaasEnum creditCardSaasEnum : CreditCardSaasEnum.values()) {
            if(creditCardSaasEnum.key == key) {
                CreditCardSaasEnum = creditCardSaasEnum;
                break;
            }
        }
        return CreditCardSaasEnum;
    }
}
