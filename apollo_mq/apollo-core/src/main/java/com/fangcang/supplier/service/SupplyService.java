package com.fangcang.supplier.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.supplier.domain.SupplyUserBindDO;
import com.fangcang.supplier.dto.FrequentSupplyDTO;
import com.fangcang.supplier.dto.SupplyBankCardDTO;
import com.fangcang.supplier.dto.SupplyConfirmTypeDTO;
import com.fangcang.supplier.dto.SupplyDTO;
import com.fangcang.supplier.dto.SupplyInfoDTO;
import com.fangcang.supplier.dto.SupplyOtherCostDTO;
import com.fangcang.supplier.dto.SupplyVisitInfoDTO;
import com.fangcang.supplier.request.AddSupplyContractFileRequestDTO;
import com.fangcang.supplier.request.AddSupplyCostRequestDTO;
import com.fangcang.supplier.request.AddSupplyRequestDTO;
import com.fangcang.supplier.request.AddVisitRequestDTO;
import com.fangcang.supplier.request.CommonSupplyListResquestDTO;
import com.fangcang.supplier.request.DeductSupplierCreditLineRequestDTO;
import com.fangcang.supplier.request.DeductSupplyCashDTO;
import com.fangcang.supplier.request.DeleteConfirmTypeRequestDTO;
import com.fangcang.supplier.request.DeleteSupplyContractFileRequestDTO;
import com.fangcang.supplier.request.GetSupplyOperLogRequestDTO;
import com.fangcang.supplier.request.GetSupplyVisitListQueryDTO;
import com.fangcang.supplier.request.QueryConfirmTypeDTO;
import com.fangcang.supplier.request.QueryOtherCostRequestDTO;
import com.fangcang.supplier.request.QuerySupplyCashItemDTO;
import com.fangcang.supplier.request.QuerySupplyDepositItemDTO;
import com.fangcang.supplier.request.QuerySupplyOrderDeductCreditDTO;
import com.fangcang.supplier.request.QuerySupplyUserBindDTO;
import com.fangcang.supplier.request.SaveConfirmTypeRequestDTO;
import com.fangcang.supplier.request.SetCooperationRequestDTO;
import com.fangcang.supplier.request.SetSupplyUserActiveRequestDTO;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.fangcang.supplier.request.SupplyCashRechargeDTO;
import com.fangcang.supplier.request.SupplyDepositRechargeDTO;
import com.fangcang.supplier.request.SupplyListQueryRequestDTO;
import com.fangcang.supplier.response.AddSupplyBankCardResponseDTO;
import com.fangcang.supplier.response.AddSupplyContractFileResponseDTO;
import com.fangcang.supplier.response.AddSupplyResponseDTO;
import com.fangcang.supplier.response.AddVisitResponseDTO;
import com.fangcang.supplier.response.GetSupplyOperLogResponseDTO;
import com.fangcang.supplier.response.SingleSupplyInfoResponseDTO;
import com.fangcang.supplier.response.SingleUserResponseDTO;
import com.fangcang.supplier.response.SupplyCashItemDTO;
import com.fangcang.supplier.response.SupplyDepositItemDTO;
import com.fangcang.supplier.response.SupplyOrderDeductCreditDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/28 15:59
 * @Description: 供应商Service接口
 */
public interface SupplyService {
    /**
     * 保存供应商
     *
     * @param addSupplyRequestDTO
     * @return
     */
    public ResponseDTO<AddSupplyResponseDTO> saveSupply(AddSupplyRequestDTO addSupplyRequestDTO);

    /**
     * 查询供应商列表
     *
     * @param supplyListQueryRequestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<SupplyInfoDTO>> getListForPage(SupplyListQueryRequestDTO supplyListQueryRequestDTO);

    /**
     * 根据条件查询所有供应商列表
     *
     * @return
     */
    public ResponseDTO<List<SupplyDTO>> queryAllSupplyList(SupplyListQueryRequestDTO supplyListQueryRequestDTO);

    /**
     * 设置常用供应商1-常用，0-不常用
     *
     * @param supplyInfoDTO
     * @return
     */
    public ResponseDTO setFrequentlyUse(SupplyInfoDTO supplyInfoDTO);

    /**
     * 设置供应商是否停用
     * 1-启用；0-停用
     *
     * @param supplyInfoDTO
     * @return
     */
    public ResponseDTO setActive(SupplyInfoDTO supplyInfoDTO);

    /**
     * 保存银行卡信息
     *
     * @param supplyBankCardDTO
     * @return
     */
    public ResponseDTO<AddSupplyBankCardResponseDTO> saveBankCard(SupplyBankCardDTO supplyBankCardDTO);


    /**
     * 查询常用供应商列表
     *
     * @param commonSupplyListResquestDTO
     * @return
     */
    public ResponseDTO<PaginationSupportDTO<FrequentSupplyDTO>> queryCommonSupplyList(CommonSupplyListResquestDTO commonSupplyListResquestDTO);

    /**
     * 添加拜访记录
     *
     * @param addVisitRequestDTO
     * @return
     */
    public ResponseDTO<AddVisitResponseDTO> addVisit(AddVisitRequestDTO addVisitRequestDTO);

    /**
     * 保存单个供应商用户
     *
     * @param singleUserRequestDTO
     * @return
     */
    public ResponseDTO saveUser(SingleUserRequestDTO singleUserRequestDTO);

    /**
     * 查询单个供应商详情
     *
     * @param singleUserRequestDTO
     * @return
     */
    public ResponseDTO<SingleSupplyInfoResponseDTO> getSupplyById(SingleUserRequestDTO singleUserRequestDTO);


