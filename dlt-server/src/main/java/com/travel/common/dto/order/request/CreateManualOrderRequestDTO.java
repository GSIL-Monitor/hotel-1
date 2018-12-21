package com.travel.common.dto.order.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.travel.common.dto.order.OrderProductDTO;
import com.travel.common.validate.CreateManualOrderValidate;
import com.travel.common.validate.ModifyManualOrderValidate;

import lombok.Data;

/**
 * 下手工单请求DTO
 * @author  2018/1/10
 */
@Data
public class CreateManualOrderRequestDTO implements Serializable {


    private static final long serialVersionUID = -5918102330924621341L;

    @NotNull(message = "订单ID不能为空", groups = {ModifyManualOrderValidate.class})
    private Long orderId;

    @NotNull(message = "订单编号不能为空", groups = {ModifyManualOrderValidate.class})
    private String orderCode;

    @NotNull(message = "客户编号不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String agentCode;

    @NotNull(message = "客户名称不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String agentName;
    
    @NotNull(message = "供应商编码不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String supplyCode;

    @NotNull(message = "供应商名称不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String supplyName;

    @NotNull(message = "客户单号不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String customerOrderCode;

    @NotNull(message = "支付方式不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String payMethod;

    @NotNull(message = "售卖币种不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String saleCurrency;

    @NotNull(message = "订单总价不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private BigDecimal salePrice;
    
    @NotNull(message = "订单底价不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private BigDecimal basePrice;

    @NotNull(message = "渠道编码不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String channelCode;

    private String channelState;

    @NotNull(message = "客人姓名不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private String guestName;

    private String guestFile;

    @NotNull(message = "订单创建人不能为空", groups = {CreateManualOrderValidate.class})
    private String creator;

    @NotNull(message = "入店日期不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
    private Date checkinDate;

    @NotNull(message = "离店日期不能为空", groups = {CreateManualOrderValidate.class, ModifyManualOrderValidate.class})
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
