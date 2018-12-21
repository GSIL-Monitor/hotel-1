package com.fangcang.common.enums.saas;

/**
 * Created by ASUS on 2018/6/26.
 */
public enum PetSaasEnum {

    ALLOW(9001, "允许携带宠物"), NOT_ALLOW(9002, "不允许携带宠物");

    public int key;
    public String value;

    private PetSaasEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(PetSaasEnum petSaasEnum : PetSaasEnum.values()) {
            if(petSaasEnum.value .equals(value)) {
                key = petSaasEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(PetSaasEnum petSaasEnum : PetSaasEnum.values()) {
            if(petSaasEnum.key == key) {
                value = petSaasEnum.value;
                break;
            }
        }
        return value;
    }

    public static PetSaasEnum  getEnumByKey(int key){
        PetSaasEnum p = null;
        for(PetSaasEnum petSaasEnum : PetSaasEnum.values()) {
            if(petSaasEnum.key == key) {
                p = petSaasEnum;
                break;
            }
        }
        return p;
    }
}
