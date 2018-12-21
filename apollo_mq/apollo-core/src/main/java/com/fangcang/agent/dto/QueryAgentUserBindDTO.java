package com.fangcang.agent.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/6 10:28
 * @Description:
 */
@Data
public class QueryAgentUserBindDTO implements Serializable {

    private static final long serialVersionUID = -8847709183088592115L;

    private String merchantCode;

    private String openId;
}
