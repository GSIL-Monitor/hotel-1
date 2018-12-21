package com.fangcang.message.remote.request.email;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddEmailConfigDTO implements Serializable{

    private String emailServerHost;

    /**
     * 发送邮件的服务器的端口
     */
    private String emailServerPort;

    /**
     * 邮件发件者的地址
     */
    private String from;

    private String userName;

    private String password;
}
