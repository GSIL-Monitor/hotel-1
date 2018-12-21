package com.travel.common.constant;

import com.travel.common.utils.SpringContextUtil;
import com.travel.hotel.product.entity.po.DictionaryPO;
import com.travel.hotel.product.service.DictionaryService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.*;

/**
 *   2018/1/10.
 */
public class InitData {

    /**
     * 所有字典数据，在InitServlet中赋值，用于pick方法
     */
    public static volatile List<DictionaryPO> allList;

    /**
     * 城市
     * key=MAC
     * value=澳門
     */
    public static Map<String,String> cityMap = new LinkedHashMap<String,String>();

    /**
     * 星級
     * key=19
     * value=五星級
     */
    public static Map<String,String> starMap = new LinkedHashMap<String,String>();

    /**
     * 星級
     * key=10
     * value=大床
     */
    public static Map<String,String> bedTypeMap = new LinkedHashMap<String,String>();


    /**
     * 币种
     * key=CNY
     * value=CNY
     */
    public static Map<String,String> currencyMap = new LinkedHashMap<String,String>();

    /**
     * 配额类型的下拉框
     * key=buyout1
     * value=包房一
     */
    public static Map<String,String> quotaTypeMap = new LinkedHashMap<String,String>();

    /**
     *
     */
    public static List<String> weekendList = new LinkedList<String>();

    /**
     * 附加费类型
     * key=bed
     * value=加早
     */
    public static Map<String,String> chargeMap = new LinkedHashMap<String,String>();

    /**
     * 渠道
     * key=bed
     * value=加早
     */
    public static Map<String,String> channleMap = new LinkedHashMap<String,String>();

    /**
     * 商家配置
     */
    public static Map<String, String> merchantMap = new LinkedHashMap<String,String>();

    /**
     * 商家渠道对接配置信息
     */
    public static Map<String,Map<String,String>> channelConfigMap = new HashMap<String,Map<String,String>>();

    /**
     * 代理通接口信息
     */
    public static Map<String, String> dltInterfaceInfoMap = new LinkedHashMap<String,String>();

    /**
     * 预留配额的时间：默认15分钟
     */
    public static Integer RESERVE_QUOTA_TIME = 15;

    /**
     * 银行账号
     */
    public static Map<String,String> bankMap = new LinkedHashMap<>();

    /**
     * 根据类型和编码获取值
     * @param type 类型
     * @param code 编码
     * @return
     */
    public static String pickValue(String type, String code) {
        DictionaryPO po = pick(type, code);
        return null == po ? null : po.getDataValue();
    }

    /**
     * 根据类型和编码获取记录
     * @param type 类型
     * @param code 编码
     * @return DictionaryPO
     */
    public static DictionaryPO pick(String type, String code) {
        if (CollectionUtils.isEmpty(allList)) {
            init();
        }
        for (DictionaryPO po : allList) {
            if (po.getDataType().equals(type) && po.getDataCode().equals(code)) {
                return po;
            }
        }
        return null;
    }

    /**
     * 初始化(采用双重检查锁定方式）
     */
    public static void init() {
        if (CollectionUtils.isEmpty(allList)) {
            synchronized (InitData.class) {
                if (CollectionUtils.isEmpty(allList)) {
                    DictionaryService dictionaryService = (DictionaryService) SpringContextUtil.getBean("dictionaryService");
                    allList = dictionaryService.queryAll();
                }
            }
        }
    }

}
