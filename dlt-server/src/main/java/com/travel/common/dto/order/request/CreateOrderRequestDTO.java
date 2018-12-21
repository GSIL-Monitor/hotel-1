package com.travel.common.dto.order.request;

import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.validate.CreateManualOrderValidate;
import com.travel.common.validate.CreateOTAOrderValidate;
import com.travel.common.validate.CreateOrderValidate;
import com.travel.common.validate.ModifyOTAOrderValidate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 下单请求DTO
 * @author  2018/1/10
 */
@Data
public class CreateOrderRequestDTO implements Serializable {


    private static final long serialVersionUID = -5918102330924621341L;

    @NotNull(message = "客户编号不能为空", groups = {CreateOrderValidate.class})
    private String agentCode;

    @NotNull(message = "客户名称不能为空", groups = {CreateOrderValidate.class})
    private String agentName;
    
//    @NotNull(message = "供应商编码不能为空", groups = {CreateOTAOrderValidate.class})
    private String supplyCode;

//    @NotNull(message = "供应商名称不能为空", groups = {CreateOTAOrderValidate.class})
    private String supplyName;

    @NotNull(message = "订单号不能为空", groups = {ModifyOTAOrderValidate.class})
    private String orderCode;

    @NotNull(message = "客户单号不能为空", groups = {CreateOTAOrderValidate.class})
    private String customerOrderCode;

    @NotNull(message = "支付方式不能为空", groups = {CreateOrderValidate.class})
    private String payMethod;

    @NotNull(message = "售卖币种不能为空", groups = {CreateOrderValidate.class})
    private String saleCurrency;

    @NotNull(message = "订单总价不能为空", groups = {CreateOrderValidate.class})
    private BigDecimal salePrice;

    @NotNull(message = "渠道编码不能为空", groups = {CreateOrderValidate.class, })
    private String channelCode;

    private String childChannelCode;

    @NotNull(message = "订单状态不能为空", groups = {CreateOTAOrderValidate.class, })
    private String orderState;

    @NotNull(message = "渠道订单状态不能为空", groups = {CreateOTAOrderValidate.class})
    private String channelState;

    @NotNull(message = "客人姓名不能为空", groups = {CreateOrderValidate.class})
    private String guestName;

    private String guestFile;

    @NotNull(message = "订单创建人不能为空", groups = {CreateOrderValidate.class})
    private String creator;

    @NotNull(message = "入店日期不能为空", groups = {CreateOrderValidate.class})
    private Date checkinDate;

    @NotNull(message = "离店日期不能为空", groups = {CreateOrderValidate.class})
    private Date checkoutDate;

    @NotNull(message = "部门编码不能为空", groups = {CreateManualOrderValidate.class})
    private String deptNo;

    @NotNull(message = "部门名称不能为空", groups = {CreateManualOrderValidate.class})
    private String deptName;

    private String remark;

    private String confirmNo;

    private String roomNo;

    private List<OrderProductDTO> orderProductDTOList;

}
