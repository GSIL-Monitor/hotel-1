package com.travel.common.dto.order.request;

import com.travel.common.validate.PreBookingValidate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author  2018/1/10
 */
@Data
public class PreBookingRequestDTO implements Serializable {


    private static final long serialVersionUID = 9112308402205570703L;
    private Long hotelId;

    private Long roomTypeId;

    @NotNull(message = "价格计划ID不能为空", groups = {PreBookingValidate.class})
    private Long pricePlanId;

    @NotNull(message = "入住日期不能为空", groups = {PreBookingValidate.class})
    private Date checkInDate;

    @NotNull(message = "离店日期不能为空", groups = {PreBookingValidate.class})
    private Date checkOutDate;

    @NotNull(message = "客户编码不能为空", groups = {PreBookingValidate.class})
    private String agentCode;

    @NotNull(message = "支付方式不能为空", groups = {PreBookingValidate.class})
    private Integer payMethod;

    @NotNull(message = "售卖币种不能为空", groups = {PreBookingValidate.class})
    private String saleCurrency;

    @NotNull(message = "渠道编码不能为空", groups = {PreBookingValidate.class})
    private String channelCode;

    private Integer rooms = 1;

}
