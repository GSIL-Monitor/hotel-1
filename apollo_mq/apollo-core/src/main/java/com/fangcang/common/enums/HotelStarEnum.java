package com.fangcang.common.enums;

import java.io.Serializable;

/**
 * 星级
 * @author ivan
 *
 */
public enum HotelStarEnum implements Serializable {


	FIVESTAR(50, "五星"), QUISAFIVESTAR(49, "豪华型"),FOURSTAR(40, "四星"),QUISAFOURSTAR(35,  "高档型"),
    THREESTAR(30, "三星"),QUISATHREESTAR(25,  "舒适型"),TWOSTAR(20, "二星"),QUISATWOSTAR(15,  "经济型"),
    BELOWTWOSTAR(10,  "二星以下");

	public int key;
	public String value;

	private HotelStarEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static int getKeyByValue(String value) {
		int key = 0;
		for(HotelStarEnum hotelStarEnum : HotelStarEnum.values()) {
			if(hotelStarEnum.value .equals(value)) {
				key = hotelStarEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(int key) {
		String value = null;
		for(HotelStarEnum hotelStarEnum : HotelStarEnum.values()) {
			if(hotelStarEnum.key == key) {
				value = hotelStarEnum.value;
				break;
			}
		}
		return value;
	}
	
	public static HotelStarEnum getEnumByKey(int key){
		HotelStarEnum hotelStarEnum = null;
		for(HotelStarEnum hotelStar : HotelStarEnum.values()) {
			if(hotelStar.key == key) {
				hotelStarEnum = hotelStar;
				break;
			}
		}
		return hotelStarEnum;
	}
	
}
