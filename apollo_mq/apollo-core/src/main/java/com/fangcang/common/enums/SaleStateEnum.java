package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/19.
 */
public enum SaleStateEnum {

    NO_CHANGED(-2,"不变"),NO_OPEN(-1,"未开通"),OFF_SALE(0,"下架"),ON_SALE(1,"上架"),NO_SHOW(2,"不展示");

    public int  key;
    public String value;

    private SaleStateEnum(int key,String name){
        this.key=key;
        this.value=name;
    }

    public static String getValueByKey(int key){
        String value=null;
        for(SaleStateEnum saleStateEnum:SaleStateEnum.values()){
            if(key==saleStateEnum.key){
                value=saleStateEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value){
        int key = 0;
        for(SaleStateEnum saleStateEnum:SaleStateEnum.values()){
            if(saleStateEnum.value.equals(value)){
                key = saleStateEnum.key;
                break;
            }
        }
        return key;
    }

    public static SaleStateEnum getEnumByKey(int key){
        SaleStateEnum returnEnum=null;
        for(SaleStateEnum saleStateEnum:SaleStateEnum.values()){
            if(key==saleStateEnum.key){
                returnEnum = saleStateEnum;
                break;
            }
        }
        return returnEnum;
    }
}
