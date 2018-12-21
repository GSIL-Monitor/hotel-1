package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/17.
 */
public enum PutTypeEnum {

    THEATRE(1,"剧场式"),CLASS_ROOM(2,"课桌式"),U_TYPE(3,"U型式"),RETURN_TYPE(4, "回型式"),BANQUET(5,"宴会式"),DIRECTORATE(6,"董会式");

    public Integer key;
    public String value;

    private PutTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        Integer key = null;
        for(PutTypeEnum putTypeEnum : PutTypeEnum.values()) {
            if(putTypeEnum.value.equals(value)) {
                key = putTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(Integer key) {
        String value = null;
        for(PutTypeEnum putTypeEnum : PutTypeEnum.values()) {
            if(putTypeEnum.key.equals(key)) {
                value = putTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static PutTypeEnum getEnumByKey(String key){
        PutTypeEnum putTypeEnum = null;
        for(PutTypeEnum putType : PutTypeEnum.values()) {
            if(putType.key.equals(key)) {
                putTypeEnum = putType;
                break;
            }
        }
        return putTypeEnum;
    }

}
