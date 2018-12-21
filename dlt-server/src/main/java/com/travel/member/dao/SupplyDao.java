package com.travel.member.dao;

import java.util.List;

import com.travel.common.dto.member.query.SupplyQueryDTO;
import com.travel.member.entity.Supply;

/**
 * @Description 供应商数据操作接口
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午11:44:08
 */
public interface SupplyDao {
	
	/**
	 * 保存供应商
	 * @param supply
	 * @return
	 */
	public int saveSupply(Supply supply);
	
	/**
	 * 修改供应商
	 * @param supply
	 * @return
	 */
	public int updateSupply(Supply supply);
	
	/**
	 * 删除供应商
	 * @param supplyId
	 * @return
	 */
	public void deleteSupply(Long supplyId);
	
	/**
	 * 根据供应商id查询供应商
	 * @param supplyId
	 * @return
	 */
	public Supply querySupplyById(Long supplyId);
	
	/**
	 * 根据供应商编码查询供应商
	 * @param supplyCode
	 * @return
	 */
	public Supply querySupplyBySupplyCode(String supplyCode);
	
	/**
	 * 根据供应商编码查询有效的供应商
	 * @param supplyCode
	 * @return
	 */
	public Supply querySupplyActiveBySupplyCode(String supplyCode);
	
	/**
	 * 根据供应商名称查询供应商
	 * @param supplyName
	 * @return
	 */
	public Supply querySupplyByName(String supplyName);
	
	/**
	 * 查询供应商集合
	 * @param supplyQuery
	 * @return
	 */
	public List<Supply> listSupply(SupplyQueryDTO supplyQuery);
	
	/**
	 * 根据酒店关联关系查询供应商
	 * @param hotelId
	 * @return
	 */
	public List<Supply> querySupplyByRelation(Long hotelId);
}
