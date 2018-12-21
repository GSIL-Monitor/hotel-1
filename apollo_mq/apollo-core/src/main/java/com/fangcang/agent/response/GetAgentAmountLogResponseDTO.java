package com.fangcang.agent.response;

import com.fangcang.agent.dto.AgentAmountLogDTO;
import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/8 19:14
 * @Description:
 */
@Data
public class GetAgentAmountLogResponseDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3401225585388018339L;

    /**
     * 额度修改日志列表
     */
    private List<AgentAmountLogDTO> amountMotifyLogList;
}
