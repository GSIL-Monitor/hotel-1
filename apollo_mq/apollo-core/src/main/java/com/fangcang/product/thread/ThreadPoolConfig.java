package com.fangcang.product.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ASUS on 2018/6/1.
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {

    @Bean("asyncServiceExecutor")
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        log.info("---------start asyncServiceExecutor---------");
        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(100);
        //配置最大线程数
        executor.setMaxPoolSize(200);
        //某线程空闲超过这个时间，就回收该线程
        executor.setKeepAliveSeconds(300);
        //配置队列大小
        executor.setQueueCapacity(1000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("apollo-");
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 增量推送线程池
     * @return
     */
    @Bean("incrementExecutor")
    public ThreadPoolTaskExecutor incrementExecutor() {
        log.info("---------start incrementExecutor---------");
        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(20);
        //配置最大线程数
        executor.setMaxPoolSize(100);
        //某线程空闲超过这个时间，就回收该线程
        executor.setKeepAliveSeconds(300);
        //配置队列大小
        executor.setQueueCapacity(10000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("increment-");
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 迁移酒店基础数据线程池
     * @return
     */
    @Bean("transferHotelInfoExecutor")
    public ThreadPoolTaskExecutor transferHotelInfoExecutor() {
        log.info("---------start transferHotelInfoExecutor---------");
        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(15);
        //配置最大线程数
        executor.setMaxPoolSize(15);
        //某线程空闲超过这个时间，就回收该线程
        executor.setKeepAliveSeconds(300);
        //配置队列大小
        executor.setQueueCapacity(10000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("transferHotelInfo-");
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
