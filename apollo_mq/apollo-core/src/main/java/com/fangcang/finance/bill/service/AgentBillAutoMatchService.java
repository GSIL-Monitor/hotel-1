package com.fangcang.finance.bill.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.bill.request.AddMatchedOrderDTO;
import com.fangcang.finance.bill.request.DeleteBillImportDTO;
import com.fangcang.finance.bill.request.QueryBillImportDTO;
import com.fangcang.finance.bill.response.BillImportDTO;

import java.io.InputStream;

public interface AgentBillAutoMatchService {

    /**
     * 查询导入订单记录
     * @param requestDTO
     * @return
     */
    public PaginationSupportDTO<BillImportDTO> queryAgentBillImport(QueryBillImportDTO requestDTO);

    /**
     * 读取上传的excel文件并自动匹配订单
     */
    public ResponseDTO autoMatchCustomerOrder(InputStream in,String operator);

    /**
     * 将已匹配订单添加到账单
     * @param requestDTO
     * @return
     */
    public ResponseDTO addMatchedOrderToBill(AddMatchedOrderDTO requestDTO);

    /**
     * 删除导入订单
     */
    public ResponseDTO deleteAgentBillImport(DeleteBillImportDTO request);
}
