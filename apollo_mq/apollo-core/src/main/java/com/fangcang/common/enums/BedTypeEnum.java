package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/17.
 */
public enum BedTypeEnum {

    SINGLE(0,"单床"),KING(1,"大床"),TWIN(2,"双床"),THREE(3, "三床"),FOUR(4,"四床"),
    CIRCLE(5,"圆床"),COMPOSITE(6,"复合床"),BUNK(7,"上下铺"),ONDOL(8, "炕"),
    WATER(9,"水床"),TATAMI(10,"榻榻米"),MULTI(11,"多床");

    public Integer key;
    public String value;

    private BedTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        Integer key = null;
        for(BedTypeEnum bedTypeEnum : BedTypeEnum.values()) {
            if(bedTypeEnum.value.equals(value)) {
                key = bedTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(Integer key) {
        String value = null;
        for(BedTypeEnum bedTypeEnum : BedTypeEnum.values()) {
            if(bedTypeEnum.key.equals(key)) {
                value = bedTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static BedTypeEnum  getEnumByKey(String key){
        BedTypeEnum bedTypeEnum = null;
        for(BedTypeEnum bedType : BedTypeEnum.values()) {
            if(bedType.key.equals(key)) {
                bedTypeEnum = bedType;
                break;
            }
        }
        return bedTypeEnum;
    }

}
