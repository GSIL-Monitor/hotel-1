package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LockRequestDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private List<String> supplyOrderCodeList;

    private String lockUser;

    private String lockName;
}
