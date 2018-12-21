package com.fangcang.finance.bill.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.bill.request.CancelBillFiannceOrderDTO;
import com.fangcang.finance.bill.request.ConfirmBillFiannceOrderDTO;

public interface SupplyBillPayHandle {

    /**
     * 确认工单
     */
    public ResponseDTO confirmBillFiannceOrder(ConfirmBillFiannceOrderDTO request);

    /**
     * 取消工单
     */
    public ResponseDTO cancelBillFiannceOrder(CancelBillFiannceOrderDTO request);
}
