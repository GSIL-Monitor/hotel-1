package com.travel.hotel;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * testcase基类，基于Junit4
 *
 * @author
 * @description 一句话描述该文件的用途
 * @date 2018-05-14
 */
public class BaseTest
{

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 待加载的测试的配置文件根
     */
    private static final String CONFIG_PATH = "spring-test-dlt.xml";

    protected ApplicationContext context;


    @Before
    public void setUp() throws Exception
    {
        new BindJndi();

        context = new ClassPathXmlApplicationContext(CONFIG_PATH);

    }


    @After
    public void tearDown() throws Exception
    {

    }

}