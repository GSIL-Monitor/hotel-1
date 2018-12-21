package com.fangcang.agent.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/1 20:46
 * @Description: 常用分销商请求
 */
@Data
public class CommonAgentListRequestDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = 2360326621442280083L;

    /**
     * 分销商名
     */
    private String agentName;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 是否启用
     * 启用:1,未启用:0.点选显示的常用列表传1,手动输入的不传
     */
    private Integer isActive;
}
