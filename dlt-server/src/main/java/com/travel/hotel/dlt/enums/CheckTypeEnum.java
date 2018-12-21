package com.travel.hotel.dlt.enums;

public enum CheckTypeEnum {
	
	C("C", "进店统计"), S("S", "住店统计");
	
	public String key;
	
	public String value;
	
	private CheckTypeEnum(String key, String value){
		this.key = key;
		this.value = value;
	}
}
