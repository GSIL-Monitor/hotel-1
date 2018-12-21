package com.fangcang.finance.financeorder.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/7/11.
 */
@Data
public class FinancePayItemAttchRequestDTO implements Serializable{

    /**
     * 附件名称
     */
    private String name;

    /**
     * url地址
     */
    private String url;

    /**
     * 实际地址
     */
    private String realPath;

}
