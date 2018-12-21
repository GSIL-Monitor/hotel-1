package com.fangcang.merchant.response;

import java.io.Serializable;
import java.util.List;

import com.fangcang.merchant.dto.BankCardDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/23.
 */
@Data
public class UserBankCardResponseDTO implements Serializable {
    private static final long serialVersionUID = 8700712597966473228L;

    /**
     * 银行卡列表
     */
    private List<BankCardDTO> bankCardList;
}
