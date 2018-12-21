package com.fangcang.agent;

import com.fangcang.agent.dto.*;
import com.fangcang.agent.request.*;
import com.fangcang.agent.response.*;
import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.CurrencyEnum;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.agent.request.ModifyAmountRequestDTO;
import com.fangcang.agent.request.UserAccountLogQueryDTO;
import com.fangcang.agent.response.UserAccountLogResponseDTO;
import com.fangcang.common.util.DateUtil;
import com.fangcang.supplier.dto.ContractFileDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 14:12
 * @Description: 测试分销商相关接口
 */
@RestController
@RequestMapping("/test/agent")
public class TestAgentController {

    public static void main(String[] args){
        System.out.print(DateUtil.stringToDate("2018-11-28").getTime());
    }

    public static void main1(String[] args){
        List list= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
        int size=list.size();
        int maxThreadCount=5;
        int threadCount=maxThreadCount;
        int evCount=0;
        int lastCount=0;
        if(size<maxThreadCount){
            threadCount=size;
            evCount=1;
        }else if(size%maxThreadCount==0){
            evCount=size/maxThreadCount;
        }else{
            evCount=size/(maxThreadCount-1);
            lastCount=size%(maxThreadCount-1);
            if (lastCount==0){
                threadCount--;
            }
        }
        for(int i=0;i<threadCount;i++) {
            List commodityIdList = null;
            if (i+1 == threadCount && lastCount > 0){
                commodityIdList = list.subList(i * evCount, i * evCount + lastCount);
            } else {
                commodityIdList = list.subList(i * evCount, (i + 1) * evCount);
            }
            System.out.println(commodityIdList);
        }
    }

    /**
     * 保存分销商（添加/编辑时使用）
     *
     * @param addAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddAgentResponseDTO> saveagent(@RequestBody @Valid AddAgentRequestDTO addAgentRequestDTO) {

        AddAgentResponseDTO addAgentResponseDTO = new AddAgentResponseDTO();
        addAgentResponseDTO.setAgentId(100L);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(addAgentResponseDTO);
        return responseDTO;
    }

    /**
     * 查询分销商列表
     *
     * @param agentListQueryRequestDTO
     * @return
     */
    @RequestMapping(value = "/getListForPage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<PaginationSupportDTO<AgentListResponseDTO>> getListForPage(@RequestBody AgentListQueryRequestDTO agentListQueryRequestDTO) {
        List<AgentListResponseDTO> list = new ArrayList();
        for (int j = 1000; j < 1005; j++) {
            AgentListResponseDTO agentListResponseDTO = new AgentListResponseDTO();
            agentListResponseDTO.setAgentId(Long.valueOf(j));
            agentListResponseDTO.setAgentName("畅游假期" + j);
            agentListResponseDTO.setAgentCode("F10000" + j);
            agentListResponseDTO.setRealName("张三" + j);
            agentListResponseDTO.setPhone("1999999" + j);
            agentListResponseDTO.setFrequentlyUse(1);
            agentListResponseDTO.setIsActive(1);
            agentListResponseDTO.setMerchantBM(Long.valueOf(1000 + j));
            agentListResponseDTO.setMerchantBMName("李思" + j);
            agentListResponseDTO.setAmount(new BigDecimal(200));
            agentListResponseDTO.setUsedAmount(new BigDecimal(100));
            agentListResponseDTO.setCustomerLevel(1);
            list.add(agentListResponseDTO);

        }
        PaginationSupportDTO paginationSupportDTO = new PaginationSupportDTO();
        paginationSupportDTO.setTotalCount(5);
        paginationSupportDTO.setTotalPage(1);
        paginationSupportDTO.setCurrentPage(agentListQueryRequestDTO.getCurrentPage());
        paginationSupportDTO.setPageSize(agentListQueryRequestDTO.getPageSize());
        paginationSupportDTO.setItemList(list);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(paginationSupportDTO);
        return responseDTO;

    }

