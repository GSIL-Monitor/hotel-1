package com.fangcang.agent.response;

import java.io.Serializable;

import com.fangcang.common.BaseDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class AddAgentContractFileResponseDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1871403115954740181L;
    /**
     * 合同编号
     */
    private Long contractFileId;
    
    /**
     * 真实路径
     */
    private String realPath;
}
