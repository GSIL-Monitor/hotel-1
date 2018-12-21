package com.fangcang.finance.prepay.request;

import com.fangcang.finance.prepay.dto.PrepayRechargeItemAttchDTO;
import com.fangcang.finance.prepay.dto.PrepayRechargeItemDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class PrepayRechargeRequestDTO extends PrepayRechargeItemDTO implements Serializable {


    private static final long serialVersionUID = -7577728802923887180L;

    /**
     * 上传的附件列表
     */
    private List<PrepayRechargeItemAttchDTO> attchDTOList;

}
