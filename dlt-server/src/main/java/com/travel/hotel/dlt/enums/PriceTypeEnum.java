package com.travel.hotel.dlt.enums;

public enum PriceTypeEnum {
	Sell("Sell", "卖价"),Cost("Cost", "底价"),SC("SC", "底卖价");
	
	public String key;
	public String value;
	
	private PriceTypeEnum(String key, String value){
		this.key = key;
		this.value = value;
	}
}
