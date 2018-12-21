package com.fangcang.common.util;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * Created by wd on 2016/6/28.
 */
public class HttpClientUtil {

    private static CloseableHttpClient client;

    /**
     * 获取http连接
     *
     * @return
     */
    public static CloseableHttpClient getCloseableHttpClient() {
        if (client != null) {
            return client;
        }
        HttpClientBuilder builder = HttpClients.custom()
                .setConnectionManager(getPoolingHttpClientConnectionManager())
                .setRetryHandler(getHttpRequestRetryHandler())
                .setDefaultRequestConfig(getRequestConfig());

        client = builder.build();
        return client;
    }

    /**
     * 获取请求超时等配置
     *
     * @return
     */
    public static RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000) // 连接服务器端超时
//                .setConnectionRequestTimeout(5000) // 从连接池获取连接超时
                .setSocketTimeout(30 * 1000) // 从服务器端获取数据超时
                .build();
        return requestConfig;
    }

    /**
     * 默认连接管理器
     *
     * @return
     */
    private static PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到200
        cm.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(20);

        return cm;
    }

    /**
     * 获取默认重试机制
     *
     * @return
     */
    private static HttpRequestRetryHandler getHttpRequestRetryHandler() {
        return new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 1) {// 如果已经重试了1次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 提交json数据
     *
     * @param url
     * @param requestJson
     * @return
     * @throws IOException
     */
    public static String postJson(String url, String requestJson) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(requestJson, ContentType.APPLICATION_JSON));
        return getCloseableHttpClient().execute(post, new HttpStringResponse());
    }

    public static String doGet(String url) throws IOException{
        HttpGet get = new HttpGet(url);
        return getCloseableHttpClient().execute(get, new HttpStringResponse());
    }

}
