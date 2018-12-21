package com.travel.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *   2018/1/15.
 */
public class BeanCopy {

    /**
     * 拷贝两个list中的元素对象，内部采用json转换对象来实现
     * @param sourceList
     * @param targetClass
     * @param <T> 原对象类型
     * @param <M> 目标对象类型
     * @return List<M> 目标对象列表
     */
    public static <T, M> List<M> listCopy(List<T> sourceList, Class<M> targetClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<M>();
        }

        List<M> targetList = new ArrayList<>(sourceList.size());
        for (T t : sourceList) {
            targetList.add(copyProperties(t, targetClass));
        }
        return targetList;
    }

    /**
     * 对象属性复制
     * <p>只对两个对象属性名字一样的属性复制</p>
     *
     * @param source
     * @param target
     * @return
     */
    public static <T, S> S copyProperties(T source, Class<S> target) {
        return JSONObject.parseObject(JSON.toJSONString(source), target);
    }

}
