package com.fangcang.order.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrderNoteDTO implements Serializable {

    private static final long serialVersionUID = 5432139309682086750L;

    /**
     * 主键ID
     */
    private Integer orderNoteId;

    /**
     * 订单ID
     */
    @NotNull
    private Integer orderId;

    /**
     * 1: 分销商备注，2：供应商备注 ，3：内部备注
     */
    @NotNull
    private Integer noteType;

    /**
     * 备注
     */
    @NotBlank
    private String note;

    /**
     * 备注对象
     */
    @NotBlank
    private String noteObject;

    /**
     * 备注人
     */
    private String creator;

    /**
     * 备注时间
     */
    private String createTime;

}