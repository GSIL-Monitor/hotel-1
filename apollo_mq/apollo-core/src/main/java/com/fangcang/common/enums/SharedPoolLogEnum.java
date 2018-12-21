package com.fangcang.common.enums;

public enum SharedPoolLogEnum {

	 CREATE_SHARED_POOL(1,"创建共享池"),ADD_TO_SHARED_POOL(2,"加入共享池"),CANCEL_SHARED_POOL(3,"取消共享池");

    public Integer key;
    public String value;

    private SharedPoolLogEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        Integer key = null;
        for(SharedPoolLogEnum sharedPoolLogEnum : SharedPoolLogEnum.values()) {
            if(sharedPoolLogEnum.value.equals(value)) {
                key = sharedPoolLogEnum.key;
                break;
            }
        }
        return key;
    }

    public static String getValueByKey(Integer key) {
        String value = null;
        for(SharedPoolLogEnum sharedPoolLogEnum : SharedPoolLogEnum.values()) {
            if(sharedPoolLogEnum.key.equals(key)) {
                value = sharedPoolLogEnum.value;
                break;
            }
        }
        return value;
    }

    public static SharedPoolLogEnum getEnumByKey(Integer key){
    	SharedPoolLogEnum sharedPoolLogEnum = null;
        for(SharedPoolLogEnum sharedPool : SharedPoolLogEnum.values()) {
            if(sharedPool.key.equals(key)) {
                sharedPoolLogEnum = sharedPool;
                break;
            }
        }
        return sharedPoolLogEnum ;
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
