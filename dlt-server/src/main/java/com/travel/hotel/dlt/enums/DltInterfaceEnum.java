package com.travel.hotel.dlt.enums;

public enum DltInterfaceEnum {

    GET_DLT_COUNTRY_LIST("getdltcountrylist", "获取国家列表"),
    BATCH_PUSH_ROOM_DATA("BatchPushRoomData", "直连推送报价等数据接口"),
    GET_DLT_ORDER_NOTIFY("getdltordernotify", "订单变化通知接口"),
    GET_DLT_ORDER_INFO("getdltorderinfo", "订单详情接口"),
    OPERATER_DLT_ORDER("operaterDltOrder", "订单操作接口"),
    SET_ROOM_SALE_RULE_DATA("setRoomSaleRule", "更新房型售卖规则"),;

    public String code;
    public String name;

    DltInterfaceEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        if (null != code) {
            for (DltInterfaceEnum e : DltInterfaceEnum.values()) {
                if (e.code.equals(code)) {
                    return e.name;
                }
            }
        }
        return null;
    }
}
