package com.fangcang.agent.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class UserAccountLogQueryDTO implements Serializable {
    private static final long serialVersionUID = -966238726062002607L;

    /**
     * 分销商ID
     */
    @NotNull(message = "agentId不能为Null")
    private Long agentId;

    /**
     * 用户ID
     */
    @NotNull(message = "userId不能为Null")
    private Long userId;
}
