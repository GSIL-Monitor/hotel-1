package com.travel.hotel.product.service.impl;

import com.travel.common.dto.member.query.BankAccountQueryDTO;
import com.travel.common.dto.member.response.BankAccountDTO;
import com.travel.common.enums.DictionaryEnum;
import com.travel.hotel.dlt.utils.StringUtil;
import com.travel.hotel.product.dao.ChannelConfigMapper;
import com.travel.hotel.product.dao.DictionaryPOMapper;
import com.travel.hotel.product.entity.po.DictionaryPO;
import com.travel.hotel.product.entity.po.DictionaryPOExample;
import com.travel.hotel.product.entity.po.DltChannelConfig;
import com.travel.hotel.product.service.DictionaryService;
import com.travel.member.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/10.
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private DictionaryPOMapper dictionaryPOMapper;

    @Autowired
    private ChannelConfigMapper channelConfigMapper;

    @Override
    public List<DictionaryPO> queryAll() {
        return dictionaryPOMapper.selectAll();
    }

    @Override
    public Date queryLastEndTime(){
        DictionaryPOExample example=new DictionaryPOExample();
        DictionaryPOExample.Criteria criteria=example.createCriteria();
        criteria.andDataCodeEqualTo("lastEndTime");
        List<DictionaryPO> dictionaryPOList=dictionaryPOMapper.selectByExample(example);
        if (dictionaryPOList==null || dictionaryPOList.size()==0
                || !StringUtil.isValidString(dictionaryPOList.get(0).getDataValue())){
            return null;
        }else{
            Date lastEndTime=new Date();
            lastEndTime.setTime(Long.valueOf(dictionaryPOList.get(0).getDataValue()));
            return lastEndTime;
        }
    }

    @Override
    public void saveLastEndTime(Date lastEndTime){
        DictionaryPO dictionaryPO=new DictionaryPO();
        dictionaryPO.setDataValue(String.valueOf(lastEndTime.getTime()));
        DictionaryPOExample example=new DictionaryPOExample();
        DictionaryPOExample.Criteria criteria=example.createCriteria();
        criteria.andDataCodeEqualTo("lastEndTime");
        int result=dictionaryPOMapper.updateByExampleSelective(dictionaryPO,example);
        if (result<1){
            dictionaryPO.setDataCode("lastEndTime");
            dictionaryPO.setDataType("parameter");
            dictionaryPO.setDataDescription("增量同步时间");
            dictionaryPOMapper.insert(dictionaryPO);
        }
    }

    @Override
    public Date queryLastLeakTime() {
        DictionaryPOExample example=new DictionaryPOExample();
        DictionaryPOExample.Criteria criteria=example.createCriteria();
        criteria.andDataCodeEqualTo("lastLeakTime");
        List<DictionaryPO> dictionaryPOList=dictionaryPOMapper.selectByExample(example);
        if (dictionaryPOList==null || dictionaryPOList.size()==0
                || !StringUtil.isValidString(dictionaryPOList.get(0).getDataValue())){
            return null;
        }else{
            Date lastLeakTime=new Date();
            lastLeakTime.setTime(Long.valueOf(dictionaryPOList.get(0).getDataValue()));
            return lastLeakTime;
        }
    }

    @Override
    public void saveLastLeakTime(Date lastLeakTime) {
        DictionaryPO dictionaryPO=new DictionaryPO();
        dictionaryPO.setDataValue(String.valueOf(lastLeakTime.getTime()));
        DictionaryPOExample example=new DictionaryPOExample();
        DictionaryPOExample.Criteria criteria=example.createCriteria();
        criteria.andDataCodeEqualTo("lastLeakTime");
        int result=dictionaryPOMapper.updateByExampleSelective(dictionaryPO,example);
        if (result<1){
            dictionaryPO.setDataCode("lastLeakTime");
            dictionaryPO.setDataType("parameter");
            dictionaryPO.setDataDescription("查询漏单时间");
            dictionaryPOMapper.insert(dictionaryPO);
        }
    }

    @Override
    public Map<String, String> queryStarMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.STAR.dataType,allList);
    }

    @Override
    public Map<String, String> queryCityMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.CITY.dataType,allList);
    }

    @Override
    public Map<String, String> queryBedTypeMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.BED_TYPE.dataType,allList);
    }

    @Override
    public Map<String, String> queryQuotaTypeMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.QUOTA_TYPE.dataType,allList);
    }

    @Override
    public List<String> getListByDataType(String dataType, List<DictionaryPO> allList) {
        List<String> resultList = new LinkedList<String>();
        for (DictionaryPO po : allList)
            if (dataType.equals(po.getDataType())) {
                resultList.add(po.getDataValue());
            }
        return resultList;
    }

    @Override
    public List<String> queryWeekendList(List<DictionaryPO> allList) {
        return this.getListByDataType(DictionaryEnum.WEEKEND.dataType,allList);
    }

    @Override
    public Map<String, String> queryChargeTypeMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.CHARGE_TYPE.dataType,allList);
    }

    @Override
    public Map<String, String> queryCurrencyMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.CURRENCY.dataType,allList);
    }
    
    /**
     *  根据dataType类型组装不同的数据字典map
     * @param dataType
     * @return
     */
    @Override
    public Map<String,String> getMapByDataType(String dataType, List<DictionaryPO> allList){
        Map<String, String> resultMap = new LinkedHashMap<String,String>();
        for (DictionaryPO po : allList)
            if (dataType.equals(po.getDataType())) {
                resultMap.put(po.getDataCode(), po.getDataValue());
            }
        return resultMap;
    }

	@Override
	public Map<String, String> queryMerchantMap(List<DictionaryPO> allList) {
		return this.getMapByDataType(DictionaryEnum.MERCHANT.dataType, allList);
	}

    @Override
    public Map<String, String> queryDltInterfaceInfoMap(List<DictionaryPO> allList) {
        return this.getMapByDataType(DictionaryEnum.DLT_INTERFACE_INFO.dataType, allList);
    }

    @Override
    public Map<String, String> queryBankMap() {
        BankAccountQueryDTO queryDTO = new BankAccountQueryDTO();
        queryDTO.setIsActive(1);
        List<BankAccountDTO> bankList = bankAccountService.listBankAccountListByCondition(queryDTO);
        Map<String,String> bankMap = new LinkedHashMap<>();
        bankList.forEach(bankAccountDTO -> bankMap.put(bankAccountDTO.getBankNo(),bankAccountDTO.getBankName()));
        return bankMap;
    }

    @Override
    public Map<String,Map<String,String>> queryChannelConfig() {
        List<DltChannelConfig> channelConfigList = channelConfigMapper.queryAllConfig();

        Map<String,Map<String,String>> channelConfigMap = null;

        if(null!=channelConfigList && channelConfigList.size()>0) {
            channelConfigMap = new HashMap<String,Map<String,String>>();
            for(DltChannelConfig dltChannelConfig:channelConfigList) {
                if(null==channelConfigMap.get(dltChannelConfig.getMerchantCode())) {
                    Map<String,String> configMap = new HashMap<String,String>();
                    configMap.put(dltChannelConfig.getFieldName(),dltChannelConfig.getFieldValue());
                    channelConfigMap.put(dltChannelConfig.getMerchantCode(),configMap);
                }else {
                    channelConfigMap.get(dltChannelConfig.getMerchantCode()).put(dltChannelConfig.getFieldName(),dltChannelConfig.getFieldValue());
                }
            }
        }

        return channelConfigMap;
    }

}
