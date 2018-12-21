package com.travel.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.dto.AutoCompleteDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.finance.request.CreditRequestDTO;
import com.travel.common.dto.member.query.AgentQueryDTO;
import com.travel.common.dto.member.request.AgentRequestDTO;
import com.travel.common.dto.member.response.AgentResponseDTO;
import com.travel.common.enums.ActiveEnum;
import com.travel.common.enums.CreditTypeEnum;
import com.travel.common.enums.SettlementEnum;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.OrgCodeUtil;
import com.travel.finance.dao.BillMapper;
import com.travel.finance.entity.CreditItem;
import com.travel.member.dao.AgentDao;
import com.travel.member.dao.SequenceDao;
import com.travel.member.entity.Agent;
import com.travel.member.service.AgentBusinessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 分销商业务操作接口实现
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午10:49:27
 */
@Service
public class AgentBusinessBusinessServiceImpl implements AgentBusinessService {

	@Autowired
	private AgentDao agentMapper;
	
	@Autowired
	private BillMapper billMapper;
	
	@Autowired
	private SequenceDao sequenceDao;

	@Override
	public Long saveAgent(AgentRequestDTO agent) {
		Agent ag = this.agentMapper.queryAgentByName(agent.getAgentName());
		if (null != ag) {
			throw new ServiceException("当前客户名称已存在");
		}
		Agent a = new Agent();
		BeanUtils.copyProperties(agent, a);
		Long agentSeq = this.sequenceDao.querySequence();
		String agentCode = OrgCodeUtil.generateAgentCode(agentSeq);
		a.setAgentCode(agentCode);
		this.agentMapper.saveAgent(a);
		return a.getAgentId();
	}

	@Override
	public Long updateAgent(AgentRequestDTO agent) {
		Agent a = this.agentMapper.queryAgentById(agent.getAgentId());
		if (a == null) {
			throw new ServiceException("分销商不存在!");
		}
		if (!a.getAgentName().equals(agent.getAgentName())) {
			Agent ag = this.agentMapper.queryAgentByName(agent.getAgentName());
			if (null != ag) {
				throw new ServiceException("当前客户名称已存在");
			}
		}
		BeanUtils.copyProperties(agent, a);
		this.agentMapper.updateAgent(a);
		return a.getAgentId();
	}
	
	@Override
	public void updateAgentUsedCredit(CreditRequestDTO creditRequest) {
		//如果是订单确认，挂账金额默认转成负数
		if (creditRequest.getCreditType() == CreditTypeEnum.CREDIT.key) {
			creditRequest.setOrderCreditAmount(creditRequest.getOrderCreditAmount() == null ? BigDecimal.ZERO : creditRequest.getOrderCreditAmount().negate());
			creditRequest.setSupplyCreditAmount(creditRequest.getSupplyCreditAmount() == null ? BigDecimal.ZERO : creditRequest.getSupplyCreditAmount().negate());
		}
		Agent agent = this.agentMapper.queryAgentByCode(creditRequest.getAgentCode());
		//更新分销商信用额度
		BigDecimal usedAmount = agent.getUsedCreditAmount() == null ? BigDecimal.ZERO : agent.getUsedCreditAmount();
		agent.setUsedCreditAmount(usedAmount.add(creditRequest.getOrderCreditAmount() == null ? BigDecimal.ZERO : creditRequest.getOrderCreditAmount()));
		this.agentMapper.updateAgent(agent);
		
		CreditItem ci = new CreditItem();
		BeanUtils.copyProperties(creditRequest, ci);
		//保存挂账记录
		this.billMapper.saveCreditItem(ci);
	}

	@Override
	public void deleteAgent(Long agentId) {
		this.agentMapper.deleteAgent(agentId);
	}

	@Override
	public AgentResponseDTO getAgentById(Long agentId) {
		AgentResponseDTO ar = new AgentResponseDTO();
		Agent a = this.agentMapper.queryAgentById(agentId);
		BeanUtils.copyProperties(a, ar);
		if (null != ar.getIsActive()) {
			ar.setIsActiveText(ActiveEnum.getDescripByKey(ar.getIsActive()));
		}
		if (StringUtils.isNotBlank(ar.getSettlement())) {
			ar.setSettlementText(SettlementEnum.getDescripByCode(ar.getSettlement()));
		}
		if (null != ar.getSettlementRMB()) {
			ar.setSettlementRMBText(ar.getSettlementRMB() == 1 ? "是" : "否");
		}
		BigDecimal creditLine = ar.getCreditLine() == null ? BigDecimal.ZERO : ar.getCreditLine();
		BigDecimal usedCreditAmount = ar.getUsedCreditAmount() == null ? BigDecimal.ZERO : ar.getUsedCreditAmount();
		ar.setSurplus(creditLine.add(usedCreditAmount));
		return ar;
	}

