package com.travel.common.enums;
/**
 * @Description : 订单结算方式
 * @author : Zhiping Sun
 * @date : 2018年1月10日下午4:58:51
 */
public enum SettlementEnum {
	
	MONTHLY("monthly","月结"),HALFMONTHLY("halfmonthly","半月结"),WEEKLY("weekly","周结"),SINGLE("single","单结");
	
	public String code;
	
	public String descrip;

	private SettlementEnum(String code, String descrip) {
		this.code = code;
		this.descrip = descrip;
	}
	
	public static SettlementEnum getEnumByCode(String code) {
		if (null == code || "".equals(code)) {
			return null;
		}
		for (SettlementEnum se : SettlementEnum.values()) {
			if (se.code.equals(code)) {
				return se;
			}
		}
		return null;
	}
	
	public static String getDescripByCode(String code) {
		String des = "";
		if (null == code || "".equals(code)) {
			return null;
		}
		for (SettlementEnum se : SettlementEnum.values()) {
			if (se.code.equals(code)) {
				des = se.descrip;
				break;
			}
		}
		return des;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

}
