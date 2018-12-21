package com.fangcang.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.IncrementRetryDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.HttpClientUtil;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.product.service.IncrementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * Created by ASUS on 2018/6/20.
 */
@Service
@Slf4j
public class IncrementServiceImpl implements IncrementService {

    /**
     * 重试队列
     */
    private static LinkedBlockingQueue<IncrementRetryDTO> queue = new LinkedBlockingQueue<IncrementRetryDTO>();

    public Boolean offer(IncrementRetryDTO incrementRetryDTO){
        return queue.offer(incrementRetryDTO);
    }

    @PostConstruct
    public void  consume(){
        ConsumeThread consumeThread = new ConsumeThread();
        consumeThread.start();
    }

    private class ConsumeThread extends Thread{
        @Override
        public void run(){
            while (true){
                Boolean result = true;
                IncrementRetryDTO incrementRetryDTO = null;
                try {
                    incrementRetryDTO = queue.take();
                    TimeUnit.SECONDS.sleep(1);
                    if(null != incrementRetryDTO){
                        Long currentTime = System.currentTimeMillis();
                        log.info("consume message:" + JSON.toJSONString(incrementRetryDTO));
                        if(incrementRetryDTO.getRetryNum() >= IncrementConfig.RETRY_NUM){
                            //重试次数过多
                            log.warn("Retry num too much!" + JSON.toJSONString(incrementRetryDTO));
                            continue;
                        }else if(currentTime >= incrementRetryDTO.getTime() && incrementRetryDTO.getRetryNum() < IncrementConfig.RETRY_NUM){
                            //当前时间大于等于要执行的时间且重试次数小于常量值
                            String  message = JSON.toJSONString(incrementRetryDTO.getIncrementDTOList());
                            String responseStr = HttpClientUtil.postJson(incrementRetryDTO.getUrl(),message);
                            ResponseDTO responseDTO = JSON.parseObject(responseStr,ResponseDTO.class);
                            if(null == responseDTO || ResultCodeEnum.FAILURE.code == responseDTO.getResult()){
                                result= false;
                            }
                            log.info("Consume message push increment:" + message + ",push result : " + result + ",url:" + incrementRetryDTO.getUrl());
                        }else if(currentTime < incrementRetryDTO.getTime() && incrementRetryDTO.getRetryNum() < IncrementConfig.RETRY_NUM){
                            queue.offer(incrementRetryDTO);
                        }else{
                            log.warn("Do not support this scene.");
                        }
                    }
                } catch (Exception e) {
                    log.error("consume message has error" + JSON.toJSONString(incrementRetryDTO),e);
                    result = false;
                }
                if(!result){
                    if(null != incrementRetryDTO){
                        incrementRetryDTO.setRetryNum(incrementRetryDTO.getRetryNum() + 1);
                        queue.offer(incrementRetryDTO);
                    }
                }
            }
        }
    }
}

