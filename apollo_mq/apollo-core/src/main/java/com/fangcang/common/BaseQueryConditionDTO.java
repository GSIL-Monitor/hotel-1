package com.fangcang.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseQueryConditionDTO implements Serializable{
    private static final long serialVersionUID = -6147279044482949578L;
    /**
     * 当前页 默认1
     */
    private int currentPage = 1;

    /**
     * 页面记录数
     */
    private int pageSize = 15;
}
