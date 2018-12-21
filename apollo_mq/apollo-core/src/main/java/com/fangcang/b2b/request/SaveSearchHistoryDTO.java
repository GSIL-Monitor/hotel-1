package com.fangcang.b2b.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class SaveSearchHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1573706739932678109L;

    /**
     * 登录名
     */
    @NotEmpty(message = "loginName不能为空")
    private String loginName;

    /**
     * 关键字
     */
    @NotEmpty(message = "keyWord不能为空")
    private String keyWord;

    /**
     * 当前用户的id(session里面取)
     */
    private Long agentUserId;
}
