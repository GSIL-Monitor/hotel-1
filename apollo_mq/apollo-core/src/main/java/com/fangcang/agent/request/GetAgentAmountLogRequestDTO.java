package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/8 19:11
 * @Description: 获取分销商额度调整日志RequestDTO
 */
@Data
public class GetAgentAmountLogRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 4891660488639228721L;

    @NotNull(message = "分销商id不能为空")
    private Long agentId;
}
