package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyVisitDO;
import com.fangcang.supplier.request.GetSupplyVisitListQueryDTO;

import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/31 20:41
 * @Description:
 */
public interface SupplyVisitMapper extends MyMapper<SupplyVisitDO> {

    /**
     * 添加拜访记录
     * @param supplyVisitDO
     * @return
     */
    Integer addVisit(SupplyVisitDO supplyVisitDO);

    /**
     * 查询供应商拜访记录列表
     * @param getSupplyVisitListQueryDTO
     * @return
     */
    List<SupplyVisitDO> getVisitList(GetSupplyVisitListQueryDTO getSupplyVisitListQueryDTO);

}
