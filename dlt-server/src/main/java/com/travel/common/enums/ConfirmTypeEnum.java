package com.travel.common.enums;
/**
 * @Description : 订单确认枚举类
 * @author : Zhiping Sun
 * @date : 2018年3月2日上午9:29:12
 */
public enum ConfirmTypeEnum {

	CONFIRM("confirm","确认单"), PAY("pay","支付单"), REFUND("refund","退款单"), BOOKING("booking","预定单"), EDIT("edit","修改单"), CANCEL("cancel","取消单");
	
	public String code;

	public String descrip;
	
	private ConfirmTypeEnum(String code, String descrip) {
		this.code = code;
		this.descrip = descrip;
	}
	
	public static ConfirmTypeEnum getEnumByCode(String code) {
		for (ConfirmTypeEnum ct : ConfirmTypeEnum.values()) {
			if (ct.code.equals(code)) {
				return ct;
			}
		}
		return null;
	}
	
	public static String getDescripByCode(String code) {
		String des = "";
		for (ConfirmTypeEnum ct : ConfirmTypeEnum.values()) {
			if (ct.code.equals(code)) {
				des = ct.descrip;
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
