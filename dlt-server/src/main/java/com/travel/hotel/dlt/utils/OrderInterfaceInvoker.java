package com.travel.hotel.dlt.utils;

import com.travel.common.constant.InitData;
import com.travel.common.utils.SpringContextUtil;
import com.travel.hotel.dlt.enums.ResultCodeEnum;
import com.travel.hotel.dlt.response.ResponseDTO;
import com.travel.hotel.dlt.response.dto.OrderDetailResponseDTO;
import com.travel.hotel.product.entity.po.DictionaryPO;
import com.travel.hotel.product.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 *  m_ 2018/6/18.
 */
public class OrderInterfaceInvoker {

    private static volatile String createOrderUrl;

    private static volatile String queryOrderUrl;

    private static volatile String addOrderRequestUrl;

    private static volatile String changeOrderStatusUrl;

    private static volatile String cancelOrderUrl;

    private static final Logger LOG = LoggerFactory.getLogger(OrderInterfaceInvoker.class);

    private static void initInterfaceInfo() {
        if (StringUtils.isEmpty(createOrderUrl) || StringUtils.isEmpty(queryOrderUrl)
                || StringUtils.isEmpty(addOrderRequestUrl) || StringUtils.isEmpty(changeOrderStatusUrl)
                || StringUtils.isEmpty(cancelOrderUrl)) {
            synchronized (OrderInterfaceInvoker.class) {
                if (StringUtils.isEmpty(createOrderUrl) || StringUtils.isEmpty(queryOrderUrl) || StringUtils.isEmpty(addOrderRequestUrl) || StringUtils.isEmpty(changeOrderStatusUrl)
                        || StringUtils.isEmpty(cancelOrderUrl)) {
                    if (CollectionUtils.isEmpty(InitData.allList)) {
                        DictionaryService dictionaryService = (DictionaryService) SpringContextUtil.getBean("dictionaryService");
                        InitData.allList = dictionaryService.queryAll();
                    }
                    for(DictionaryPO dictionaryPO : InitData.allList) {
                        if(dictionaryPO.getDataCode().equals("create_order_url")) {
                            createOrderUrl = dictionaryPO.getDataValue();
                        }else if(dictionaryPO.getDataCode().equals("add_order_request_url")) {
                            addOrderRequestUrl = dictionaryPO.getDataValue();
                        }else if(dictionaryPO.getDataCode().equals("query_order_url")) {
                            queryOrderUrl = dictionaryPO.getDataValue();
                        }else if(dictionaryPO.getDataCode().equals("change_order_status_url")) {
                            changeOrderStatusUrl = dictionaryPO.getDataValue();
                        }else if(dictionaryPO.getDataCode().equals("cancel_order_url")) {
                            cancelOrderUrl = dictionaryPO.getDataValue();
                        }
                    }
                }
            }
        }
    }

    public static ResponseDTO addManualOrder(String requestJson) {
        initInterfaceInfo();
        if(StringUtil.isValidString(createOrderUrl)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            LOG.info("url:"+createOrderUrl+";requestJson:"+requestJson);
            return restTemplate.postForObject(createOrderUrl, entity, ResponseDTO.class);
        }else {
            return new ResponseDTO(ResultCodeEnum.FAILURE.code, "ORDER_INTERFACE_ISNULL","订单接口未配置");
        }
    }

    public static ResponseDTO<OrderDetailResponseDTO> queryOrder(String requestJson) {
        initInterfaceInfo();
        if(StringUtil.isValidString(queryOrderUrl)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            LOG.info("url:"+queryOrderUrl+";requestJson:"+requestJson);
            return restTemplate.postForObject(queryOrderUrl,entity,ResponseDTO.class);
        }else {
            return new ResponseDTO(ResultCodeEnum.FAILURE.code, "ORDER_INTERFACE_ISNULL","订单接口未配置");
        }
    }

    public static ResponseDTO addOrderRequest(String requestJson) {
        initInterfaceInfo();
        if(StringUtil.isValidString(addOrderRequestUrl)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            LOG.info("url:"+addOrderRequestUrl+";requestJson:"+requestJson);
            return restTemplate.postForObject(addOrderRequestUrl,entity,ResponseDTO.class);
        }else {
            return new ResponseDTO(ResultCodeEnum.FAILURE.code, "ORDER_INTERFACE_ISNULL","订单接口未配置");
        }
    }

    public static ResponseDTO changeOrderStatus(String requestJson) {
        initInterfaceInfo();
        if(StringUtil.isValidString(changeOrderStatusUrl)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            LOG.info("更新渠道订单状态url:"+changeOrderStatusUrl+";requestJson:"+requestJson);
            return restTemplate.postForObject(changeOrderStatusUrl,entity,ResponseDTO.class);
        }else {
            return new ResponseDTO(ResultCodeEnum.FAILURE.code, "ORDER_INTERFACE_ISNULL","订单接口未配置");
        }
    }

    public static ResponseDTO cancelOrder(String requestJson) {
        initInterfaceInfo();
        if(StringUtil.isValidString(cancelOrderUrl)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            LOG.info("取消订单url:"+cancelOrderUrl+";requestJson:"+requestJson);
            return restTemplate.postForObject(cancelOrderUrl,entity,ResponseDTO.class);
        }else {
            return new ResponseDTO(ResultCodeEnum.FAILURE.code, "ORDER_INTERFACE_ISNULL","订单接口未配置");
        }
    }
}
