package com.travel.common.dto.order;

import com.travel.common.validate.CreateManualOrderValidate;
import com.travel.common.validate.CreateOTAOrderValidate;
import com.travel.common.validate.CreateOrderValidate;
import com.travel.common.validate.ModifyManualOrderValidate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单产品DTO
 * @author  2018/1/10
 */
@Data
public class OrderProductDTO implements Serializable {

    private static final long serialVersionUID = -6854828942482509268L;

    private Long opid;

    @NotNull(message = "酒店ID不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Long hotelId;

    private String hotelName;

//    @NotNull(message = "房型ID不能为空", groups = {CreateOTAOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Long roomTypeId;

    private String roomTypeName;

    @NotNull(message = "价格计划ID不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Long priceplanId;

    private String priceplanName;

    @NotNull(message = "入店日期不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Date checkinDate;

    @NotNull(message = "离店日期不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Date checkoutDate;

    private Integer isactive;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private String orderCode;

    @NotNull(message = "产品类型（房费、杂费）不能为空", groups = {CreateOrderValidate.class, CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String productType;

    private List<OrderDayPriceDTO> orderDayPriceDTOList;

}
