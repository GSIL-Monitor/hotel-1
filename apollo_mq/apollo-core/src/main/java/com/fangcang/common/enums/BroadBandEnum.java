package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/6/26.
 */
public enum BroadBandEnum {

    CHARGE(2, "收费"), FREE(1, "免费"),NONE(0, "无");

    public int key;
    public String value;

    private BroadBandEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(BroadBandEnum broadBandSassEnum : BroadBandEnum.values()) {
            if(broadBandSassEnum.value .equals(value)) {
                key = broadBandSassEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(BroadBandEnum broadBandSassEnum : BroadBandEnum.values()) {
            if(broadBandSassEnum.key == key) {
                value = broadBandSassEnum.value;
                break;
            }
        }
        return value;
    }

    public static BroadBandEnum getEnumByKey(int key){
        BroadBandEnum broadBandSassEnum = null;
        for(BroadBandEnum bbs : BroadBandEnum.values()) {
            if(bbs.key == key) {
                broadBandSassEnum = bbs;
                break;
            }
        }
        return broadBandSassEnum;
    }
}
