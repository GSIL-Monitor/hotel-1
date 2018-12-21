package com.fangcang.merchant.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.common.BaseDTO;
import com.fangcang.merchant.dto.RoleDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class StaffListResponseDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -8598094737211529410L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户登录名
     */
    private String userName;

    /**
     * 角色列表
     */
    private List<RoleDTO> roleList;

    /**
     * 状态：1-启用；0-停用
     */
    private Integer isActive;

    /**
     * 是否是当前账号  1 是 0不是
     */
    private Integer isCurrentAccount;

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
