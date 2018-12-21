package com.fangcang.common.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TemplateFileUtil {

    public interface FileType{
        String email="email";
    }

    //模板缓存集合
    private static Map<String,Template> TEMPLATE_MAP = new HashMap();
    //配置类 通过Configuration 对Freemaker 进行初始化工作
    private static Configuration cfg = new Configuration();

    static{
        cfg.setTemplateLoader(new ClassTemplateLoader(TemplateFileUtil.class, "/config/"));
    }

    private static Template getTemplate(String templateFile){
        Template template = TemplateFileUtil.TEMPLATE_MAP.get(templateFile);
        if(template == null){
            try {
                template = cfg.getTemplate("template/"+templateFile+".ftl");
                TemplateFileUtil.TEMPLATE_MAP.put(templateFile, template);
            } catch (IOException e) {
                log.error("模板获取异常", e);
            }
        }
        return template;
    }

    public static String getTemplateContent(String templateFile,Map<String, Object> data) throws TemplateException, IOException{
        if(templateFile == null){
            return null;
        }
        Template template = TemplateFileUtil.getTemplate(templateFile);
        template.setEncoding("UTF-8");
        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
