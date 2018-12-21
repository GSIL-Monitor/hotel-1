package com.fangcang.supplier;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.controller.BaseController;
import com.fangcang.common.enums.BalanceMethodEnum;
import com.fangcang.common.enums.CurrencyEnum;
import com.fangcang.common.enums.CustomerLevelEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.supplier.dto.*;
import com.fangcang.supplier.request.*;
import com.fangcang.supplier.response.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 15:58
 * @Description: 测试供应商相关接口
 */
@RestController
@RequestMapping("/test/supply")
public class TestSupplierController extends BaseController {

    /**
     * 保存供应商（添加/编辑时使用）
     *
     * @param addSupplyRequestDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddSupplyResponseDTO> saveSupply(@RequestBody @Valid AddSupplyRequestDTO addSupplyRequestDTO) {

        AddSupplyResponseDTO addSupplyResponseDTO = new AddSupplyResponseDTO();
        addSupplyResponseDTO.setSupplyId(100L);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(addSupplyResponseDTO);
        return responseDTO;
    }

    /**
     * 查询供应商列表
     *
     * @param supplyListQueryRequestDTO
     * @return
     */
    @RequestMapping(value = "/getListForPage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<SupplyInfoDTO>> getListForPage(@RequestBody SupplyListQueryRequestDTO supplyListQueryRequestDTO) {
        List<SupplyInfoDTO> list = new ArrayList();
        for (int j = 1000; j < 1005; j++) {
//            SupplyListResponseDTO supplyListResponseDTO = new SupplyListResponseDTO();
            SupplyInfoDTO supplyInfoDTO = new SupplyInfoDTO();
            supplyInfoDTO.setSupplyId(Long.valueOf(j));
            supplyInfoDTO.setSupplyName("中国航空" + j);
            supplyInfoDTO.setSupplyCode("TT" + j);
            supplyInfoDTO.setRealName("测试" + j);
            supplyInfoDTO.setPhone("1999999" + j);
            supplyInfoDTO.setMerchantBMName("业务经理" + j);
            supplyInfoDTO.setFrequentlyUse(1);
            supplyInfoDTO.setIsActive(1);
            supplyInfoDTO.setCustomerLevel(CustomerLevelEnum.GOLD.key);
//            supplyListResponseDTO.setSupplyInfoDTO(supplyInfoDTO);
            list.add(supplyInfoDTO);
        }
        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(5);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setCurrentPage(supplyListQueryRequestDTO.getCurrentPage());
        paginationSupportDTO.setPageSize(supplyListQueryRequestDTO.getPageSize());
        paginationSupportDTO.setItemList(list);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;


    }

