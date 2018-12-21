package com.fangcang.merchant.request;

import java.io.Serializable;
import java.util.List;

import com.fangcang.common.BaseQueryConditionDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class StaffListQueryDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = -1134203804452703912L;

    /**
     * 登录账号
     */
    private String userName;

    /**
     * 0:停用状态；1：启用状态；如果为空默认查全部。
     */
    private Integer isActive;
    
    /**
     * 用户Id
     */
    private Long userId;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 用户IDs
     */
    private List<Long> userIds;
}
