package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/19.
 */
public enum PageOperateEnum {

    NO_CHANGED(-1,"不变");

    public int  key;
    public String value;

    private PageOperateEnum(int key, String name){
        this.key=key;
        this.value=name;
    }

    public static String getValueByKey(int key){
        String value=null;
        for(PageOperateEnum saleStateEnum: PageOperateEnum.values()){
            if(key==saleStateEnum.key){
                value=saleStateEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value){
        int key = 0;
        for(PageOperateEnum saleStateEnum: PageOperateEnum.values()){
            if(saleStateEnum.value.equals(value)){
                key = saleStateEnum.key;
                break;
            }
        }
        return key;
    }

    public static PageOperateEnum getEnumByKey(int key){
        PageOperateEnum returnEnum=null;
        for(PageOperateEnum saleStateEnum: PageOperateEnum.values()){
            if(key==saleStateEnum.key){
                returnEnum = saleStateEnum;
                break;
            }
        }
        return returnEnum;
    }
}