    /**
     * 查询单个供应商详情
     *
     * @param singleUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/getSupplyById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SingleSupplyInfoResponseDTO> getSupplyById(@RequestBody SingleUserRequestDTO singleUserRequestDTO) {

        SingleSupplyInfoResponseDTO singleSupplyInfoResponseDTO = new SingleSupplyInfoResponseDTO();

        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<ContractFileDTO> contractFileDTOS = new ArrayList<>();
        List<SupplyVisitInfoDTO> supplyVisitInfoDTOS = new ArrayList<>();
        List<SupplyBankCardDTO> supplyBankCardDTOS = new ArrayList<>();


        AccountDTO accountDTO = new AccountDTO();
        AccountDTO accountDTO2 = new AccountDTO();
        ContractFileDTO contractFileDTO = new ContractFileDTO();
        ContractFileDTO contractFileDTO2 = new ContractFileDTO();
        SupplyVisitInfoDTO supplyVisitInfoDTO = new SupplyVisitInfoDTO();
        SupplyVisitInfoDTO supplyVisitInfoDTO2 = new SupplyVisitInfoDTO();
        SupplyBankCardDTO supplyBankCardDTO = new SupplyBankCardDTO();
        SupplyBankCardDTO supplyBankCardDTO2 = new SupplyBankCardDTO();

        //用户列表
        accountDTO.setSupplyUserId(1000L);
        accountDTO.setRealName("Test01");
        accountDTO.setPassword("110");
        accountDTO.setUserName("TestTest01");
        accountDTO.setIsActive(1);
        accountDTO2.setSupplyUserId(1001L);
        accountDTO2.setRealName("Test02");
        accountDTO2.setPassword("110");
        accountDTO2.setUserName("TestTest02");
        accountDTO2.setIsActive(1);
        accountDTOS.add(accountDTO);
        accountDTOS.add(accountDTO2);

        //合同列表
        contractFileDTO.setDescription("2017年合同");
        contractFileDTO.setCreator("Test01");
        contractFileDTO.setFileName("2017年合约.doc");
//        contractFileDTO.setCreateTime("2018/5/8/15:59:18");
//        contractFileDTO.setFileURL("~/download/files/2017年合约.doc");
        contractFileDTO2.setDescription("2018年合同");
        contractFileDTO2.setCreator("Test02");
        contractFileDTO2.setFileName("2018年合约.doc");
//        contractFileDTO2.setCreateTime("2018/5/8/15:59:18");
//        contractFileDTO2.setFileURL("~/download/files/2018年合约.doc");
        contractFileDTOS.add(contractFileDTO);
        contractFileDTOS.add(contractFileDTO2);

        //拜访记录列表
        supplyVisitInfoDTO.setVisitor("Test02");
        supplyVisitInfoDTO.setVisitTime(new Date());
        supplyVisitInfoDTO.setContent("主要老板谈论了关于续约的问题，老板希望能就价格作进一步协商");
        supplyVisitInfoDTO.setCreateTime(new Date());
        supplyVisitInfoDTO.setCreator("韩梅梅");
        supplyVisitInfoDTO2.setVisitor("Test04");
        supplyVisitInfoDTO2.setVisitTime(new Date());
        supplyVisitInfoDTO2.setContent("222222主要老板谈论了关于续约的问题，老板希望能就价格作进一步协商");
        supplyVisitInfoDTO2.setCreateTime(new Date());
        supplyVisitInfoDTO2.setCreator("韩梅梅2");
        supplyVisitInfoDTOS.add(supplyVisitInfoDTO);
        supplyVisitInfoDTOS.add(supplyVisitInfoDTO2);

        //添加银行卡信息
        supplyBankCardDTO.setAccountName("Test01");
        supplyBankCardDTO.setAccountNumber("111123456789");
        supplyBankCardDTO.setBankCardId(1111);
        supplyBankCardDTO.setOpeningBank("招商银行");
        supplyBankCardDTO2.setAccountName("Test02");
        supplyBankCardDTO2.setAccountNumber("222223456789");
        supplyBankCardDTO2.setBankCardId(2222);
        supplyBankCardDTO2.setOpeningBank("建设银行");
        supplyBankCardDTOS.add(supplyBankCardDTO);
        supplyBankCardDTOS.add(supplyBankCardDTO2);


        singleSupplyInfoResponseDTO.setDeputyPhone("13111111111");
        singleSupplyInfoResponseDTO.setFinancePhone("13111111111");
        singleSupplyInfoResponseDTO.setFrontPhone("13111111111");
        singleSupplyInfoResponseDTO.setMainPhone("13111111111");
        singleSupplyInfoResponseDTO.setBillingMethod(BalanceMethodEnum.MONTH.key);
        singleSupplyInfoResponseDTO.setAddress("湖北武汉洪山区");
        singleSupplyInfoResponseDTO.setBaseCurrency(CurrencyEnum.CNY.value);
        singleSupplyInfoResponseDTO.setCustomerLevel(CustomerLevelEnum.GOLD.key);
        singleSupplyInfoResponseDTO.setSupplyId(1000L);
        singleSupplyInfoResponseDTO.setCityCode("鄂A");
        singleSupplyInfoResponseDTO.setCityName("Wuhan");
        singleSupplyInfoResponseDTO.setSupplyCode("1000");
        singleSupplyInfoResponseDTO.setSupplyName("CNAir");
        singleSupplyInfoResponseDTO.setMerchantBM(111L);
        singleSupplyInfoResponseDTO.setMerchantBMName("产品经理");
        singleSupplyInfoResponseDTO.setMerchantBM(222L);
        singleSupplyInfoResponseDTO.setMerchantBMName("业务经理");
        singleSupplyInfoResponseDTO.setMerchantFinancer(444L);
        singleSupplyInfoResponseDTO.setMerchantFinancerName("财务员");
        singleSupplyInfoResponseDTO.setUserList(accountDTOS);
        singleSupplyInfoResponseDTO.setContractFileList(contractFileDTOS);
        singleSupplyInfoResponseDTO.setVisitList(supplyVisitInfoDTOS);
        singleSupplyInfoResponseDTO.setBankCardList(supplyBankCardDTOS);


        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(singleSupplyInfoResponseDTO);
        return responseDTO;


    }

    /**
     * 设置常用供应商
     *
     * @param supplyInfoDTO
     * @return
     */
    @RequestMapping(value = "/setFrequentlyUse", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setFrequentlyUse(@RequestBody SupplyInfoDTO supplyInfoDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        return responseDTO;
    }


    /**
     * 设置供应商是否停用
     *
     * @param supplyInfoDTO
     * @return
     */
    @RequestMapping(value = "/setActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setActive(@RequestBody SupplyInfoDTO supplyInfoDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        return responseDTO;
    }

    /**
     * 查询单个用户
     *
     * @param singleUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SingleUserResponseDTO> getUserInfo(@RequestBody SingleUserRequestDTO singleUserRequestDTO) {

        SingleUserResponseDTO singleUserResponseDTO = new SingleUserResponseDTO();

        singleUserResponseDTO.setSupplyId(singleUserRequestDTO.getSupplyId());
        singleUserResponseDTO.setUserId(singleUserRequestDTO.getUserId());
        singleUserResponseDTO.setUserName("Test01");
        singleUserResponseDTO.setPassword("120");
        singleUserResponseDTO.setRealName("Test02");

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(singleUserResponseDTO);
        return responseDTO;
    }

    /**
     * 保存用户（编辑使用）
     *
     * @param singleUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveUser(@RequestBody SingleUserRequestDTO singleUserRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        return responseDTO;
    }

    /**
     * 上传合同文件
     *
     * @param uploadContractRequestDTO
     * @return
     */
    @RequestMapping(value = "/uploadContract")
    public ResponseDTO<UploadContractRsponseDTO> uploadContract(UploadContractRequestDTO uploadContractRequestDTO) {

        UploadContractRsponseDTO uploadContractRsponseDTO = new UploadContractRsponseDTO();

        uploadContractRsponseDTO.setContractFileId(10001L);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(uploadContractRsponseDTO);
        return responseDTO;
    }

    /**
     * 添加拜访记录
     *
     * @param addVisitRequestDTO
     * @return
     */
    @RequestMapping(value = "/addVisit", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddVisitResponseDTO> addVisit(@RequestBody AddVisitRequestDTO addVisitRequestDTO) {

        AddVisitResponseDTO addVisitResponseDTO = new AddVisitResponseDTO();
        addVisitResponseDTO.setSupplyVisitId(100L);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(addVisitResponseDTO);
        return responseDTO;
    }

    /**
     * 常用供应商列表
     * 入参的商家列表从Session里面取
     *
     * @return
     */
    @RequestMapping(value = "/queryCommonSupplyList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<FrequentSupplyResponseDTO> queryCommonSupplyList() {

        FrequentSupplyResponseDTO frequentSupplyResponseDTO = new FrequentSupplyResponseDTO();

        List<FrequentSupplyDTO> frequentSupplyDTOS = new ArrayList<>();

        for (int i = 1000; i < 1005; i++) {
            FrequentSupplyDTO frequentSupplyDTO = new FrequentSupplyDTO();
            frequentSupplyDTO.setSupplyCode("1000" + i);
            frequentSupplyDTO.setSupplyName("CNAir" + i);
            frequentSupplyDTO.setCurrency(CurrencyEnum.CNY.value);
            frequentSupplyDTOS.add(frequentSupplyDTO);
        }
        frequentSupplyResponseDTO.setSupplierList(frequentSupplyDTOS);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(frequentSupplyResponseDTO);
        return responseDTO;
    }


    /**
     * 添加供应商银行卡信息
     *
     * @param supplyBankCardDTO
     * @return
     */
    @RequestMapping(value = "/saveBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddSupplyBankCardResponseDTO> saveBankCard(@RequestBody SupplyBankCardDTO supplyBankCardDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        AddSupplyBankCardResponseDTO addSupplyBankCardResponseDTO = new AddSupplyBankCardResponseDTO();
        addSupplyBankCardResponseDTO.setBankCardId(123L);
        responseDTO.setModel(addSupplyBankCardResponseDTO);
        return responseDTO;
    }


    /**
     * 获取供应商用户操作日志
     * @param getSupplyUserLogRequestDTO
     * @return
     */
    @RequestMapping(value = "/getUserLog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<GetSupplyUserLogReponseDTO> getUserLog(@RequestBody GetSupplyUserLogRequestDTO getSupplyUserLogRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<SupplyUserLogDTO> supplyUserLogDTOlist = new ArrayList<>();
        for (int i = 1000; i < 1005; i++) {
            SupplyUserLogDTO supplyUserLogDTO = new SupplyUserLogDTO();
            supplyUserLogDTO.setCreator(this.getCacheUser().getUsername());
            supplyUserLogDTO.setCreateTime(DateUtil.getCurrentDate());
            supplyUserLogDTO.setContent("重置了密码" + i);
            supplyUserLogDTOlist.add(supplyUserLogDTO);
        }
        GetSupplyUserLogReponseDTO getSupplyUserLogReponseDTO = new GetSupplyUserLogReponseDTO();
        getSupplyUserLogReponseDTO.setOperateList(supplyUserLogDTOlist);
        responseDTO.setModel(getSupplyUserLogReponseDTO);
        return responseDTO;
    }

    /**
     * 获取供应商拜访记录列表
     * @param getSupplyVisitListQueryDTO
     * @return
     */
    @RequestMapping(value = "/getVisitList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<GetSupplyVisitListResponseDTO> getVisitList(@RequestBody GetSupplyVisitListQueryDTO getSupplyVisitListQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        GetSupplyVisitListResponseDTO getVisitListResponseDTO = new GetSupplyVisitListResponseDTO();
        List<SupplyVisitInfoDTO> supplyVisitInfoDTOList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            SupplyVisitInfoDTO supplyVisitInfoDTO = new SupplyVisitInfoDTO();
            supplyVisitInfoDTO.setVisitor("张三" + i);
            supplyVisitInfoDTO.setVisitTime(new Date());
            supplyVisitInfoDTO.setContent("今天拜访你了,,,,");
            supplyVisitInfoDTO.setSupplyName("xxx旅行社" + i);
            supplyVisitInfoDTO.setSupplyCode("F10000"+i);
            supplyVisitInfoDTO.setCreator("121231321(张三)");
            supplyVisitInfoDTO.setCreateTime(new Date());
            supplyVisitInfoDTOList.add(supplyVisitInfoDTO);
        }
        getVisitListResponseDTO.setVisitList(supplyVisitInfoDTOList);
        responseDTO.setModel(getVisitListResponseDTO);
        return responseDTO;
    }



}



