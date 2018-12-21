package com.fangcang.product.dto;

import com.fangcang.product.response.SaleStateResponseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class PricePlanInfoDTO implements Serializable{

    private static final long serialVersionUID = 3078595061343313398L;
    /**
     * 价格计划ID
     */
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
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 价格计划名称
     */
    private String pricePlanName;

    /**
     * 床型
     */
    private String bedType;

    /**
     * 早餐类型
     */
    private Integer breakFastType;

    /**
     * 配额类型
     * com.fangcang.common.enums.QuotaTypeEnum
     */
    private Integer quotaType;

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
    private String [] freeRoomPolicy;

    /**
     * 底价币种
     */
    private String currency;

    /**
     * 是否共享(1 共享  ,0 不共享)
     */
    private Integer isShare;

    /**
     * 是否有价格：0无，1有
     */
    private Integer hasPrice=0;

    /**
     * 每日价格房态信息
     */
    private List<ProductDailyDTO> productDailyList;

    /**
     * 价格计划上下架详情
     */
    private SaleStateResponseDTO saleStateResponseDTO;
}
