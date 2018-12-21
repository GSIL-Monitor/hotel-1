package com.fangcang.agent.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:52
 * @Description: 分销商合同文件响应DTO
 */
@Data
public class AgentUploadContractResponseDTO implements Serializable {

    private static final long serialVersionUID = -2425893167377161931L;

    /**
     * 合同文件ID
     */
    private Long contractFileId;
}
