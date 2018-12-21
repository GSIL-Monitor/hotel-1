package com.fangcang.common.enums;

public enum ImageTypeEnum {
	
	 OUTVIEW(1,"外观图"),ROOMVIEW(2,"房型图"),INVIEW(3,"大堂图"),TOOLVIEW(4, "设施图"),BANQUET(5,"宴会厅"),MEET(6,"会议厅"),OTHERVIEW(7,"其他");

    public Integer key;
    public String value;

    private ImageTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        Integer key = null;
        for(ImageTypeEnum imageTypeEnum : ImageTypeEnum.values()) {
            if(imageTypeEnum.value.equals(value)) {
                key = imageTypeEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(Integer key) {
        String value = null;
        for(ImageTypeEnum imageTypeEnum : ImageTypeEnum.values()) {
            if(imageTypeEnum.key.equals(key)) {
                value = imageTypeEnum.value;
                break;
            }
        }
        return value;
    }

    public static ImageTypeEnum getEnumByKey(Integer key){
    	ImageTypeEnum imageTypeEnum = null;
        for(ImageTypeEnum imageType : ImageTypeEnum.values()) {
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
