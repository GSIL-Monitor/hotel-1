package com.travel.hotel.dlt.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware
{
    private static final Log LOG = LogFactory.getLog(AutowiringSpringBeanJobFactory.class);

    private transient AutowireCapableBeanFactory beanFactory;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle)
    {
        Object job = null;
        try
        {
            job = super.createJobInstance(bundle);
        }
        catch (Exception e)
        {
            LOG.error("创建bean错误", e);
        }

        beanFactory.autowireBean(job);

        return job;
    }

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle) throws SchedulerException {
        Job job = null;
        try
        {
            job = (Job) super.createJobInstance(triggerFiredBundle);
        }
        catch (Exception e)
        {
            LOG.error("创建bean错误", e);
        }

        beanFactory.autowireBean(job);

        return job;
    }
}
