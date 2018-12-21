package com.fangcang.common.enums.saas;

/**
 * Created by ASUS on 2018/6/25.
 */
public enum  ThemeSaasEnum {
    VILLA(1012, "度假村或别墅"), FLAT(1013, "公寓"),HOTEL(1014, "酒店"),HOMESTAY(1015,  "民宿或旅社"),
    BUSINESS_METTING(1001, "商务会议"),HOLIDAY(1002,  "温泉度假"),SEASIDE(1003, "海滨休闲"),THEMATIC(1004,  "主题特色"),
    ENTERTAINMENT(1005,  "娱乐休闲"),PARENT_CHILD_TRAVEL(1008,  "亲子出游"),INN(1009,  "客栈"),AGRITAINMENT(1010,  "农家乐")
    ,YOUTH_HOSTEL(1011,  "青年旅舍"),SCENIC_HOTEL(1006,  "景区酒店"),OTHER(1007,  "其他");

    public int key;
    public String value;

    private ThemeSaasEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(ThemeSaasEnum themeSaasEnum : ThemeSaasEnum.values()) {
            if(themeSaasEnum.value .equals(value)) {
                key = themeSaasEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(ThemeSaasEnum themeSaasEnum : ThemeSaasEnum.values()) {
            if(themeSaasEnum.key == key) {
                value = themeSaasEnum.value;
                break;
            }
        }
        return value;
    }

    public static ThemeSaasEnum  getEnumByKey(int key){
        ThemeSaasEnum themeSaasEnum = null;
        for(ThemeSaasEnum themeSaas : ThemeSaasEnum.values()) {
            if(themeSaas.key == key) {
                themeSaasEnum = themeSaas;
                break;
            }
        }
        return themeSaasEnum;
    }
}
