package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/17.
 */
@Data
public class PricePlanRequestDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -5932592876084815791L;

    private Long pricePlanId;
    /**
     * 酒店ID
     */
    @NotNull(message = "hotelId不能为Null")
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型ID
     */
    @NotNull(message = "roomTypeId不能为Null")
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
    @NotEmpty(message = "supplyCode不能为Null或者空字符串")
    private String supplyCode;

    /**
     * 币种
     */
    @NotNull(message = "currency不能为Null")
    private String currency;

    /**
     * 价格计划名称
     */
    @NotEmpty(message = "pricePlanName不能为Null或者空字符串")
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
    @NotNull(message = "payMethod不能为Null")
    private Integer payMethod;

    /**
     * 供应方式
     */
    @NotNull(message = "supplyWay不能为Null")
    private Integer supplyWay;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 产品类型(1 散房  ,2团房)
     */
    private Integer productType;

    /**
     * 面房政策(1 全陪免半 2 8免半16兔1)
     */
    private String [] freeRoomPolicy;


    /**
     * 提前预订天数
     */
    private Integer advanceBookingDays;
    /**
     * 提前预订小时数
     */
    private Integer advanceBookingHours;

    /**
     * 连住天数
     */
    private Integer occupancyOfDays;

    /**
     * 至少预订间数
     */
    private Integer numberOfBooking;

    /**
     * 1-已经预定不可取消；2-提前取消
     */
    private Integer cancelType;

    /**
     * 提前取消天数
     */
    private Integer cancelDays;

    /**
     * 提前取消小时
     * 12点就是1200
     * 18:30就是1830
     */
    private String cancelHours;

    /**
     * 最晚确认天数
     */
    private Integer lastConfirmDays;

    /**
     * 最晚确认时间
     */
    private Integer lastConfirmHours;

    /**
     * 适用人群
     */
    private String applicablePeople;

}
