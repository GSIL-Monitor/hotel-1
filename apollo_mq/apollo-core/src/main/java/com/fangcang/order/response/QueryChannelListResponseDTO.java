package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class QueryChannelListResponseDTO implements Serializable {
    private static final long serialVersionUID = -7867878705841679175L;

    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;

}
