package com.travel.common.enums;

/**
 *   2018/1/23.
 */
public enum ChannelEnum {

    B2B("B2B","B2B"),
    CTRIP("ctrip","携程"),
    CTRIP_B2B("ctrip_b2b","携程B2B"),
    CTRIP_CHANNEL_A("ctrip_channel_a","携程ChannelA"),
    QUNAR("qunar","去哪儿"),
    FEIZHU("feizhu","飞猪"),
    XMD("xmd","新美大"),
    TUNIU("tuniu","途牛"),
    JD("jd","京东"),
    TONGCHENG("tongcheng","同程"),
    QUNAR_SON("qunar_son","去哪儿子渠道");

    public String key;
    public String value;

    private ChannelEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getKeyByValue(String value) {
        String key = null;
        ChannelEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ChannelEnum roomStateEnum = arr$[i$];
            if (roomStateEnum.value.equals(value)) {
                key = roomStateEnum.key;
                break;
            }
        }

        return key;
    }

    public static String getValueByKey(String key) {
        String value = null;
        ChannelEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ChannelEnum roomStateEnum = arr$[i$];
            if (roomStateEnum.key.equals(key)) {
                value = roomStateEnum.value;
                break;
            }
        }

        return value;
    }

    public static ChannelEnum getEnumByKey(String key) {
        ChannelEnum roomStateEnum = null;
        ChannelEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ChannelEnum bedType = arr$[i$];
            if (bedType.key.equals(key)) {
                roomStateEnum = bedType;
                break;
            }
        }

        return roomStateEnum;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
