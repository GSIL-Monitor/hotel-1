package com.fangcang.supplier.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/1 11:04
 * @Description: 常用供应商列表RequestDTO
 */
@Data
public class CommonSupplyListResquestDTO extends BaseQueryConditionDTO implements Serializable {

    private static final long serialVersionUID = -7049922718240612453L;


    /**
     * 供应商名
     */
    private String supplyName;


    /**
     * 商家ID
     */
    private Long merchantId;


    /**
     * 是否启用
     * 启用:1,未启用:0.点选显示的常用列表传1,手动输入的不传
     */
    private Integer isActive;


}
