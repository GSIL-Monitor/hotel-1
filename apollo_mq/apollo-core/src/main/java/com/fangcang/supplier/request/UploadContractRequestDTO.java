package com.fangcang.supplier.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 17:31
 * @Description: 上传合同文件请求
 */
@Data
public class UploadContractRequestDTO implements Serializable {

    private static final long serialVersionUID = -2993300146701784430L;

    private Long supplyId;

    /**
     * 合同描述
     */
    private String contractName;
    /**
     * 合同文件
     */
    private MultipartFile file;
}
