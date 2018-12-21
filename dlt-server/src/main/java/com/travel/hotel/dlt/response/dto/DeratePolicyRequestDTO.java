package com.travel.hotel.dlt.response.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanwang
 */
@Data
public class DeratePolicyRequestDTO extends DeratePolicyDTO implements Serializable {

    private static final long serialVersionUID = -8211085961390913038L;

    /**
     * 每日减免明细
     */
    private List<DeratePolicyPriceDTO> dayList;

}