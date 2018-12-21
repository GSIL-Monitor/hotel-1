package com.travel.hotel.dlt.enums;

public enum RoomstatusEnum {
	//G针对配额大于10，房态有房的；
	//S针对配额小于10，有房，或者有房可超的；
	//L针对有配额不可超，有房，配额小于10？
	N("N", "满房"),S("S", "房量有限"),G("G", "良好"),L("L", "不可超");
	
	public String key;
	public String value;
	
	private RoomstatusEnum(String key, String value){
		this.key = key;
		this.value = value;
	}
}
