package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 17:44
 * @Description: 拜访记录响应
 */
@Data
public class AddVisitResponseDTO implements Serializable {

    private static final long serialVersionUID = -4604943494643743515L;

    /**
     * 拜访记录的ID
     */
    private Long supplyVisitId;
}
