package com.fangcang.agent.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/11.
 */
@Data
public class AgentBankCardRequestDTO implements Serializable {

    @NotNull(message = "分销商编码不能为空")
    private String agentCode;

    private String merchantCode;
}
