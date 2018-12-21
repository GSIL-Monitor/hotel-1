package com.fangcang.supplier.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 17:20
 * @Description: 单个用户请求字段, 查询和保存用
 */
@Data
public class SingleUserRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3321953716747028701L;

    /**
     * 供应商ID
     */
    @NotNull(message = "供应商ID不能为null")
    private Long supplyId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 手机号
     */
    private String phone;
}
