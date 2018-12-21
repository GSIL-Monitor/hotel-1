package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyBankCardDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description:    供应商银行卡管理Mapper接口
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/29 14:08
*/
public interface SupplyBankCardMapper extends MyMapper<SupplyBankCardDO> {

    /**
     * 添加供应商银行卡信息
     * @return
     */
    Integer insertSupplyBankCard(List<SupplyBankCardDO> supplyBankCardDOS);

    /**
     * 修改供应商银行卡信息
     * @param supplyBankCardDOS
     * @return
     */
    Integer batchUpdateSupplyBankCard(List<SupplyBankCardDO> supplyBankCardDOS);

    /**
     * 添加单个银行卡信息
     * @param supplyBankCardDO
     * @return
     */
    Integer insertSingleSupplyBankCard(SupplyBankCardDO supplyBankCardDO);

    /**
     * 更新单个银行卡信息
     * @param supplyBankCardDO
     * @return
     */
    Integer updateSingleSupplyBankCard(SupplyBankCardDO supplyBankCardDO);

    /**
     * 删除供应商银行卡信息
     * @param bankCardId
     * @return
     */
    Integer deleteBankCardById(@Param("bankCardId")Long bankCardId);
}