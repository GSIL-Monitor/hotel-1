package com.fangcang.common;

import lombok.Data;

import java.util.List;

/**
 * Created by ASUS on 2018/6/20.
 */
@Data
public class IncrementRetryDTO {

    /**
     * url
     */
    private String url;
    /**
     * 增量
     */
    private List<IncrementDTO> incrementDTOList;
    /**
     * 重试时间
     */
    private Long time;

    /**
     * 重试次数
     */
    private Integer retryNum;

}
