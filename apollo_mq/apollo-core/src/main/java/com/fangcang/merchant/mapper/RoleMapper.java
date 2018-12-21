package com.fangcang.merchant.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.merchant.domain.ResourceDO;
import com.fangcang.merchant.domain.RoleDO;
import com.fangcang.merchant.request.GetRoleListByResourceIdRequestDTO;
import com.fangcang.merchant.request.GetUserByIdRequestDTO;
import com.fangcang.merchant.request.RoleQueryDTO;

import java.util.List;

/**
 * @Description    角色获取接口
 * @author         wen.zhong@fangcang.com
 * @CreateDate:     2018/5/30
 */
public interface RoleMapper extends MyMapper<RoleDO>{

	/**
	 * 获取角色列表
	 * @return List<RoleDO>
	 */
    public List<RoleDO> queryRoleList();
    
    /**
     * 根据角色Id获取相应的菜单权限
     * @param roleQueryDTO
     * @return
     */
    public List<ResourceDO> queryResourceByRoleId(RoleQueryDTO roleQueryDTO);
    
    public RoleDO getRoleInfoByRoleId(RoleQueryDTO roleQueryDTO);
    
    /**
     * 根据用户ID获取用户权限
     * @param getUserByIdRequestDTO
     * @return
     */
    public List<RoleDO> getRoleListByUserId(GetUserByIdRequestDTO getUserByIdRequestDTO);

    /**
     * 根据resourceId获取角色列表
     * @return
     */
	public List<RoleDO> queryRoleListByResourceId(GetRoleListByResourceIdRequestDTO getRoleListByResourceIdRequestDTO);

    /**
     * 根据权限Id，获取菜单列表
     * @param
     * @return
     */
    public List<ResourceDO> queryMenuByRoleId(List<Long> roleId);

}
