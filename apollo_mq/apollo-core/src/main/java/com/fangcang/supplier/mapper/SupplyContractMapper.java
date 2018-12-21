package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyContractFileDO;

public interface SupplyContractMapper extends MyMapper<SupplyContractFileDO>{
    
    /**
     * 添加合同信息
     * @param supplyContractFileDO
     * @return
     */
    public Integer addSupplyContractFile(SupplyContractFileDO supplyContractFileDO);

    /**
     * 根据合同文件id查询合同文件名
     * @param contractFileId
     * @return
     */
    String getContractFileName(Long contractFileId);
}
