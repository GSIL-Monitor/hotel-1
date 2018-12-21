package com.fangcang.b2b.mapper;

import com.fangcang.b2b.domain.B2BHistoryDO;
import com.fangcang.b2b.dto.KeyWordDTO;
import com.fangcang.b2b.request.SearchHistoryQueryDTO;
import com.fangcang.common.MyMapper;
import com.fangcang.merchant.dto.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/3 10:09
 * @Description:
 */
public interface B2BHistoryMapper extends MyMapper<B2BHistoryDO>  {

    /**
     * 保存搜索历史
     * @param b2BHistoryDO
     * @return
     */
    Integer saveHistory(B2BHistoryDO b2BHistoryDO);

    /**
     * 查询搜索历史
     * @param b2BHistoryDO
     * @return
     */
    List<KeyWordDTO> queryHistory(B2BHistoryDO b2BHistoryDO);

    /**
     * 联系我们:获取产品经理真实名,联系方式
     *
     * @param merchantId
     * @param agentCode
     * @return
     */
    UserDTO getMerchantBMInfo(@Param("merchantId") Long merchantId, @Param("agentCode") String agentCode);

    /**
     * 联系我们:获取业务经理真实名,联系方式
     *
     * @param merchantId
     * @param agentCode
     * @return
     */
    //UserDTO getMerchantOPInfo(@Param("merchantId") Long merchantId, @Param("agentCode") String agentCode);

    /**
     * 删除搜索历史
     * @param b2BHistoryDO
     */
    public void deleteSearchHistory(B2BHistoryDO b2BHistoryDO);
}
