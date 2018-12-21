package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/17.
 */
public enum HotelFeatureEnum {

    NETWORK(1,"宽带网络"),PARKING(2,"停车场"),DINING_FEATURE(3,"餐饮设施"),HOTEL_SERVICE(4, "酒店服务"),ROOM(5,"房间设施"),MEAL_TYPE(6,"餐型");

    public Integer key;
    public String value;

    private HotelFeatureEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        Integer key = null;
        for(HotelFeatureEnum hotelFeatureEnum : HotelFeatureEnum.values()) {
            if(hotelFeatureEnum.value.equals(value)) {
                key = hotelFeatureEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(Integer key) {
        String value = null;
        for(HotelFeatureEnum hotelFeatureEnum : HotelFeatureEnum.values()) {
            if(hotelFeatureEnum.key.equals(key)) {
                value = hotelFeatureEnum.value;
                break;
            }
        }
        return value;
    }

    public static HotelFeatureEnum getEnumByKey(String key){
        HotelFeatureEnum hotelFeatureEnum = null;
        for(HotelFeatureEnum hotelFeature : HotelFeatureEnum.values()) {
            if(hotelFeature.key.equals(key)) {
                hotelFeatureEnum = hotelFeature;
                break;
            }
        }
        return hotelFeatureEnum;
    }

}
