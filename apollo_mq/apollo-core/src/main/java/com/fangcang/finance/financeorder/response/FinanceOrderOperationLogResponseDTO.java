package com.fangcang.finance.financeorder.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/17.
 */
@Data
public class FinanceOrderOperationLogResponseDTO implements Serializable {
    private String operateTime;
    private String operator;
    private String content;
}
