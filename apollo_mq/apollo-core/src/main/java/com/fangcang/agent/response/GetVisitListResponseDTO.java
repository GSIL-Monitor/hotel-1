package com.fangcang.agent.response;

import com.fangcang.agent.dto.AgentVisitInfoDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class GetVisitListResponseDTO implements Serializable {
    private static final long serialVersionUID = 5382928139944571686L;

    /**
     * 分销商的拜访记录
     */
    private List<AgentVisitInfoDTO> visitList;
}
