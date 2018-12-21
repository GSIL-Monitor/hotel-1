package com.travel.common.enums;
/**
 * @Description : 是否有效
 * @author : Zhiping Sun
 * @date : 2018年1月10日下午5:25:25
 */
public enum ActiveEnum {
	
	ACTIVE(1,"有效"), INVALID(0,"无效");
	
	public int key;

	public String descrip;

	private ActiveEnum(int key, String descrip) {
		this.key = key;
		this.descrip = descrip;
	}
	
	public static ActiveEnum getEnumByKey(int key) {
		for (ActiveEnum ae : ActiveEnum.values()) {
			if (ae.key == key) {
				return ae;
			}
		}
		return null;
	}
	
	public static String getDescripByKey(int key) {
		String des = "";
		for (ActiveEnum ae : ActiveEnum.values()) {
			if (ae.key == key) {
				des = ae.descrip;
				break;
			}
		}
		return des;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
}
