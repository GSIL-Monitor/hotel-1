package com.fangcang.merchant.controller;

import com.fangcang.agent.response.BankCardListResponseDTO;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.merchant.dto.BankCardDTO;
import com.fangcang.merchant.dto.RoleDTO;
import com.fangcang.merchant.request.*;
import com.fangcang.merchant.response.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/23.
 */
@RestController
@RequestMapping(value = "/test/user")
public class TestUserController {

    @RequestMapping(value = "/saveStaff",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<SaveStaffResponseDTO> saveStaff(@RequestBody @Valid SaveStaffRequestDTO saveStaffRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        if(null == saveStaffRequestDTO.getUserId()){
            //新增
            SaveStaffResponseDTO saveStaffResponseDTO = new SaveStaffResponseDTO();
            saveStaffResponseDTO.setUserId(1000L);
            responseDTO.setModel(saveStaffResponseDTO);
        }else {
            //修改
        }
        return responseDTO;
    }

    @RequestMapping(value = "/queryStaffList",method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<PaginationSupportDTO<StaffListResponseDTO>> queryStaffList(@RequestBody StaffListQueryDTO staffQueryDTO){
        List<StaffListResponseDTO> staffListResponseDTOList = new ArrayList<>();
        for(int i=0;i<5;i++){
            StaffListResponseDTO staffListResponseDTO = new StaffListResponseDTO();
            staffListResponseDTO.setUserId(Long.valueOf(1000+ i));
            staffListResponseDTO.setRealName("张三" + i);
            staffListResponseDTO.setUserName("zhangsan" + i);
            if(i % 2==0 && i != 0){
                staffListResponseDTO.setIsActive(0);
            }else{
                staffListResponseDTO.setIsActive(1);
            }
            if(i == 0){
                staffListResponseDTO.setIsCurrentAccount(1);
            }
            List<RoleDTO> roleDTOList = new ArrayList<>();
            for(int j = 0;j<3;j++){
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setRoleId(Long.valueOf(50000 + i + j));
                roleDTO.setRoleName("产品员" + i + j);
                roleDTOList.add(roleDTO);
            }
            staffListResponseDTO.setRoleList(roleDTOList);
            staffListResponseDTOList.add(staffListResponseDTO);
        }

        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setItemList(staffListResponseDTOList);
        paginationSupportDTO.setPageSize(5);
        paginationSupportDTO.setCurrentPage(1);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setTotalCount(5);
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/getUserById",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<EditStaffInfoResponseDTO> getUserById(@RequestBody @Valid EditStaffInfoQueryDTO queryDTO){
        EditStaffInfoResponseDTO editStaffInfoResponseDTO = new EditStaffInfoResponseDTO();
        editStaffInfoResponseDTO.setUserId(Long.valueOf(1000));
        editStaffInfoResponseDTO.setRealName("张三");
        editStaffInfoResponseDTO.setUserName("zhangsan");
        editStaffInfoResponseDTO.setPassword("asdashdsssaadsxdassa");
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for(int j = 0;j<3;j++){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(Long.valueOf(50000 + j));
            roleDTO.setRoleName("产品员" + j);
            roleDTOList.add(roleDTO);
        }
        editStaffInfoResponseDTO.setRoleList(roleDTOList);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(editStaffInfoResponseDTO);
        return  responseDTO;
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO changePassword(@RequestBody @Valid ChangePasswordRequestDTO changePasswordRequestDTO){
        ResponseDTO response = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return response;
    }

    @RequestMapping(value = "/setActive",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO setActive(@RequestBody @Valid SetActiveRequestDTO setActiveRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/addBankCard",method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<AddBankCardResponseDTO> addBankCard(@RequestBody @Valid AddBankCardRequestDTO addBankCardRequestDTO){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        AddBankCardResponseDTO addBankCardResponseDTO = new AddBankCardResponseDTO();
        addBankCardResponseDTO.setBankCardId(100000L);
        responseDTO.setModel(addBankCardResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/bankCardList",produces = { "application/json;charset=UTF-8" })
    public ResponseDTO<BankCardListResponseDTO> bankCardList(){
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<BankCardDTO> bankCardDTOList = new ArrayList<>();
        for(int i= 0;i<10;i++){
            BankCardDTO bankCardDTO = new BankCardDTO();
            bankCardDTO.setBankCardId(Long.valueOf(1000 + i));
            bankCardDTO.setOpeningBank("中国工商银行南山支行" + i);
            bankCardDTO.setAccountName("张三" + i);
            bankCardDTO.setAccountNumber("123434307879" + i);
            bankCardDTOList.add(bankCardDTO);
        }
        BankCardListResponseDTO bankCardListResponseDTO = new BankCardListResponseDTO();
        bankCardListResponseDTO.setBankCardList(bankCardDTOList);
        responseDTO.setModel(bankCardListResponseDTO);
        return responseDTO;
    }

}
