package com.travel.hotel.product.service;


import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.request.PricePlanQueryDTO;
import com.travel.common.dto.product.request.PriceQuotaRestrictRequestDTO;
import com.travel.hotel.product.entity.PricePlanDTO;
import com.travel.hotel.product.entity.PricePlanRoom;
import com.travel.hotel.product.entity.po.PricePO;
import com.travel.hotel.product.entity.po.PricePlanPO;
import com.travel.hotel.product.entity.po.ReserveQuotaPO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/20.
 */
public interface PricePlanService {

    /**
     * 新增价格计划时，组装供应商的下拉框选项
     * @return
     */
    public String queryAutoCompleteSupplyList();


    /**
     * 新增价格计划
     * @param po
     * @return
     */
    public int addPricePlan(PricePlanPO po);

    /**
     * 保存价格，房态、配额、条款
     * @param dto
     * @return
     */
    public int addPriceQuotaRestrict(PriceQuotaRestrictRequestDTO dto) throws ParseException;


    /**
     * 多个价格计划调价
     * @param dtoList
     * @return
     */
    int batchSetPriceQuotaRestrict(List<PriceQuotaRestrictRequestDTO> dtoList,String userName) throws ParseException;

    /**
     * 根据酒店id查询早餐
     * @param hotelId
     * @return
     */
    public List<PricePO> queryBreakfastByHotelId(Long hotelId);
    
    /**
     * 根据酒店id查询价格计划和房型
     * @param pricePlanQueryDTO
     * @return
     */
    public List<PricePlanRoom> queryPricePlanRoomByHotelId(PricePlanQueryDTO pricePlanQueryDTO);

    /**
     *
     * @param queryPO
     * @return
     */
    public PaginationDTO<PricePlanDTO> queryPricePlanList(PricePlanPO queryPO);

    public List<PricePlanDTO> queryPricePlanByCondition(PricePlanPO queryPO);

    /**
     *
     * @return
     */
    public int updateByCondition(PricePlanPO po);

    /**
     * 更改价格计划的有效性
     * @param pricePlanId
     * @param isActive
     * @return
     */
    public int updateAcvtive(Long pricePlanId, Integer isActive,String modifier);

    /**
     *
     * @param pricePlanId
     * @return
     */
    public PricePlanDTO queryPrciePlanById(Long pricePlanId);

    /**
     * 分页查询预留配额列表
     * @param reserveQuotaPO
     * @return
     */
    PaginationDTO<ReserveQuotaPO> queryReserveQuotaByCondition(ReserveQuotaPO reserveQuotaPO);

    /**
     * 查询价格计划pricePlanId，在saleDate这一天，对分销商distributeCode设置的预留配额。
     * @param pricePlanId
     * @param saleDate
     * @param distributeCode
     * @return
     */
    ReserveQuotaPO queryReserveQuota(Long pricePlanId, Date saleDate, String distributeCode);

    /**
     * 价格价格pricePlanId，在saleDate这一天，对分销商distributeCode是否设置过预留配额。
     * @param pricePlanId
     * @param saleDate
     * @param distributeCode
     * @return
     */
    Boolean hasReserveQuota(Long pricePlanId, Date saleDate, String distributeCode);

    /**
     * 扣价格计划pricePlanId，在saleDate这一天，对分销商distributeCode设置的预留配额。
     * @param pricePlanId
     * @param saleDate
     * @param distributeCode
     * @param quotaNum
     * @return
     */
    void deductReserveQuota(Long pricePlanId, Date saleDate, String distributeCode, Integer quotaNum);

    /**
     * 配额账号quotaAccountId，在这一天saleDate，的预留配额是否已经超过了在售配额
     * @param quotaAccountId
     * @param saleDate
     * @param addReserveQuotaNum
     * @return
     */
    Boolean isReserveQuotaOverDraft(Long quotaAccountId,Date saleDate,Integer addReserveQuotaNum);

    /**
     * 1、更新配额表的预留配额和在售配额字段
     * 2、预留配额表中插入一条记录
     * @param reserveQuotaPO
     * @return
     */
    int addDistributeReserveQuota(ReserveQuotaPO reserveQuotaPO);

    /**
     * 1、更新配额表的预留配额和在售配额字段
     * 2、更新预留配额表的记录
     * 将更新的配额数转换为需要增加的配额数：
     * 例：原来预留配额为10，现在页面上要改为4。
     * 则需要增加预留配额数为-6，设置给amount属性，用来更新配额表。
     * 4设置给reserveQuotaNum属性，用来更新预留配额表。
     * @param reserveQuotaPO
     * @return
     */
    int updateDistributeReserveQuota(ReserveQuotaPO reserveQuotaPO);


    List<ReserveQuotaPO> queryDaySumByPricePlan(Map param);

}
