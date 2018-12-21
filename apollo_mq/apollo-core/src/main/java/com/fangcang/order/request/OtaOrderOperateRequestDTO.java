package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class OtaOrderOperateRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -120475818355057802L;
    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 发单类型：1预订单，2修改单，3取消单
     */
    @NotNull
    private Integer sendType;

    /**
     * 操作类型：1接收，2拒绝，3更改确认号，4申请退订
     */
    @NotNull
    private Integer handleType;

    /**
     * 确认类型：1按入住姓名，2按确认号
     */
    private Integer confirmType;

    /**
     * 拒绝类型：1满房封首日，2满房封订单入住日，3房价不对，4其它原因
     */
    private Integer refuseType;

    /**
     * 拒绝原因
     */
    private String refuseReason;

    /**
     * 订单确认号
     */
    private String confirmNo;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

}
