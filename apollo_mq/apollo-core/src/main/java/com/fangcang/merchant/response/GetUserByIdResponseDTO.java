package com.fangcang.merchant.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.merchant.dto.RoleDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class GetUserByIdResponseDTO implements Serializable{
    private static final long serialVersionUID = 5168279320438963828L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     *角色列表
     */
    private List<RoleDTO> roleList;

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
