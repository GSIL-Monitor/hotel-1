package com.fangcang.order.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.order.dto.SupplyOrderDTO;
import com.fangcang.order.dto.SupplyProductDTO;
import com.fangcang.order.request.AddAdditionChargeRequestDTO;
import com.fangcang.order.request.AddDeratePolicyRequestDTO;
import com.fangcang.order.request.AddProductRequestDTO;
import com.fangcang.order.request.ChangeAdditionChargeRequestDTO;
import com.fangcang.order.request.ChangeDeratePolicyRequestDTO;
import com.fangcang.order.request.ChangeProductRequestDTO;
import com.fangcang.order.request.DeleteAdditionChargeRequestDTO;
import com.fangcang.order.request.DeleteDeratePolicyRequestDTO;
import com.fangcang.order.request.DeleteProductRequestDTO;
import com.fangcang.order.request.NotifySupplierRequestDTO;
import com.fangcang.order.request.OrderDetailRequestDTO;
import com.fangcang.order.request.ProductDetailRequestDTO;
import com.fangcang.order.request.QueryProductListRequestDTO;
import com.fangcang.order.request.QueryProductPriceRequestDTO;
import com.fangcang.order.request.SaveSupplyResultRequestDTO;
import com.fangcang.order.request.SendToSupplierRequestDTO;
import com.fangcang.order.request.SupplyDetailRequestDTO;
import com.fangcang.order.request.UpdateSupplyOrderExceptionAmountDTO;
import com.fangcang.order.response.PriceDetailResponseDTO;
import com.fangcang.order.response.QueryProductListResponseDTO;
import com.fangcang.order.response.QuerySupplyReqResponseDTO;
import com.fangcang.order.response.SupplyDetailResponseDTO;
import com.fangcang.order.response.SupplyProductPriceResponseDTO;

import java.util.List;

/**
 * 供货单服务
 *
 * @author : zhanwang
 * @date : 2018/5/23
 */
public interface SupplyOrderService {

    /**
     * 设置订单异常金额
     * @return
     */
    ResponseDTO updateSupplyOrderExceptionAmount(UpdateSupplyOrderExceptionAmountDTO request);

    /**
     * 新增产品（团房）
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addProduct(AddProductRequestDTO requestDTO);

    /**
     * 修改产品
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeProduct(ChangeProductRequestDTO requestDTO);

    /**
     * 删除产品（团房）
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO deleteProduct(DeleteProductRequestDTO requestDTO);

    /**
     * 产品价格明细
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<PriceDetailResponseDTO> priceDetail(ProductDetailRequestDTO requestDTO);

    /**
     * 新增附加项
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addAdditionCharge(AddAdditionChargeRequestDTO requestDTO);

    /**
     * 修改附加项
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeAdditionCharge(ChangeAdditionChargeRequestDTO requestDTO);

    /**
     * 删除附加项
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO deleteAdditionCharge(DeleteAdditionChargeRequestDTO requestDTO);

    /**
     * 发单给供应商
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO sendToSupplier(SendToSupplierRequestDTO requestDTO);

    /**
     * 录供货单结果
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO saveSupplyResult(SaveSupplyResultRequestDTO requestDTO);

    /**
     * 查看发单进度
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<QuerySupplyReqResponseDTO> querySupplyRequest(SupplyDetailRequestDTO requestDTO);

    /**
     * 催供应商
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO notifySupplier(NotifySupplierRequestDTO requestDTO);

    /**
     * 分页查询产品列表接口
     *
     * @param requestDTO
     * @return
     */
    PaginationSupportDTO<QueryProductListResponseDTO> queryProductList(QueryProductListRequestDTO requestDTO);

    /**
     * 产品详情接口
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<SupplyProductDTO> productDetail(ProductDetailRequestDTO requestDTO);

    /**
     * 查产品最新价格接口
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<SupplyProductPriceResponseDTO>> queryProductPrice(QueryProductPriceRequestDTO requestDTO);

    /**
     * 供货单详情接口
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<SupplyDetailResponseDTO> supplyDetail(SupplyDetailRequestDTO requestDTO);

    /**
     * 查供货单列表
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO<List<SupplyOrderDTO>> querySupplyList(OrderDetailRequestDTO requestDTO);


    /**
     * 添加减免政策
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO addDeratePolicy(AddDeratePolicyRequestDTO requestDTO);

    /**
     * 修改减免政策
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO changeDeratePolicy(ChangeDeratePolicyRequestDTO requestDTO);

    /**
     * 删除减免政策
     *
     * @param requestDTO
     * @return
     */
    ResponseDTO deleteDeratePolicy(DeleteDeratePolicyRequestDTO requestDTO);
}
