package com.fangcang.merchant.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleResourceDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 资源URL
     */
    private String urlPattern;

}
