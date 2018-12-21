package com.fangcang.finance.remote;

import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.remote.request.CreditOrderPayDTO;

public interface CreditRemote {

    /**
     * 订单挂账
     */
    public ResponseDTO orderPay(CreditOrderPayDTO request);

    /**
     * 订单退挂账
     */
    public ResponseDTO orderRefund(CreditOrderPayDTO request);

    /**
     * 供货单挂账
     */
    public ResponseDTO supplierOrderPay(CreditOrderPayDTO request);

    /**
     * 供货单退挂账
     */
    public ResponseDTO supplierOrderRefund(CreditOrderPayDTO request);
}
