package com.travel.common.enums;

/**
 *   2018/1/23.
 */
public enum RoomStateEnum {

    HAS_ROOM_NOT_OVER("hasNotOver","有房不可超"),HAS_ROOM_WITH_OVER("hasOver","有房可超"),ASK("ask","待查")
    ,NO_ROOM("noRoom","满房"),FREESALE("freeSale","FreeSale");

    public String key;
    public String value;

    private RoomStateEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getKeyByValue(String value) {
        String key = null;
        RoomStateEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            RoomStateEnum roomStateEnum = arr$[i$];
            if (roomStateEnum.value.equals(value)) {
                key = roomStateEnum.key;
                break;
            }
        }

        return key;
    }

    public static String getValueByKey(String key) {
        String value = null;
        RoomStateEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            RoomStateEnum roomStateEnum = arr$[i$];
            if (roomStateEnum.key.equals(key)) {
                value = roomStateEnum.value;
                break;
            }
        }

        return value;
    }

    public static RoomStateEnum getEnumByKey(String key) {
        RoomStateEnum roomStateEnum = null;
        RoomStateEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            RoomStateEnum bedType = arr$[i$];
            if (bedType.key.equals(key)) {
                roomStateEnum = bedType;
                break;
            }
        }

        return roomStateEnum;
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
