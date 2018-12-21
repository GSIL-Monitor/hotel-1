package com.fangcang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/6/4.
 */
@Component
@Slf4j
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取锁
     * @param key
     * @param value  时间戳
     * @return
     */
    public boolean lock(String key,String value){
        try {
            if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){//相当于setnx命令
                return true;
            }
            //如果没有获取到锁,判断value里面的时间戳是否过期
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(StringUtil.isValidString(currentValue) && Long.valueOf(currentValue) < System.currentTimeMillis()){
                //锁过期
                log.info("The key:" + key + " has expired.");
                //再次获取锁的上一个时间节点并设置新的时间，如果多线程并发，则只有一个线程才能获取到锁
                String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
                if(StringUtil.isValidString(oldValue) && oldValue.equals(currentValue)){
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("Get lock has error,key:" + key,e);
        }
        return false;
    }

    /**
     * 释放锁
     * @param key
     * @param value
     */
    public void unLock(String key,String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(StringUtil.isValidString(currentValue) && currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("unLock has error,key:" + key);
        }
    }
}
