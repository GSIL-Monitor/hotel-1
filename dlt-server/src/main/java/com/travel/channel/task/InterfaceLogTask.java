package com.travel.channel.task;

import com.travel.channel.dao.InterfaceLogPOMapper;
import com.travel.channel.entity.po.InterfaceLogPOWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *   2018/3/9.
 */
public class InterfaceLogTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(InterfaceLogTask.class);

    private InterfaceLogPOMapper interfaceLogPOMapper;

    private InterfaceLogPOWithBLOBs interfaceLogPOWithBLOBs;

    public InterfaceLogTask(InterfaceLogPOMapper interfaceLogPOMapper, InterfaceLogPOWithBLOBs interfaceLogPOWithBLOBs) {
        this.interfaceLogPOMapper = interfaceLogPOMapper;
        this.interfaceLogPOWithBLOBs = interfaceLogPOWithBLOBs;
    }

    @Override
    public void run() {
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
