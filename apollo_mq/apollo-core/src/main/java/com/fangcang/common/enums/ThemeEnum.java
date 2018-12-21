package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/6/25.
 */
public enum ThemeEnum {
    HOMESTAY(1,  "民宿或旅社"),FLAT(2, "公寓"),SCENIC_HOTEL(3, "景区酒店"),INN(4,  "客栈"), BUSINESS_METTING(5, "商务会议"),
    YOUTH_HOSTEL(6,  "青年旅舍"),HOLIDAY_VILLAGE(7, "度假村"),SEASIDE(8, "海滨休闲"),ENTERTAINMENT(9,  "娱乐休闲"),
    PARENT_CHILD_TRAVEL(10,  "亲子出游"),AGRITAINMENT(11,  "农家乐"),HOLIDAY(12, "温泉度假"),VILLA(13,"别墅"),HOTEL(14, "酒店"),
     THEMATIC(15,  "主题特色"),OTHER(16,  "其他");

    public int key;
    public String value;

    private ThemeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(ThemeEnum themeSaasEnum : ThemeEnum.values()) {
            if(themeSaasEnum.value .equals(value)) {
                key = themeSaasEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(ThemeEnum themeSaasEnum : ThemeEnum.values()) {
            if(themeSaasEnum.key == key) {
                value = themeSaasEnum.value;
                break;
            }
        }
        return value;
    }

    public static ThemeEnum getEnumByKey(int key){
        ThemeEnum themeSaasEnum = null;
        for(ThemeEnum themeSaas : ThemeEnum.values()) {
            if(themeSaas.key == key) {
                themeSaasEnum = themeSaas;
                break;
            }
        }
        return themeSaasEnum;
    }
}
