package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 14:59
 * @Description: 产品详情DTO
 */
@Data
public class ProductDetailsDTO implements Serializable {

    private static final long serialVersionUID = 3743465045777683378L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 房型ID
     */
    private Integer roomTypeId;

    /**
     * 价格计划ID
     */
    private Integer pricePlanId;

    /**
     * 价格计划名称
     */
    private String pricePlanName;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * ZERO(0,"无早"), ONE(1,"单早"), TWO(2,"双早"),HEAD_NUM(3,"人头早");
     */
    private Integer breakFastType;

    /**
     * 配额类型
     */
    private Integer quotaType;

    /**
     * SINGLE(0,"单床"),KING(1,"大床"),TWIN(2,"双床"),THREE(3, "三床"),FOUR(4,"四床");
     */
    private String bedType;

    /**
     * 是否有附加项
     */
    private Integer isAdditional;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * PAY(1, "pay", "现付(有佣金)"), PRE_PAY(2, "pre_pay", "预付"),PAY_NOCOMISSION(3,"pay_nocommission","现付(无佣金)");
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
     * 产品类型(1散房 2团房)
     */
    private Integer productType;

    /**
     * 免房政策(1 全陪免半 2 8免半16兔1)
     */
    private String [] freeRoomPolicy;

    /**
     * 首日价格
     */
    private BigDecimal firstDayPrice;

    /**
     * 首日团房售价
     */
    private BigDecimal firstGroupSalePrice;

    /**
     * 展示币种
     */
    private String showCurrency;

    /**
     * 1 预定 2部分可定
     */
    private String bookType;

    /**
     * 产品每日详情
     */
    private List<SaleItemDetailDTO> productDailyList;

    /**
     * 起价
     */
    private BigDecimal startPrice;

    /**
     * 散房价格类型  1 预订  2部分可订 3 不可订
     */
    private Integer scatteredRoomBookType;

    /**
     * 团房价格类型  1 预订  2部分可订 3 不可订
     */
    private Integer groupRoomBookType;
}
