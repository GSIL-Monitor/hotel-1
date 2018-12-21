package com.fangcang.merchant.response;

import com.fangcang.merchant.dto.RoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class AllRoleResponseDTO implements Serializable {
    private static final long serialVersionUID = 8700712597966473228L;

    /**
     * 角色列表
     */
    private List<RoleDTO> roleList;
}
