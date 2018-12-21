package com.fangcang.finance.financeorder.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/31.
 */
@Data
public class FinanceOrderLogResponseDTO implements Serializable {

    private Integer financeOrderId;

    private String operator;

    private String operateTime;

    private String content;
}
