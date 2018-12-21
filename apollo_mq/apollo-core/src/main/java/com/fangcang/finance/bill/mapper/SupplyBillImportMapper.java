package com.fangcang.finance.bill.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.finance.bill.domain.SupplyBillImportDO;
import com.fangcang.finance.bill.request.AddMatchedOrderDTO;
import com.fangcang.finance.bill.request.QuerySupplyBillImportDTO;
import com.fangcang.finance.bill.response.BillAutoMatchResultDTO;
import com.fangcang.finance.bill.response.SupplyBillImportDTO;

import java.util.List;

public interface SupplyBillImportMapper extends MyMapper<SupplyBillImportDO> {

    /**
     * 获取导入批次号
     */
    public int queryImportNo();

    /**
     * 更新自动匹配结果
     */
    public int updateAutoMatchResult(int importNo);

    /**
     * 查询自动对账结果
     */
    public BillAutoMatchResultDTO queryAutoMatchResult(int importNo);

    /**
     * 自动对账结果查询
     */
    public List<SupplyBillImportDTO> querySupplyBillImport(QuerySupplyBillImportDTO requestDTO);

    /**
     * 添加已匹配的订单到账单中
     */
    public int addMatchedOrderToBill(AddMatchedOrderDTO requestDTO);

    /**
     * 删除导入订单
     */
    public int deleteSupplyBillImport(Integer importNo);
}