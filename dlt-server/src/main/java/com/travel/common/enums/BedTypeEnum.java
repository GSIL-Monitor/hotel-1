package com.travel.common.enums;

/**
 *   2018/1/11.
 */
import java.io.Serializable;

public enum BedTypeEnum implements Serializable {
    KING("10", "大床"),
    TWIN("20", "双床"),
    THREE("30", "三床"),
    FOUR("40", "四床"),
    OTHER("99", "其他");

    public String key;
    public String value;

    private BedTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getKeyByValue(String value) {
        String key = null;
        BedTypeEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            BedTypeEnum bedTypeEnum = arr$[i$];
            if (bedTypeEnum.value.equals(value)) {
                key = bedTypeEnum.key;
                break;
            }
        }

        return key;
    }

    public static String getValueByKey(String key) {
        String value = null;
        BedTypeEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            BedTypeEnum bedTypeEnum = arr$[i$];
            if (bedTypeEnum.key.equals(key)) {
                value = bedTypeEnum.value;
                break;
            }
        }

        return value;
    }

    public static BedTypeEnum getEnumByKey(String key) {
        BedTypeEnum bedTypeEnum = null;
        BedTypeEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            BedTypeEnum bedType = arr$[i$];
            if (bedType.key.equals(key)) {
                bedTypeEnum = bedType;
                break;
            }
        }

        return bedTypeEnum;
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
