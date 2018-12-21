package com.travel.hotel;

import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;

/**
 * 绑定jdbc/cfgDS数据源到jndi，供测试时使用
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-09-05
 */
class BindJndi
{
    /**
     * jndi名称
     */
    private static final String JNDI_NAME_MYSQL_SAAS= "jdbc/hmmp2";


    BindJndi() throws NamingException, ClassNotFoundException
    {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind(JNDI_NAME_MYSQL_SAAS, ConfigDataSource.getMySqlSAASDataSource());
        builder.activate();
    }
}
