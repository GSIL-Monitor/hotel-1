package com.travel.common.enums;
/**
 * @Description : 附加费类型枚举
 * @author : Zhiping Sun
 * @date : 2018年1月10日下午5:25:25
 */
public enum AdditionalTypeEnum {
	
	BED("bed","加床"), BREAKFAST("breakfast","加早");
	
	public String key;

	public String descrip;

	private AdditionalTypeEnum(String key, String descrip) {
		this.key = key;
		this.descrip = descrip;
	}
	
	public static AdditionalTypeEnum getEnumByKey(String key) {
		for (AdditionalTypeEnum ae : AdditionalTypeEnum.values()) {
			if (ae.key.equals(key)) {
				return ae;
			}
		}
		return null;
	}
	
	public static String getDescripByKey(String key) {
		String des = "";
		for (AdditionalTypeEnum ae : AdditionalTypeEnum.values()) {
			if (ae.key.equals(key)) {
				des = ae.descrip;
				break;
			}
		}
		return des;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
}
