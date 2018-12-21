package com.travel.common.enums;
/**
 * @Description : 支付方式枚举类
 * @author : Zhiping Sun
 * @date : 2018年1月31日上午11:13:50
 */
public enum PayMethodEnum {
	
	PREPAYROOM(1,"prepay_room","房费挂账杂费自理"), PREPAYALL(2,"prepay_all","所有费用挂账"), PAY(3,"pay","前台现付"), CASH(4,"cash","现金支付");
	
	public int key;
	
	public String code;
	
	public String descrip;

	private PayMethodEnum(int key, String code, String descrip) {
		this.key = key;
		this.code = code;
		this.descrip = descrip;
	}
	
	public static PayMethodEnum getEnumByKey(int key) {
		for (PayMethodEnum pm : PayMethodEnum.values()) {
			if (pm.key == key) {
				return pm;
			}
		}
		return null;
	}
	
	public static String getCodeByKey(int key) {
		String code = "";
		for (PayMethodEnum pm : PayMethodEnum.values()) {
			if (pm.key == key) {
				code = pm.code;
				break;
			}
		}
		return code;
	}
	
	public static String getDescByKey(int key) {
		String desc = "";
		for (PayMethodEnum pm : PayMethodEnum.values()) {
			if (pm.key == key) {
				desc = pm.descrip;
				break;
			}
		}
		return desc;
	}
	
	public static String getDescByCode(String code) {
		String desc = "";
		for (PayMethodEnum pm : PayMethodEnum.values()) {
			if (pm.code.equals(code)) {
				desc = pm.descrip;
				break;
			}
		}
		return desc;
	}

}
