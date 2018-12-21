package com.fangcang.common.enums.saas;

import java.io.Serializable;

/**
 * 床型
 * @author ivan
 *
 */
public enum BedTypeSaasEnum implements Serializable {

	
	KING("1000000","大床"),TWIN("2000000","双床"),SINGLE("A000000","单床"),THREE("3000000", "三床"),FOUR("4000000","四床"),BDdoble("5000000","大/双床"),BUNK("B000000","上下铺"),BUNKHOUSE("C000000","通铺"),TATAMI("D000000","榻榻米"),WATER("E000000","水床"),ROUND("F000000","圆床"),FIGHT("G000000","拼床"),KANG("H000000","炕");

	public String key;
	public String value;

	private BedTypeSaasEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static String getKeyByValue(String value) {
		String key = null;
		for(BedTypeSaasEnum bedTypeSaasEnum : BedTypeSaasEnum.values()) {
			if(bedTypeSaasEnum.value.equals(value)) {
				key = bedTypeSaasEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(String key) {
		String value = null;
		for(BedTypeSaasEnum bedTypeSaasEnum : BedTypeSaasEnum.values()) {
			if(bedTypeSaasEnum.key.equals(key)) {
				value = bedTypeSaasEnum.value;
				break;
			}
		}
		return value;
	}
	
	public static BedTypeSaasEnum  getEnumByKey(String key){
		BedTypeSaasEnum BedTypeSaasEnum = null;
		for(BedTypeSaasEnum bedType : BedTypeSaasEnum.values()) {
			if(bedType.key.equals(key)) {
				BedTypeSaasEnum = bedType;
				break;
			}
		}
		return BedTypeSaasEnum;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
