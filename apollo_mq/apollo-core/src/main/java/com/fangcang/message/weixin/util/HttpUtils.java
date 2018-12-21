package com.fangcang.message.weixin.util;


import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * https 请求 微信为https的请求
 */
@Slf4j
public class HttpUtils {
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	
    /**
     * @return 返回类型:
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
	        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
	        builder.setBodyEncoding(DEFAULT_CHARSET);
	        if (params != null && !params.isEmpty()) {
	            Set<String> keys = params.keySet();
	            for (String key : keys) {
	                builder.addQueryParameter(key, params.get(key));
	            }
	        }
	
	        if (headers != null && !headers.isEmpty()) {
	            Set<String> keys = headers.keySet();
	            for (String key : keys) {
	                builder.addHeader(key, params.get(key));
	            }
	        }
	        Future<Response> f = builder.execute();
	        String body = f.get().getResponseBody(DEFAULT_CHARSET);
	        http.close();
	        return body;
        }catch(Exception e){
        	if(http != null){
        		try{
        			http.close();
        		}catch(Exception e2){
        			log.error("发生异常时关闭AsyncHttpClient又发生异常", e2);
        		}
        	}
        	throw e;
        }
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: get 请求
     */
    public static String get(String url) throws Exception {
        return get(url, null);
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnsupportedEncodingException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) throws Exception {
        return get(url, params, null);
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: POST 请求
     */
    public static String post(String url, Map<String, String> params) throws Exception {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
	        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
	        builder.setBodyEncoding(DEFAULT_CHARSET);
	        if (params != null && !params.isEmpty()) {
	            Set<String> keys = params.keySet();
	            for (String key : keys) {
	                builder.addParameter(key, params.get(key));
	            }
	        }
	        Future<Response> f = builder.execute();
	        String body = f.get().getResponseBody(DEFAULT_CHARSET);
	        http.close();
	        return body;
        }catch(Exception e){
        	if(http != null){
        		try{
        			http.close();
        		}catch(Exception e2){
        			log.error("发生异常时关闭AsyncHttpClient又发生异常", e2);
        		}
        	}
        	throw e;
        }
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: POST 请求
     */
    public static String post(String url, String s) throws Exception {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
	        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
	        builder.setBodyEncoding(DEFAULT_CHARSET);
	        builder.setBody(s);
	        Future<Response> f = builder.execute();
	        String body = f.get().getResponseBody(DEFAULT_CHARSET);
	        http.close();
	        return body;
        }catch(Exception e){
        	if(http != null){
        		try{
        			http.close();
        		}catch(Exception e2){
        			log.error("发生异常时关闭AsyncHttpClient又发生异常", e2);
        		}
        	}
        	throw e;
        }
    }
    
    /**
     * @return 返回类型:String
     * @throws UnsupportedEncodingException
     * @description 功能描述: 为URL加上参数
     */
    public static String getUrlWithParams(String url,Map<String, String> params) throws UnsupportedEncodingException{
    	StringBuilder newUrl = new StringBuilder(url).append("?");
    	for (Map.Entry<String, String> m : params.entrySet()) {
    		if(m.getValue() == null){
    			continue;
    		}
    		newUrl.append(m.getKey())
    			.append("=")
    			.append(URLEncoder.encode(m.getValue()==null?"":m.getValue(), "UTF-8"))
    			.append("&");
    	}
    	return newUrl.toString();
    }
    
    /**
     * 向微信上传媒体文件<br>
     * file、fileDataInputStream 为文件内容或文件输入流，二者必需且只能输入一个，以file优先<br>
     * 要么输入file<br>
     * 要么输入fileName + fileDataInputStream<br>
     * @param url
     * @param file
     * @param fileDataInputStream
     * @return String
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    public static String upload(String url, File file, String fileName, InputStream fileDataInputStream) throws Exception {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
	        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
	        builder.setBodyEncoding(DEFAULT_CHARSET);
	        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
	        builder.setHeader("connection", "Keep-Alive");
	        builder.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
	        builder.setHeader("Charsert", "UTF-8");
	        builder.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
	        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
	        builder.setBody(new UploadEntityWriter(end_data, file, fileName, fileDataInputStream));
	
	        Future<Response> f = builder.execute();
	        String body = f.get().getResponseBody(DEFAULT_CHARSET);
	        http.close();
	        return body;
        }catch(Exception e){
        	if(http != null){
        		try{
        			http.close();
        		}catch(Exception e2){
        			log.error("发生异常时关闭AsyncHttpClient又发生异常", e2);
        		}
        	}
        	throw e;
        }
    }
    
    /**
     * 获取网络文件输入流
     * @param url
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws IOException
     */
    public static BufferedInputStream getNetFileInputStream(String url) throws Exception {
        AsyncHttpClient http = new AsyncHttpClient();
        try{
	        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
	        builder.setBodyEncoding(DEFAULT_CHARSET);
	        Future<Response> f = builder.execute();
	        
	        BufferedInputStream bis = new BufferedInputStream(f.get().getResponseBodyAsStream());
	        http.close();
	        return bis;
        }catch(Exception e){
        	if(http != null){
        		try{
        			http.close();
        		}catch(Exception e2){
        			log.error("发生异常时关闭AsyncHttpClient又发生异常", e2);
        		}
        	}
        	throw e;
        }
    }
    
}