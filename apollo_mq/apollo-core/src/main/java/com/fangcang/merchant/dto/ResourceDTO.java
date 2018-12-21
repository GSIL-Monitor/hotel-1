package com.fangcang.merchant.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class ResourceDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -4616846654667390390L;

    /**
     * 菜单ID
     */
    private Long resourceId;

    private String resourceCode;

    /**
     * 菜单名称
     */
    private String resourceName;

    private String resourceType;

    private String urlPattern;

    private String description;

    /**
     *
     */
    private Long pid;

    private Integer level;

    private Integer isActive;

    //父节点编码
    private String pCode;
    //父节点名称
    private String pName;
    //父节点等级
    private Integer pLevel;

    //父节点类型
    private String pType;

    //父节点URL
    private String pUrl;

}
