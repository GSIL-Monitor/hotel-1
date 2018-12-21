package com.fangcang.common.enums;

/**
 * Created by ASUS on 2018/5/17.
 */
public enum SupplyWayEnum {

    SupplyWayEnum(1,"商家自签"),DirectConn(2,"系统直连");

    public int  key;
    public String value;

    private SupplyWayEnum(int key,String name){
        this.key=key;
        this.value=name;
    }

    public static String getValueByKey(int key){
        String value=null;
        for(SupplyWayEnum supplyWayEnum:SupplyWayEnum.values()){
            if(key==supplyWayEnum.key){
                value=supplyWayEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value){
        int key = 0;
        for(SupplyWayEnum supplyWayEnum:SupplyWayEnum.values()){
            if(supplyWayEnum.value.equals(value)){
                key = supplyWayEnum.key;
                break;
            }
        }
        return key;
    }

    public static SupplyWayEnum getEnumByKey(int key){
        SupplyWayEnum returnEnum=null;
        for(SupplyWayEnum supplyWayEnum:SupplyWayEnum.values()){
            if(key==supplyWayEnum.key){
                returnEnum = supplyWayEnum;
                break;
            }
        }
        return returnEnum;
    }
}
