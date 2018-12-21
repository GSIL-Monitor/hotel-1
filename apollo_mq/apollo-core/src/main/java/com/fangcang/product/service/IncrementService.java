package com.fangcang.product.service;

import com.fangcang.common.IncrementRetryDTO;

/**
 * Created by ASUS on 2018/6/20.
 */
public interface IncrementService {

    /**
     * 添加到队列中   BlockingQueue可以容纳,则返回true,否则返回false
     * @param incrementRetryDTO
     * @return
     */
    public Boolean offer(IncrementRetryDTO incrementRetryDTO);
}
