package com.fangcang.product.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.product.domain.PricePlanDO;
import com.fangcang.product.request.*;
import com.fangcang.product.response.*;

/**
 * Created by ASUS on 2018/5/22.
 */
public interface PricePlanService {

    /**
     * 分页查询酒店列表
     * @param queryDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<HotelListResponseDTO>> queryHotelList(ProductHotelListQueryDTO queryDTO);

    /**
     *新增或者修改价格计划
     * @param pricePlanRequestDTO
     * @return
     */
    public ResponseDTO saveOrUpdatePricePlan(PricePlanRequestDTO pricePlanRequestDTO);

    /**
     * 根据价格计划ID查询价格计划信息
     * @param pricePlanQueryDTO
     * @return
     */
    public ResponseDTO<PricePlanResponseDTO> queryPricePlanInfo(PricePlanQueryDTO pricePlanQueryDTO);

    /**
     * 删除价格计划
     * @param pricePlanDO
     * @return
     */
    public ResponseDTO deletePricePlan(PricePlanDO pricePlanDO);


    /**
     * 动态查询价格计划列表
     * @param dynamicPricePlanQueryDTO
     * @return
     */
    public ResponseDTO<PricePlanListResponseDTO> dynamicPricePlanList(DynamicPricePlanQueryDTO dynamicPricePlanQueryDTO);

    /**
     * 根据酒店查询所有产品信息
     * @param queryDTO
     * @return
     */
    public ResponseDTO<HotelDetailResponseDTO> queryHotelInfo(ProductRoomTypeListQueryDTO queryDTO);

    /**
     * 分页查询产品信息 订单模块需要
     * @param productPageQueryDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<ProductDailyInfoResponseDTO>> queryProductInfoByPage(ProductPageQueryDTO productPageQueryDTO);

    /**
     * 点击添加产品
     * @param queryDTO
     * @return
     */
    public ResponseDTO<ToAddPricePlanResponseDTO> toAddPricePlan(ToAddPricePlanQueryDTO queryDTO);

}
