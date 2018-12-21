package com.fangcang.finance.prepay.response;

import com.fangcang.finance.prepay.dto.PrepayRechargeItemAttchDTO;
import com.fangcang.finance.prepay.dto.PrepayRechargeItemDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanwang
 */
@Data
public class QueryRechargeListResponseDTO extends PrepayRechargeItemDTO implements Serializable {

    private static final long serialVersionUID = -1123569862764071192L;

    /**
     * 充值记录附件明细
     */
    private List<PrepayRechargeItemAttchDTO> attchDTOList;

}