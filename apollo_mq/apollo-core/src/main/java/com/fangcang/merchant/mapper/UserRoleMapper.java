package com.fangcang.merchant.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.UserRoleDO;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRoleDO>{	
	/**
	 * 保存用户的角色信息
	 * @param userDO
	 * @return
	 */
	Integer saveUserRole(UserRoleDO userRoleDO);

	/**
	 * 批量保存用户的角色
	 * @param userRoleDOList
	 */
	void batchSaveUserRole(List<UserRoleDO> userRoleDOList);
}
