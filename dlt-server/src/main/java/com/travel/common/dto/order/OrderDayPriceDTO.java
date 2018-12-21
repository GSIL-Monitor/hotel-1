package com.travel.common.dto.order;

import com.travel.common.validate.CreateManualOrderValidate;
import com.travel.common.validate.CreateOTAOrderValidate;
import com.travel.common.validate.CreateOrderValidate;
import com.travel.common.validate.ModifyManualOrderValidate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单每日价格DTO
 * @author  2018/1/10
 */
@Data
public class OrderDayPriceDTO implements Serializable {

    private static final long serialVersionUID = -5850825541158635492L;

    private Long dayPriceId;

    private Long opId;

    @NotNull(message = "订单编号不能为空", groups = {ModifyManualOrderValidate.class})
    private String orderCode;

    @NotNull(message = "价格计划ID不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Long priceplanId;

    private String priceplanName;

    @NotNull(message = "售卖日期不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Date saleDate;

    @NotNull(message = "底价币种不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String baseCurrency;

    @NotNull(message = "底价不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private BigDecimal basePrice;

    @NotNull(message = "渠道币种不能为空", groups = {CreateOTAOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String saleBCurrency;

    @NotNull(message = "渠道售价不能为空", groups = {CreateOTAOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private BigDecimal saleBPrice;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private Integer orderBillStatus;

    private String orderBillUser;
    
    private Integer supplyBillStatus;
    
    private String supplyBillUser;

    private String saleCCurrency;

    private BigDecimal saleCPrice;

    private BigDecimal baseRate;

    private BigDecimal saleChannelRate;

    private BigDecimal saleCRate;

    private String voucherCode;

    @NotNull(message = "房间数不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Integer rooms;

    @NotNull(message = "早餐不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String breakfastNum;
    
    private BigDecimal agentCommission;
    
    private BigDecimal supplyCommission;

}
