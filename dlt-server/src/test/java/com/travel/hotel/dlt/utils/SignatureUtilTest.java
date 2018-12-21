package com.travel.hotel.dlt.utils;

/**
 *   2018/4/11.
 */
public class SignatureUtilTest {

    public static void main(String[] args) {
        Integer supplierId = 16551;
        String key = "209903b0-5f92-4759-9ea9-8723124f8c9e";
        System.out.println(System.currentTimeMillis());
        System.out.println(SignatureUtil.buildSignature(supplierId, key, String.valueOf(System.currentTimeMillis())));
    }

}
