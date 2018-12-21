package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanwang
 */
@Data
public class DeratePolicyResponseDTO extends DeratePolicyDTO implements Serializable {


    private static final long serialVersionUID = -5535428643791853129L;
    /**
     * 每日减免明细
     */
    private List<DeratePolicyPriceDTO> dayList;

}