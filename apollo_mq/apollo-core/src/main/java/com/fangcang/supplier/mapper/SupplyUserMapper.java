package com.fangcang.supplier.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.supplier.domain.SupplyUserDO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.response.SingleUserResponseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description:    供应商里的账号Mapper接口
* @Author:         yanming.li@fangcang.com
* @CreateDate:     2018/5/28 11:40
*/
public interface SupplyUserMapper extends MyMapper<SupplyUserDO> {

    /**
     * 添加供应商账号
     * @param supplyUserDOS
     * @return
     */
    Integer insertUsers(List<SupplyUserDO> supplyUserDOS);

    /**
     * 修改供应商账号信息
     * @param supplyUserDOS
     * @return
     */
    Integer batchUpdateUsers(List<SupplyUserDO> supplyUserDOS);

    /**
     * 添加单个供应商账号
     * @param supplyUserDO
     * @return
     */
    Integer insertSingleSupplyUser(SupplyUserDO supplyUserDO);

    /**
     * 编辑单个供应商账号
     * @return
     */
    Integer updateSingleSupplyUser(SupplyUserDO supplyUserDO);

    /**
     * 根据supplyUserId查询详情
     * @param supplyUserId
     * @return
     */
    SupplyUserDO getUserByUserId(@Param("supplyUserId") Long supplyUserId);

    /**
     * 设置供应商用户是否启用
     * @param supplyUserDO
     * @return
     */
    Integer setUserActive(SupplyUserDO supplyUserDO);

    /**
     * 查询单个用户详情
     * @param singleUserRequestDTO
     * @return
     */
    SingleUserResponseDTO getUserInfo(SingleUserRequestDTO singleUserRequestDTO);

    /**
     * 根据username和merchantId查有多少条记录(新增单个用户时用)
     * @param username
     * @param merchantId
     * @return
     */
    Long selectUserByUsernameAndMerchantId(@Param("username") String username,@Param("merchantId") Long merchantId);

    /**
     * 根据usernameList和merchantId查有多少条记录(新增供应商时用)
     * @param usernameList
     * @param merchantId
     * @return
     */
    Long selectUserByUsernameListAndMerchantId(@Param("list") List usernameList,@Param("merchantId") Long merchantId);

}