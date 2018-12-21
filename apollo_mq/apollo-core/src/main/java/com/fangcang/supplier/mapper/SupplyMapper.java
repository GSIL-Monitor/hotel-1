package com.fangcang.supplier.mapper;


import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyDO;
import com.fangcang.supplier.request.CommonSupplyListResquestDTO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.request.SupplyListQueryRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
* @Description:    供应商Mapper接口
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/28 09:49
*/
public interface SupplyMapper extends MyMapper<SupplyDO> {

    /**
     * 添加供应商
     * @param supplyDO
     * @return
     */
    Integer insertSupply(SupplyDO supplyDO);

    /**
     * 修改供应商
     * @param supplyDO
     * @return
     */
    Integer updateSupply(SupplyDO supplyDO);

    /**
     * 查询供应商列表
     * @return
     */
    List<SupplyDO> listSupply(SupplyListQueryRequestDTO supplyListQueryRequestDTO);

    /**
     * 根据条件查询供应商列表
     * @param supplyListQueryRequestDTO
     * @return
     */
    List<SupplyDO> queryAllSupplyList(SupplyListQueryRequestDTO supplyListQueryRequestDTO);

    /**
     * 设置常用
     * @param supplyDO
     * @return
     */
    Integer setFrequentlyUse(SupplyDO supplyDO);

    /**
     * 获取总记录数
     * @return
     */
    Long getToalCount();

    /**
     * 设置是否停用
     * @param supplyDO
     * @return
     */
    Integer setActive(SupplyDO supplyDO);

    /**
     * 查询常用供应商列表
     * @param commonSupplyListResquestDTO
     * @return
     */
    List<SupplyDO> queryCommonSupplyList(CommonSupplyListResquestDTO commonSupplyListResquestDTO);

    /**
     * 根据supplyId查询供应商编码
     * @param supplyId
     * @return
     */
    String getSupplyCodeById(@Param("supplyId") Long supplyId);

    /**
     * 查询单个供应商详情
     * @param singleUserRequestDTO
     * @return
     */
    SupplyDO selectSingleSupplyInfo(SingleUserRequestDTO singleUserRequestDTO);

    /**
     * 供应商现金充值
     */
    public int supplyCashRecharge(@Param("supplyId")Long supplyId,@Param("amount")BigDecimal amount);

    /**
     * 供应商押金充值
     */
    public int supplyDepositRecharge(@Param("supplyId")Long supplyId,@Param("amount")BigDecimal amount);
}