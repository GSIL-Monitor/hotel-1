package com.fangcang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wd on 2016/6/28.
 */
@Slf4j
public class HttpStringResponse implements ResponseHandler<String> {

    @Override
    public String handleResponse(HttpResponse response) throws IOException {

        StatusLine statusLine = response.getStatusLine();

        log.info("请求status:{}", statusLine);
        log.info("请求statusCode:{}", statusLine.getStatusCode());

        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            log.info("请求被重定向，无法接受任何数据！");
            return null;
        }
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }
        return EntityUtils.toString(entity);
    }
}
