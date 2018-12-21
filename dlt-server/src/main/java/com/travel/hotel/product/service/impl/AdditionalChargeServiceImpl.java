package com.travel.hotel.product.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.constant.InitData;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.exception.ParameterException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.PageConvert;
import com.travel.hotel.product.dao.AdditionalChargePOMapper;
import com.travel.hotel.product.entity.AdditionalDTO;
import com.travel.hotel.product.entity.po.AdditionalChargePO;
import com.travel.hotel.product.service.AdditionalChargeService;

/**
 * @Description : 附加费业务接口实现
 * @author : Zhiping Sun
 * @date : 2018年1月30日上午9:46:33
 */
@Service("additionalChargeService")
public class AdditionalChargeServiceImpl implements AdditionalChargeService {
	
	@Autowired
	private AdditionalChargePOMapper additionalChargePOMapper;

	@Override
	public List<AdditionalChargePO> queryAdditionalChargeByHotelId(Long hotelId) {
		return this.additionalChargePOMapper.queryAdditionalChargeByHotelId(hotelId);
	}

	@Override
	public PaginationDTO<AdditionalDTO> queryAdditionalChargeByHotelIdForPage(AdditionalDTO queryPO) {
		PageHelper.startPage(queryPO.getCurrentPage(), queryPO.getPageSize());

		List<AdditionalChargePO> poList = this.additionalChargePOMapper.queryAdditionalChargeByHotelId(queryPO.getHotelId());
		List<AdditionalDTO> dtoList = new LinkedList<AdditionalDTO>();
		for (AdditionalChargePO po : poList){
			AdditionalDTO dto = BeanCopy.copyProperties(po,AdditionalDTO.class);
			dto.setHotelName(queryPO.getHotelName());
			dto.setChargeTypeName(InitData.chargeMap.get(dto.getChargeType()));
			dtoList.add(dto);
		}
		PageInfo<AdditionalDTO> pageInfo = new PageInfo<AdditionalDTO>(dtoList);
		return PageConvert.getPaginationSupport(pageInfo);
	}

	@Override
	public AdditionalChargePO selectByPrimaryKey(Long chargeId) {
		return this.additionalChargePOMapper.selectByPrimaryKey(chargeId);
	}

	@Override
	public AdditionalDTO queryById(Long chargeId) {
		AdditionalChargePO po = this.selectByPrimaryKey(chargeId);
		AdditionalDTO additionalDTO = BeanCopy.copyProperties(po,AdditionalDTO.class);
		additionalDTO.setChargeTypeName(InitData.chargeMap.get(po.getChargeType()));
		additionalDTO.setBaseCurrencyName(InitData.currencyMap.get(po.getBaseCurrency()));
		additionalDTO.setSaleCCurrencyName(InitData.currencyMap.get(po.getSaleCCurrency()));
		additionalDTO.setSaleBCurrencyName(InitData.currencyMap.get(po.getSaleBCurrency()));
		return  additionalDTO;
	}

	@Override
	public Long addAdditional(AdditionalDTO additionalDTO) {
		this.additionalChargePOMapper.insertSelective(additionalDTO);
		return additionalDTO.getChargeId();
	}

	@Override
	public int delAdditional(AdditionalDTO additionalDTO) {
		return this.additionalChargePOMapper.deleteByPrimaryKey(additionalDTO.getChargeId());
	}

	@Override
	public int delAdditionalById(Long chargeId) {
		if (null == chargeId){
			throw new ParameterException("删除附加费失败，ID为空");
		}
		AdditionalDTO additionalDTO = new AdditionalDTO();
		additionalDTO.setChargeId(chargeId);
		return this.delAdditional(additionalDTO);
	}

	@Override
	public int updateAdditional(AdditionalChargePO po) {
		po.setModifydate(DateUtil.getCurrentDate());
		return this.additionalChargePOMapper.updateByPrimaryKeySelective(po);
	}

}
