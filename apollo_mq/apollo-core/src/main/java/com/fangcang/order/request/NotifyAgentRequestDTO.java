package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class NotifyAgentRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 4732235213455541519L;

    /**
     * 订单id
     */
    @NotNull
    private Integer orderId;
    /**
     * 通知方式，1：在线
     */
    @NotNull
    private Integer notifyMethod;
    /**
     * 通知类型，1：确认，2：付款，3：退款
     */
    @NotNull
    private Integer notifyType;
    /**
     * 通知备注
     */
    @NotBlank
    private String note;
}
