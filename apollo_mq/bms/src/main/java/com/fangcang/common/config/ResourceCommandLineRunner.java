package com.fangcang.common.config;

import com.fangcang.common.constant.InitData;
import com.fangcang.merchant.service.MerchantService;
import com.fangcang.merchant.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Created by Vinney on 2018/6/21.
 */
@Service
@Slf4j
public class ResourceCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MerchantService merchantService;

    @Override
    public void run(String... strings) throws Exception {

        InitData.NEED_CHECK_URL_LIST = resourceService.getNeedCheckResourceUrl();
        log.info("初始化需要交验的资源:"+InitData.NEED_CHECK_URL_LIST);

        InitData.MERCHANT_CODE_NAME_MAP = merchantService.queryAllMerchantMap();
        log.info("初始化商家编码:"+InitData.MERCHANT_CODE_NAME_MAP);
    }
}
