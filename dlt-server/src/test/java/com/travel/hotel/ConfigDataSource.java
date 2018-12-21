package com.travel.hotel;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 获取数据源
 *
 * @author Xuhua
 * @description 一句话描述该文件的用途
 * @date 2018-01-03
 */
final class ConfigDataSource
{
    /**
     * 数据库驱动
     */
    private static final String MYSQL_DATABASE_DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /**
     * mysql . saas
     */
//    private static final String MYSQL_DATABASE_URL_SAAS = "jdbc:mysql://120.79.142.88:3306/saas?characterEncoding=utf-8&amp;allowMultiQueries=true&amp;autoReconnect=true";

    private static final String MYSQL_DATABASE_URL_SAAS = "jdbc:mysql://192.168.10.199:3306/apollo?characterEncoding=utf-8&amp;allowMultiQueries=true&amp;autoReconnect=true";

    /**
     * 数据库用户名
     */
//    private static final String MYSQL_DATABASE_URL_SAAS_USERNAME = "zhdb";
    private static final String MYSQL_DATABASE_URL_SAAS_USERNAME = "apollo";
    /**
     * 数据库密码
     */
//    private static final String MYSQL_DATABASE_URL_SAAS_PASSWORD = "Zhdb123!@#";
    private static final String MYSQL_DATABASE_URL_SAAS_PASSWORD = "apollo@#0713";
    private ConfigDataSource() throws NamingException, ClassNotFoundException
    {
    }

    static DataSource getMySqlSAASDataSource() throws ClassNotFoundException, NamingException
    {
        Class.forName(MYSQL_DATABASE_DRIVER_CLASS);
        return new DriverManagerDataSource(MYSQL_DATABASE_URL_SAAS, MYSQL_DATABASE_URL_SAAS_USERNAME,
                MYSQL_DATABASE_URL_SAAS_PASSWORD);
    }

}