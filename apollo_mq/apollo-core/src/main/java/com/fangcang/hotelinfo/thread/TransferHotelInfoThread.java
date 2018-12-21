package com.fangcang.hotelinfo.thread;

import com.alibaba.fastjson.JSON;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.HttpClientUtil;
import com.fangcang.hotelinfo.dto.TransferHotelInfoResult;
import com.fangcang.hotelinfo.saas.dto.HotelSaasDTO;
import com.fangcang.hotelinfo.service.HotelInfoService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ASUS on 2018/6/26.
 */
@Slf4j
public class TransferHotelInfoThread implements Callable<TransferHotelInfoResult> {

    private CountDownLatch countDownLatch;

    private String hotelDetailURL;

    private HotelSaasDTO hotelSaasDTO;

    private HotelInfoService hotelInfoService;

    public TransferHotelInfoThread(CountDownLatch countDownLatch, String hotelDetailURL,
                                   HotelSaasDTO hotelSaasDTO,HotelInfoService hotelInfoService) {
        this.countDownLatch = countDownLatch;
        this.hotelDetailURL = hotelDetailURL;
        this.hotelSaasDTO = hotelSaasDTO;
        this.hotelInfoService = hotelInfoService;
    }

    @Override
    public TransferHotelInfoResult call() throws Exception {
        TransferHotelInfoResult transferHotelInfoResult = new TransferHotelInfoResult();
        transferHotelInfoResult.setOldHotelId(hotelSaasDTO.getHotelId());
        transferHotelInfoResult.setResult(ResultCodeEnum.FAILURE.code);
        //查询酒店详情
        try {
            String result = HttpClientUtil.doGet(hotelDetailURL + hotelSaasDTO.getHotelId());
            ResponseDTO responseDTO = JSON.parseObject(result,ResponseDTO.class);
            if(null == responseDTO || ResultCodeEnum.FAILURE.code == responseDTO.getResult()){
                log.error("Query hotel detail info response has empty.");
                return transferHotelInfoResult;
            }
            HotelSaasDTO hotelDetailDTO = JSON.parseObject(JSON.toJSONString(responseDTO.getModel()),HotelSaasDTO.class);
            if(null == hotelDetailDTO){
                log.error("Query hotel detail info dto has empty.");
                return  transferHotelInfoResult;
            }
            transferHotelInfoResult =  hotelInfoService.saveTransferResult(hotelDetailDTO);
        } catch (IOException e) {
            log.error("Query hotel detail info has error,",e);
        }finally {
            countDownLatch.countDown();
        }
        return transferHotelInfoResult;
    }
}
