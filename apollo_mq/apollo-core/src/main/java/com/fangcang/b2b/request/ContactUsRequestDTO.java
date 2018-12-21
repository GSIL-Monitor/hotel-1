package com.fangcang.b2b.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class ContactUsRequestDTO implements Serializable {

    private static final long serialVersionUID = -3537178136220158157L;
    /**
     * 分销商编码
     */
    private String agentCode;
}
