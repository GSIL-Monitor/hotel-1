package com.travel.hotel.dlt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.travel.channel.entity.po.InterfaceLogPOWithBLOBs;
import com.travel.channel.service.InterfaceLogService;
import com.travel.common.constant.InitData;
import com.travel.common.utils.SpringContextUtil;
import com.travel.hotel.dlt.enums.DltInterfaceEnum;
import com.travel.hotel.dlt.request.base.DltRequestHeader;
import com.travel.hotel.dlt.request.base.Requestor;
import com.travel.hotel.dlt.request.dto.BatchPushRoomDataRequest;
import com.travel.hotel.dlt.request.dto.GetDltOrderInfoRequest;
import com.travel.hotel.dlt.request.dto.GetDltOrderNotifyRequest;
import com.travel.hotel.dlt.request.dto.OperaterDltOrderRequest;
import com.travel.hotel.dlt.request.dto.SetRoomSaleRuleRequest;
import com.travel.hotel.dlt.response.base.DltHttpResponse;
import com.travel.hotel.dlt.response.dto.BatchPushRoomDataResponse;
import com.travel.hotel.dlt.response.dto.GetDltOrderInfoResponse;
import com.travel.hotel.dlt.response.dto.GetDltOrderNotifyResponse;
import com.travel.hotel.dlt.response.dto.OperaterDltOrderResponse;
import com.travel.hotel.dlt.response.dto.SetRoomSaleRuleResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 代理通接口调用工具类
 *   2018/4/11.
 */
public class DltInterfaceInvoker {

    private static final Logger LOG = LoggerFactory.getLogger(DltInterfaceInvoker.class);

    /**
     * 检查商家渠道配置信息是否完整
     * @param merchantCode
     * @return
     */
    private static boolean checkChannelConfig(String merchantCode) {
        boolean flag = false;
        if(StringUtil.isValidString(merchantCode) && null!=InitData.channelConfigMap.get(merchantCode)
                && StringUtil.isValidString(InitData.channelConfigMap.get(merchantCode).get("url"))
                && StringUtil.isValidString(InitData.channelConfigMap.get(merchantCode).get("supplierId"))
                && StringUtil.isValidString(InitData.channelConfigMap.get(merchantCode).get("key"))) {
            flag = true;
        }
        return flag;
    }

    public static GetDltOrderInfoResponse invoke(GetDltOrderInfoRequest request,String merchantCode) {
        if(checkChannelConfig(merchantCode)) {
            request.setSupplierID(Integer.valueOf(InitData.channelConfigMap.get(merchantCode).get("supplierId")));
            request.setRequestor(new Requestor());
            String requestJson = JSON.toJSONString(request);
            DltHttpResponse dltHttpResponse = invoke(DltInterfaceEnum.GET_DLT_ORDER_INFO, requestJson,InitData.channelConfigMap.get(merchantCode).get("url"),
                    InitData.channelConfigMap.get(merchantCode).get("supplierId"),InitData.channelConfigMap.get(merchantCode).get("key"));
            if (null == dltHttpResponse || 200 != dltHttpResponse.getCode() || null == dltHttpResponse.getResponse()) {
                return null;
            }
            return JSON.parseObject(dltHttpResponse.getResponse(), new TypeReference<GetDltOrderInfoResponse>(){});
        }else {
            LOG.error("商家渠道配置信息错误");
            return null;
        }
    }

    public static GetDltOrderNotifyResponse invoke(GetDltOrderNotifyRequest request,String merchantCode) {
        if(checkChannelConfig(merchantCode)) {
            request.setSupplierID(Integer.valueOf(InitData.channelConfigMap.get(merchantCode).get("supplierId")));
            request.setRequestor(new Requestor());
            String requestJson = JSON.toJSONString(request);
            DltHttpResponse dltHttpResponse = invoke(DltInterfaceEnum.GET_DLT_ORDER_NOTIFY, requestJson,InitData.channelConfigMap.get(merchantCode).get("url"),
                    InitData.channelConfigMap.get(merchantCode).get("supplierId"),InitData.channelConfigMap.get(merchantCode).get("key"));
            if (null == dltHttpResponse || 200 != dltHttpResponse.getCode() || null == dltHttpResponse.getResponse()) {
                return null;
            }
            return JSON.parseObject(dltHttpResponse.getResponse(), new TypeReference<GetDltOrderNotifyResponse>(){});
        }else {
            LOG.error("商家渠道配置信息错误");
            return null;
        }

    }

