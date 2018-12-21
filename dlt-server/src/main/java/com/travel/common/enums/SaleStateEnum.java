package com.travel.common.enums;
/**
 * @Description : 上下架
 * @author : Vinney
 * @date : 2018年1月10日下午5:25:25
 */
public enum SaleStateEnum {

	ON_SALE(1,"上架"), OFF_SALE(0,"下架");

	public int key;

	public String descrip;

	private SaleStateEnum(int key, String descrip) {
		this.key = key;
		this.descrip = descrip;
	}
	
	public static SaleStateEnum getEnumByKey(int key) {
		for (SaleStateEnum ae : SaleStateEnum.values()) {
			if (ae.key == key) {
				return ae;
			}
		}
		return null;
	}
	
	public static String getDescripByKey(int key) {
		String des = "";
		for (SaleStateEnum ae : SaleStateEnum.values()) {
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
