package com.fangcang.common;

import java.io.Serializable;

/**
 * Created by Vinney on 2018/5/17.
 */
public class UploadRequestDTO<T> implements Serializable {

    /**
     * 业务类型，可以参考枚举UploadTypeEnum
     */
    private String uploadType;

    /**
     * 各业务上传附带的业务属性
     */
    private T businessDTO;

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public T getBusinessDTO() {
        return businessDTO;
    }

    public void setBusinessDTO(T businessDTO) {
        this.businessDTO = businessDTO;
    }
}
