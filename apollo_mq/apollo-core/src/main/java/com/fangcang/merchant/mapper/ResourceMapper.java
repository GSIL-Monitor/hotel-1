package com.fangcang.merchant.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.ResourceDO;
import com.fangcang.merchant.domain.RoleResourceDO;

import java.util.List;

/**
 * @Description    resource获取接口
 * @author         wen.zhong@fangcang.com
 * @CreateDate:     2018/5/30
 */
public interface ResourceMapper extends MyMapper<ResourceDO>{

	/**
	 * 获取resource列表
	 * @return List<RoleDO>
	 */
    public List<ResourceDO> queryResourceList();

	/**
	 * 获取资源列表，并查询访问资源所需的角色
	 * @return
     */
	List<RoleResourceDO> queryRoleResourceList();

}
