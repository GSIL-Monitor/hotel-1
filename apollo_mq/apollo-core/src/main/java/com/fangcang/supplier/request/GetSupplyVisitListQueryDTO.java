package com.fangcang.supplier.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
* @Description:    获取供应商访问记录RequestDTO
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/30 16:35
*/
@Data
public class GetSupplyVisitListQueryDTO extends BaseQueryConditionDTO implements Serializable {


    private static final long serialVersionUID = -215872351210667206L;
    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商id
     */
    private Long supplyId;

    /**
     * 业务经理ID
     */
    private Long merchantBM;

    /**
     * 业务经理名
     */
    private String merchantBMName;

    /**
     * 商家ID
     */
    private Long merchantId;
}
