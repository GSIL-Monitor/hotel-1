package com.fangcang.agent.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/7 21:04
 * @Description: 删除分销商银行卡信息RequestDTO
 */
@Data
public class DeleteAgentBanCardRequestDTO implements Serializable {

    private static final long serialVersionUID = -386879014332401519L;

    @NotNull(message = "bankCardId不能为null")
    private Long bankCardId;
}
