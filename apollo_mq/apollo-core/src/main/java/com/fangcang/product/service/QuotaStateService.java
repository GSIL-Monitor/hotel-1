package com.fangcang.product.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.product.request.*;
import com.fangcang.product.response.DebuctOrRetreatQuotaResponseDTO;
import com.fangcang.product.response.DebuctQuotaQueryResponseDTO;
import com.fangcang.product.response.QuotaStateResponseDTO;

/**
 * Created by ASUS on 2018/5/28.
 */
public interface QuotaStateService {

    /**
     * 批量调整房态 --支持批量调整、单个批量调整
     * @param requestDTO
     * @return
     */
    public ResponseDTO batchModifyQuotaState(BatchSaveQuotaStateRequestDTO requestDTO);

    /**
     * 查看配额池房态
     * @param quotaStateQueryDTO
     * @return
     */
    public ResponseDTO<QuotaStateResponseDTO> queryQuotaStateDailyInfo(QuotaStateQueryDTO quotaStateQueryDTO);

    /**
     * 单日调整配额房态
     * @param requestDTO
     * @return
     */
    public ResponseDTO saveOrUpdateQuotaStateDaily(QuotaStateDailyRequestDTO requestDTO);

    /**
     * 扣退配额
     * @param debuctOrRetreatQuotaRequestDTO
     * @return
     */
    public ResponseDTO<DebuctOrRetreatQuotaResponseDTO> debuctOrRetreatQuota(DebuctOrRetreatQuotaRequestDTO debuctOrRetreatQuotaRequestDTO);

    /**
     * 查询订单实扣配额
     * @param debuctQuotaQueryDTO
     * @return
     */
    public ResponseDTO<DebuctQuotaQueryResponseDTO> queryOrderDebuctQuotaInfo(DebuctQuotaQueryDTO debuctQuotaQueryDTO);
}
