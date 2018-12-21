package com.fangcang.product.service;

import com.fangcang.common.ResponseDTO;
import com.fangcang.product.request.*;
import com.fangcang.product.response.AllSharedPoolResponseDTO;
import com.fangcang.product.response.NeedSharedProductResponseDTO;
import com.fangcang.product.response.SharedPoolResponseDTO;

/**
 * Created by ASUS on 2018/5/24.
 */
public interface SharedPoolService {

    /**
     * 新建共享池
     * @param addSharedPoolRequestDTO
     * @return
     */
    public ResponseDTO createSharedPool(AddSharedPoolRequestDTO addSharedPoolRequestDTO);

    /**
     * 重命名共享池
     * @param sharedPoolRequestDTO
     * @return
     */
    public ResponseDTO renameSharedPool(SharedPoolRequestDTO sharedPoolRequestDTO);

    /**
     * 新增产品到共享池
     * @param addProductToSharedPoolRequestDTO
     * @return
     */
    public ResponseDTO addProductToSharedPool(AddProductToSharedPoolRequestDTO addProductToSharedPoolRequestDTO);

    /**
     * 从共享池移除产品
     * @param cancelSharingRequestDTO
     * @return
     */
    public ResponseDTO deleteProductFromSharedPool(CancelSharingRequestDTO cancelSharingRequestDTO);

    /**
     * 选择要共享的产品
     * @param sharedPoolRequestDTO
     * @return
     */
    public ResponseDTO<NeedSharedProductResponseDTO> queryNeedSharedProduct(SharedPoolRequestDTO sharedPoolRequestDTO);

    /**
     * 共享房态
     * @param quotaStateQueryDTO
     * @return
     */
    public ResponseDTO<SharedPoolResponseDTO> queryCurrentSharedPool(QuotaStateQueryDTO quotaStateQueryDTO);

    /**
     * 查询酒店下所有的共享池
     * @param sharedPoolQueryDTO
     * @return
     */
    public ResponseDTO<AllSharedPoolResponseDTO> queryAllSharedPool(SharedPoolQueryDTO sharedPoolQueryDTO);
}
