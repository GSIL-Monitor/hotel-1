package com.fangcang.supplier.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/8/13.
 */
@Data
public class MerchantOPDTO implements Serializable {
    private static final long serialVersionUID = 5840495739896123088L;

    /**
     * 跟单员账号ID
     */
    private Long merchantOP;

    /**
     * 跟单员姓名
     */
    private String merchantOPName;

    /**
     * 业务联系电话
     */
    private String merchantOPPhone;
}
