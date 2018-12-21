package com.fangcang.product.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class PricePlanDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = 7196324521416668299L;

    private Long pricePlanId;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;


    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 价格计划名称
     */
    private String pricePlanName;

    /**
     * 早餐类型
     * com.fangcang.common.enums.BreakFastTypeEnum
     */
    private Integer breakFastType;

    /**
     * 配额类型
     * com.fangcang.common.enums.QuotaTypeEnum
     */
    private Integer quotaType;

    /**
     *床型
     * com.fangcang.common.enums.BedTypeEnum
     */
    private String bedType;

    /**
     *是否含有附加项(1含有, 0 不含有)
     */
    private Integer isAdditional;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 支付方式
     * com.fangcang.common.enums.PayMethodEnum
     */
    private Integer payMethod;

    /**
     * 供应方式
     */
    private Integer supplyWay;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 适用人群
     */
    private String applicablePeople;
}
