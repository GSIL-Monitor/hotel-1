package com.fangcang.common.controller;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.ThreadInfoDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by ASUS on 2018/6/11.
 */
@RestController
@RequestMapping(value = "/threadMonitor")
public class ThreadMonitorController {

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping(value = "/queryThreadPoolInfo")
    public ResponseDTO<ThreadInfoDTO> queryThreadPoolInfo(){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        ThreadInfoDTO threadInfoDTO = new ThreadInfoDTO();
        threadInfoDTO.setPoolSize(threadPoolTaskExecutor.getPoolSize());
        threadInfoDTO.setCorePoolSize(threadPoolTaskExecutor.getCorePoolSize());
        threadInfoDTO.setMaxPoolSize(threadPoolTaskExecutor.getMaxPoolSize());
        threadInfoDTO.setActiveCount(threadPoolTaskExecutor.getActiveCount());
        responseDTO.setModel(threadInfoDTO);
        return responseDTO;
    }
}
