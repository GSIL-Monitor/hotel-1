package com.travel.hotel.dlt.enums;

public enum DeductTypeEnum {
	C("C", "全额扣款"), F("F", "首日扣款");

	public String key;
	public String value;
	
	private DeductTypeEnum(String key, String value){
		this.key = key;
		this.value = value;
	}

}
