package com.fangcang.product.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.product.request.BatchModifyPriceRequestDTO;
import com.fangcang.product.request.ModifyPriceQuotaStateRequestDTO;
import com.fangcang.product.request.ProductDailyInfoQueryDTO;
import com.fangcang.product.response.ProductDailyInfoResponseDTO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/31.
 */
public interface PriceInfoService {

    /**
     * 批量调价
     * @param batchModifyPriceRequestDTO
     * @return
     */
    public ResponseDTO batchModifyPrice(BatchModifyPriceRequestDTO batchModifyPriceRequestDTO);



    /**
     *  批量查询产品价格配额房态信息
     * @param productDailyInfoQueryDTO
     * @return
     */
    public ResponseDTO<List<ProductDailyInfoResponseDTO>> queryProductDailyInfo(ProductDailyInfoQueryDTO productDailyInfoQueryDTO);

    /**
     *单日调整价格和房态配额
     * @param modifyPriceQuotaStateRequestDTO
     * @return
     */
    public ResponseDTO updatePriceAndQuotaState(ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO);


    /**
     *日历界面调整价格和房态配额
     * @param modifyPriceQuotaStateRequestDTO
     * @return
     */
    public ResponseDTO updatePriceAndQuotaStateByCalendar(ModifyPriceQuotaStateRequestDTO modifyPriceQuotaStateRequestDTO);

}
