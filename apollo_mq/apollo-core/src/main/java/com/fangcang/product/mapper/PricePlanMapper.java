package com.fangcang.product.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.hotelinfo.domain.HotelDO;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.request.DynamicPricePlanQueryDTO;
import com.fangcang.product.request.ProductHotelListQueryDTO;
import com.fangcang.product.response.HotelListResponseDTO;
import com.fangcang.report.dto.PricePlanQuotaDTO;
import com.fangcang.supplier.domain.SupplyDO;

import java.util.List;

/**
 * Created by ASUS on 2018/5/22.
 */
public interface PricePlanMapper extends MyMapper<PricePlanDO>{
    /**
     * 查询酒店列表
     * @param queryDTO
     * @return
     */
    public List<HotelDO> queryHotelList(ProductHotelListQueryDTO queryDTO);

    /**
     * 新增价格计划
     * @param pricePlanDO
     * @return
     */
    public Integer insertPricePlan(PricePlanDO pricePlanDO);

    /**
     * 修改价格计划
     * @param pricePlanDO
     * @return
     */
    public Integer updatePricePlan(PricePlanDO pricePlanDO);

    /**
     * 新增产品到共享池
     * @param pricePlanList
     */
    public void addProductToSharedPool(List<PricePlanDO> pricePlanList);

    /**
     * 取消共享
     * @param pricePlanDO
     * @return
     */
    public Integer deleteProductFromSharedPool(PricePlanDO pricePlanDO);

    /**
     * 选择要共享的产品
     * @param pricePlanDO
     * @return
     */
    public List<PricePlanDO> queryNeedSharedProduct(PricePlanDO pricePlanDO);

    /**
     * 共享房态
     * @param pricePlanDO
     * @return
     */
    public List<PricePlanDO> queryCurrentSharedPool(PricePlanDO pricePlanDO);

    /**
     * 动态查询价格计划
     * @param dynamicPricePlanQueryDTO
     * @return
     */
    public List<PricePlanDO> dynamicQueryPricePlanList(DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO);

    /**
     * 批量修改价格计划
     * @param pricePlanDOList
     */
    public void batchUpdatePricePlan(List<PricePlanDO> pricePlanDOList);

    /**
     * 查询价格计划信息--配额报表使用
     * @param dynamicPricePlanQueryDTO
     * @return
     */
    public List<PricePlanQuotaDTO> queryPricePlanOfQuotaReport(DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO);

    /**
     * 查询酒店下有产品的供应商
     * @param pricePlanDO
     * @return
     */
    public List<SupplyDO> queryHasProductSupplyList(PricePlanDO pricePlanDO);
}
