package com.fangcang.common.enums;

import java.io.Serializable;

public enum ChannelTypeEnum implements Serializable {

	B2B("B2B","B2B"),CTRIP("ctrip","携程"),CTRIP_B2B("ctrip_b2b","携程B2B"),CTRIP_CHANNEL_A("ctrip_channel_a","携程"),ELONG("elong","艺龙"),TONGCHENG("tongcheng","同程"),TUNIU("tuniu","途牛"),XMD("xmd","新美大"),JD("jd","京东"),QUNAR("qunar","去哪儿TTS"),QUNAR_B2B("qunar_B2B","去哪儿大B"),QUNAR_NGT("qunar_ngt","去哪儿夜销"),QUNAR_USD("qunar_usd","去哪儿美元"),QUNAR_SON("qunar_son","去哪儿子渠道"),TAOBAO("taobao","淘宝");
	
	public String key;
	public String value;

	private ChannelTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static String getKeyByValue(String value) {
		String key = "";
		for(ChannelTypeEnum channelTypeEnum : ChannelTypeEnum.values()) {
			if(channelTypeEnum.value.equals(value)) {
				key = channelTypeEnum.key;
				break;
			}
		}
		return key;
	}
	
	public static String getValueByKey(String key) {
		String value = null;
		for(ChannelTypeEnum channelTypeEnum : ChannelTypeEnum.values()) {
			if(channelTypeEnum.key.equals(key)) {
				value = channelTypeEnum.value;
				break;
			}
		}
		return value;
	}
	
	public static ChannelTypeEnum  getEnumByKey(String key){
		ChannelTypeEnum channelTypeEnum = null;
		for(ChannelTypeEnum channelType : ChannelTypeEnum.values()) {
			if(channelType.key.equals(key)) {
				channelTypeEnum = channelType;
				break;
			}
		}
		return channelTypeEnum;
	}

}
