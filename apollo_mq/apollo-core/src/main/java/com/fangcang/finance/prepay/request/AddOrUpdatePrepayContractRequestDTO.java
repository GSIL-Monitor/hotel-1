package com.fangcang.finance.prepay.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.finance.prepay.dto.PrepayContractDTO;
import com.fangcang.finance.prepay.dto.PrepayContractTargetDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/5/21
 */
@Data
public class AddOrUpdatePrepayContractRequestDTO extends PrepayContractDTO implements Serializable {


    private static final long serialVersionUID = -131301224046679525L;

    /**
     * 合约目标间夜明细
     */
    @NotEmpty
    private List<PrepayContractTargetDTO> contractTargetDTOList;


}
