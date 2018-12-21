package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class OrderConfirmRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8885069287499984175L;

    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;
    /**
     * 确认类型：1在线
     */
    @NotNull
    private Integer confirmType;
    /**
     * 确认内容
     */
    private String confirmContent;

    /**
     * 订单确认号
     */
    private String confirmNo;

}
