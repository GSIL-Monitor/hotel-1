package com.travel.channel.service.impl;

import com.travel.channel.dao.InterfaceLogPOMapper;
import com.travel.channel.entity.po.InterfaceLogPOWithBLOBs;
import com.travel.channel.service.InterfaceLogService;
import com.travel.channel.task.InterfaceLogTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 *   2018/3/9.
 */
@Service("interfaceLogService")
public class InterfaceLogServiceImpl implements InterfaceLogService {

    private static final Logger LOG = LoggerFactory.getLogger(InterfaceLogServiceImpl.class);

    @Autowired
    private ThreadPoolTaskExecutor interfaceLogTaskExecutor;

    @Autowired
    private InterfaceLogPOMapper interfaceLogPOMapper;

    @Override
    public void saveInterfaceLog(InterfaceLogPOWithBLOBs interfaceLogPOWithBLOBs) {
//        try {
//            InterfaceLogTask task = new InterfaceLogTask(interfaceLogPOMapper, interfaceLogPOWithBLOBs);
//            interfaceLogTaskExecutor.execute(task);
//        } catch (Exception e) {
//            LOG.error("插入接口日志失败", e);
//        }
        try {
            LOG.info("插入接口日志开始，渠道: " + interfaceLogPOWithBLOBs.getChannelCode()
                    + "接口：" + interfaceLogPOWithBLOBs.getInterfaceName());
            interfaceLogPOMapper.insertSelective(interfaceLogPOWithBLOBs);
            LOG.info("插入接口日志结束，渠道: " + interfaceLogPOWithBLOBs.getChannelCode()
                    + "接口：" + interfaceLogPOWithBLOBs.getInterfaceName());
        } catch (Exception e) {
            LOG.error("插入接口日志失败，interfaceLogPOWithBLOBs = " + interfaceLogPOWithBLOBs, e);
        }
    }
}
