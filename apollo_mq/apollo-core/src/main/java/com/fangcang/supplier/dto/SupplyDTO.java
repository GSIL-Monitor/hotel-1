package com.fangcang.supplier.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SupplyDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = 5927320318232435538L;
    private Long supplyId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名
     */
    private String supplyName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 电话
     */
    private String phone;

    private String email;

    /**
     * 产品经理账号
     */
    private String merchantPM;

    /**
     * 业务经理账号
     */
    private String merchantBM;

    /**
     * 财务经理账号
     */
    private String merchantFinancer;

    /**
     * 跟单员账号
     */
    private String merchantOP;

    /**
     * 是否启用
     */
    private Integer isActive;

    /**
     * 地址
     */
    private String address;

    /**
     * 低价币种
     */
    private String baseCurrency;

    /**
     * 是否常用
     */
    private Integer frequentlyUse;

    /**
     * 财务电话
     */
    private String financePhone;

    /**
     * 前台电话
     */
    private String frontPhone;

    /**
     * 总机电话
     */
    private String mainPhone;

    /**
     * 副总电话
     */
    private String deputyPhone;

    /**
     * 对接电话
     */
    private String jointPhone;

    /**
     * 结算方式
     */
    private Integer billingMethod;

    /**
     * 客户等级
     */
    private Integer customerLevel;

    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;

    /**
     * 备注
     */
    private String note;

}