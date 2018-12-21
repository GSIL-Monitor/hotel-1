package com.fangcang.b2b.response;

import com.fangcang.merchant.dto.BankCardDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/30.
 */
@Data
public class GetOfflinePayResponseDTO implements Serializable {

    private static final long serialVersionUID = 6852396331189217569L;
    /**
     * 银行卡列表
     */
    private List<BankCardDTO> bankCardList;

    /**
     * 支付宝二维码
     */
    private String alipayUrl;

    /**
     * 微信支付二维码
     */
    private String wechatPayUrl;
}
