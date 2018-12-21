package com.fangcang.agent.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class GetVisitListQueryDTO extends BaseQueryConditionDTO implements Serializable {
    private static final long serialVersionUID = 9087914863624463443L;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商id
     */
    private Long agentId;

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
