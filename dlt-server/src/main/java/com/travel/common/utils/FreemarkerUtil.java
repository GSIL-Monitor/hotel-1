package com.travel.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.travel.common.exception.ServiceException;

import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * @Description : 模板配置工具类
 * @author : Zhiping Sun
 * @date : 2018年3月23日下午4:14:23
 */
public class FreemarkerUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
	
	/**
	 * 获取文件路径获取模板配置
 	 * @param templatePath
	 * @return
	 * @throws IOException
	 */
	public static Template getTemplateByDirectory(String templatePath, String templateName) throws IOException {
		Configuration config = new Configuration(Configuration.VERSION_2_3_23);
		config.setDirectoryForTemplateLoading(new File(templatePath));
		config.setEncoding(Locale.CHINA, "utf-8");
		return config.getTemplate(templateName);
	}
	
	/**
	 * 根据上下文获取模板配置
 	 * @param templatePath
	 * @return
	 * @throws IOException
	 */
	public static Template getTemplateByServletContext(String templatePath, String templateName) throws IOException {
		ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		Configuration config = new Configuration(Configuration.VERSION_2_3_23);
		config.setServletContextForTemplateLoading(servletContext, templatePath);
		config.setEncoding(Locale.CHINA, "utf-8");
		return config.getTemplate(templateName);
	}
	
	/**
	 * 根据上下文模板获取获取html
	 * @param templatePath
	 * @param templateName
	 * @param data
	 * @return
	 */
	public static String getHtmlByServletContext(String templatePath, String templateName, Map<String,Object> data) {
		String html = "";
		try {
			Template tpl = getTemplateByServletContext(templatePath, templateName);
			tpl.setOutputEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			tpl.process(data, writer);
			writer.flush();
			html = writer.toString();
		} catch (TemplateNotFoundException e) {
			logger.error("找不到模板",e);
			throw new ServiceException("获取模板异常");
		} catch (MalformedTemplateNameException e) {
			logger.error("获取模板异常",e);
			throw new ServiceException("获取模板异常");
		} catch (IOException e) {
			logger.error("读取模板IO异常",e);
			throw new ServiceException("获取模板异常");
		} catch (TemplateException e) {
			logger.error("模板数据渲染异常",e);
			throw new ServiceException("获取模板异常");
		}
		return html;
	}
	
	/**
	 * 根据路径模板获取html
	 * @param templatePath
	 * @param templateName
	 * @param data
	 * @return
	 */
	public static String getHtmlByDirectory(String templatePath, String templateName, Map<String,Object> data) {
		String html = "";
		try {
			Template tpl = getTemplateByDirectory(templatePath, templateName);
			tpl.setOutputEncoding("UTF-8");
			StringWriter writer = new StringWriter();
			tpl.process(data, writer);
			writer.flush();
			html = writer.toString();
		} catch (TemplateNotFoundException e) {
			logger.error("找不到模板",e);
			throw new ServiceException("获取模板异常");
		} catch (MalformedTemplateNameException e) {
			logger.error("获取模板异常",e);
			throw new ServiceException("获取模板异常");
		} catch (IOException e) {
			logger.error("读取模板IO异常",e);
			throw new ServiceException("获取模板异常");
		} catch (TemplateException e) {
			logger.error("模板数据渲染异常",e);
			throw new ServiceException("获取模板异常");
		}
		return html;
	}
	
}
