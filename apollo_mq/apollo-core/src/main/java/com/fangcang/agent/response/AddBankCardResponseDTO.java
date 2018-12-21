package com.fangcang.agent.response;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class AddBankCardResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -608401017715049244L;

    /**
     * 银行卡ID
     */
    private Long bankCardId;
}
