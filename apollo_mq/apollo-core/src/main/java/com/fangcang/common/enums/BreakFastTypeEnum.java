package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/16.
 */
public enum BreakFastTypeEnum {

//    ZERO(0,"无早"), ONE(1,"单早"), TWO(2,"双早"),HEAD_NUM(3,"人头早");
    BED( -1, "床位早"),NONE( 0, "无早"), ONE(1, "单早"), TWO(2, "双早"),THREE(3, "三早"),FOUR(4, "四早"),
    FIVE( 5, "五早"), SIX(6, "六早"), SEVEN(7, "七早"),EIGHT(8, "八早"),NINE(9, "九早"),EXIST(10, "含早");


    public int key;
    public String value;

    private BreakFastTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(BreakFastTypeEnum breakfastNumEnum : BreakFastTypeEnum.values()) {
            if(breakfastNumEnum.value .equals(value)) {
                key = breakfastNumEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(BreakFastTypeEnum breakfastNumEnum : BreakFastTypeEnum.values()) {
            if(breakfastNumEnum.key == key) {
                value = breakfastNumEnum.value;
                break;
            }
        }
        return value;
    }

    public static BreakFastTypeEnum  getEnumByKey(int key){
        BreakFastTypeEnum breakFastTypeEnum = null;
        for(BreakFastTypeEnum breakfastNum : BreakFastTypeEnum.values()) {
            if(breakfastNum.key == key) {
                breakFastTypeEnum = breakfastNum;
                break;
            }
        }
        return breakFastTypeEnum;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