    /**
     * 设置供应商用户是否启用
     *
     * @param setSupplyUserActiveRequestDTO
     * @return
     */
    public ResponseDTO setUserActive(@RequestBody SetSupplyUserActiveRequestDTO setSupplyUserActiveRequestDTO);

    /**
     * 上传合同文件
     *
     * @param addSupplyContractFileRequestDTO
     * @return
     */
    public ResponseDTO<AddSupplyContractFileResponseDTO> uploadContractForSupply(MultipartFile file,AddSupplyContractFileRequestDTO addSupplyContractFileRequestDTO);

    /**
     * 获取供应商拜访记录列表
     *
     * @param getSupplyVisitListQueryDTO
     * @return
     */
    public ResponseDTO<SupplyVisitInfoDTO> getVisitList(GetSupplyVisitListQueryDTO getSupplyVisitListQueryDTO);

    /**
     * 查询用户日志列表
     *
     * @return
     */
    public ResponseDTO<GetSupplyOperLogResponseDTO> querySupplyOperLogList(GetSupplyOperLogRequestDTO getSupplyOperLogRequestDTO);

    /**
     * 下载合同文件
     * @param realPath
     * @return
     */
    public ResponseEntity<byte[]> getFile(String realPath,Long contractFileId);


    /**
     * 根据bankCardId删除银行卡信息
     * @param bankCardId
     * @return
     */
    ResponseDTO deleteBankCardById(Long bankCardId);

    /**
     * 查询单个供应商用户信息
     * @param singleUserRequestDTO
     * @return
     */
    ResponseDTO<SingleUserResponseDTO> getUserInfo(SingleUserRequestDTO singleUserRequestDTO);

    /**
     * 删除合同文件接口
     * @param deleteSupplyContractFileRequestDTO
     * @return
     */
	public ResponseDTO deleteContract(DeleteSupplyContractFileRequestDTO deleteSupplyContractFileRequestDTO);

    /**
     * 查询微信绑定用户信息
     */
    public List<SupplyUserBindDO> querySupplyUserBind(QuerySupplyUserBindDTO requestDTO);

    /**
     * 保存微信绑定用户信息
     */
    public ResponseDTO saveSupplyUserBind(SupplyUserBindDO supplyUserBindDO);

    /**
     * 扣除供应商额度
     */
    public ResponseDTO deductSupplierCreditLine(DeductSupplierCreditLineRequestDTO requestDTO);


    List<SupplyBankCardDTO> querySupplyBankCardList(@NotNull(message = "供应商编码不能为空")@NotNull String supplyCode);


    /**
     * 查询订单已挂账金额
     */
    SupplyOrderDeductCreditDTO querySupplyOrderDeductCredit(QuerySupplyOrderDeductCreditDTO requestDTO);

    /**
     * 删除确认方式
     * @param deleteConfirmTypeRequestDTO
     * @return
     */
    ResponseDTO deleteConfirmType(DeleteConfirmTypeRequestDTO deleteConfirmTypeRequestDTO);

    /**
     * 添加更新确认方式
     * @param saveConfirmTypeRequestDTO
     * @return
     */
    ResponseDTO saveConfirmType(SaveConfirmTypeRequestDTO saveConfirmTypeRequestDTO);

    /**
     * 查询当前可用的确认方式
     * @param queryConfirmTypeDTO
     * @return
     */
    List<SupplyConfirmTypeDTO> queryCurrentConfirmType(QueryConfirmTypeDTO queryConfirmTypeDTO);

    /**
     * 设置合作预警
     * @param setCooperationRequestDTO
     * @return
     */
    ResponseDTO setCooperation(SetCooperationRequestDTO setCooperationRequestDTO);

    /**
     * 保存其他成本：新增或者更新
     * @param addSupplyCostRequestDTO
     * @return
     */
    ResponseDTO saveOtherCost(AddSupplyCostRequestDTO addSupplyCostRequestDTO);

    /**
     * 供应商现金充值
     */
    public ResponseDTO supplyCashRecharge(SupplyCashRechargeDTO requestDTO);

    /**
     * 扣除供应商现金余额
     */
    public ResponseDTO deductSupplyCash(DeductSupplyCashDTO requestDTO);

    /**
     * 供应商押金充值
     */
    public ResponseDTO supplyDepositRecharge(SupplyDepositRechargeDTO requestDTO);

    /**
     * 查询供应商现金账户明细
     */
    public PaginationSupportDTO<SupplyCashItemDTO> querySupplyCashItem(QuerySupplyCashItemDTO requestDTO);

    /**
     * 查询供应商押金账户明细
     */
    public PaginationSupportDTO<SupplyDepositItemDTO> querySupplyDepositItem(QuerySupplyDepositItemDTO requestDTO);

    /**
     * 分页查询其他成本
     * @param queryOtherCostRequestDTO
     * @return
     */
    PaginationSupportDTO<SupplyOtherCostDTO> queryOtherCostForPage(QueryOtherCostRequestDTO queryOtherCostRequestDTO);

    /**
     *  查询单个成本
     * @param queryOtherCostRequestDTO
     * @return
     */
    ResponseDTO<SupplyOtherCostDTO> queryOtherCostById(QueryOtherCostRequestDTO queryOtherCostRequestDTO);

    /**
     * 设置是否启用
     * @param supplyOtherCostDTO
     * @return
     */
    ResponseDTO setOtherCostIsActive(SupplyOtherCostDTO supplyOtherCostDTO);


    /**
     * 根据售卖日期，查询此日期的成本
     * @param date
     * @return
     */
    List<SupplyOtherCostDTO> queryOtherCost(Date date);

}
