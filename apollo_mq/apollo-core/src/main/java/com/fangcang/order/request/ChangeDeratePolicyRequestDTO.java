package com.fangcang.order.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.order.dto.DeratePolicyPriceDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/22
 */
@Data
public class ChangeDeratePolicyRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -549603672307214732L;

    /**
     * 减免政策id
     */
    private Integer deratePolicyId;
    /**
     * 每日减免明细
     */
    private List<DeratePolicyPriceDTO> dayList;
}
