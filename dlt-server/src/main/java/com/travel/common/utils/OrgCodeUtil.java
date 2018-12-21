package com.travel.common.utils;

import com.travel.hotel.product.dao.DictionaryPOMapper;
import com.travel.hotel.product.entity.po.DictionaryPO;
import com.travel.hotel.product.entity.po.DictionaryPOExample;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description : 机构编码工具类
 * @author : Zhiping Sun
 * @date : 2018年1月10日下午6:46:45
 */
public class OrgCodeUtil {

	private static final Logger LOG = LoggerFactory.getLogger(OrgCodeUtil.class);
	/**
	 * 生成分销商编码
	 * @param agentSeq
	 * @return
	 */
	public static String generateAgentCode(Long agentSeq) {
		StringBuilder sb = null;
		if(null != agentSeq && 0 != agentSeq){
			sb = new StringBuilder();
			sb.append("C01");
			sb.append(100000L + agentSeq);
		}		
		return null == sb ? "" : sb.toString();
	}
	
	/**
	 * 生成供应商编码
	 * @param supplySeq
	 * @return
	 */
	public static String generateSupplyCode(Long supplySeq) {
		StringBuilder sb = null;
		if(null != supplySeq && 0 != supplySeq){
			sb = new StringBuilder();
			sb.append("S01");
			sb.append(10000L + supplySeq);
		}		
		return null == sb ? "" : sb.toString();
	}

	/**
	 * 生成订单号
	 * 订单号格式： ZH + yyMMdd + 递增序列（0000-9999）， 总共12位，需要保证后4位递增且不重复
	 * @return
     */
	public static synchronized String generateZhOrderCode() {

		// 到天
		try {
			String currentTime = DateUtil.dateToString(new java.util.Date(), "yyMMdd");

			DictionaryPOMapper dictionaryPOMapper = (DictionaryPOMapper) SpringContextUtil.getBean("dictionaryPOMapper");
			DictionaryPOExample example = new DictionaryPOExample();
			example.createCriteria().andDataTypeEqualTo("order_code").andDataCodeEqualTo(currentTime);
			List<DictionaryPO> dictionaryPOList = dictionaryPOMapper.selectByExample(example);

			DictionaryPO po;
			if (CollectionUtils.isEmpty(dictionaryPOList)) {
				po = new DictionaryPO();
				po.setDataType("order_code");
				po.setDataCode(currentTime);
				po.setDataValue("0");
				po.setDataDescription("订单编号生成序列");
				dictionaryPOMapper.insert(po);
			} else {
				po = dictionaryPOList.get(0);
				po.setDataValue(String.valueOf(Integer.valueOf(po.getDataValue()) + 1));
				dictionaryPOMapper.updateByPrimaryKey(po);
			}
			return new StringBuilder().append("ZH").append(currentTime).append(getNum(po.getDataValue())).toString();
		} catch (Exception e) {
			LOG.error("订单号生产失败", e);
			return null;
		}
	}

	private static String getNum(String s) {
		Integer i = Integer.valueOf(s);
		if (i < 10) {
			return "000" + i;
		}
		if (i < 100) {
			return "00" + i;
		}
		if (i < 1000) {
			return "0" + i;
		}
		return s;
	}

}
