/**
 * Copyright (c) 2006-2015 Fangcang Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Fangcang. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Fangcang,http://www.fangcang.com.
 *  
 */
package com.travel.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author liuyunquan
 * @date 2017年12月5日 上午11:03:23
 * @version
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String doPost(String url, String data, String contentType, int connectTimeout, int socketTimeout) {
	CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
		httpPost.setConfig(config);
		HttpEntity entity = new StringEntity(data, "utf-8");
		httpPost.setEntity(entity);
		return sendRequest(httpClient, httpPost, contentType);
    }

    private static String sendRequest(CloseableHttpClient httpClient, HttpUriRequest request, String contentType) {
	String content = null;
	CloseableHttpResponse response = null;
	try {
	    request.setHeader("Content-Type", contentType);
	    response = httpClient.execute(request);
	    HttpEntity entity = response.getEntity();
	    content = EntityUtils.toString(entity, "utf-8");
	} catch (Exception e) {
	    logger.error("http request error.uri:" + request.getURI(), e);
	} finally {
	    try {
		if (response != null)
		    response.close();
	    } catch (Exception e) {
		logger.error("http error", e);
	    }
	    try {
		if (httpClient != null)
		    httpClient.close();
	    } catch (Exception e) {
		logger.error("http error", e);
	    }
	}
	return content;
    }

}
