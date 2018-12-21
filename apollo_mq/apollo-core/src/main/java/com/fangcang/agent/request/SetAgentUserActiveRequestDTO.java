package com.fangcang.agent.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/4 15:34
 * @Description: 分销商设置用户是否启用
 */
@Data
public class SetAgentUserActiveRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -6804073552718157518L;

    /**
     * 用户Id 对应数据库agent_user_id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 当前启用状态
     * 1-启用；0-停用
     */
    @NotNull(message = "启用状态不能空")
    private Integer isActive;
}
