package com.fangcang.common.enums.order;

import java.io.Serializable;

/**
 * 附加类型
 */
public enum AttachTypeEnum implements Serializable {

    SUPPLIERCONFIRM(1, "供应商确认预定"),
    SUPPLIERMODIFY(2, "供应商确认修改"),
    SUPPLIERCANCEL(3, "供应商确认取消"),
    SUPPLIERPAY(4, "给供应商付款凭证"),

    DISTRIBUTORCONFIRM(5, "分销商预定单"),
    DISTRIBUTORMODIFY(6, "分销商修改单"),
    DISTRIBUTORCANCEL(7, "分销商取消单"),
    DISTRIBUTORPAY(8, "分销商付款凭证"),

    CHECKIN_LIST(9, "入住名单"),
    OTHER(10, "其它");

    public int key;

    public String value;

    private AttachTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(int key) {
        for (AttachTypeEnum attachTypeEnum : AttachTypeEnum.values()) {
            if (attachTypeEnum.key == key) {
                return attachTypeEnum.value;
            }
        }
        return null;
    }

}
