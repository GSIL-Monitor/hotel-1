package com.fangcang.supplier.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:23
 * @Description:
 */
@Data
public class SupplyListQueryRequestDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = -4618116426319321438L;

    /**
     supplyCode为空时，使用名称模糊匹配
     */
    private String supplyName;

    /**
     supplyCode不为空时,不使用supplyName查询
     */
    private String supplyCode;

    /**
     * 0-停用；1-启用；空表示全部
     */
    private Integer isActive;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 业务经理ID
     */
    private Long merchantBM;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 业务经理名
     */
    private String merchantBMName;

    /**
     * 合作预警
     */
    private Integer cooperationStatus;
}
