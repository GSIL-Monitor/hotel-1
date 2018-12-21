package com.fangcang.agent.response;

import com.fangcang.merchant.dto.BankCardDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class BankCardListResponseDTO implements Serializable {
    private static final long serialVersionUID = -44510170463672216L;

    /**
     * 银行卡列表
     */
    private List<BankCardDTO> bankCardList;
}
