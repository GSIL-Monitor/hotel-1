package com.fangcang.product.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class PricePlanResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -7540711362723815109L;

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
     * 附加项展示字符
     */
    private String additionalStr;

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
     * 产品类型
     */
    private Integer productType;

    /**
     * 免房政策(1 全陪免半 2 8免半16兔1)
     */
    private String [] freeRoomPolicy;

    /**
     * 是否有效
     */
    private Integer isActive;

    private Integer advanceBookingDays;

    private Integer advanceBookingHours;

    private Integer occupancyOfDays;

    private Integer numberOfBooking;

    /**
     * 1-已经预定不可取消；2-提前取消
     */
    private Integer cancelType;

    private Integer cancelDays;

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
