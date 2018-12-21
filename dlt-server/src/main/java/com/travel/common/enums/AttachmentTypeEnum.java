package com.travel.common.enums;
/**
 * @Statetion : 附件类型枚举类
 * @author : Zhiping Sun
 * @date : 2018年1月10日下午4:58:51
 */
public enum AttachmentTypeEnum {

	GUESTNAME("guestName", "入住人"),
	RETURNORDER("returnOrder", "回传单");

	public String key;

	public String value;

	AttachmentTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static AttachmentTypeEnum getEnumByKey(String key) {
		if (null == key || "".equals(key)) {
			return null;
		}
		for (AttachmentTypeEnum at : AttachmentTypeEnum.values()) {
			if (at.key.equals(key)) {
				return at;
			}
		}
		return null;
	}
	
	public static String getValueByKey(String key) {
		String val = "";
		if (null == key || "".equals(key)) {
			return null;
		}
		for (AttachmentTypeEnum at : AttachmentTypeEnum.values()) {
			if (at.key.equals(key)) {
				val = at.value;
				break;
			}
		}
		return val;
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

	public void setVal(String value) {
		this.value = value;
	}

}
