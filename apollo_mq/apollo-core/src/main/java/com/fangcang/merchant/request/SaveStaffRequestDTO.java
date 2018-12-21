package com.fangcang.merchant.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.merchant.dto.RoleDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class SaveStaffRequestDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -7115820333814794759L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录名
     */
    @NotEmpty(message = "userName不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotEmpty(message = "password不能为空")
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 角色列表
     */
    private List<RoleDTO> roleList;
    
    /**
     * 商家Id
     */
    private Long merchantId;
    
    /**
     * 电话号码
     */
    private String phone;

    /**
     * qq
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 座机
     */
    private String landlineTelephone;

}
