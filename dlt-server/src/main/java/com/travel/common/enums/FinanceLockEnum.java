package com.travel.common.enums;
/**
 * @Description : 订单锁单状态枚举类
 * @author : Zhiping Sun
 * @date : 2018年1月22日上午10:39:43
 */
public enum FinanceLockEnum {
	
	LOCKED(1, "locked", "已锁定"), UNCLOCK(0, "unlock", "已解锁");

	public int key;
	
	public String code;
	
	public String desc;

	private FinanceLockEnum(int key, String code, String desc) {
		this.key = key;
		this.code = code;
		this.desc = desc;
	}
	
	public static FinanceLockEnum getEnumByKey(int key) {
		for (FinanceLockEnum as : FinanceLockEnum.values()) {
			if (as.key == key) {
				return as;
			}
		}
		return null;
	}
	
	public static String getCodeByKey(int key) {
		String code = "";
		for (FinanceLockEnum fl : FinanceLockEnum.values()) {
			if (fl.key == key) {
				code = fl.code;
				break;
			}
		}
		return code;
	}
	
	public static String getDescByKey(int key) {
		String desc = "";
		for (FinanceLockEnum fl : FinanceLockEnum.values()) {
			if (fl.key == key) {
				desc = fl.desc;
				break;
			}
		}
		return desc;
	}
	
	public static String getDescByCode(String code) {
		String desc = "";
		if (null == code || "".equals(code)) {
			return "";
		}
		for (FinanceLockEnum fl : FinanceLockEnum.values()) {
			if (code.equals(fl.code)) {
				desc = fl.desc;
				break;
			}
		}
		return desc;
	}
}
