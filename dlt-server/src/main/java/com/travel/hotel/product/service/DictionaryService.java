package com.travel.hotel.product.service;

import com.travel.hotel.product.entity.po.DictionaryPO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   2018/1/10.
 */
public interface DictionaryService {
    /**
     * 查询所有的数据字典
     * @return
     */
    public List<DictionaryPO> queryAll();

    /**
     * 获取上次更新时间
     * @return
     */
    public Date queryLastEndTime();

    /**
     * 保存本次更新时间
     * @param lastEndTime
     */
    public void saveLastEndTime(Date lastEndTime);

    /**
     * 获取上次补漏时间
     * @return
     */
    public Date queryLastLeakTime();

    /**
     * 保存本次补漏时间
     * @param lastLeakTime
     */
    public void saveLastLeakTime(Date lastLeakTime);

    /**
     * 根据类型组装Map
     * @param dataType
     * @param allList
     * @return
     */
    public Map<String,String> getMapByDataType(String dataType, List<DictionaryPO> allList);

    /**
     * 组装星级的Map
     * key:19
     * value:五星级
     * @return
     */
    public Map<String,String> queryStarMap(List<DictionaryPO> allList);

    /**
     * 组装城市的Map
     * key:MAC
     * value:澳门
     * @return
     */
    public Map<String,String> queryCityMap(List<DictionaryPO> allList);

    /**
     * 组装床型的Map
     * key: BedTypeEnum.key  ---> 10
     * value: BedTypeEnum.value  -----> 大床
     * @return
     */
    public Map<String,String> queryBedTypeMap(List<DictionaryPO> allList);

    /**
     * 组装床型的Map
     * key: CNY
     * value: CNY
     * @return
     */
    public Map<String, String> queryCurrencyMap(List<DictionaryPO> allList);

    /**
     * 组装包房类型（采购类型）的Map
     * key: buyout1    contract  quota
     * value: 包房一   合约房    配额房
     * @return
     */
    public Map<String,String> queryQuotaTypeMap(List<DictionaryPO> allList);

    public List<String> getListByDataType(String dataType, List<DictionaryPO> allList);

    public List<String> queryWeekendList(List<DictionaryPO> allList);

    /**
     * 查询杂费类型
     * @param allList
     * @return
     */
    public Map<String,String> queryChargeTypeMap(List<DictionaryPO> allList);
    
    /**
     * 查询商家配置
     * @param allList
     * @return
     */
    public Map<String,String> queryMerchantMap(List<DictionaryPO> allList);

    /**
     * 查询代理通接口信息配置
     * @param allList
     * @return
     */
    public Map<String,String> queryDltInterfaceInfoMap(List<DictionaryPO> allList);

    public Map<String,String> queryBankMap();

    public Map<String,Map<String,String>> queryChannelConfig();

}
