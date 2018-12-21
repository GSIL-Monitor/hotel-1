package com.travel.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;

/**
 * JAXB工具类
 *
 * @author 903889
 *
 * @param <T>
 */
public class JAXBUtil<T extends Object> {

    private static Logger logger = Logger.getLogger(JAXBUtil.class);

    private final static String ENCODING = "UTF-8";

    private static HashMap<String, JAXBContext> JAXBContextMap = new HashMap<String, JAXBContext>();

    /**
     * 描 述: XML to POJO
     *
     * @param clazz
     * @param xml
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public static <T> T jaxbXMLToBean(Class<T> clazz, String xml) {

        if (StringUtils.isEmpty(xml)) {
            return null;
        }
        Unmarshaller um = null;
        ByteArrayInputStream ins = null;
        T result = null;
        try {
            JAXBContext context = getJAXBContext(clazz);
            um = context.createUnmarshaller();
            ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            result = (T) um.unmarshal(ins);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @Title: convertStreamToString
     * @Description: 流转换为字符串
     * @param @param is
     * @param @return
     * @return String    返回类型
     * @throws
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            if (reader != null) {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     *
     * @Title: jaxbBeanToStream
     * @Description: Bean转换为XML格式流
     * @param @param <T>
     * @param @param t
     * @param @param outs
     * @return void    返回类型
     * @throws
     */
    public static <T> void jaxbBeanToStream(T t, OutputStream outs) {

        if (t == null) {
            return;
        }
        try {
            String xmlString = jaxbBeanToXml(t);
            outs.write(xmlString.getBytes(ENCODING));

        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                if (outs != null) {
                    outs.flush();
                    outs.close();
                }

            } catch (Throwable ex) {
            }
        }
    }

    /**
     * @param <T>
     * @param t 待转换为XML格式的对象
     * @return
     */
    public static <T> String jaxbBeanToXml(T t) {

        if (null == t) {
            return null;
        }
        Marshaller marshaller = null;
        String xmlString = null;
        ByteArrayOutputStream outs = null;
        try {
            outs = new ByteArrayOutputStream();
            JAXBContext context = getJAXBContext(t.getClass());
            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, ENCODING);// UTF-8
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(t, outs);
            xmlString = new String(outs.toByteArray(), ENCODING);
            xmlString = removeFirstLine(xmlString);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (outs != null) {
                    outs.flush();
                    outs.close();
                }
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
        }
        return xmlString;
    }

    /**
     * 简单创建实例,缓存
     *
     * @param clazz
     * @return
     * @throws JAXBException
     */
    public static JAXBContext getJAXBContext(Class<?> clazz)
            throws JAXBException {

        JAXBContext context = JAXBContextMap.get(clazz.getName());
        if (context == null) {

            context = JAXBContext.newInstance(clazz);
            JAXBContextMap.put(clazz.getName(), context);
        }

        return context;
    }

    private static String removeFirstLine(String s) {
        if (null == s) {
            return null;
        }

        return s.substring(s.indexOf("\n") + 1);
    }
}
