package com.fangcang.common.enums.saas;

public enum ImageTypeSaasEnum {

    ROOMVIEW(1,"房型图"),OUTVIEW(2,"外观图"),INVIEW(3,"大堂图"),TOOLVIEW(4, "设施图"),OTHERVIEW(5,"其他"),BANQUET(6,"宴会厅"),MEET(7,"会议厅");

    public Integer key;
    public String value;

    private ImageTypeSaasEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        Integer key = null;
        for(ImageTypeSaasEnum imageTypeEnum : ImageTypeSaasEnum.values()) {
            if(imageTypeEnum.value.equals(value)) {
                key = imageTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(Integer key) {
        String value = null;
        for(ImageTypeSaasEnum imageTypeEnum : ImageTypeSaasEnum.values()) {
            if(imageTypeEnum.key.equals(key)) {
                value = imageTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static ImageTypeSaasEnum getEnumByKey(Integer key){
    	ImageTypeSaasEnum imageTypeEnum = null;
        for(ImageTypeSaasEnum imageType : ImageTypeSaasEnum.values()) {
            if(imageType.key.equals(key)) {
            	imageTypeEnum = imageType;
                break;
            }
        }
        return imageTypeEnum ;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
