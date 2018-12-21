package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:12
 * @Description: 响应添加供应商请求
 */
@Data
public class AddSupplyResponseDTO implements Serializable {


    private static final long serialVersionUID = 2607934703118869190L;

    /**
     * 新增时返回供应商ID，编辑时没有
     */
    private Long supplyId;

    private String supplyCode;

    private String supplyName;
}
