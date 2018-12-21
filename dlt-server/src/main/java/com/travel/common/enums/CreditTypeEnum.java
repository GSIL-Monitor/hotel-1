package com.travel.common.enums;

/**
 * @Description 挂账类型枚举类
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年11月27日 下午10:19:13
 */
public enum CreditTypeEnum {
	
	CREDIT(1, "credit", "挂账"), CANCEL(2, "cancel", "取消");
	
	public int key;
	
	public String code;
	
	public String desc;

	private CreditTypeEnum(int key, String code, String desc) {
		this.key = key;
		this.code = code;
		this.desc = desc;
	}
	
	public static CreditTypeEnum getEnumByKey(int key) {
		for (CreditTypeEnum ut : CreditTypeEnum.values()) {
			if (ut.key == key) {
				return ut;
			}
		}
		return null;
	}
	
	public static String getCodeByKey(int key) {
		String code = "";
		for (CreditTypeEnum ut : CreditTypeEnum.values()) {
			if (ut.key == key) {
				code = ut.code;
				break;
			}
		}
		return code;
	}
	
	public static String getDescByKey(int key) {
		String desc = "";
		for (CreditTypeEnum ut : CreditTypeEnum.values()) {
			if (ut.key == key) {
				desc = ut.desc;
				break;
			}
		}
		return desc;
	}
}
