package com.travel.hotel.product.service;

import com.travel.common.dto.PaginationDTO;
import com.travel.hotel.product.entity.AdditionalDTO;
import com.travel.hotel.product.entity.po.AdditionalChargePO;

import java.util.List;

/**
 * @Description : 附加费业务接口
 * @author : Zhiping Sun
 * @date : 2018年1月30日上午9:46:06
 */
public interface AdditionalChargeService {
	
	/**
	 * 根据酒店id查询附加费
	 * @param hotelId
	 * @return
	 */
	public List<AdditionalChargePO> queryAdditionalChargeByHotelId(Long hotelId);

	/**
	 * 分页查询
	 * @param hotelId
	 * @return
     */
	public PaginationDTO<AdditionalDTO> queryAdditionalChargeByHotelIdForPage(AdditionalDTO queryPO);
	
	/**
	 * 根据附加费id查询附加费
	 * @param chargeId
	 * @return
	 */
	public AdditionalChargePO selectByPrimaryKey(Long chargeId);

	public AdditionalDTO queryById(Long chargeId);

	/**
	 * 保存附加费
	 * @param additionalDTO
	 * @return  主键：附加费ID
     */
	public Long addAdditional(AdditionalDTO additionalDTO);

	/**
	 *
	 * @param additionalDTO
	 * @return
     */
	public int delAdditional(AdditionalDTO additionalDTO);

	/**
	 * 根据ID删除
	 * @param chargeId
	 * @return
     */
	public int delAdditionalById(Long chargeId);

	public int updateAdditional(AdditionalChargePO po);

}