    /**
     * 查询单个分销商详情
     *
     * @param singleAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/getAgentById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<SingleAgentResponseDTO> getAgentById(@RequestBody SingleAgentRequestDTO singleAgentRequestDTO) {

        SingleAgentResponseDTO singleAgentResponseDTO = new SingleAgentResponseDTO();

        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        List<ContractFileDTO> contractFileDTOS = new ArrayList<>();
        List<AgentVisitInfoDTO> agentVisitInfoDTOS = new ArrayList<>();
        List<AgentBankCardDTO> agentBankCardDTOS = new ArrayList<>();


        UserInfoDTO userInfoDTO = new UserInfoDTO();
        UserInfoDTO userInfoDTO2 = new UserInfoDTO();
        ContractFileDTO contractFileDTO = new ContractFileDTO();
        ContractFileDTO contractFileDTO2 = new ContractFileDTO();
        AgentVisitInfoDTO agentVisitInfoDTO = new AgentVisitInfoDTO();
        AgentVisitInfoDTO agentVisitInfoDTO2 = new AgentVisitInfoDTO();
        AgentBankCardDTO agentBankCardDTO = new AgentBankCardDTO();
        AgentBankCardDTO agentBankCardDTO2 = new AgentBankCardDTO();

        //用户列表
        userInfoDTO.setAgentUserId(1000L);
        userInfoDTO.setRealName("Test01");
        userInfoDTO.setPassword("110");
        userInfoDTO.setUserName("TestTest01");
        userInfoDTO.setIsActive(1);
        userInfoDTO2.setAgentUserId(2000L);
        userInfoDTO2.setRealName("Test02");
        userInfoDTO2.setPassword("110");
        userInfoDTO2.setUserName("TestTest02");
        userInfoDTO2.setIsActive(1);
        userInfoDTOS.add(userInfoDTO);
        userInfoDTOS.add(userInfoDTO2);

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
        agentVisitInfoDTO.setVisitor("Test02");
        agentVisitInfoDTO.setVisitTime(new Date());
        agentVisitInfoDTO.setContent("主要老板谈论了关于续约的问题，老板希望能就价格作进一步协商");
        agentVisitInfoDTO.setCreateTime(new Date());
        agentVisitInfoDTO.setCreator("韩梅梅");
        agentVisitInfoDTO2.setVisitor("222Test02");
        agentVisitInfoDTO2.setVisitTime(new Date());
        agentVisitInfoDTO2.setContent("22222主要老板谈论了关于续约的问题，老板希望能就价格作进一步协商");
        agentVisitInfoDTO2.setCreateTime(new Date());
        agentVisitInfoDTO2.setCreator("2222韩梅梅");
        agentVisitInfoDTOS.add(agentVisitInfoDTO);
        agentVisitInfoDTOS.add(agentVisitInfoDTO2);

        //添加银行卡信息
        agentBankCardDTO.setAccountName("Test01");
        agentBankCardDTO.setAccountNumber("111123456789");
        agentBankCardDTO.setBankCardId(1111);
        agentBankCardDTO.setOpeningBank("招商银行");
        agentBankCardDTO2.setAccountName("Test02");
        agentBankCardDTO2.setAccountNumber("222223456789");
        agentBankCardDTO2.setBankCardId(2222);
        agentBankCardDTO2.setOpeningBank("建设银行");
        agentBankCardDTOS.add(agentBankCardDTO);
        agentBankCardDTOS.add(agentBankCardDTO2);

        singleAgentResponseDTO.setBillingMethod(1);
        singleAgentResponseDTO.setCustomerLevel(1);
        singleAgentResponseDTO.setAgentId(1000L);
        singleAgentResponseDTO.setCityCode("SZX");
        singleAgentResponseDTO.setCityName("深圳");
        singleAgentResponseDTO.setAgentCode("F10000001");
        singleAgentResponseDTO.setAgentName("CNAir");
        singleAgentResponseDTO.setMerchantPM(100000L);
        singleAgentResponseDTO.setMerchantPMName("产品经理");
        singleAgentResponseDTO.setMerchantBM(2000L);
        singleAgentResponseDTO.setMerchantBMName("业务经理");
        singleAgentResponseDTO.setMerchantFinancer(40000L);
        singleAgentResponseDTO.setMerchantFinancerName("财务员");
        singleAgentResponseDTO.setUserList(userInfoDTOS);
        singleAgentResponseDTO.setContractFileList(contractFileDTOS);
        singleAgentResponseDTO.setVisitList(agentVisitInfoDTOS);
        singleAgentResponseDTO.setBankCardList(agentBankCardDTOS);

        singleAgentResponseDTO.setAmount(new BigDecimal(1000));
        singleAgentResponseDTO.setAddress("中国湖北省武汉市洪山区");
        singleAgentResponseDTO.setFinanceCurrency(CurrencyEnum.CNY.value);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(singleAgentResponseDTO);
        return responseDTO;


    }

    /**
     * 设置常用分销商
     *
     * @param singleAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/setFrequentlyUse", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setFrequentlyUse(@RequestBody SingleAgentRequestDTO singleAgentRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        return responseDTO;
    }

    /**
     * 设置分销商是否停用
     *
     * @param singleAgentRequestDTO
     * @return
     */
    @RequestMapping(value = "/setActive", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO setActive(@RequestBody SingleAgentRequestDTO singleAgentRequestDTO) {

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        return responseDTO;
    }


    /**
     * 查询单个用户
     *
     * @param agentUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AgentUserResponseDTO> getUserInfo(@RequestBody AgentUserRequestDTO agentUserRequestDTO) {

        AgentUserResponseDTO agentUserResponseDTO = new AgentUserResponseDTO();

        agentUserResponseDTO.setAgentId(agentUserRequestDTO.getAgentId());
        agentUserResponseDTO.setUserId(agentUserRequestDTO.getUserId());
        agentUserResponseDTO.setUserName("Test01");
        agentUserResponseDTO.setPassword("120");
        agentUserResponseDTO.setRealName("Test02");

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(agentUserResponseDTO);
        return responseDTO;
    }

    /**
     * 保存用户（编辑使用）
     *
     * @param agentUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO saveUser(@RequestBody AgentUserRequestDTO agentUserRequestDTO) {

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
    public ResponseDTO<AgentUploadContractResponseDTO> uploadContract(AgentUploadContractRequestDTO uploadContractRequestDTO) {

        AgentUploadContractResponseDTO uploadContractRsponseDTO = new AgentUploadContractResponseDTO();

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
    public ResponseDTO<AgentAddVisitResponseDTO> addVisit(@RequestBody AgentAddVisitRequestDTO addVisitRequestDTO) {

        AgentAddVisitResponseDTO addVisitResponseDTO = new AgentAddVisitResponseDTO();
        addVisitResponseDTO.setAgentVisitId(100L);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(addVisitResponseDTO);
        return responseDTO;
    }

    /**
     * 常用分销商列表
     * 入参的商家列表从Session里面取
     *
     * @return
     */
    @RequestMapping(value = "/queryCommonAgentList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<FrequentAgentResponseDTO> queryCommonAgentList() {

        FrequentAgentResponseDTO frequentAgentResponseDTO = new FrequentAgentResponseDTO();

        List<FrequentAgentDTO> frequentAgentDTOS = new ArrayList<>();

        for (int i = 1000; i < 1005; i++) {
            FrequentAgentDTO frequentAgentDTO = new FrequentAgentDTO();
            frequentAgentDTO.setAgentCode("1000" + i);
            frequentAgentDTO.setAgentName("CNAir" + i);
            frequentAgentDTO.setCurrency(CurrencyEnum.CNY.value);
            frequentAgentDTOS.add(frequentAgentDTO);
        }
        frequentAgentResponseDTO.setAgentList(frequentAgentDTOS);

        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(frequentAgentResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/saveBankCard", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<AddBankCardResponseDTO> saveBankCard(@RequestBody @Valid AddBankCardRequestDTO addBankCardRequestDTO) {
        AddBankCardResponseDTO addBankCardResponseDTO = new AddBankCardResponseDTO();
        addBankCardResponseDTO.setBankCardId(1000L);
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(addBankCardResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/getUserLog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<UserAccountLogResponseDTO> saveBankCard(@RequestBody @Valid UserAccountLogQueryDTO userAccountLogQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        UserAccountLogResponseDTO userAccountLogResponseDTO = new UserAccountLogResponseDTO();
        List<UserAccountLogDTO> userAccountLogList = new ArrayList<>();
        for(int i = 0 ;i < 10;i++){
            UserAccountLogDTO userAccountLogDTO = new UserAccountLogDTO();
            userAccountLogDTO.setContent("修改账户名称为xxx");
            userAccountLogDTO.setCreateTime(new Date());
            userAccountLogDTO.setCreator("123432(张三)");
            userAccountLogList.add(userAccountLogDTO);
        }
        userAccountLogResponseDTO.setUserAccountLogList(userAccountLogList);
        responseDTO.setModel(userAccountLogResponseDTO);
        return responseDTO;
    }

    @RequestMapping(value = "/modifyAmount", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO modifyAmount(@RequestBody @Valid ModifyAmountRequestDTO modifyAmountRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        return responseDTO;
    }

    @RequestMapping(value = "/getVisitList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseDTO<GetVisitListResponseDTO> getVisitList(@RequestBody GetVisitListQueryDTO getVisitListQueryDTO) {
        ResponseDTO responseDTO = new ResponseDTO(ResultCodeEnum.SUCCESS.code);

        GetVisitListResponseDTO getVisitListResponseDTO = new GetVisitListResponseDTO();
        List<AgentVisitInfoDTO> agentVisitInfoDTOList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            AgentVisitInfoDTO agentVisitInfoDTO = new AgentVisitInfoDTO();
            agentVisitInfoDTO.setVisitor("张三" + i);
            agentVisitInfoDTO.setVisitTime(new Date());
            agentVisitInfoDTO.setContent("今天拜访你了,,,,");
            agentVisitInfoDTO.setAgentName("xxx旅行社" + i);
            agentVisitInfoDTO.setAgentCode("F10000"+i);
            agentVisitInfoDTO.setCreator("121231321(张三)");
            agentVisitInfoDTO.setCreateTime(new Date());
            agentVisitInfoDTOList.add(agentVisitInfoDTO);
        }
        getVisitListResponseDTO.setVisitList(agentVisitInfoDTOList);
        responseDTO.setModel(getVisitListResponseDTO);
        return responseDTO;
    }
}
