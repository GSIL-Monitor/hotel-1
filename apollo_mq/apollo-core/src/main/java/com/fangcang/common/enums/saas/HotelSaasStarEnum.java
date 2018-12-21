package com.fangcang.common.enums.saas;

import java.io.Serializable;

/**
 * 星级
 * @author ivan
 *
 */
public enum HotelSaasStarEnum implements Serializable {


	FIVESTAR(19, "五星"), QUISAFIVESTAR(29, "豪华型"),FOURSTAR(39, "四星"),QUISAFOURSTAR(49,  "高档型"),
    THREESTAR(59, "三星"),QUISATHREESTAR(64,  "舒适型"),TWOSTAR(69, "二星"),QUISATWOSTAR(66,  "经济型"),
    BELOWTWOSTAR(79,  "二星以下");

	public int key;
	public String value;

	private HotelSaasStarEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static int getKeyByValue(String value) {
		int key = 0;
		for(HotelSaasStarEnum hotelStarEnum : HotelSaasStarEnum.values()) {
			if(hotelStarEnum.value .equals(value)) {
				key = hotelStarEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(int key) {
		String value = null;
		for(HotelSaasStarEnum hotelStarEnum : HotelSaasStarEnum.values()) {
			if(hotelStarEnum.key == key) {
				value = hotelStarEnum.value;
				break;
			}
		}
		return value;
	}
	
	public static HotelSaasStarEnum  getEnumByKey(int key){
		HotelSaasStarEnum hotelStarEnum = null;
		for(HotelSaasStarEnum hotelStar : HotelSaasStarEnum.values()) {
			if(hotelStar.key == key) {
				hotelStarEnum = hotelStar;
				break;
			}
		}
		return hotelStarEnum;
	}
	
}
