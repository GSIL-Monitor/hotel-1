package com.travel.member.service;

import com.travel.common.dto.AutoCompleteDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.member.query.SupplyQueryDTO;
import com.travel.common.dto.member.request.SupplyRequestDTO;
import com.travel.common.dto.member.response.SupplyResponseDTO;
import com.travel.member.entity.Supply;

import java.util.List;

/**
 * @Description 供应商业务操作接口
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日下午12:44:32
 */
public interface SupplierService {

	/**
	 * 保存供应商
	 * @param supply
	 * @return
	 */
	public Supply saveSupply(SupplyRequestDTO supply);
	
	/**
	 * 修改供应商
	 * @param supply
	 * @return
	 */
	public Long updateSupply(SupplyRequestDTO supply);
	
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
	public SupplyResponseDTO getSupplyById(Long supplyId);
	
	/**
	 * 根据供应商编码查询供应商
	 * @param supplyCode
	 * @return
	 */
	public SupplyResponseDTO getSupplyBySupplyCode(String supplyCode);
	
	/**
	 * 根据供应商编码查询有效的供应商
	 * @param supplyCode
	 * @return
	 */
	public SupplyResponseDTO getSupplyActiveBySupplyCode(String supplyCode);
	
	/**
	 * 查询供应商集合
	 * @param supplyQuery
	 * @return
	 */
	public List<SupplyResponseDTO> listSupply(SupplyQueryDTO supplyQuery);
	
	/**
	 * 供应商联想输入
	 * @return
	 */
	public List<AutoCompleteDTO> autocomplete();
	
	/**
	 * 分页查询供应商集合
	 * @param supplyQuery
	 * @return
	 */
	public PaginationDTO<SupplyResponseDTO> listSupplyForPage(SupplyQueryDTO supplyQuery);
	
	/**
	 * 根据酒店关联关系查询供应商
	 * @param hotelId
	 * @return
	 */
	public List<SupplyResponseDTO> listSupplyByRelation(Long hotelId);
}
