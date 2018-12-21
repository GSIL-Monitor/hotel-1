package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/16.
 */
public enum ProductTypeEnum {
    SCATTERED_ROOM(1,"散房"),GROUP_ROOM(2,"团房");

    public int key;
    public String value;

    private ProductTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for(ProductTypeEnum quotaTypeEnum : ProductTypeEnum.values()) {
            if(quotaTypeEnum.value .equals(value)) {
                key = quotaTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for(ProductTypeEnum quotaTypeEnum : ProductTypeEnum.values()) {
            if(quotaTypeEnum.key == key) {
                value = quotaTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static ProductTypeEnum getEnumByKey(int key){
        ProductTypeEnum quotaTypeEnum = null;
        for(ProductTypeEnum quotaType : ProductTypeEnum.values()) {
            if(quotaType.key == key) {
                quotaTypeEnum = quotaType;
                break;
            }
        }
        return quotaTypeEnum;
    }

    public String getValue() {
        return value;
    }

}
