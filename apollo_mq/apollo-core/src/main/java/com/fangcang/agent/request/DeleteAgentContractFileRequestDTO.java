package com.fangcang.agent.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fangcang.common.BaseDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class DeleteAgentContractFileRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3915157452518806984L;

    /**
     * 文件Id
     */
    @NotNull(message = "contractFileId不能为空")
    private Long contractFileId;
    
    /**
     * 真实路径
     */
    @NotNull(message = "realPath不能为空")
    private String realPath;
    
}
