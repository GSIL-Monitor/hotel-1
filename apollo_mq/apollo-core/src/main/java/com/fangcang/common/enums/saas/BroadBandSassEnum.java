package com.fangcang.common.enums.saas;

/**
 * Created by ASUS on 2018/6/26.
 */
public enum BroadBandSassEnum {

    CHARGE(1, "收费"), FREE(2, "免费"),NONE(3, "无");

    public int key;
    public String value;

    private BroadBandSassEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(BroadBandSassEnum broadBandSassEnum : BroadBandSassEnum.values()) {
            if(broadBandSassEnum.value .equals(value)) {
                key = broadBandSassEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(BroadBandSassEnum broadBandSassEnum : BroadBandSassEnum.values()) {
            if(broadBandSassEnum.key == key) {
                value = broadBandSassEnum.value;
                break;
            }
        }
        return value;
    }

    public static BroadBandSassEnum  getEnumByKey(int key){
        BroadBandSassEnum broadBandSassEnum = null;
        for(BroadBandSassEnum bbs : BroadBandSassEnum.values()) {
            if(bbs.key == key) {
                broadBandSassEnum = bbs;
                break;
            }
        }
        return broadBandSassEnum;
    }
}
