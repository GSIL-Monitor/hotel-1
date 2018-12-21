package com.fangcang.common.enums.saas;

import java.io.Serializable;

public enum PutTypeSaasEnum implements Serializable {

	THEATER(1,"剧场式"),DESK(2,"课桌式"),UTYPE(3,"U型式"),RTYPE(4,"回型式"),BANQUET(5,"宴会式"),DIRECTOR(6,"董事会"),FISHBONE(7,"鱼骨式");
	
	public int key;

	public String value;
	
	private PutTypeSaasEnum(int key,String value){
		this.key=key;
		this.value=value;
	}
	
	public static String getValueByKey(int key){
		String value="";
		for(PutTypeSaasEnum e:PutTypeSaasEnum.values()){
			if(e.key == key){
				value=e.value;
				break;
			}
		}
		return value;
	}
	
	public static int getKeyByValue(String value){
		int key=0;
		for(PutTypeSaasEnum e:PutTypeSaasEnum.values()){
			if(e.value.equals(value)){
				key=e.key;
				break;
			}
		}
		return key;
	}
	
	public static PutTypeSaasEnum getEnumByKey(int key){
		PutTypeSaasEnum en=null;
		for(PutTypeSaasEnum e:PutTypeSaasEnum.values()){
			if(e.key == key){
				en=e;
				break;
			}
		}
		return en;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
