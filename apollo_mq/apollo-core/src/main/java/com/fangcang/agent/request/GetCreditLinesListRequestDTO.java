package com.fangcang.agent.request;

import java.io.Serializable;

import com.fangcang.common.BaseQueryConditionDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/30.
 * 获取信用额度列表请求参数
 */
@Data
public class GetCreditLinesListRequestDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = -3915157452518806984L;

    /**
     * 分销商名称(对应表t_agent的agent_name)
     */
    private String agentName;
    
    /**
     * 分销商状态(对应表t_agent的is_active)
     */
    private Integer isActive;
    
    /**
     * 我方负责人(对应表t_user的username)
     */
    private String userName;
    
    private String realName;
    
    /**
     * 管理账号(对应表t_agent_user的username)
     */
    private String agentUserName;
    
    /**
     * 联系人（对应表t_agent_user的phone）
     */
    private String agentUserPhone;

    /**
     * 商家ID
     */
    private Long merchantId;
}
