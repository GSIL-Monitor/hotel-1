package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:56
 * @Description: 分销商添加拜访记录请求DTO
 */
@Data
public class AgentAddVisitRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 2841749403426472888L;

    @NotNull(message = "分销商ID不能为空")
    private Long agentId;

    private String agentCode;

    /**
     * 拜访人
     */
    private String visitor;

    /**
     * 拜访时间
     */
    private String visitTime;

    /**
     * 拜访内容
     */
    private String content;
}
