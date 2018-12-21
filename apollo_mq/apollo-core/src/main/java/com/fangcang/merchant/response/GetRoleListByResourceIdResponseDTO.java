package com.fangcang.merchant.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.merchant.dto.RoleDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class GetRoleListByResourceIdResponseDTO implements Serializable {
    private static final long serialVersionUID = 8700712597966473228L;

    /**
     * 角色列表
     */
    private List<RoleDTO> roleList;
}
