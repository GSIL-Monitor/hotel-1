package com.fangcang.finance.bill.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.bill.domain.SupplyBillCurrencyDO;
import com.fangcang.finance.bill.domain.SupplyBillDO;
import com.fangcang.finance.bill.domain.SupplyBillOrderDO;
import com.fangcang.finance.bill.domain.SupplyBillOrderItemDO;
import com.fangcang.finance.bill.mapper.SupplyBillCurrencyMapper;
import com.fangcang.finance.bill.mapper.SupplyBillImportMapper;
import com.fangcang.finance.bill.mapper.SupplyBillMapper;
import com.fangcang.finance.bill.mapper.SupplyBillOrderItemMapper;
import com.fangcang.finance.bill.mapper.SupplyBillOrderMapper;
import com.fangcang.finance.bill.mapper.SupplyCreditOrderMapper;
import com.fangcang.finance.bill.request.AddBillOrderDTO;
import com.fangcang.finance.bill.request.BillIdDTO;
import com.fangcang.finance.bill.request.BillOrderIdDTO;
import com.fangcang.finance.bill.request.CreateBillDTO;
import com.fangcang.finance.bill.request.CreateBillFinanceOrderDTO;
import com.fangcang.finance.bill.request.DeleteBillOrderDTO;
import com.fangcang.finance.bill.request.InsertBillOrderDTO;
import com.fangcang.finance.bill.request.InsertBillOrderItemDTO;
import com.fangcang.finance.bill.request.QueryBillCurrencyDTO;
import com.fangcang.finance.bill.request.QueryBillDTO;
import com.fangcang.finance.bill.request.QueryBillOrderDTO;
import com.fangcang.finance.bill.request.UpdateBillNameDTO;
import com.fangcang.finance.bill.request.UpdateBillOrderFinanceDTO;
import com.fangcang.finance.bill.response.BillAutoMatchResultDTO;
import com.fangcang.finance.bill.response.BillDTO;
import com.fangcang.finance.bill.response.BillFinanceOrderDTO;
import com.fangcang.finance.bill.response.BillOrderDTO;
import com.fangcang.finance.bill.response.BillOrderItemDTO;
import com.fangcang.finance.bill.response.BillOrderItemExportDTO;
import com.fangcang.finance.bill.service.SupplyBillService;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.enums.AccountStatusEnum;
import com.fangcang.finance.enums.BillStatusEnum;
import com.fangcang.finance.enums.FinanceStatusEnum;
import com.fangcang.finance.enums.FinanceTypeEnum;
import com.fangcang.finance.enums.PassageEnum;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.LockOrderItemRequestDTO;
import com.fangcang.finance.financelock.service.FinanceLockService;
import com.fangcang.finance.financeorder.request.ConfirmPayRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderItemDTO;
import com.fangcang.finance.financeorder.request.QuerySupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.SupplyFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QuerySupplyFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.VoucherResponseDTO;
import com.fangcang.finance.financeorder.service.SupplyFinanceOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SupplyBillServiceImpl implements SupplyBillService {

    @Autowired
    private SupplyBillMapper supplyBillMapper;

    @Autowired
    private SupplyBillCurrencyMapper supplyBillCurrencyMapper;

    @Autowired
    private SupplyBillOrderMapper supplyBillOrderMapper;

    @Autowired
    private SupplyBillOrderItemMapper supplyBillOrderItemMapper;

    @Autowired
    private SupplyBillImportMapper supplyBillImportMapper;

    @Autowired
    private FinanceLockService financeLockService;

    @Autowired
    private SupplyFinanceOrderService supplyFinanceOrderService;

    @Autowired
    private SupplyCreditOrderMapper supplyCreditOrderMapper;

    @Override
    @Transactional
    public ResponseDTO createSupplyBill(@Valid CreateBillDTO requestDTO) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        //创建账单
        SupplyBillDO supplyBillDO=new SupplyBillDO();
        BeanUtils.copyProperties(requestDTO,supplyBillDO);
        supplyBillDO.setBillStatus(BillStatusEnum.NEW.key);
        supplyBillDO.setBeginDate(DateUtil.stringToDate(requestDTO.getBeginDate()));
        supplyBillDO.setEndDate(DateUtil.stringToDate(requestDTO.getEndDate()));
        supplyBillDO.setCreator(requestDTO.getOperator());
        supplyBillDO.setCreateTime(new Date());
        supplyBillMapper.insert(supplyBillDO);

        //保存账单明细
        if (requestDTO.getIsAuto()==null || requestDTO.getIsAuto()==0){
            InsertBillOrderDTO insertBillOrderDTO=new InsertBillOrderDTO();
            BeanUtils.copyProperties(requestDTO,insertBillOrderDTO);
            insertBillOrderDTO.setBillId(supplyBillDO.getId());
            supplyBillOrderMapper.saveBatchBillOrder(insertBillOrderDTO);

            InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
            insertBillOrderItemDTO.setBillId(supplyBillDO.getId());
            insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
            supplyBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
            supplyBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
            supplyBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
            supplyBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

            BillIdDTO billIdDTO=new BillIdDTO();
            billIdDTO.setBillId(supplyBillDO.getId());
            billIdDTO.setOperator(requestDTO.getOperator());
            supplyBillOrderMapper.saveBillCurrency(billIdDTO);
            //更新结算状态为已纳入账单
            UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
            updateBillOrderFinanceDTO.setBillId(supplyBillDO.getId());
            updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.HOLD.key);
            supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

            //将订单加锁
            QueryBillOrderDTO queryBillOrderDTO=new QueryBillOrderDTO();
            queryBillOrderDTO.setBillId(supplyBillDO.getId());
            List<Integer> orderIdList=supplyBillOrderMapper.querySupplyBillOrderId(queryBillOrderDTO);
            FinanceLockOrderRequestDTO financeLockOrderRequestDTO=new FinanceLockOrderRequestDTO();
            financeLockOrderRequestDTO.setOrderList(new ArrayList<>());
            for (Integer orderId:orderIdList){
                LockOrderItemRequestDTO lockOrderItemRequestDTO=new LockOrderItemRequestDTO();
                lockOrderItemRequestDTO.setOrderId(orderId);
                lockOrderItemRequestDTO.setLockType(1);
                financeLockOrderRequestDTO.getOrderList().add(lockOrderItemRequestDTO);
            }
            financeLockOrderRequestDTO.setCreator(requestDTO.getOperator());
            financeLockOrderRequestDTO.setCreateTime(new Date());
            financeLockOrderRequestDTO.setModifier(requestDTO.getOperator());
            financeLockOrderRequestDTO.setModifyTime(new Date());
            financeLockService.lockOrder(financeLockOrderRequestDTO);
        }
        QueryBillDTO queryBillDTO=new QueryBillDTO();
        queryBillDTO.setBillId(supplyBillDO.getId());
        queryBillDTO.setMerchantCode(requestDTO.getMerchantCode());
        PaginationSupportDTO paginationSupportDTO=querySupplyBill(queryBillDTO);
        responseDTO.setModel(paginationSupportDTO.getItemList().get(0));
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO updateSupplyBillName(UpdateBillNameDTO requestDTO){
        SupplyBillDO supplyBillParam=new SupplyBillDO();
        supplyBillParam.setId(requestDTO.getBillId());
        supplyBillParam.setBillName(requestDTO.getBillName());
        supplyBillParam.setModifier(requestDTO.getOperator());
        supplyBillParam.setModifyTime(new Date());
        supplyBillMapper.updateByPrimaryKeySelective(supplyBillParam);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public PaginationSupportDTO<BillDTO> querySupplyBill(QueryBillDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<BillDTO> list =supplyBillMapper.querySupplyBill(requestDTO);
        PageInfo<BillDTO> page = new PageInfo<BillDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());

        if (list.size()>0){
            List<Integer> billIdList=new ArrayList<>();
            Map<Integer,BillDTO> billDTOMap=new HashMap<>();
            List<String> billCodeList=new ArrayList<>();
            Map<String,BillDTO> billCodeDTOMap=new HashMap<>();
            for (BillDTO billDTO:list){
                billIdList.add(billDTO.getBillId());
                billDTOMap.put(billDTO.getBillId(),billDTO);

                billCodeList.add(billDTO.getBillCode());
                billCodeDTOMap.put(billDTO.getBillCode(),billDTO);

                billDTO.setMultipleCurrencyList(new ArrayList<>());
                billDTO.setFinanceOrderDTOList(new ArrayList<>());
            }
            Example example=new Example(SupplyBillCurrencyDO.class);
            Example.Criteria criteria=example.createCriteria();
            criteria.andIn("billId",billIdList);
            List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.selectByExample(example);
            for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
                MultipleCurrencyAmountDTO multipleCurrencyAmountDTO=new MultipleCurrencyAmountDTO();
                multipleCurrencyAmountDTO.setCurrency(supplyBillCurrencyDO.getCurrency());
                multipleCurrencyAmountDTO.setReceivableAmount(supplyBillCurrencyDO.getReceivableAmount());
                multipleCurrencyAmountDTO.setPaidinAmount(supplyBillCurrencyDO.getPaidinAmount());
                multipleCurrencyAmountDTO.setNoticeAmount(supplyBillCurrencyDO.getNoticeAmount());
                billDTOMap.get(supplyBillCurrencyDO.getBillId()).getMultipleCurrencyList().add(multipleCurrencyAmountDTO);

                BigDecimal receivableAmount=supplyBillCurrencyDO.getReceivableAmount()==null?BigDecimal.ZERO:supplyBillCurrencyDO.getReceivableAmount();
                BigDecimal noticeAmount=supplyBillCurrencyDO.getNoticeAmount()==null?BigDecimal.ZERO:supplyBillCurrencyDO.getNoticeAmount();
                if (receivableAmount.compareTo(noticeAmount)!=0){
                    billDTOMap.get(supplyBillCurrencyDO.getBillId()).setIsFinishedNotice(0);
                }
            }

            //如果是自动对账，则将自动对账的结果查询出来
            if (list.size()==1 && list.get(0).getIsAuto()==1 && list.get(0).getImportNo()!=null){
                BillAutoMatchResultDTO matchResultDTO=supplyBillImportMapper.queryAutoMatchResult(list.get(0).getImportNo());
                list.get(0).setAutoMatchResultDTO(matchResultDTO);
            }

            //查询账单对应的工单
            if (requestDTO.getTagType()==3 || requestDTO.getTagType()==4 || requestDTO.getTagType()==-1){
                QuerySupplyFinanceOrderRequestDTO querySupplyFinanceOrderRequestDTO=new QuerySupplyFinanceOrderRequestDTO();
                querySupplyFinanceOrderRequestDTO.setOrderCodeList(billCodeList);
                if (requestDTO.getTagType()==3){
                    querySupplyFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
                }
                ResponseDTO<List<QuerySupplyFinanceOrderResponseDTO>> listResponseDTO=supplyFinanceOrderService.queryPayItemList(querySupplyFinanceOrderRequestDTO);
                if (listResponseDTO.getResult()==ResultCodeEnum.SUCCESS.code
                        && listResponseDTO.getModel()!=null
                        && listResponseDTO.getModel().size()>0){
                    for (QuerySupplyFinanceOrderResponseDTO financeOrderResponseDTO:listResponseDTO.getModel()){
                        BillDTO billDTO=billCodeDTOMap.get(financeOrderResponseDTO.getOrderCode());
                        BillFinanceOrderDTO billFinanceOrderDTO=new BillFinanceOrderDTO();
                        billFinanceOrderDTO.setFinanceId(financeOrderResponseDTO.getFinanceOrderId());
                        billFinanceOrderDTO.setFinanceCode(financeOrderResponseDTO.getFinanceCode());
                        billFinanceOrderDTO.setFinanceType(financeOrderResponseDTO.getFinanceType());
                        billFinanceOrderDTO.setFinanceStatus(financeOrderResponseDTO.getFinanceStatus());
                        billFinanceOrderDTO.setOrgCode(billDTO.getOrgCode());
                        billFinanceOrderDTO.setOrgName(billDTO.getOrgName());
                        billFinanceOrderDTO.setMultipleCurrencyList(
                                Arrays.asList(new MultipleCurrencyAmountDTO(financeOrderResponseDTO.getCurrency()
                                        ,null,null,financeOrderResponseDTO.getNotifyAmount())));
                        StringBuilder sbNote=new StringBuilder();
                        List<String> passageList=new ArrayList<>();
                        for (VoucherResponseDTO voucherResponseDTO:financeOrderResponseDTO.getVoucherList()){
                            passageList.add(PassageEnum.getValueByKey(voucherResponseDTO.getPassage()));
                            sbNote.append(voucherResponseDTO.getNote());
                        }
                        billFinanceOrderDTO.setPassageList(passageList);
                        billFinanceOrderDTO.setNote(sbNote.toString());
                        billFinanceOrderDTO.setCreator(financeOrderResponseDTO.getCreator());
                        billFinanceOrderDTO.setCreateTime(financeOrderResponseDTO.getCreateTime());
                        billDTO.getFinanceOrderDTOList().add(billFinanceOrderDTO);
                    }
                }
            }
        }
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<BillOrderDTO> querySupplyBillOrder(QueryBillOrderDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<BillOrderDTO> list =supplyBillOrderMapper.querySupplyBillOrder(requestDTO);
        PageInfo<BillOrderDTO> page = new PageInfo<BillOrderDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public List<BillOrderItemDTO> querySupplyBillOrderItem(BillOrderIdDTO requestDTO) {
        return supplyBillOrderItemMapper.querySupplyBillOrderItem(requestDTO);
    }

    @Override
    public List<BillOrderItemExportDTO> exportBillOrderItem(BillIdDTO requestDTO) {
        return supplyBillOrderItemMapper.exportBillOrderItem(requestDTO);
    }

    @Override
    @Transactional
    public ResponseDTO addSupplyBillOrder(@Valid AddBillOrderDTO requestDTO) {
        InsertBillOrderDTO insertBillOrderDTO=new InsertBillOrderDTO();
        insertBillOrderDTO.setBillId(requestDTO.getBillId());
        insertBillOrderDTO.setOrderIdList(requestDTO.getOrderIdList());
        insertBillOrderDTO.setMerchantCode(requestDTO.getMerchantCode());
        insertBillOrderDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBatchBillOrder(insertBillOrderDTO);

        InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
        insertBillOrderItemDTO.setBillId(requestDTO.getBillId());
        insertBillOrderItemDTO.setOrderIdList(requestDTO.getOrderIdList());
        insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
        supplyBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
        supplyBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
        supplyBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        supplyBillCurrencyMapper.delete(supplyBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBillCurrency(billIdDTO);

        //更新结算状态为已纳入账单
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setOrderIdList(requestDTO.getOrderIdList());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.HOLD.key);
        supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        //将订单加锁
        List<Integer> orderIdList=supplyCreditOrderMapper.queryOrderIdBySupplyOrderId(requestDTO.getOrderIdList());
        FinanceLockOrderRequestDTO financeLockOrderRequestDTO=new FinanceLockOrderRequestDTO();
        financeLockOrderRequestDTO.setOrderList(new ArrayList<>());
        for (Integer orderId:orderIdList){
            LockOrderItemRequestDTO lockOrderItemRequestDTO=new LockOrderItemRequestDTO();
            lockOrderItemRequestDTO.setOrderId(orderId);
            lockOrderItemRequestDTO.setLockType(1);
            financeLockOrderRequestDTO.getOrderList().add(lockOrderItemRequestDTO);
        }
        financeLockOrderRequestDTO.setCreator(requestDTO.getOperator());
        financeLockOrderRequestDTO.setCreateTime(new Date());
        financeLockOrderRequestDTO.setModifier(requestDTO.getOperator());
        financeLockOrderRequestDTO.setModifyTime(new Date());
        financeLockService.lockOrder(financeLockOrderRequestDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO deleteSupplyBillOrder(@Valid DeleteBillOrderDTO requestDTO) {
        //更新结算状态为可出账
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setBillOrderIdList(requestDTO.getBillOrderIdList());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CANCHECK.key);
        supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        Example example=new Example(SupplyBillOrderDO.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andIn("id",requestDTO.getBillOrderIdList());
        supplyBillOrderMapper.deleteByExample(example);

        Example example1=new Example(SupplyBillOrderItemDO.class);
        Example.Criteria criteria1=example1.createCriteria();
        criteria1.andIn("billOrderId",requestDTO.getBillOrderIdList());
        supplyBillOrderItemMapper.deleteByExample(example1);

        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        supplyBillCurrencyMapper.delete(supplyBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBillCurrency(billIdDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO clearSupplyBillOrder(@Valid BillIdDTO requestDTO) {
        //更新结算状态为可出账
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CANCHECK.key);
        supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        SupplyBillOrderDO supplyBillOrderParam=new SupplyBillOrderDO();
        supplyBillOrderParam.setBillId(requestDTO.getBillId());
        supplyBillOrderMapper.delete(supplyBillOrderParam);

        SupplyBillOrderItemDO supplyBillOrderItemParam=new SupplyBillOrderItemDO();
        supplyBillOrderItemParam.setBillId(requestDTO.getBillId());
        supplyBillOrderItemMapper.delete(supplyBillOrderItemParam);

        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        supplyBillCurrencyMapper.delete(supplyBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBillCurrency(billIdDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO confirmSupplyBill(BillIdDTO requestDTO) {
        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.select(supplyBillCurrencyParam);

        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=supplyBillOrderMapper.queryBillNewCurrency(billIdDTO);
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:multipleCurrencyAmountDTOList){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(multipleCurrencyAmountDTO.getReceivableAmount())!=0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"账单应收金额发生变化，请先更新账单");
                    }
                }
            }
        }

        SupplyBillDO supplyBillParam=new SupplyBillDO();
        supplyBillParam.setId(requestDTO.getBillId());
        supplyBillParam.setBillStatus(BillStatusEnum.CHECKED.key);
        supplyBillParam.setModifier(requestDTO.getOperator());
        supplyBillParam.setModifyTime(new Date());
        supplyBillMapper.updateByPrimaryKeySelective(supplyBillParam);

        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CHECKED.key);
        supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO updateSupplyBill(@Valid BillIdDTO requestDTO) {
        supplyBillOrderMapper.updateSupplyBillOrder(requestDTO);

        SupplyBillOrderItemDO supplyBillOrderItemParam=new SupplyBillOrderItemDO();
        supplyBillOrderItemParam.setBillId(requestDTO.getBillId());
        supplyBillOrderItemMapper.delete(supplyBillOrderItemParam);

        InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
        insertBillOrderItemDTO.setBillId(requestDTO.getBillId());
        insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
        supplyBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
        supplyBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
        supplyBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
        supplyBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        supplyBillCurrencyMapper.delete(supplyBillCurrencyParam);
        supplyBillOrderMapper.saveBillCurrency(requestDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO deleteSupplyBill(@Valid BillIdDTO requestDTO) {
        SupplyBillDO supplyBillDO=supplyBillMapper.selectByPrimaryKey(requestDTO.getBillId());
        if (supplyBillDO.getBillStatus()==BillStatusEnum.WAITCHECKOUT.key){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"已通知结算，不能删除账单");
        }
        if (supplyBillDO.getBillStatus()==BillStatusEnum.CHECKOUT.key){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"已结算，不能删除账单");
        }

        //更新结算状态为可出账
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CANCHECK.key);
        supplyBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        supplyBillMapper.deleteByPrimaryKey(requestDTO.getBillId());

        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        supplyBillCurrencyMapper.delete(supplyBillCurrencyParam);

        SupplyBillOrderDO supplyBillOrderParam=new SupplyBillOrderDO();
        supplyBillOrderParam.setBillId(requestDTO.getBillId());
        supplyBillOrderMapper.delete(supplyBillOrderParam);

        SupplyBillOrderItemDO supplyBillOrderItemParam=new SupplyBillOrderItemDO();
        supplyBillOrderItemParam.setBillId(requestDTO.getBillId());
        supplyBillOrderItemMapper.delete(supplyBillOrderItemParam);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO createSupplyBillFinanceOrder(@Valid CreateBillFinanceOrderDTO requestDTO) {
        SupplyBillDO supplyBillDO=supplyBillMapper.selectByPrimaryKey(requestDTO.getBillId());

        //更新已通知金额
        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.select(supplyBillCurrencyParam);

        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=supplyBillOrderMapper.queryBillNewCurrency(billIdDTO);
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:multipleCurrencyAmountDTOList){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(multipleCurrencyAmountDTO.getReceivableAmount())!=0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"账单应收金额发生变化，请先更新账单");
                    }
                }
            }
        }

        boolean finished=true;
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:requestDTO.getMultipleCurrencyList()){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    BigDecimal noticeAmount=supplyBillCurrencyDO.getNoticeAmount()==null? BigDecimal.ZERO:supplyBillCurrencyDO.getNoticeAmount();
                    supplyBillCurrencyDO.setNoticeAmount(noticeAmount.add(multipleCurrencyAmountDTO.getReceivableAmount()));
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&supplyBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)<0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应付金额不能为负数");
                    }
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&supplyBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应付金额不能为正数");
                    }
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&supplyBillCurrencyDO.getReceivableAmount().compareTo(supplyBillCurrencyDO.getNoticeAmount())<0
                            ||supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&supplyBillCurrencyDO.getReceivableAmount().compareTo(supplyBillCurrencyDO.getNoticeAmount())>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"通知付款金额超过账单应付金额");
                    }
                    supplyBillCurrencyMapper.updateByPrimaryKeySelective(supplyBillCurrencyDO);
                }
            }
            if (supplyBillCurrencyDO.getReceivableAmount().compareTo(supplyBillCurrencyDO.getNoticeAmount())!=0){
                finished=false;
            }
        }

        //更新账单状态
        SupplyBillDO supplyBillParam=new SupplyBillDO();
        supplyBillParam.setId(requestDTO.getBillId());
        supplyBillParam.setBillStatus(BillStatusEnum.WAITCHECKOUT.key);
        supplyBillMapper.updateByPrimaryKeySelective(supplyBillParam);

        //创建工单
        SupplyFinanceOrderRequestDTO supplyFinanceOrderRequestDTO=new SupplyFinanceOrderRequestDTO();
        supplyFinanceOrderRequestDTO.setFinanceType(FinanceTypeEnum.SUPPLIERBILLSATTLE.key);
        supplyFinanceOrderRequestDTO.setOrderCode(supplyBillDO.getBillCode());
        supplyFinanceOrderRequestDTO.setBillName(supplyBillDO.getBillName());
        supplyFinanceOrderRequestDTO.setOrgCode(supplyBillDO.getOrgCode());
        supplyFinanceOrderRequestDTO.setOrgName(supplyBillDO.getOrgName());
        supplyFinanceOrderRequestDTO.setMerchantCode(supplyBillDO.getMerchantCode());
        supplyFinanceOrderRequestDTO.setCurrency(requestDTO.getMultipleCurrencyList().get(0).getCurrency());
        supplyFinanceOrderRequestDTO.setNotifyAmount(requestDTO.getMultipleCurrencyList().get(0).getReceivableAmount());
        supplyFinanceOrderRequestDTO.setVoucherList(requestDTO.getFinancePayItemDTOList());
        supplyFinanceOrderRequestDTO.setCreator(requestDTO.getOperator());
        supplyFinanceOrderRequestDTO.setCreateTime(new Date());

        if (finished){
            List<FinanceOrderItemDTO> financeOrderItemDTOList=new ArrayList<>();
            QueryBillOrderDTO queryBillOrderDTO=new QueryBillOrderDTO();
            queryBillOrderDTO.setBillId(requestDTO.getBillId());
            List<BillOrderDTO> billOrderDTOList=supplyBillOrderMapper.querySupplyBillOrder(queryBillOrderDTO);
            for (BillOrderDTO billOrderDTO:billOrderDTOList){
                FinanceOrderItemDTO financeOrderItemDTO=new FinanceOrderItemDTO();
                financeOrderItemDTO.setOrderCode(billOrderDTO.getOrderCode());
                financeOrderItemDTO.setCurrency(billOrderDTO.getCurrency());
                financeOrderItemDTO.setAmount(billOrderDTO.getCurrPaidinAmount());
                financeOrderItemDTOList.add(financeOrderItemDTO);
            }
            supplyFinanceOrderRequestDTO.setFinanceOrderItemDTOList(financeOrderItemDTOList);
        }
        return supplyFinanceOrderService.notifyPayableAmount(supplyFinanceOrderRequestDTO);
    }

    @Override
    @Transactional
    public ResponseDTO createSupplyBillFinanceOrderWithConfirm(@Valid CreateBillFinanceOrderDTO requestDTO) {
        SupplyBillDO supplyBillDO=supplyBillMapper.selectByPrimaryKey(requestDTO.getBillId());

        //更新已通知金额
        SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
        supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
        List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.select(supplyBillCurrencyParam);

        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=supplyBillOrderMapper.queryBillNewCurrency(billIdDTO);
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:multipleCurrencyAmountDTOList){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(multipleCurrencyAmountDTO.getReceivableAmount())!=0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"账单应收金额发生变化，请先更新账单");
                    }
                }
            }
        }

        boolean finished=true;
        for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:requestDTO.getMultipleCurrencyList()){
                if (supplyBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    BigDecimal noticeAmount=supplyBillCurrencyDO.getNoticeAmount()==null? BigDecimal.ZERO:supplyBillCurrencyDO.getNoticeAmount();
                    supplyBillCurrencyDO.setNoticeAmount(noticeAmount.add(multipleCurrencyAmountDTO.getReceivableAmount()));
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&supplyBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)<0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应付金额不能为负数");
                    }
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&supplyBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应付金额不能为正数");
                    }
                    if (supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&supplyBillCurrencyDO.getReceivableAmount().compareTo(supplyBillCurrencyDO.getNoticeAmount())<0
                            ||supplyBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&supplyBillCurrencyDO.getReceivableAmount().compareTo(supplyBillCurrencyDO.getNoticeAmount())>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"通知付款金额超过账单应付金额");
                    }
                    supplyBillCurrencyMapper.updateByPrimaryKeySelective(supplyBillCurrencyDO);
                }
            }
            if (supplyBillCurrencyDO.getReceivableAmount().compareTo(supplyBillCurrencyDO.getNoticeAmount())!=0){
                finished=false;
            }
        }

        //更新账单状态
        SupplyBillDO supplyBillParam=new SupplyBillDO();
        supplyBillParam.setId(requestDTO.getBillId());
        supplyBillParam.setBillStatus(BillStatusEnum.WAITCHECKOUT.key);
        supplyBillMapper.updateByPrimaryKeySelective(supplyBillParam);

        //创建并确认工单
        ConfirmPayRequestDTO confirmPayRequestDTO=new ConfirmPayRequestDTO();
        confirmPayRequestDTO.setFinanceType(FinanceTypeEnum.SUPPLIERBILLSATTLE.key);
        confirmPayRequestDTO.setOrderCode(supplyBillDO.getBillCode());
        confirmPayRequestDTO.setBillName(supplyBillDO.getBillName());
        confirmPayRequestDTO.setOrgCode(supplyBillDO.getOrgCode());
        confirmPayRequestDTO.setOrgName(supplyBillDO.getOrgName());
        confirmPayRequestDTO.setMerchantCode(supplyBillDO.getMerchantCode());
        confirmPayRequestDTO.setCurrency(requestDTO.getMultipleCurrencyList().get(0).getCurrency());
        confirmPayRequestDTO.setNotifyAmount(requestDTO.getMultipleCurrencyList().get(0).getReceivableAmount());
        confirmPayRequestDTO.setVoucherList(requestDTO.getFinancePayItemDTOList());
        confirmPayRequestDTO.setCreator(requestDTO.getOperator());
        confirmPayRequestDTO.setCreateTime(new Date());

        if (finished){
            List<FinanceOrderItemDTO> financeOrderItemDTOList=new ArrayList<>();
            QueryBillOrderDTO queryBillOrderDTO=new QueryBillOrderDTO();
            queryBillOrderDTO.setBillId(requestDTO.getBillId());
            List<BillOrderDTO> billOrderDTOList=supplyBillOrderMapper.querySupplyBillOrder(queryBillOrderDTO);
            for (BillOrderDTO billOrderDTO:billOrderDTOList){
                FinanceOrderItemDTO financeOrderItemDTO=new FinanceOrderItemDTO();
                financeOrderItemDTO.setOrderCode(billOrderDTO.getOrderCode());
                financeOrderItemDTO.setCurrency(billOrderDTO.getCurrency());
                financeOrderItemDTO.setAmount(billOrderDTO.getCurrPaidinAmount());
                financeOrderItemDTOList.add(financeOrderItemDTO);
            }
            confirmPayRequestDTO.setFinanceOrderItemDTOList(financeOrderItemDTOList);
        }
        return supplyFinanceOrderService.confirmPay(confirmPayRequestDTO);
    }

    @Override
    public List<MultipleCurrencyAmountDTO> querySupplyBillCurrency(QueryBillCurrencyDTO requestDTO) {
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=new ArrayList<>();
        if (requestDTO.getBalanceMethodType()==null ||requestDTO.getBalanceMethodType()==0){
            SupplyBillCurrencyDO supplyBillCurrencyParam=new SupplyBillCurrencyDO();
            supplyBillCurrencyParam.setBillId(requestDTO.getBillId());
            List<SupplyBillCurrencyDO> supplyBillCurrencyDOList=supplyBillCurrencyMapper.select(supplyBillCurrencyParam);
            for (SupplyBillCurrencyDO supplyBillCurrencyDO:supplyBillCurrencyDOList){
                MultipleCurrencyAmountDTO multipleCurrencyAmountDTO=new MultipleCurrencyAmountDTO();
                BeanUtils.copyProperties(supplyBillCurrencyDO,multipleCurrencyAmountDTO);
                multipleCurrencyAmountDTOList.add(multipleCurrencyAmountDTO);
            }
        }else{
            multipleCurrencyAmountDTOList=supplyBillOrderMapper.querySupplyBillCurrency(requestDTO);
        }
        return multipleCurrencyAmountDTOList;
    }
}
