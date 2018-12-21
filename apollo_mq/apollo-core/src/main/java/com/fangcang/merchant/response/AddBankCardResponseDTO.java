package com.fangcang.merchant.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class AddBankCardResponseDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1871403115954740181L;
    /**
     * 银行卡ID
     */
    private Long bankCardId;
}
