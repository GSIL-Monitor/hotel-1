package com.fangcang.message.remote.request.email;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryEmailConfigDTO implements Serializable {

    private String userName;

    private String merchantCode;
}
