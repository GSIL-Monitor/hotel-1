package com.travel.hotel.dlt.utils;

import org.junit.Test;

/**
 *   2018/4/11.
 */
public class DltInterfaceInvokerTest {

    @Test
    public void invoke() throws Exception {

        String url = "http://m.fat.ctripqa.com/restapi/soa2/13353/";
        String supplierId = "16551";
        String key = "209903b0-5f92-4759-9ea9-8723124f8c9e";
        String requestJson = "{\n" +
                "  \"requestor\": {\n" +
                "    \"invoker\": \"ZS\",\n" +
                "    \"operatorName\": \"ZS\",\n" +
                "    \"opClientIP\": \"127.0.0.1\",\n" +
                "    \"userId\": 1,\n" +
                "    \"languageType\": \"CN\"\n" +
                "  },\n" +
                "  \"supplierID\": 16551\n" +
                "}\n";

//        DltHttpResponse dltHttpResponse = DltInterfaceInvoker.invoke(DltInterfaceEnum.GET_DLT_COUNTRY_LIST, requestJson);
//        System.out.println(dltHttpResponse);
    }

}