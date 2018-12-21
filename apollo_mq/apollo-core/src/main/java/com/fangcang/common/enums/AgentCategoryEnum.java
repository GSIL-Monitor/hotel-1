package com.fangcang.common.enums;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/10 10:16
 * @Description: 分销商类别
 */
public enum AgentCategoryEnum {

    ENTERPRISE(1, "企业"), TRAVELAGENCY(2, "旅行社"), TOURISTGUIDE(3, "导游"), FLEET(4, "车队"), HOTELSALESMAN(5, "酒店业务员");

    public int key;
    public String value;

    private AgentCategoryEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(int key) {
        String value = "";
        for (AgentCategoryEnum agentCategoryEnum : AgentCategoryEnum.values()) {
            if (agentCategoryEnum.key == key) {
                value = agentCategoryEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (AgentCategoryEnum agentCategoryEnum : AgentCategoryEnum.values()) {
            if (agentCategoryEnum.value.equals(value)) {
                key = agentCategoryEnum.key;
                break;
            }
        }
        return key;
    }

    public static AgentCategoryEnum getEnumByKey(int key) {
        AgentCategoryEnum resultEnum = null;
        for (AgentCategoryEnum agentCategoryEnum : AgentCategoryEnum.values()) {
            if (agentCategoryEnum.key == key) {
                resultEnum = agentCategoryEnum;
                break;
            }
        }
        return resultEnum;
    }


}
