package com.fangcang.ebk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbkNotifyDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String content;

    private String supplyOrderCode;
}
