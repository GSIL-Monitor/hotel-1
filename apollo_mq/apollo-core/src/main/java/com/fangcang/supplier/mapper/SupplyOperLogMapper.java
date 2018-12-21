package com.fangcang.supplier.mapper;

import java.util.List;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyOperLogDO;
import com.fangcang.supplier.request.GetSupplyOperLogRequestDTO;

public interface SupplyOperLogMapper extends MyMapper<SupplyOperLogDO>{
    
    /**
     * 添加日志信息
     * @param supplyOperLogDO
     * @return
     */
    public Integer addSupplyOperLog(SupplyOperLogDO supplyOperLogDO); 
    
    /**
	 * 获取日志列表
	 * @return List<SupplyOperLogDO>
	 */
    public List<SupplyOperLogDO> querySupplyOperLogList(GetSupplyOperLogRequestDTO getSupplyOperLogRequestDTO);
}
