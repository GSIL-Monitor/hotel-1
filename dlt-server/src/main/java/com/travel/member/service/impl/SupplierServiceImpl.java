package com.travel.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.AutoCompleteDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.member.query.SupplyQueryDTO;
import com.travel.common.dto.member.request.SupplyRequestDTO;
import com.travel.common.dto.member.response.SupplyResponseDTO;
import com.travel.common.enums.ActiveEnum;
import com.travel.common.enums.SettlementEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.OrgCodeUtil;
import com.travel.member.dao.SequenceDao;
import com.travel.member.dao.SupplyDao;
import com.travel.member.entity.Supply;
import com.travel.member.service.SupplierService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 供应商业务接口实现
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日下午12:47:37
 */
@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplyDao supplyMapper;
	
	@Autowired
	private SequenceDao sequenceDao;

	@Override
	public Supply saveSupply(SupplyRequestDTO supply) {
		Supply sp = this.supplyMapper.querySupplyByName(supply.getSupplyName());
		if (null != sp) {
			throw new ServiceException("当前供应商名称已存在");
		}
		Supply s = new Supply();
		BeanUtils.copyProperties(supply, s);
		Long supplySeq = this.sequenceDao.querySequence();
		String supplyCode = OrgCodeUtil.generateSupplyCode(supplySeq);
		s.setSupplyCode(supplyCode);
		this.supplyMapper.saveSupply(s);
		return s;
	}

	@Override
	public Long updateSupply(SupplyRequestDTO supply) {
		Supply s = this.supplyMapper.querySupplyById(supply.getSupplyId());
		if (null == s) {
			throw new ServiceException("供应商不存在!");
		}
		if (!supply.getSupplyName().equals(s.getSupplyName())) {
			Supply sp = this.supplyMapper.querySupplyByName(supply.getSupplyName());
			if (null != sp) {
				throw new ServiceException("当前供应商名称已存在");
			}
		}
		BeanUtils.copyProperties(supply, s);
		this.supplyMapper.updateSupply(s);
		return s.getSupplyId();
	}

	@Override
	public void deleteSupply(Long supplyId) {
		this.supplyMapper.deleteSupply(supplyId);
	}

	@Override
	public SupplyResponseDTO getSupplyById(Long supplyId) {
		SupplyResponseDTO sr = new SupplyResponseDTO();
		Supply s = this.supplyMapper.querySupplyById(supplyId);
		BeanUtils.copyProperties(s, sr);
		if (null != sr.getIsActive()) {
			sr.setIsActiveText(ActiveEnum.getDescripByKey(sr.getIsActive()));
		}
		if (StringUtils.isNotBlank(sr.getSettlement())) {
			sr.setSettlementText(SettlementEnum.getDescripByCode(sr.getSettlement()));
		}
		return sr;
	}
	
	@Override
	public SupplyResponseDTO getSupplyBySupplyCode(String supplyCode) {
		SupplyResponseDTO sr = new SupplyResponseDTO();
		Supply s = this.supplyMapper.querySupplyBySupplyCode(supplyCode);
		BeanUtils.copyProperties(s, sr);
		if (null != sr.getIsActive()) {
			sr.setIsActiveText(ActiveEnum.getDescripByKey(sr.getIsActive()));
		}
		if (StringUtils.isNotBlank(sr.getSettlement())) {
			sr.setSettlementText(SettlementEnum.getDescripByCode(sr.getSettlement()));
		}
		return sr;
	}
	
	@Override
	public SupplyResponseDTO getSupplyActiveBySupplyCode(String supplyCode) {
		SupplyResponseDTO sr = new SupplyResponseDTO();
		Supply s = this.supplyMapper.querySupplyActiveBySupplyCode(supplyCode);
		BeanUtils.copyProperties(s, sr);
		if (null != sr.getIsActive()) {
			sr.setIsActiveText(ActiveEnum.getDescripByKey(sr.getIsActive()));
		}
		if (StringUtils.isNotBlank(sr.getSettlement())) {
			sr.setSettlementText(SettlementEnum.getDescripByCode(sr.getSettlement()));
		}
		return sr;
	}

	@Override
	public List<SupplyResponseDTO> listSupply(SupplyQueryDTO supplyQuery) {
		SupplyResponseDTO sr = null;
		List<SupplyResponseDTO> list = new ArrayList<SupplyResponseDTO>();
		List<Supply> supplys = this.supplyMapper.listSupply(supplyQuery);
		if (CollectionUtils.isNotEmpty(supplys)) {
			for (Supply s : supplys) {
				sr = new SupplyResponseDTO();
				BeanUtils.copyProperties(s, sr);
				if (null != sr.getIsActive()) {
					sr.setIsActiveText(ActiveEnum.getDescripByKey(sr.getIsActive()));
				}
				if (StringUtils.isNotBlank(sr.getSettlement())) {
					sr.setSettlementText(SettlementEnum.getDescripByCode(sr.getSettlement()));
				}
				list.add(sr);
			}
		}
		return list;
	}

	@Override
	public PaginationDTO<SupplyResponseDTO> listSupplyForPage(SupplyQueryDTO supplyQuery) {
		SupplyResponseDTO sr = null;
		PaginationDTO<SupplyResponseDTO> pagination = new PaginationDTO<SupplyResponseDTO>();
		List<SupplyResponseDTO> recordList = new ArrayList<SupplyResponseDTO>();
		PageHelper.startPage(supplyQuery.getCurrentPage(), supplyQuery.getPageSize());
		List<Supply> supplys = this.supplyMapper.listSupply(supplyQuery);
		PageInfo<Supply> page = new PageInfo<Supply>(supplys);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (Supply s : page.getList()) {
				sr = new SupplyResponseDTO();
				BeanUtils.copyProperties(s, sr);
				if (null != sr.getIsActive()) {
					sr.setIsActiveText(ActiveEnum.getDescripByKey(sr.getIsActive()));
				}
				if (StringUtils.isNotBlank(sr.getSettlement())) {
					sr.setSettlementText(SettlementEnum.getDescripByCode(sr.getSettlement()));
				}
				recordList.add(sr);
			}
		}
		pagination.setRecordList(recordList);
		pagination.setCurrentPage(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		pagination.setTotalCount(Long.valueOf(page.getTotal()).intValue());
		pagination.setTotalPages(page.getPages());
		return pagination;
	}

	@Override
	public List<AutoCompleteDTO> autocomplete() {
		SupplyQueryDTO supplyQuery = new SupplyQueryDTO();
		List<Supply> list = this.supplyMapper.listSupply(supplyQuery);
		List<AutoCompleteDTO> autoCompleteList = new ArrayList<AutoCompleteDTO>();
		if (CollectionUtils.isNotEmpty(list)) {
			AutoCompleteDTO auto = null;
			for (Supply supply : list) {
				auto = new AutoCompleteDTO();
				auto.setLabel(supply.getSupplyName());
				auto.setValue(supply.getSupplyCode());
				autoCompleteList.add(auto);
			}
		}
		return autoCompleteList;
	}

	@Override
	public List<SupplyResponseDTO> listSupplyByRelation(Long hotelId) {
		List<SupplyResponseDTO> recordList = new ArrayList<SupplyResponseDTO>();
		SupplyResponseDTO sr = null;
		List<Supply> list = this.supplyMapper.querySupplyByRelation(hotelId);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Supply s : list) {
				sr = new SupplyResponseDTO();
				BeanUtils.copyProperties(s, sr);
				recordList.add(sr);
			}
		}
		return recordList;
	}

}
