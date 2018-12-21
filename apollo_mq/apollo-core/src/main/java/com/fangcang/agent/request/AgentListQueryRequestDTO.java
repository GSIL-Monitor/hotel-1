package com.fangcang.agent.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 01:13
 * @Description: 查询分销商列表请求DTO
 */
@Data
public class AgentListQueryRequestDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = -907521775472065584L;

    /**
     * agentCode为空时，使用名称模糊匹配
     */
    private String agentName;

    /**
     * agentCode不为空时,不使用agentName查询
     */
    private String agentCode;

    /**
     * 0-停用；1-启用；空表示全部
     */
    private Integer isActive;

    /**
     * 业务经理ID
     */
    private Long merchantBM;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 业务经理名
     */
    private String merchantBMName;

    /**
     * 商家ID
     */
    private Long merchantId;
}
