package com.fangcang.supplier.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name="t_supply_contract_file")
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplyContractFileDO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supply_contract_file_id")
    private Long supplyContractFileId;

    /*
     * 供应商ID
     */
    @Column(name = "supply_id")
    private Long supplyId;
    
    /*
     * 描述
     */
    @Column(name = "description")
    private String description;
    
    /*
     * 文件url
     */
    @Column(name = "file_url")
    private String fileUrl;
    
    /*
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /*
     * 文件路径
     */
    @Column(name = "real_path")
    private String realPath;
    
    /*
     * 创建者
     */
    @Column(name = "creator")
    private String creator;
    
    /*
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    
    /*
     * 修改者
     */
    @Column(name = "modifier")
    private String modifier;
    
    /*
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;
}
