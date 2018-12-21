package com.travel.common.enums;
/**
 * @Description : 币种枚举类
 * @author :
 * @date : 2018年2月1日上午11:13:50
 */
public enum CurrencyEnum {

	CNY("CNY","人民币"),
	HKD("HKD","港币"),
	MOP("MOP","澳门葡币");

	public String code;

	public String desc;

	CurrencyEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		String desc = null;
		for (CurrencyEnum pm : CurrencyEnum.values()) {
			if (pm.code.equals(code)) {
				desc = pm.desc;
				break;
			}
		}
		return desc;
	}

}
