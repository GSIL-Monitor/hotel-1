package com.fangcang.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayItemAttchDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private Integer payItemId;

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
    private String realpath;
}
