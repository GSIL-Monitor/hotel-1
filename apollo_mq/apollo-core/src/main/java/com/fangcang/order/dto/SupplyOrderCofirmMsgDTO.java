package com.fangcang.order.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Vinney on 2018/9/14.
 */
@Data
public class SupplyOrderCofirmMsgDTO implements Serializable {

    private String merchantCode;
    private String orderCode;

    @NotNull
    private Integer orderId;
    /**
     * 回复结果:
     * bookYes-同意预订,
     * bookNo-拒绝预订;
     * modifyYes-同意修改,
     * modifyNo-拒绝修改;
     * cancelYes-同意取消,
     * cancelNo-拒绝取消.
     */
    private String confirmResult;

    /**
     * 拒绝原因
     */
    private String refuseReason;
    private String supplyCode;
    private String supplyName;

    private Long createTime;
}
