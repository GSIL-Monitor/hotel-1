package com.fangcang.product.thread;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.IncrementDTO;
import com.fangcang.common.IncrementRetryDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.HttpClientUtil;
import com.fangcang.common.util.IncrementConfig;
import com.fangcang.product.service.IncrementService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2018/6/20.
 */
@Slf4j
public class IncrementThread  extends Thread{

    private List<IncrementDTO> incrementDTOList;

    private String url;

    private IncrementService incrementService;

    public IncrementThread(List<IncrementDTO> incrementDTOList, String url,IncrementService incrementService) {
        this.incrementDTOList = incrementDTOList;
        this.url = url;
        this.incrementService = incrementService;
    }

    @Override
    public void run() {
        Boolean result = false;
        try {
            //休眠1秒执行
            Thread.sleep(1000);

            String message = JSON.toJSONString(incrementDTOList);
            String responseStr = HttpClientUtil.postJson(url, message);
            ResponseDTO responseDTO = JSON.parseObject(responseStr,ResponseDTO.class);
            if(null == responseDTO || ResultCodeEnum.FAILURE.code == responseDTO.getResult()){
                result= false;
            }else if(null != responseDTO && ResultCodeEnum.SUCCESS.code == responseDTO.getResult()){
                result = true;
            }
            log.info("End push increment:" + message + ",push result : " + result + ",url:" + url);
        }  catch (InterruptedException e) {
            log.error("Push increment has error" ,e);
            result = false;
        }  catch (IOException e) {
            log.error("Push increment has error" ,e);
            result = false;
        }
        if(!result){
            //创建重试对象，推送失败则放入队列中
            IncrementRetryDTO incrementRetryDTO = new IncrementRetryDTO();
            incrementRetryDTO.setUrl(url);
            incrementRetryDTO.setIncrementDTOList(incrementDTOList);
            incrementRetryDTO.setRetryNum(0);
            incrementRetryDTO.setTime(System.currentTimeMillis() + IncrementConfig.DELAY_TIME);
            incrementService.offer(incrementRetryDTO);
        }
    }
}
