package com.fangcang.ebk.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class EbkUserLoginDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    private String userLoginName;

    private String userPwd;
}
