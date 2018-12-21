package com.fangcang.common.enums.B2B;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 15:59
 * @Description: 免房政策
 */
public enum FreeRoomPolicyEnum {

    OVERFIVE(1, "全陪免半"),
    HALFPEREIGHT(2, "8免半16兔1");

    public int key;
    public String value;

    private FreeRoomPolicyEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(int key) {
        String value = null;
        for (FreeRoomPolicyEnum freeRoomPolicyEnum : FreeRoomPolicyEnum.values()) {
            if (key == freeRoomPolicyEnum.key) {
                value = freeRoomPolicyEnum.value;
                break;
            }
        }
        return value;
    }

    public static int getKeyByValue(String value) {
        int key = 0;
        for (FreeRoomPolicyEnum freeRoomPolicyEnum : FreeRoomPolicyEnum.values()) {
            if (freeRoomPolicyEnum.value.equals(value)) {
                key = freeRoomPolicyEnum.key;
                break;
            }
        }
        return key;
    }

    public static FreeRoomPolicyEnum getEnumByKey(int key) {
        FreeRoomPolicyEnum returnEnum = null;
        for (FreeRoomPolicyEnum freeRoomPolicyEnum : FreeRoomPolicyEnum.values()) {
            if (key == freeRoomPolicyEnum.key) {
                returnEnum = freeRoomPolicyEnum;
                break;
            }
        }
        return returnEnum;
    }

}
