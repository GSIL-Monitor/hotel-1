package com.fangcang.merchant.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class RoleDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1740668148760176926L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否有效
     */
    private Integer isActive;

}
