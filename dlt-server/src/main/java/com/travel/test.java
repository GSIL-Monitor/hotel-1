package com.travel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vinney on 2018/7/19.
 */
public class test {

    public static void main(String args[]){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        long time = 1531924680000L;
        Date date = new Date(time);
        System.out.println(simpleDateFormat.format(date));

        long time2 = 1531924650000L;
        Date date2 = new Date(time2);
        System.out.println(simpleDateFormat.format(date2));


        long time3 = 1518099825000L;
        Date date3 = new Date(time3);
        System.out.println(simpleDateFormat.format(date3));




    }
}
