package com.fangcang.agent.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:52
 * @Description: 分销商上传合同请求DTO
 */
@Data
public class AgentUploadContractRequestDTO implements Serializable {

    private static final long serialVersionUID = 6735308012773287814L;

    private Long agentId;

    /**
     * 合同描述
     */
    private String contractName;
    /**
     * 合同文件
     */
    private MultipartFile file;
}
