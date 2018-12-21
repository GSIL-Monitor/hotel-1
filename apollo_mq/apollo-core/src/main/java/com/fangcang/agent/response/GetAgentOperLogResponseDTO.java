package com.fangcang.agent.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.agent.dto.AgentOperLogDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class GetAgentOperLogResponseDTO implements Serializable {
    private static final long serialVersionUID = 8700712597966473228L;

    /**
     * 日志列表
     */
    private List<AgentOperLogDTO> userAccountLogList;
}
