package com.fangcang.supplier.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 17:36
 * @Description: 上传合同文件响应
 */
@Data
public class UploadContractRsponseDTO implements Serializable {

    private static final long serialVersionUID = -196073235280527141L;

    /**
     * 合同文件ID
     */
    private Long contractFileId;
}
