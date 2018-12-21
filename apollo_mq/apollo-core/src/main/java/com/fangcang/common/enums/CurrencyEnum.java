package com.fangcang.common.enums;

import java.io.Serializable;


/**
 * 
 * @author ivan
 * 提供币种常量和币种常用的方法
 */
public enum CurrencyEnum implements Serializable {
	
	CNY(1,"CNY","人民币"),
	HKD(2,"HKD","港币"),
	MOP(3,"MOP","澳门币"),
	USD(4,"USD","美元"),
	TWD(5,"TWD","台币"),
	THB(6,"THB","泰铢"),
	SGD(7,"SGD","新加坡币"),
	MYR(8,"MYR","马币"),
	JPY(9,"JPY","日币"),
	KRW(10,"KRW","韩币"),
	CAD(11,"CAD","加元"),
	EUR(12,"EUR","欧元"),
	PHP(13,"PHP","比索"),
	VND(14,"VND","越南盾"),
	KPW(15,"KPW","元"),
	KHR(16,"KHR","瑞尔"),
	INR(17,"INR","卢比"),
	BUK(18,"BUK","缅元"),
	NPR(19,"NPR","尼泊尔卢比"),
	MVR(20,"MVR","马尔代夫卢比"),
	LKR(21,"LKR","斯里兰卡卢比"),
	PRK(22,"PRK","巴基斯坦卢比"),
	GBP(23,"GBP","英镑"),
	RUB(24,"RUB","卢布"),
	CHF(25,"CHF","瑞士法郎"),
	AED(26,"AED","迪拉姆"),
	AUD(27,"AUD","澳大利亚元"),
	IDR(28,"IDR","卢比(盾)"),
	NZD(29,"NZD","新西兰元"),
	SAR(30,"SAR","亚尔"),
	IRR(31,"IRR","伊朗里亚尔"),
	IQD(32,"IQD","伊拉克第纳尔"),
	TRL(33,"TRL","土耳其镑"),
	KWD(34,"KWD","科威特第纳尔"),
	BDT(35,"BDT","孟加拉塔卡"),
	BND(36,"BND","文莱元"),
	FJD(37,"FJD","斐济元"),
	LAK(38,"LAK","基普"),
	SEK(39,"SEK","瑞典克朗"),
	OMR(40,"OMR","阿曼里亚尔");
	

	public int key;
	public String value;
	public String desc;

	private CurrencyEnum(int key, String value,String desc) {
		this.key = key;
		this.value = value;
		this.desc = desc;
	}
	
	public static int getKeyByValue(String value) {
		int key = 0;
		for(CurrencyEnum currencyEnum : CurrencyEnum.values()) {
			if(currencyEnum.value .equals(value)) {
				key = currencyEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(int key) {
		String value = "";
		for(CurrencyEnum currencyEnum : CurrencyEnum.values()) {
			if(currencyEnum.key == key) {
				value = currencyEnum.value;
				break;
			}
		}
		return value;
	}
	
	public static String getDescByKey(int key) {
		String desc = "";
		for(CurrencyEnum currencyEnum : CurrencyEnum.values()) {
			if(currencyEnum.key == key) {
				desc = currencyEnum.desc;
				break;
			}
		}
		return desc;
	}
	
	public static String getDescByValue(int value) {
		String desc = "";
		for(CurrencyEnum currencyEnum : CurrencyEnum.values()) {
			if(currencyEnum.value .equals(value)) {
				desc = currencyEnum.desc;
				break;
			}
		}
		return desc;
	}
	
	public static CurrencyEnum  getEnumByKey(int key){
		CurrencyEnum currencyEnum = null;
		for(CurrencyEnum currency : CurrencyEnum.values()) {
			if(currency.key == key) {
				currencyEnum = currency;
				break;
			}
		}
		return currencyEnum;
	}
	
	public static CurrencyEnum getEnumByValue(String value) {
		CurrencyEnum currencyEnum = null;
		for(CurrencyEnum currency : CurrencyEnum.values()) {
			if(currency.value.equals(value)) {
				currencyEnum = currency;
				break;
			}
		}
		return currencyEnum;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
