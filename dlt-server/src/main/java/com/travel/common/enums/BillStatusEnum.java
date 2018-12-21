package com.travel.common.enums;

/**
 * @Description 对账状态枚举类
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年11月27日 下午10:19:13
 */
public enum BillStatusEnum {
	
	UNCHECKOUT(1, "uncheckout", "未对账"), CHECKOUTED(2, "checkouted", "已对账"), BILLOFF(3, "billoff", "已销账");
	
	public int key;
	
	public String code;
	
	public String desc;

	private BillStatusEnum(int key, String code, String desc) {
		this.key = key;
		this.code = code;
		this.desc = desc;
	}
	
	public static BillStatusEnum getEnumByKey(int key) {
		for (BillStatusEnum ut : BillStatusEnum.values()) {
			if (ut.key == key) {
				return ut;
			}
		}
		return null;
	}
	
	public static String getCodeByKey(int key) {
		String code = "";
		for (BillStatusEnum ut : BillStatusEnum.values()) {
			if (ut.key == key) {
				code = ut.code;
				break;
			}
		}
		return code;
	}
	
	public static String getDescByKey(int key) {
		String desc = "";
		for (BillStatusEnum ut : BillStatusEnum.values()) {
			if (ut.key == key) {
				desc = ut.desc;
				break;
			}
		}
		return desc;
	}
}
