package com.travel.hotel.dlt.enums;

public enum CtripBreakfastEnum {

	BED("bed", 2, "床位早"),
	NONE("none", 0, "无早"),
	ONE("one", 1, "单早"),
	TWO("two",2,  "双早"),
	THREE("three", 3, "三早"),
	FOUR("four", 4, "四早");

	public String key;
	public Integer num;
	public String value;

	CtripBreakfastEnum(String key, Integer num, String value) {
		this.key = key;
		this.num = num;
		this.value = value;
	}
	
	public static String getKeyByNum(Integer num) {
		String key = "";
		for(CtripBreakfastEnum breakfastNumEnum : CtripBreakfastEnum.values()) {
			if(breakfastNumEnum.num .equals(num)) {
				key = breakfastNumEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static Integer getNumByKey(String key) {
		Integer num = null;
		for(CtripBreakfastEnum breakfastNumEnum : CtripBreakfastEnum.values()) {
			if(breakfastNumEnum.key.equals(key)) {
				num = breakfastNumEnum.num;
				break;
			}
		}
		return num;
	}

}
