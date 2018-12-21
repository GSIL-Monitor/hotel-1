package com.fangcang.supplier.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fangcang.common.BaseDTO;

import lombok.Data;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class AddSupplyContractFileRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3915157452518806984L;

    /**
     * 供应商Id
     */
    @NotNull(message = "supplyId不能为空")
    private Long supplyId;
    
    /**
     * 文件名称
     */
    @NotNull(message = "contractName不能为空")
    private String contractName;
    
    /**
     * 文件
     */
    @NotNull(message = "file不能为空")
    private MultipartFile file;
    
}