    public static SetRoomSaleRuleResponse invoke(SetRoomSaleRuleRequest request, String merchantCode) {
        if(checkChannelConfig(merchantCode)) {
            request.setRequestor(new Requestor());
            request.setSupplierId(Integer.valueOf(InitData.channelConfigMap.get(merchantCode).get("supplierId")));
            String requestJson = JSON.toJSONString(request);
            DltHttpResponse dltHttpResponse = invoke(DltInterfaceEnum.SET_ROOM_SALE_RULE_DATA,requestJson, InitData.channelConfigMap.get(merchantCode).get("url"),
                    InitData.channelConfigMap.get(merchantCode).get("supplierId"),InitData.channelConfigMap.get(merchantCode).get("key"));
            if (null == dltHttpResponse || 200 != dltHttpResponse.getCode() || null == dltHttpResponse.getResponse()) {
                return null;
            }

            return JSON.parseObject(dltHttpResponse.getResponse(), new TypeReference<SetRoomSaleRuleResponse>(){});
        }else {
            LOG.error("商家渠道配置信息错误");
            return null;
        }
    }

    public static BatchPushRoomDataResponse invoke(BatchPushRoomDataRequest request,String merchantCode) {
        if(checkChannelConfig(merchantCode)) {
            request.setSupplierId(Integer.valueOf(InitData.channelConfigMap.get(merchantCode).get("supplierId")));
            String requestJson = JSON.toJSONString(request);
            DltHttpResponse dltHttpResponse = invoke(DltInterfaceEnum.BATCH_PUSH_ROOM_DATA,requestJson, InitData.channelConfigMap.get(merchantCode).get("url"),
                    InitData.channelConfigMap.get(merchantCode).get("supplierId"),InitData.channelConfigMap.get(merchantCode).get("key"));
            if (null == dltHttpResponse || 200 != dltHttpResponse.getCode() || null == dltHttpResponse.getResponse()) {
                return null;
            }

            return JSON.parseObject(dltHttpResponse.getResponse(), new TypeReference<BatchPushRoomDataResponse>(){});
        }else {
            LOG.error("商家渠道配置信息错误");
            return null;
        }
    }

    public static OperaterDltOrderResponse invoke(OperaterDltOrderRequest request,String merchantCode) {
        if(checkChannelConfig(merchantCode)) {
            request.setSupplierID(Integer.valueOf(InitData.channelConfigMap.get(merchantCode).get("supplierId")));
            request.setRequestor(new Requestor());
            String requestJson = JSON.toJSONString(request, SerializerFeature.WriteDateUseDateFormat);
            DltHttpResponse dltHttpResponse = invoke(DltInterfaceEnum.OPERATER_DLT_ORDER, requestJson,InitData.channelConfigMap.get(merchantCode).get("url"),
                    InitData.channelConfigMap.get(merchantCode).get("supplierId"),InitData.channelConfigMap.get(merchantCode).get("key"));
            if (null == dltHttpResponse || 200 != dltHttpResponse.getCode() || null == dltHttpResponse.getResponse()) {
                return null;
            }
            return JSON.parseObject(dltHttpResponse.getResponse(), new TypeReference<OperaterDltOrderResponse>(){});
        }else {
            LOG.error("商家渠道配置信息错误");
            return null;
        }
    }

    private static DltHttpResponse invoke(DltInterfaceEnum interfaceEnum, String requestJson,String url,String supplierId,String key) {

        if (null == interfaceEnum) {
            LOG.error("调用代理通接口传入的接口名不合法");
            return new DltHttpResponse(200, "调用代理通接口传入的接口名不合法", "");
        }

        if (StringUtils.isEmpty(supplierId) || StringUtils.isEmpty(key)  || StringUtils.isEmpty(url) || StringUtils.isEmpty(requestJson)) {
            LOG.error("调用代理通接口传入的参数不合法, interfaceEnum=" + interfaceEnum
                    + ", supplierId=" + supplierId + ", key=" + key  + ", url=" + url + ", requestJson=" + requestJson);
            return new DltHttpResponse(200, "调用代理通接口传入的参数不合法", "");
        }

        DltRequestHeader header = new DltRequestHeader();
        header.setUrl(url);
        header.setInterfaceEnum(interfaceEnum);
        header.setSupplierID(Integer.valueOf(supplierId));
        header.setInterfacekey(key);
        header.setTimestamp(String.valueOf(System.currentTimeMillis()));
        header.setSignature(SignatureUtil.buildSignature(Integer.valueOf(supplierId), key, header.getTimestamp()));
        return invoke(header, requestJson,url,supplierId,key);
    }

