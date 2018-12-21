package com.fangcang.supplier.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/30 16:02
 * @Description: 获取供应商账户操作日志RequestDTO
 */
@Data
public class GetSupplyUserLogRequestDTO implements Serializable {

    private static final long serialVersionUID = -7695004552192425392L;

    /**
     * 供应商id
     */
    private Long supplyId;

    /**
     * 供应商下用户Id
     */
    private Long userId;
}
