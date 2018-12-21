package com.fangcang.merchant.response;

import com.fangcang.merchant.dto.ResourceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class MenuResponseDTO implements Serializable{
    private static final long serialVersionUID = 5168279320438963828L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String describe;
    /**
     *菜单列表
     */
    private List<ResourceDTO> resourceList;
}
