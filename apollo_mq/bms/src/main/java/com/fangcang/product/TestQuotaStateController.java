package com.fangcang.product;

import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.product.dto.QuotaStateDTO;
import com.fangcang.product.dto.QuotaStateDailyDTO;
import com.fangcang.product.request.BatchSaveQuotaStateRequestDTO;
import com.fangcang.product.request.DebuctOrRetreatQuotaRequestDTO;
import com.fangcang.product.request.QuotaStateQueryDTO;
import com.fangcang.product.response.DebuctOrRetreatQuotaResponseDTO;
import com.fangcang.product.response.QuotaStateResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@RestController
@RequestMapping(value = "/test/product")
public class TestQuotaStateController {

    @RequestMapping(value = "/queryQuotaStateDailyInfo",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<QuotaStateResponseDTO> queryQuotaStateDailyInfo(@RequestBody @Valid QuotaStateQueryDTO quotaStateQueryDTO){
        List<QuotaStateDailyDTO> quotaStateDailyDTOList = new ArrayList<>();
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);//减少一个月
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        Date firstDay = cal_1.getTime();
        List<Date> dateList = DateUtil.getDateList(DateUtil.dateFormat(firstDay,DateUtil.defaultFormat), DateUtil.getDate(firstDay, 30, 0));
        for (Date date : dateList) {
            QuotaStateDailyDTO quotaStateDailyDTO = new QuotaStateDailyDTO();
            quotaStateDailyDTO.setAllQuotaNum(100);
            quotaStateDailyDTO.setOverDraft(1);
            quotaStateDailyDTO.setQuotaNum(50);
            quotaStateDailyDTO.setQuotaState(1);
            quotaStateDailyDTO.setSaleDate(DateUtil.dateFormat(date,DateUtil.defaultFormat));
            quotaStateDailyDTOList.add(quotaStateDailyDTO);
        }
        QuotaStateResponseDTO quotaStateResponseDTO = new QuotaStateResponseDTO();
        quotaStateResponseDTO.setQuotaAccountId(quotaStateQueryDTO.getQuotaAccountId());
        quotaStateResponseDTO.setQuotaAccountName("大床房配额池丨每天50间");
        quotaStateResponseDTO.setQuotaStateDailyList(quotaStateDailyDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(quotaStateResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/batchModifyQuotaState",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO batchModifyQuotaState(@RequestBody BatchSaveQuotaStateRequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/debuctOrRetreatQuota",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<DebuctOrRetreatQuotaResponseDTO> debuctOrRetreatQuota(@RequestBody @Valid DebuctOrRetreatQuotaRequestDTO debuctOrRetreatQuotaRequestDTO){
        DebuctOrRetreatQuotaResponseDTO debuctOrRetreatQuotaResponseDTO = new DebuctOrRetreatQuotaResponseDTO();
        List<QuotaStateDTO> quotaStateDTOList = new ArrayList<>();
        List<QuotaStateDailyDTO> quotaStateDailyList = debuctOrRetreatQuotaRequestDTO.getQuotaStateDailyList();
        for (QuotaStateDailyDTO quotaStateDailyDTO : quotaStateDailyList) {
            QuotaStateDTO quotaStateDTO = new QuotaStateDTO();
            quotaStateDTO.setQuotaAccountId(debuctOrRetreatQuotaRequestDTO.getQuotaAccountId());
            quotaStateDTO.setSaleDate(quotaStateDailyDTO.getSaleDate());
            quotaStateDTO.setQuotaNum(quotaStateDailyDTO.getQuotaNum());
            quotaStateDTOList.add(quotaStateDTO);
        }
        debuctOrRetreatQuotaResponseDTO.setQuotaStateList(quotaStateDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(debuctOrRetreatQuotaResponseDTO);
        return responseDTO;
    }
}
