package com.fangcang.order.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/31
 */
@Data
public class EbkCallbackRequestDTO implements Serializable {
    private static final long serialVersionUID = 39123737435505369L;

    /**
     * 订单ID
     */
    @NotNull
    private Integer supplyRequestId;
    /**
     * 本次确认类型，1：已确认，2：已拒绝
     */
    @NotNull
    private Integer confirmType;
    /**
     * 确认号
     */
    private String confirmNo;
    /**
     * 确认人
     */
    @NotBlank
    private String confirmName;
    /**
     * 拒绝原因：变价，满房，其他自定义
     */
    private String refuseReason;
    /**
     * 备注
     */
    private String note;

}
