package com.fangcang.agent.response;

import com.fangcang.agent.dto.FrequentAgentDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 20:10
 * @Description:
 */
@Data
public class FrequentAgentResponseDTO implements Serializable {

    private static final long serialVersionUID = 6600136578364644669L;

    /**
     * 常用分销商列表
     */
    List<FrequentAgentDTO> agentList;
}
