package com.fangcang.agent.dto;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:59
 * @Description: 拜访记录信息
 */
@Data
public class AgentVisitInfoDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8149191683837995112L;

    private Long agentVisitId;

    private Long agentId;

    /**
     * 拜访人
     */
    private String visitor;

    /**
     * 拜访时间
     */
    private Date visitTime;

    /**
     * 拜访内容
     */
    private String content;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 分销商编码
     */
    private String agentCode;
}
