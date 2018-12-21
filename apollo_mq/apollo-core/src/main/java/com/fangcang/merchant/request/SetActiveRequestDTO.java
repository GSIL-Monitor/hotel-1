package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class SetActiveRequestDTO implements Serializable {
    private static final long serialVersionUID = -7303655764483977219L;

    /**
     * 用户ID
     */
    @NotNull(message = "userId不能为Null")
    private Long userId;

    /**
     * 1-启动，0-停用
     */
    @NotNull(message = "isActive不能为Null")
    private Integer isActive;
}
