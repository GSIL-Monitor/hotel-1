package com.travel.hotel.dlt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *   2018/4/11.
 */
public class JSONTest {

    private static String json = "{\n" +
            "\t\"ResponseStatus\": {\n" +
            "\t\t\"Timestamp\": \"/Date(1526280735200+0800)/\",\n" +
            "\t\t\"Ack\": \"Success\",\n" +
            "\t\t\"Errors\": [],\n" +
            "\t\t\"Build\": null,\n" +
            "\t\t\"Version\": \"v1\",\n" +
            "\t\t\"Extension\": [{\n" +
            "\t\t\t\"Id\": \"CLOGGING_TRACE_ID\",\n" +
            "\t\t\t\"Version\": null,\n" +
            "\t\t\t\"ContentType\": null,\n" +
            "\t\t\t\"Value\": \"4435641051208634130\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"Id\": \"RootMessageId\",\n" +
            "\t\t\t\"Version\": null,\n" +
            "\t\t\t\"ContentType\": null,\n" +
            "\t\t\t\"Value\": \"921812-0a022628-423966-113246\"\n" +
            "\t\t}]\n" +
            "\t},\n" +
            "\t\"resultStatus\": {\n" +
            "\t\t\"resultCode\": -1,\n" +
            "\t\t\"resultMsg\": \"房价未知渠道roomId=123123456,startDate=20180514 00:00:00,endDate=20180517 00:00:00,channel=EBK\"\n" +
            "\t}\n" +
            "}";

    public static void main(String[] args) {

//        JSONObject jo = JSON.parseObject(json);
//        System.out.println(jo.get("resultStatus"));
//        JSONObject jo1 = JSON.parseObject(jo.get("resultStatus").toString());
//        System.out.println(jo1.get("resultCode"));


        String updateTimeStr = "2018-05-15 10:57";
        Date updateTime = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            updateTime = df.parse(updateTimeStr);
            System.out.println(updateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
