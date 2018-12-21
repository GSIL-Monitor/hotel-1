package com.fangcang.finance.bill.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.bill.request.AddBillOrderDTO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.BillOrderIdDTO;
import com.fangcang.finance.bill.request.CreateBillDTO;
import com.fangcang.finance.bill.request.CreateBillFinanceOrderDTO;
import com.fangcang.finance.bill.request.DeleteBillOrderDTO;
import com.fangcang.finance.bill.request.QueryBillCurrencyDTO;
import com.fangcang.finance.bill.request.QueryBillDTO;
import com.fangcang.finance.bill.request.QueryBillOrderDTO;
import com.fangcang.finance.bill.request.UpdateBillNameDTO;
import com.fangcang.finance.bill.response.BillDTO;
import com.fangcang.finance.bill.response.BillOrderDTO;
import com.fangcang.finance.bill.response.BillOrderItemDTO;
import com.fangcang.finance.bill.response.BillOrderItemExportDTO;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;

import java.util.List;

public interface SupplyBillService {

    /**
     * 创建账单
     */
    public ResponseDTO createSupplyBill(CreateBillDTO requestDTO);

    /**
     * 更新账单名称
     */
    public ResponseDTO updateSupplyBillName(UpdateBillNameDTO requestDTO);

    /**
     * 查询分销商账单
     */
    public PaginationSupportDTO<BillDTO> querySupplyBill(QueryBillDTO requestDTO);

    /**
     * 查询分销商账单对应的订单
     */
    public PaginationSupportDTO<BillOrderDTO> querySupplyBillOrder(QueryBillOrderDTO requestDTO);

    /**
     * 查询分销商账单对应的订单明细
     */
    public List<BillOrderItemDTO> querySupplyBillOrderItem(BillOrderIdDTO requestDTO);

    /**
     * 导出分销商账单对应的订单明细
     */
    public List<BillOrderItemExportDTO> exportBillOrderItem(BillIdDTO requestDTO);

    /**
     * 添加账单明细
     */
    public ResponseDTO addSupplyBillOrder(AddBillOrderDTO requestDTO);

    /**
     * 删除账单明细
     */
    public ResponseDTO deleteSupplyBillOrder(DeleteBillOrderDTO requestDTO);

    /**
     * 清空账单明细
     */
    public ResponseDTO clearSupplyBillOrder(BillIdDTO requestDTO);

    /**
     * 确认账单
     */
    public ResponseDTO confirmSupplyBill(BillIdDTO requestDTO);

    /**
     * 更新账单
     */
    public ResponseDTO updateSupplyBill(BillIdDTO requestDTO);

    /**
     * 删除账单
     */
    public ResponseDTO deleteSupplyBill(BillIdDTO requestDTO);

    /**
     * 账单通知财务收款
     */
    public ResponseDTO createSupplyBillFinanceOrder(CreateBillFinanceOrderDTO requestDTO);

    /**
     * 账单财务直接收款
     */
    public ResponseDTO createSupplyBillFinanceOrderWithConfirm(CreateBillFinanceOrderDTO requestDTO);

    /**
     * 查询账单金额
     */
    public List<MultipleCurrencyAmountDTO> querySupplyBillCurrency(QueryBillCurrencyDTO requestDTO);
}
