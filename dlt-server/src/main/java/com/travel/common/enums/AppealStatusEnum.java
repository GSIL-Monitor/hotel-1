package com.travel.common.enums;
/**
 * @Description : 申诉状态枚举类
 * @author : Zhiping Sun
 * @date : 2018年1月17日下午4:03:57
 */
public enum AppealStatusEnum {

	NEW(1, "new", "新建"), PROCESSING(2, "processing", "处理中"), SUCCESS(3, "success", "申诉成功"), FAIL(0, "fail", "申诉失败");
	
	public int key;
	
	public String code;
	
	public String desc;

	private AppealStatusEnum(int key, String code, String desc) {
		this.key = key;
		this.code = code;
		this.desc = desc;
	}
	
	public static AppealStatusEnum getEnumByKey(int key) {
		for (AppealStatusEnum as : AppealStatusEnum.values()) {
			if (as.key == key) {
				return as;
			}
		}
		return null;
	}
	
	public static String getCodeByKey(int key) {
		String code = "";
		for (AppealStatusEnum as : AppealStatusEnum.values()) {
			if (as.key == key) {
				code = as.code;
				break;
			}
		}
		return code;
	}
	
	public static String getDescByKey(int key) {
		String desc = "";
		for (AppealStatusEnum as : AppealStatusEnum.values()) {
			if (as.key == key) {
				desc = as.desc;
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
		for (AppealStatusEnum as : AppealStatusEnum.values()) {
			if (code.equals(as.code)) {
				desc = as.desc;
				break;
			}
		}
		return desc;
	}
}
