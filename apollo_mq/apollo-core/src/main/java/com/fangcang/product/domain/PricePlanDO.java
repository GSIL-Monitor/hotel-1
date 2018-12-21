package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import com.fangcang.hotelinfo.domain.HotelAdditionalDO;
import lombok.Data;

import java.util.List;

/**
 * Created by ASUS on 2018/5/16.
 */
@Data
public class PricePlanDO extends BaseDO{

    private Long pricePlanId;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;
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
     * 是否有效
     */
    private Integer isActive;

    /**
     * 产品类型(1散房 2团房)
     */
    private Integer productType;

    /**
     * 面房政策(1 全陪免半 2 8免半16兔1)
     */
    private String freeRoomPolicyStr;

    /**
     * 是否共享
     */
    private Integer isShare;

    private List<HotelAdditionalDO> hotelAdditionalList;

    /*****上下架状态*****/

    /**
     * B2B渠道售卖状态(0 下架,1 上架,空则没有上架)
     */
    private Integer b2bSaleState;

    /**
     * 艺龙渠道售卖状态(0 下架,1 上架,空则没有上架)
     */
    private Integer elongSaleState;


    /**
     * 适用人群
     */
    private String applicablePeople;
}
