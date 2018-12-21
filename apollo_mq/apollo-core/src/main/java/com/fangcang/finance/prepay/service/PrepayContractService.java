package com.fangcang.finance.prepay.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.finance.prepay.dto.PrepayContractItemDTO;
import com.fangcang.finance.prepay.dto.PrepayContractLogDTO;
import com.fangcang.finance.prepay.request.AddOrUpdatePrepayContractRequestDTO;
import com.fangcang.finance.prepay.request.DebuctOrRetreatPrepayRequestDTO;
import com.fangcang.finance.prepay.request.PrepayLogInfoRequestDTO;
import com.fangcang.finance.prepay.request.PrepayRechargeRequestDTO;
import com.fangcang.finance.prepay.request.QueryPrepayItemListRequestDTO;
import com.fangcang.finance.prepay.request.QueryPrepayListRequestDTO;
import com.fangcang.finance.prepay.request.QueryRechargeListRequestDTO;
import com.fangcang.finance.prepay.request.UpdateDepositAmountRequestDTO;
import com.fangcang.finance.prepay.response.QueryPrepayContractListResponseDTO;
import com.fangcang.finance.prepay.response.QueryRechargeListResponseDTO;

import java.util.List;

/**
 * 预付款合约服务
 *
 * @author : zhanwang
 * @date : 2018/7/6
 */
public interface PrepayContractService {
    /**
     * 预付款合约查询
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<QueryPrepayContractListResponseDTO> queryPrepayContractList(QueryPrepayListRequestDTO requestDTO);

    /**
     * 新增或修改预付款合约
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addOrUpdatePrepayContract(AddOrUpdatePrepayContractRequestDTO requestDTO);

    /**
     * 预付款充值
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO recharge(PrepayRechargeRequestDTO requestDTO);

    /**
     * 预付款充值明细
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<QueryRechargeListResponseDTO> queryRechargeList(QueryRechargeListRequestDTO requestDTO);

    /**
     * 预付款交易明细
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<PrepayContractItemDTO> queryPrepayItemList(QueryPrepayItemListRequestDTO requestDTO);

    /**
     * 更新商家在供应商的押金
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO updateDepositAmount(UpdateDepositAmountRequestDTO requestDTO);

    /**
     * 统计供应商成交间夜数
     */
    void statisSupplyDoneRoomNight();

    /**
     * 扣还预付款
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO debuctOrRetreatPrepay(DebuctOrRetreatPrepayRequestDTO requestDTO);

    /**
     * 查询预付款日志
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<PrepayContractLogDTO>> prepayLogInfo(PrepayLogInfoRequestDTO requestDTO);
}
