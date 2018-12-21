package com.fangcang.finance.financeorder.mapper;

import com.fangcang.finance.financeorder.domain.SupplySingleBalanceDO;
import com.fangcang.finance.financeorder.request.QueryBillRequestDTO;
import com.fangcang.finance.financeorder.response.UnpayResponseDTO;

import java.util.List;

/**
 * Created by Vinney on 2018/7/19.
 */
public interface SupplyFinanceOrderSQLMapper {

    /**
     * 查询待收款工单列表
     * @param supplySingleBalanceDO
     * @return
     */
    List<SupplySingleBalanceDO> queryUnpaid(SupplySingleBalanceDO supplySingleBalanceDO);

    /**
     * 导出订单待收款
     * @param supplySingleBalanceDO
     * @return
     */
    List<UnpayResponseDTO> exportUnpayOrder(SupplySingleBalanceDO supplySingleBalanceDO);

    /**
     * 导出账单待收款
     * @param requestDTO
     * @return
     */
    List<UnpayResponseDTO> exportUnpayBill(QueryBillRequestDTO requestDTO);

    /**
     * 查询已收款工单列表
     * @param supplySingleBalanceDO
     * @return
     */
    List<SupplySingleBalanceDO> queryPaid(SupplySingleBalanceDO supplySingleBalanceDO);

    /**
     * 查询未完成工单列表
     * @param supplySingleBalanceDO
     * @return
     */
    List<SupplySingleBalanceDO> queryUnfinished(SupplySingleBalanceDO supplySingleBalanceDO);

}
