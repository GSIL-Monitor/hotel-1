package com.travel.finance.dao;

import java.util.List;

import com.travel.common.dto.finance.query.AgentCheckOutQueryDTO;
import com.travel.finance.entity.DayCheckOut;
import com.travel.finance.entity.OrgCheckOut;
import com.travel.finance.entity.OrgCheckOutDetail;

/**
 * @Description : 分销商出账数据接口
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午4:36:15
 */
public interface AgentCheckOutMapper {
	
	/**
	 * 分销商出账汇总
	 * @param agentCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOut> queryAgentCheckOutList(AgentCheckOutQueryDTO agentCheckOutQuery);
	
	/**
	 * 分销商每日小结查询
	 * @param agentCheckOutQuery
	 * @return
	 */
	public List<DayCheckOut> queryAgentDayCheckOutList(AgentCheckOutQueryDTO agentCheckOutQuery);
	
	/**
	 * 分销商每日小结明细查询
	 * @param agentCheckOutQuery
	 * @return
	 */
	public List<OrgCheckOutDetail> queryAgentDayCheckOutDetailList(AgentCheckOutQueryDTO agentCheckOutQuery);
	
}
