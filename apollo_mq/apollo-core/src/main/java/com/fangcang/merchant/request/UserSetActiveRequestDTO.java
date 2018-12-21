package com.fangcang.merchant.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fangcang.common.BaseDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class UserSetActiveRequestDTO  extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -9179160103816200551L;

    @NotNull(message = "userId不能为Null")
    private Long userId;
    
    @NotNull(message = "isActive不能为Null")
    private String isActive;
}
