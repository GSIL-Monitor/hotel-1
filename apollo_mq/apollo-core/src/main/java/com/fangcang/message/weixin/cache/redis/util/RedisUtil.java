package com.fangcang.message.weixin.cache.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ASUS on 2018/8/2.
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 保存缓存到redis中
     *
     * @param key
     * @param value
     * @param second
     * @return
     */
    public boolean saveDataInCache(String key, String value, Integer second) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
            log.info("saveDataInCache has success,key:" + key + ",value:" + value);
            return true;
        } catch (Exception e) {
            log.error("saveDataInCache has error", e);
        }
        return false;
    }

    public String queryDataInCache(String key) {
        try {
            String s = stringRedisTemplate.opsForValue().get(key);
            return s;
        } catch (Exception e) {
            log.error("queryDataInCache has error", e);
        }
        return null;
    }

    /**
     * 从缓存中删除指定key
     *
     * @param key
     * @return
     */
    public boolean delDataInCache(String key) {
        try {
            Boolean result = stringRedisTemplate.delete(key);
            log.info("delDataInCache has success,key:" + key);
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            log.error("delDataInCache has error", e);
        }
        return false;
    }

    /**
     * 非阻塞查询符合给定模式的key
     *
     * @param keyPattern
     * @param count
     * @return
     */
    public Set<String> scanDataInCache(String keyPattern, Integer count) {
        try {
            Set<String> set = stringRedisTemplate.execute((RedisCallback<Set<String>>) connection -> {
                Set<String> binaryKeys = new HashSet<>();
                Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(keyPattern).count(count).build());
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next()));
                }
                return binaryKeys;
            });
            return set;
        } catch (Exception e) {
            log.error("scanDataInCache has error", e);
        }
        return null;
    }

}
