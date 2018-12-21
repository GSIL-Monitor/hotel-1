package com.fangcang.merchant.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class ChangePasswordRequestDTO implements Serializable {
    private static final long serialVersionUID = 1442880122465503866L;

    /**
     * 用户ID
     */
    @NotNull(message = "userId不能为Null")
    private Long userId;

    /**
     * 新密码
     */
    @NotEmpty(message = "password不能为空")
    private String password;

    /**
     * 确认新密码
     */
    @NotEmpty(message = "confirmPassword不能为空")
    private String confirmPassword;

    /**
     * 原密码
     */
    @NotEmpty(message = "oldPassword不能为空")
    private String oldPassword;
}
