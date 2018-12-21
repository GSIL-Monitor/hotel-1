package com.fangcang.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.UserBankCardDO;

public interface UserBankCardMapper extends MyMapper<UserBankCardDO>{

	/**
	 * 获取商家用户银行卡列表
	 * @return List<UserBankCardDO>
	 */
    public List<UserBankCardDO> queryUserBankCardList(@Param("merchantId")Long merchantId);
    
    /**
     * 添加商家用户银行卡信息
     * @param userBankCardDO
     * @return
     */
    public Integer insertUserBankCard(UserBankCardDO userBankCardDO); 
}