	@Override
	public List<AgentResponseDTO> listAgent(AgentQueryDTO agentQuery) {
		AgentResponseDTO ar = null;
		List<AgentResponseDTO> agentList = new ArrayList<AgentResponseDTO>();
		List<Agent> list = this.agentMapper.listAgent(agentQuery);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Agent a : list) {
				ar = new AgentResponseDTO();
				BeanUtils.copyProperties(a, ar);
				if (null != ar.getIsActive()) {
					ar.setIsActiveText(ActiveEnum.getDescripByKey(ar.getIsActive()));
				}
				if (StringUtils.isNotBlank(ar.getSettlement())) {
					ar.setSettlementText(SettlementEnum.getDescripByCode(ar.getSettlement()));
				}
				if (null != ar.getSettlementRMB()) {
					ar.setSettlementRMBText(ar.getSettlementRMB() == 1 ? "是" : "否");
				}
				BigDecimal creditLine = ar.getCreditLine() == null ? BigDecimal.ZERO : ar.getCreditLine();
				BigDecimal usedCreditAmount = ar.getUsedCreditAmount() == null ? BigDecimal.ZERO : ar.getUsedCreditAmount();
				ar.setSurplus(creditLine.add(usedCreditAmount));
				agentList.add(ar);
			}
		}
		return agentList;
	}

	@Override
	public PaginationDTO<AgentResponseDTO> listAgentForPage(AgentQueryDTO agentQuery) {
		AgentResponseDTO ar = null;
		PaginationDTO<AgentResponseDTO> pagination = new PaginationDTO<AgentResponseDTO>();
		List<AgentResponseDTO> recordList = new ArrayList<AgentResponseDTO>();
		PageHelper.startPage(agentQuery.getCurrentPage(), agentQuery.getPageSize());
		List<Agent> agens = this.agentMapper.listAgent(agentQuery);
		PageInfo<Agent> page = new PageInfo<Agent>(agens);
		if (CollectionUtils.isNotEmpty(page.getList())) {
			for (Agent a : page.getList()) {
				ar = new AgentResponseDTO();
				BeanUtils.copyProperties(a, ar);
				if (null != ar.getIsActive()) {
					ar.setIsActiveText(ActiveEnum.getDescripByKey(ar.getIsActive()));
				}
				if (StringUtils.isNotBlank(ar.getSettlement())) {
					ar.setSettlementText(SettlementEnum.getDescripByCode(ar.getSettlement()));
				}
				if (null != ar.getSettlementRMB()) {
					ar.setSettlementRMBText(ar.getSettlementRMB() == 1 ? "是" : "否");
				}
				BigDecimal creditLine = ar.getCreditLine() == null ? BigDecimal.ZERO : ar.getCreditLine();
				BigDecimal usedCreditAmount = ar.getUsedCreditAmount() == null ? BigDecimal.ZERO : ar.getUsedCreditAmount();
				ar.setSurplus(creditLine.add(usedCreditAmount));
				recordList.add(ar);
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
		AgentQueryDTO agentQuery = new AgentQueryDTO();
		List<Agent> list = this.agentMapper.listAgent(agentQuery);
		List<AutoCompleteDTO> autoCompleteList = new ArrayList<AutoCompleteDTO>();
		BigDecimal credit = BigDecimal.ZERO;
		BigDecimal usedCredit = BigDecimal.ZERO;
		if (CollectionUtils.isNotEmpty(list)) {
			AutoCompleteDTO auto = null;
			for (Agent agent : list) {
				credit = agent.getCreditLine() == null ? BigDecimal.ZERO : agent.getCreditLine();
				usedCredit = agent.getUsedCreditAmount() == null ? BigDecimal.ZERO : agent.getUsedCreditAmount();
				auto = new AutoCompleteDTO();
				auto.setLabel(agent.getAgentName());
				auto.setValue(agent.getAgentCode());
				auto.setAmount(credit.add(usedCredit));
				autoCompleteList.add(auto);
			}
		}
		return autoCompleteList;
	}

	@Override
	public AgentResponseDTO getAgentByCode(String agentCode) {
		if (StringUtils.isBlank(agentCode)) {
			return null;
		}
		AgentResponseDTO ar = new AgentResponseDTO();
		Agent agent = this.agentMapper.queryAgentByCode(agentCode);
		BeanUtils.copyProperties(agent, ar);
		BigDecimal creditLine = ar.getCreditLine() == null ? BigDecimal.ZERO : ar.getCreditLine();
		BigDecimal usedCreditAmount = ar.getUsedCreditAmount() == null ? BigDecimal.ZERO : ar.getUsedCreditAmount();
		ar.setSurplus(creditLine.add(usedCreditAmount));
		return ar;
	}

}
