package com.fangcang.supplier.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class SupplyOperLogDTO implements Serializable {

    private static final long serialVersionUID = -8374649153207476654L;
    
    private Long supplyOperLogId;

    /*
     * 供应商ID
     */
    private Long operSupplyId;
    
    /*
     * 供应商用户ID
     */
    private Long operSupplyUserId;
    
    /*
     * 日志描述
     */
    private String content;
    
    /*
     * 操作者
     */
    private String creator;
    
    /*
     * 操作时间
     */
    private Date createTime;
}
