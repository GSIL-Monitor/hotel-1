package com.fangcang.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/11.
 */
@Data
public class ThreadInfoDTO implements Serializable {
    private static final long serialVersionUID = -6768965974707679971L;

    /**
     * 线程池中当前线程的数目
     */
    private Integer poolSize;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize;

    /**
     * 正在执行任务的线程的大致数目
     */
    private Integer activeCount;
}