    private static DltHttpResponse invoke(DltRequestHeader header, String requestJson,String url,String supplierId,String key) {
        DltHttpResponse dltHttpResponse = new DltHttpResponse();

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            if (null == httpClient) {
                dltHttpResponse.setResponse("调用代理通接口，发起对" + header.getUrl() + "的请求失败，获取HttpClient为空");
                return dltHttpResponse;
            }

            // 拼接URL地址
            HttpPost post = new HttpPost(header.getUrl() + "/" + header.getInterfaceEnum().code);

            // 设置超时时间
            RequestConfig config = RequestConfig.custom().setConnectTimeout(15000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(10000).build();
            post.setConfig(config);

            // 构建消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("supplierID", String.valueOf(header.getSupplierID()));
            post.setHeader("interfacekey", header.getInterfacekey());
            post.setHeader("timestamp", header.getTimestamp());
            post.setHeader("signature", header.getSignature());

            // 构建消息体
            StringEntity stringEntity = new StringEntity(requestJson, Charset.forName("UTF-8"));
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");

            post.setEntity(stringEntity);

            CloseableHttpResponse httpResponse = httpClient.execute(post);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            dltHttpResponse.setCode(statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.error("调用代理通接口，请求连接失败,状态码statusCode=" + statusCode);
                dltHttpResponse.setMsg("调用代理通接口，请求连接失败,状态码statusCode=" + statusCode);
                return dltHttpResponse;
            }

            try {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    dltHttpResponse.setResponse(EntityUtils.toString(entity, "UTF-8"));
                    return dltHttpResponse;
                }
            } catch (Exception e) {
                dltHttpResponse.setMsg("转换返回结果失败");
                LOG.error("http请求异常", e);
            } finally {
                close(httpResponse);
            }
        } catch (Exception e) {
            LOG.error("调用代理通接口出现异常", e);
            dltHttpResponse.setCode(-1);
            dltHttpResponse.setMsg("调用代理通接口出现异常");
        } finally {
            saveInterfaceLog(header.getInterfaceEnum(), requestJson, dltHttpResponse.getResponse());
        }

        return dltHttpResponse;
    }

    private static void close(Closeable closeableObject) {
        if (closeableObject == null) {
            LOG.error("可关闭对象为空，closeableObject = null");
            return;
        }

        try {
            closeableObject.close();
        } catch (IOException e) {
            LOG.error("关闭发生异常", e);
        }
    }

    /**
     * 保存日志
     * @param interfaceEnum DltInterfaceEnum
     * @param requestJson String
     * @param response String
     */
    private static void saveInterfaceLog(DltInterfaceEnum interfaceEnum, String requestJson, String response) {
        try {
            InterfaceLogPOWithBLOBs ifLog = new InterfaceLogPOWithBLOBs();
            ifLog.setChannelCode("dlt");
            ifLog.setInterfaceName(interfaceEnum.code);
            ifLog.setRequest(requestJson);
            ifLog.setResponse(response);
            ifLog.setCreateDate(new Date());

            JSONObject jo = JSON.parseObject(response);
            if (null != jo && null != jo.get("resultStatus")) {
                JSONObject jo1 = JSON.parseObject(jo.get("resultStatus").toString());
                if (null != jo1 && null != jo1.get("resultCode")) {
                    ifLog.setReutrnCode(Integer.valueOf(jo1.get("resultCode").toString()));
                    ifLog.setReturnMsg(null != jo1.get("resultMsg") ? jo1.get("resultMsg").toString() : "");
                }
            }
            InterfaceLogService interfaceLogService = (InterfaceLogService) SpringContextUtil.getBean("interfaceLogService");
            interfaceLogService.saveInterfaceLog(ifLog);
        } catch (Exception e) {
            LOG.error("报错接口日志失败", e);
        }
    }
}
