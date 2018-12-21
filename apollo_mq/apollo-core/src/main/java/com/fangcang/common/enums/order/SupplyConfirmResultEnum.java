package com.fangcang.common.enums.order;

/**
 * Created by Vinney on 2018/9/15.
 */
public enum SupplyConfirmResultEnum {
    /*
    * bookYes-同意预订,
            * bookNo-拒绝预订;
    * modifyYes-同意修改,
            * modifyNo-拒绝修改;
    * cancelYes-同意取消,
            * cancelNo-拒绝取消.
            */
    BOOK_YES("bookYes","同意预订"),
    BOOK_NO("bookNo","拒绝预订"),
    MODIFY_YES("modifyYes","同意修改"),
    MODIFY_NO("modifyNo","拒绝修改"),
    CANCEL_YES("cancelYes","同意取消"),
    CANCEL_NO("cancelNo","拒绝取消");

    public String key ;
    public String value ;

    SupplyConfirmResultEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
