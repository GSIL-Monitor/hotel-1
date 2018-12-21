package com.fangcang.b2b.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class SearchHistoryQueryDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 3981441037765069524L;

    /**
     * 登录名
     */
    @NotNull(message = "loginName不能为Null")
    private String loginName;

    /**
     * 分销商用户id
     */
    private Long agentUserId;
}
