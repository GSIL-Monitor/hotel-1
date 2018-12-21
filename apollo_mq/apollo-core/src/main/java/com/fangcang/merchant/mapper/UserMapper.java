package com.fangcang.merchant.mapper;

import java.util.List;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.merchant.dto.QueryUserConditionDTO;
import com.fangcang.merchant.request.GetUserByIdRequestDTO;
import com.fangcang.merchant.request.StaffListQueryDTO;

public interface UserMapper extends MyMapper<UserDO>{

    public List<UserDO> queryUserForPage(QueryUserConditionDTO queryUserConditionDTO);
    
    /**
     * 根据userId 获取user信息
     * @param getUserByIdRequestDTO
     * @return
     */
    public UserDO queryUserInfoById(GetUserByIdRequestDTO getUserByIdRequestDTO);
    
    /**
     * 设置是否停用
     * @param userDO
     * @return
     */
    Integer setActive(UserDO userDO);

    /**
     * 修改密码
     * @param userDO
     * @return
     */
	Integer changePassword(UserDO userDO);
	
	/**
	 * 保存用户
	 * @param userDO
	 * @return
	 */
	Integer saveStaff(UserDO userDO);
	
	/**
	 * 获得员工列表
	 * @param hotelListQueryDTO
	 * @return
	 */
	public List<UserDO> getUserListByUserId(StaffListQueryDTO staffListQueryDTO);

	public UserDO getUserInfo(UserDO userCount);
}
