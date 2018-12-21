package com.fangcang.supplier.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class SupplyContractFileDTO implements Serializable {

    private static final long serialVersionUID = -8374649153207476654L;
    
    private Long supplyContractFileId;

    /*
     * 供应商ID
     */
    private Long supplyId;
    
    /*
     * 描述
     */
    private String description;
    
    /*
     * 文件url
     */
    private String fileUrl;
    
    /*
     * 文件名称
     */
    private String fileName;

    /*
     * 文件路径
     */
    private String realPath;
    
    /*
     * 创建者
     */
    private String creator;
    
    /*
     * 创建时间
     */
    private Date createTime;
    
    /*
     * 修改者
     */
    private String modifier;
    
    /*
     * 修改时间
     */
    private Date modifyTime;

}
