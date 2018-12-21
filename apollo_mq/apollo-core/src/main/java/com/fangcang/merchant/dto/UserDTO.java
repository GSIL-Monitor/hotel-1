package com.fangcang.merchant.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class UserDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String password;

    private String realName;

    private Long department;

    private String description;

    private String tel;

    private String landlineTelephone;;

    private Integer isActive;

    private Long merchantId;

    private String merchantCode;

    private String merchantName;

    /**
     * 域名2
     */
    private String secondDomain;

    /**
     * 系统名称
     */
    private String systemName;

    private Map<String,List<ResourceDTO>> menuMap;

    //当前用户能够访问的资源的URL
    private List<String> resourceList;

    private List<String> roleCodedList;

}
