package com.fangcang.supplier.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 20:11
 * @Description:常用供应商
 */
@Data
public class FrequentSupplyDTO implements Serializable {

    private static final long serialVersionUID = -226509853427318564L;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 币种
     */
    private String currency;

    /**
     * 供应商ID
     */
    private Long supplyId;


}
