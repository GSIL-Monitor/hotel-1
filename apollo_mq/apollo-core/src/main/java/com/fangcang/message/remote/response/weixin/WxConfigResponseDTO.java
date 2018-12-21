package com.fangcang.message.remote.response.weixin;

import java.io.Serializable;

public class WxConfigResponseDTO implements Serializable {
    private static final long serialVersionUID = -3798253418810526164L;

    private String appid;

    private String appsecret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
