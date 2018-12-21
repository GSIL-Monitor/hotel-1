package com.fangcang.order.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrderRequestDTO implements Serializable {
    private static final long serialVersionUID = 4059987336341082926L;

    private Integer id;

    /**
     * 订单id
     */
    @NotNull
    private Integer orderId;

    /**
     * 处理结果：0:未处理，1同意处理，2拒绝处理
     */
    private Integer handleResult;

    /**
     * 备注
     */
    private String note;

    /**
     * 入住日期
     */
    private Date checkinDate;

    /**
     * 离店日期
     */
    private Date checkoutDate;

    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 客人名称，多个以逗号分割
     */
    private String guestNames;

    /**
     * 价格计划id
     */
    private Integer rateplanId;

    /**
     * 客户单号
     */
    private String customerOrderCode;

    /**
     * 订单申请类型，1取消单申请，2修改单申请
     */
    @NotNull
    private Byte requestType;

    /**
     * OTA订单处理结果，0：未处理，1：已处理
     */
    private Integer otaHandleResult;

    /**
     * 创建人
     */
    @NotBlank
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

}