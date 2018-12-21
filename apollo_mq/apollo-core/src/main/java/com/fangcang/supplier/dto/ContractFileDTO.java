package com.fangcang.supplier.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 16:55
 * @Description: 合同文件信息
 */
@Data
public class ContractFileDTO implements Serializable {

    private static final long serialVersionUID = -6464214360098093682L;

    /**
     * 合同文件id
     */
    private Long contractFileId;

    /**
     * 合同名称（或说明)
     */
    private String description;

    /**
     * 合同文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String realPath;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;
}
