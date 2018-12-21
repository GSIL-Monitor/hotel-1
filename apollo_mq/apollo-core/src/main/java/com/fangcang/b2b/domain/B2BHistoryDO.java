package com.fangcang.b2b.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/3 10:11
 * @Description:
 */
@Data
public class B2BHistoryDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 5552982850168838558L;

    /**
     * 搜索历史id
     */
    private Long historyId;

    /**
     * 分销商用户id
     */
    private Long agentUserId;

    /**
     * 分销商用户名(手机号,前端传loginName)
     */
    private String username;

    /**
     * 关键字
     */
    private String keyword;
}
