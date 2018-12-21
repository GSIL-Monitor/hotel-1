package com.fangcang.finance.bill.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.finance.bill.domain.AgentBillCurrencyDO;
import com.fangcang.finance.bill.domain.AgentBillDO;
import com.fangcang.finance.bill.domain.AgentBillOrderDO;
import com.fangcang.finance.bill.domain.AgentBillOrderItemDO;
import com.fangcang.finance.bill.mapper.AgentBillCurrencyMapper;
import com.fangcang.finance.bill.mapper.AgentBillImportMapper;
import com.fangcang.finance.bill.mapper.AgentBillMapper;
import com.fangcang.finance.bill.mapper.AgentBillOrderItemMapper;
import com.fangcang.finance.bill.mapper.AgentBillOrderMapper;
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
import com.fangcang.finance.bill.service.AgentBillService;
import com.fangcang.finance.dto.MultipleCurrencyAmountDTO;
import com.fangcang.finance.enums.AccountStatusEnum;
import com.fangcang.finance.enums.BillStatusEnum;
import com.fangcang.finance.enums.FinanceStatusEnum;
import com.fangcang.finance.enums.FinanceTypeEnum;
import com.fangcang.finance.enums.PassageEnum;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.LockOrderItemRequestDTO;
import com.fangcang.finance.financelock.service.FinanceLockService;
import com.fangcang.finance.financeorder.request.AgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.request.ConfirmPayRequestDTO;
import com.fangcang.finance.financeorder.request.FinanceOrderItemDTO;
import com.fangcang.finance.financeorder.request.QueryAgentFinanceOrderRequestDTO;
import com.fangcang.finance.financeorder.response.QueryAgentFinanceOrderResponseDTO;
import com.fangcang.finance.financeorder.response.VoucherResponseDTO;
import com.fangcang.finance.financeorder.service.AgentFinanceOrderService;
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
public class AgentBillServiceImpl implements AgentBillService {

    @Autowired
    private AgentBillMapper agentBillMapper;

    @Autowired
    private AgentBillCurrencyMapper agentBillCurrencyMapper;

    @Autowired
    private AgentBillOrderMapper agentBillOrderMapper;

    @Autowired
    private AgentBillOrderItemMapper agentBillOrderItemMapper;

    @Autowired
    private AgentBillImportMapper agentBillImportMapper;

    @Autowired
    private FinanceLockService financeLockService;

    @Autowired
    private AgentFinanceOrderService agentFinanceOrderService;

    @Override
    @Transactional
    public ResponseDTO createAgentBill(@Valid CreateBillDTO requestDTO) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        //创建账单
        AgentBillDO agentBillDO=new AgentBillDO();
        BeanUtils.copyProperties(requestDTO,agentBillDO);
        agentBillDO.setBillStatus(BillStatusEnum.NEW.key);
        agentBillDO.setBeginDate(DateUtil.stringToDate(requestDTO.getBeginDate()));
        agentBillDO.setEndDate(DateUtil.stringToDate(requestDTO.getEndDate()));
        agentBillDO.setCreator(requestDTO.getOperator());
        agentBillDO.setCreateTime(new Date());
        agentBillMapper.insert(agentBillDO);

        //保存账单明细
        if (requestDTO.getIsAuto()==null || requestDTO.getIsAuto()==0){
            InsertBillOrderDTO insertBillOrderDTO=new InsertBillOrderDTO();
            BeanUtils.copyProperties(requestDTO,insertBillOrderDTO);
            insertBillOrderDTO.setBillId(agentBillDO.getId());
            agentBillOrderMapper.saveBatchBillOrder(insertBillOrderDTO);

            InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
            insertBillOrderItemDTO.setBillId(agentBillDO.getId());
            insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
            agentBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
            agentBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
            agentBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
            agentBillOrderMapper.saveBatchBillCanceledOrder(insertBillOrderItemDTO);
            agentBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

            BillIdDTO billIdDTO=new BillIdDTO();
            billIdDTO.setBillId(agentBillDO.getId());
            billIdDTO.setOperator(requestDTO.getOperator());
            agentBillOrderMapper.saveBillCurrency(billIdDTO);
            //更新结算状态为已纳入账单
            UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
            updateBillOrderFinanceDTO.setBillId(agentBillDO.getId());
            updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.HOLD.key);
            agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

            //将订单加锁
            AgentBillOrderDO agentBillOrderParam=new AgentBillOrderDO();
            agentBillOrderParam.setBillId(agentBillDO.getId());
            List<AgentBillOrderDO> agentBillOrderDOList=agentBillOrderMapper.select(agentBillOrderParam);
            FinanceLockOrderRequestDTO financeLockOrderRequestDTO=new FinanceLockOrderRequestDTO();
            financeLockOrderRequestDTO.setOrderList(new ArrayList<>());
            for (AgentBillOrderDO agentBillOrderDO:agentBillOrderDOList){
                LockOrderItemRequestDTO lockOrderItemRequestDTO=new LockOrderItemRequestDTO();
                lockOrderItemRequestDTO.setOrderId(agentBillOrderDO.getOrderId());
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
        queryBillDTO.setBillId(agentBillDO.getId());
        queryBillDTO.setMerchantCode(requestDTO.getMerchantCode());
        PaginationSupportDTO paginationSupportDTO=queryAgentBill(queryBillDTO);
        responseDTO.setModel(paginationSupportDTO.getItemList().get(0));
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO updateAgentBillName(UpdateBillNameDTO requestDTO){
        AgentBillDO agentBillParam=new AgentBillDO();
        agentBillParam.setId(requestDTO.getBillId());
        agentBillParam.setBillName(requestDTO.getBillName());
        agentBillParam.setModifier(requestDTO.getOperator());
        agentBillParam.setModifyTime(new Date());
        agentBillMapper.updateByPrimaryKeySelective(agentBillParam);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public PaginationSupportDTO<BillDTO> queryAgentBill(QueryBillDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<BillDTO> list =agentBillMapper.queryAgentBill(requestDTO);
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
            Example example=new Example(AgentBillCurrencyDO.class);
            Example.Criteria criteria=example.createCriteria();
            criteria.andIn("billId",billIdList);
            List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.selectByExample(example);
            for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
                MultipleCurrencyAmountDTO multipleCurrencyAmountDTO=new MultipleCurrencyAmountDTO();
                multipleCurrencyAmountDTO.setCurrency(agentBillCurrencyDO.getCurrency());
                multipleCurrencyAmountDTO.setReceivableAmount(agentBillCurrencyDO.getReceivableAmount());
                multipleCurrencyAmountDTO.setPaidinAmount(agentBillCurrencyDO.getPaidinAmount());
                multipleCurrencyAmountDTO.setNoticeAmount(agentBillCurrencyDO.getNoticeAmount());
                billDTOMap.get(agentBillCurrencyDO.getBillId()).getMultipleCurrencyList().add(multipleCurrencyAmountDTO);

                BigDecimal receivableAmount=agentBillCurrencyDO.getReceivableAmount()==null?BigDecimal.ZERO:agentBillCurrencyDO.getReceivableAmount();
                BigDecimal noticeAmount=agentBillCurrencyDO.getNoticeAmount()==null?BigDecimal.ZERO:agentBillCurrencyDO.getNoticeAmount();
                if (receivableAmount.compareTo(noticeAmount)!=0){
                    billDTOMap.get(agentBillCurrencyDO.getBillId()).setIsFinishedNotice(0);
                }
            }

            //如果是自动对账，则将自动对账的结果查询出来
            if (list.size()==1 && list.get(0).getIsAuto()==1 && list.get(0).getImportNo()!=null){
                BillAutoMatchResultDTO matchResultDTO=agentBillImportMapper.queryAutoMatchResult(list.get(0).getImportNo());
                list.get(0).setAutoMatchResultDTO(matchResultDTO);
            }

            //查询账单对应的工单
            if (requestDTO.getTagType()==3 || requestDTO.getTagType()==4 || requestDTO.getTagType()==-1){
                QueryAgentFinanceOrderRequestDTO queryAgentFinanceOrderRequestDTO=new QueryAgentFinanceOrderRequestDTO();
                queryAgentFinanceOrderRequestDTO.setOrderCodeList(billCodeList);
                if (requestDTO.getTagType()==3){
                    queryAgentFinanceOrderRequestDTO.setFinanceStatus(FinanceStatusEnum.NEW.key);
                }
                ResponseDTO<List<QueryAgentFinanceOrderResponseDTO>> listResponseDTO=agentFinanceOrderService.queryPayItemList(queryAgentFinanceOrderRequestDTO);
                if (listResponseDTO.getResult()==ResultCodeEnum.SUCCESS.code
                        && listResponseDTO.getModel()!=null
                        && listResponseDTO.getModel().size()>0){
                    for (QueryAgentFinanceOrderResponseDTO financeOrderResponseDTO:listResponseDTO.getModel()){
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
    public PaginationSupportDTO<BillOrderDTO> queryAgentBillOrder(QueryBillOrderDTO requestDTO) {
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<BillOrderDTO> list =agentBillOrderMapper.queryAgentBillOrder(requestDTO);
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
    public List<BillOrderItemDTO> queryAgentBillOrderItem(BillOrderIdDTO requestDTO) {
        return agentBillOrderItemMapper.queryAgentBillOrderItem(requestDTO);
    }

    @Override
    public List<BillOrderItemExportDTO> exportBillOrderItem(BillIdDTO requestDTO) {
        return agentBillOrderItemMapper.exportBillOrderItem(requestDTO);
    }

    @Override
    @Transactional
    public ResponseDTO addAgentBillOrder(@Valid AddBillOrderDTO requestDTO) {
        InsertBillOrderDTO insertBillOrderDTO=new InsertBillOrderDTO();
        insertBillOrderDTO.setBillId(requestDTO.getBillId());
        insertBillOrderDTO.setOrderIdList(requestDTO.getOrderIdList());
        insertBillOrderDTO.setMerchantCode(requestDTO.getMerchantCode());
        insertBillOrderDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBatchBillOrder(insertBillOrderDTO);

        InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
        insertBillOrderItemDTO.setBillId(requestDTO.getBillId());
        insertBillOrderItemDTO.setOrderIdList(requestDTO.getOrderIdList());
        insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillCanceledOrder(insertBillOrderItemDTO);
        agentBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        agentBillCurrencyMapper.delete(agentBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBillCurrency(billIdDTO);

        //更新结算状态为已纳入账单
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setOrderIdList(requestDTO.getOrderIdList());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.HOLD.key);
        agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        //将订单加锁
        FinanceLockOrderRequestDTO financeLockOrderRequestDTO=new FinanceLockOrderRequestDTO();
        financeLockOrderRequestDTO.setOrderList(new ArrayList<>());
        for (Integer orderId:requestDTO.getOrderIdList()){
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
    public ResponseDTO deleteAgentBillOrder(@Valid DeleteBillOrderDTO requestDTO) {
        //更新结算状态为可出账
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setBillOrderIdList(requestDTO.getBillOrderIdList());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CANCHECK.key);
        agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        Example example=new Example(AgentBillOrderDO.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andIn("id",requestDTO.getBillOrderIdList());
        agentBillOrderMapper.deleteByExample(example);

        Example example1=new Example(AgentBillOrderItemDO.class);
        Example.Criteria criteria1=example1.createCriteria();
        criteria1.andIn("billOrderId",requestDTO.getBillOrderIdList());
        agentBillOrderItemMapper.deleteByExample(example1);

        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        agentBillCurrencyMapper.delete(agentBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBillCurrency(billIdDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO clearAgentBillOrder(@Valid BillIdDTO requestDTO) {
        //更新结算状态为可出账
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CANCHECK.key);
        agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        AgentBillOrderDO agentBillOrderParam=new AgentBillOrderDO();
        agentBillOrderParam.setBillId(requestDTO.getBillId());
        agentBillOrderMapper.delete(agentBillOrderParam);

        AgentBillOrderItemDO agentBillOrderItemParam=new AgentBillOrderItemDO();
        agentBillOrderItemParam.setBillId(requestDTO.getBillId());
        agentBillOrderItemMapper.delete(agentBillOrderItemParam);

        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        agentBillCurrencyMapper.delete(agentBillCurrencyParam);
        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        billIdDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBillCurrency(billIdDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO confirmAgentBill(BillIdDTO requestDTO) {
        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.select(agentBillCurrencyParam);

        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=agentBillOrderMapper.queryBillNewCurrency(billIdDTO);
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:multipleCurrencyAmountDTOList){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(multipleCurrencyAmountDTO.getReceivableAmount())!=0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"账单应收金额发生变化，请先更新账单");
                    }
                }
            }
        }

        AgentBillDO agentBillParam=new AgentBillDO();
        agentBillParam.setId(requestDTO.getBillId());
        agentBillParam.setBillStatus(BillStatusEnum.CHECKED.key);
        agentBillParam.setModifier(requestDTO.getOperator());
        agentBillParam.setModifyTime(new Date());
        agentBillMapper.updateByPrimaryKeySelective(agentBillParam);

        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CHECKED.key);
        agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO updateAgentBill(@Valid BillIdDTO requestDTO) {
        agentBillOrderMapper.updateAgentBillOrder(requestDTO);

        AgentBillOrderItemDO agentBillOrderItemParam=new AgentBillOrderItemDO();
        agentBillOrderItemParam.setBillId(requestDTO.getBillId());
        agentBillOrderItemMapper.delete(agentBillOrderItemParam);

        InsertBillOrderItemDTO insertBillOrderItemDTO=new InsertBillOrderItemDTO();
        insertBillOrderItemDTO.setBillId(requestDTO.getBillId());
        insertBillOrderItemDTO.setOperator(requestDTO.getOperator());
        agentBillOrderMapper.saveBatchBillOrderProduct(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillOrderAddition(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillOrderDeratePolicy(insertBillOrderItemDTO);
        agentBillOrderMapper.saveBatchBillCanceledOrder(insertBillOrderItemDTO);
        agentBillOrderMapper.updateBillOrderItemCount(insertBillOrderItemDTO);

        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        agentBillCurrencyMapper.delete(agentBillCurrencyParam);
        agentBillOrderMapper.saveBillCurrency(requestDTO);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO deleteAgentBill(@Valid BillIdDTO requestDTO) {
        AgentBillDO agentBillDO=agentBillMapper.selectByPrimaryKey(requestDTO.getBillId());
        if (agentBillDO.getBillStatus()==BillStatusEnum.WAITCHECKOUT.key){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"已通知结算，不能删除账单");
        }
        if (agentBillDO.getBillStatus()==BillStatusEnum.CHECKOUT.key){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"已结算，不能删除账单");
        }

        //更新结算状态为可出账
        UpdateBillOrderFinanceDTO updateBillOrderFinanceDTO=new UpdateBillOrderFinanceDTO();
        updateBillOrderFinanceDTO.setBillId(requestDTO.getBillId());
        updateBillOrderFinanceDTO.setAccountStatus(AccountStatusEnum.CANCHECK.key);
        agentBillOrderMapper.updateBillOrderFinance(updateBillOrderFinanceDTO);

        agentBillMapper.deleteByPrimaryKey(requestDTO.getBillId());

        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        agentBillCurrencyMapper.delete(agentBillCurrencyParam);

        AgentBillOrderDO agentBillOrderParam=new AgentBillOrderDO();
        agentBillOrderParam.setBillId(requestDTO.getBillId());
        agentBillOrderMapper.delete(agentBillOrderParam);

        AgentBillOrderItemDO agentBillOrderItemParam=new AgentBillOrderItemDO();
        agentBillOrderItemParam.setBillId(requestDTO.getBillId());
        agentBillOrderItemMapper.delete(agentBillOrderItemParam);
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    @Transactional
    public ResponseDTO createAgentBillFinanceOrder(@Valid CreateBillFinanceOrderDTO requestDTO) {
        AgentBillDO agentBillDO=agentBillMapper.selectByPrimaryKey(requestDTO.getBillId());

        //更新已通知金额
        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.select(agentBillCurrencyParam);

        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=agentBillOrderMapper.queryBillNewCurrency(billIdDTO);
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:multipleCurrencyAmountDTOList){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(multipleCurrencyAmountDTO.getReceivableAmount())!=0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"账单应收金额发生变化，请先更新账单");
                    }
                }
            }
        }

        boolean finished=true;
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:requestDTO.getMultipleCurrencyList()){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    BigDecimal noticeAmount=agentBillCurrencyDO.getNoticeAmount()==null? BigDecimal.ZERO:agentBillCurrencyDO.getNoticeAmount();
                    agentBillCurrencyDO.setNoticeAmount(noticeAmount.add(multipleCurrencyAmountDTO.getReceivableAmount()));
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                        &&agentBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)<0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应收金额不能为负数");
                    }
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&agentBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应收金额不能为正数");
                    }
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&agentBillCurrencyDO.getReceivableAmount().compareTo(agentBillCurrencyDO.getNoticeAmount())<0
                            || agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                                    &&agentBillCurrencyDO.getReceivableAmount().compareTo(agentBillCurrencyDO.getNoticeAmount())>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"通知收款金额超过账单应收金额");
                    }
                    agentBillCurrencyMapper.updateByPrimaryKeySelective(agentBillCurrencyDO);
                }
            }
            if (agentBillCurrencyDO.getReceivableAmount().compareTo(agentBillCurrencyDO.getNoticeAmount())!=0){
                finished=false;
            }
        }

        //更新账单状态
        AgentBillDO agentBillParam=new AgentBillDO();
        agentBillParam.setId(requestDTO.getBillId());
        agentBillParam.setBillStatus(BillStatusEnum.WAITCHECKOUT.key);
        agentBillMapper.updateByPrimaryKeySelective(agentBillParam);

        //创建工单
        AgentFinanceOrderRequestDTO agentFinanceOrderRequestDTO=new AgentFinanceOrderRequestDTO();
        agentFinanceOrderRequestDTO.setFinanceType(FinanceTypeEnum.AGENTBILLSATTLE.key);
        agentFinanceOrderRequestDTO.setOrderCode(agentBillDO.getBillCode());
        agentFinanceOrderRequestDTO.setBillName(agentBillDO.getBillName());
        agentFinanceOrderRequestDTO.setOrgCode(agentBillDO.getOrgCode());
        agentFinanceOrderRequestDTO.setOrgName(agentBillDO.getOrgName());
        agentFinanceOrderRequestDTO.setMerchantCode(agentBillDO.getMerchantCode());
        agentFinanceOrderRequestDTO.setCurrency(requestDTO.getMultipleCurrencyList().get(0).getCurrency());
        agentFinanceOrderRequestDTO.setNotifyAmount(requestDTO.getMultipleCurrencyList().get(0).getReceivableAmount());
        agentFinanceOrderRequestDTO.setVoucherList(requestDTO.getFinancePayItemDTOList());
        agentFinanceOrderRequestDTO.setCreator(requestDTO.getOperator());
        agentFinanceOrderRequestDTO.setCreateTime(new Date());

        if (finished){
            List<FinanceOrderItemDTO> financeOrderItemDTOList=new ArrayList<>();
            QueryBillOrderDTO queryBillOrderDTO=new QueryBillOrderDTO();
            queryBillOrderDTO.setBillId(requestDTO.getBillId());
            List<BillOrderDTO> billOrderDTOList=agentBillOrderMapper.queryAgentBillOrder(queryBillOrderDTO);
            for (BillOrderDTO billOrderDTO:billOrderDTOList){
                FinanceOrderItemDTO financeOrderItemDTO=new FinanceOrderItemDTO();
                financeOrderItemDTO.setOrderCode(billOrderDTO.getOrderCode());
                financeOrderItemDTO.setCurrency(billOrderDTO.getCurrency());
                financeOrderItemDTO.setAmount(billOrderDTO.getCurrPaidinAmount());
                financeOrderItemDTOList.add(financeOrderItemDTO);
            }
            agentFinanceOrderRequestDTO.setFinanceOrderItemDTOList(financeOrderItemDTOList);
        }
        return agentFinanceOrderService.notifyReceivableAmount(agentFinanceOrderRequestDTO);
    }

    @Override
    @Transactional
    public ResponseDTO createAgentBillFinanceOrderWithConfirm(@Valid CreateBillFinanceOrderDTO requestDTO) {
        AgentBillDO agentBillDO=agentBillMapper.selectByPrimaryKey(requestDTO.getBillId());

        //更新已通知金额
        AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
        agentBillCurrencyParam.setBillId(requestDTO.getBillId());
        List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.select(agentBillCurrencyParam);

        BillIdDTO billIdDTO=new BillIdDTO();
        billIdDTO.setBillId(requestDTO.getBillId());
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=agentBillOrderMapper.queryBillNewCurrency(billIdDTO);
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:multipleCurrencyAmountDTOList){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(multipleCurrencyAmountDTO.getReceivableAmount())!=0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"账单应收金额发生变化，请先更新账单");
                    }
                }
            }
        }

        boolean finished=true;
        for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
            for (MultipleCurrencyAmountDTO multipleCurrencyAmountDTO:requestDTO.getMultipleCurrencyList()){
                if (agentBillCurrencyDO.getCurrency().equals(multipleCurrencyAmountDTO.getCurrency())){
                    BigDecimal noticeAmount=agentBillCurrencyDO.getNoticeAmount()==null? BigDecimal.ZERO:agentBillCurrencyDO.getNoticeAmount();
                    agentBillCurrencyDO.setNoticeAmount(noticeAmount.add(multipleCurrencyAmountDTO.getReceivableAmount()));
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&agentBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)<0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应收金额不能为负数");
                    }
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&agentBillCurrencyDO.getNoticeAmount().compareTo(BigDecimal.ZERO)>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"应收金额不能为正数");
                    }
                    if (agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)>0
                            &&agentBillCurrencyDO.getReceivableAmount().compareTo(agentBillCurrencyDO.getNoticeAmount())<0
                            || agentBillCurrencyDO.getReceivableAmount().compareTo(BigDecimal.ZERO)<0
                            &&agentBillCurrencyDO.getReceivableAmount().compareTo(agentBillCurrencyDO.getNoticeAmount())>0){
                        return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"通知收款金额超过账单应收金额");
                    }
                    agentBillCurrencyMapper.updateByPrimaryKeySelective(agentBillCurrencyDO);
                }
            }
            if (agentBillCurrencyDO.getReceivableAmount().compareTo(agentBillCurrencyDO.getNoticeAmount())!=0){
                finished=false;
            }
        }

        //更新账单状态
        AgentBillDO agentBillParam=new AgentBillDO();
        agentBillParam.setId(requestDTO.getBillId());
        agentBillParam.setBillStatus(BillStatusEnum.WAITCHECKOUT.key);
        agentBillMapper.updateByPrimaryKeySelective(agentBillParam);

        //创建并确认工单
        ConfirmPayRequestDTO confirmPayRequestDTO=new ConfirmPayRequestDTO();
        confirmPayRequestDTO.setFinanceType(FinanceTypeEnum.AGENTBILLSATTLE.key);
        confirmPayRequestDTO.setOrderCode(agentBillDO.getBillCode());
        confirmPayRequestDTO.setBillName(agentBillDO.getBillName());
        confirmPayRequestDTO.setOrgCode(agentBillDO.getOrgCode());
        confirmPayRequestDTO.setOrgName(agentBillDO.getOrgName());
        confirmPayRequestDTO.setMerchantCode(agentBillDO.getMerchantCode());
        confirmPayRequestDTO.setCurrency(requestDTO.getMultipleCurrencyList().get(0).getCurrency());
        confirmPayRequestDTO.setNotifyAmount(requestDTO.getMultipleCurrencyList().get(0).getReceivableAmount());
        confirmPayRequestDTO.setVoucherList(requestDTO.getFinancePayItemDTOList());
        confirmPayRequestDTO.setCreator(requestDTO.getOperator());
        confirmPayRequestDTO.setCreateTime(new Date());

        if (finished){
            List<FinanceOrderItemDTO> financeOrderItemDTOList=new ArrayList<>();
            QueryBillOrderDTO queryBillOrderDTO=new QueryBillOrderDTO();
            queryBillOrderDTO.setBillId(requestDTO.getBillId());
            List<BillOrderDTO> billOrderDTOList=agentBillOrderMapper.queryAgentBillOrder(queryBillOrderDTO);
            for (BillOrderDTO billOrderDTO:billOrderDTOList){
                FinanceOrderItemDTO financeOrderItemDTO=new FinanceOrderItemDTO();
                financeOrderItemDTO.setOrderCode(billOrderDTO.getOrderCode());
                financeOrderItemDTO.setCurrency(billOrderDTO.getCurrency());
                financeOrderItemDTO.setAmount(billOrderDTO.getCurrPaidinAmount());
                financeOrderItemDTOList.add(financeOrderItemDTO);
            }
            confirmPayRequestDTO.setFinanceOrderItemDTOList(financeOrderItemDTOList);
        }
        return agentFinanceOrderService.confirmPay(confirmPayRequestDTO);
    }

    @Override
    public List<MultipleCurrencyAmountDTO> queryAgentBillCurrency(QueryBillCurrencyDTO requestDTO) {
        List<MultipleCurrencyAmountDTO> multipleCurrencyAmountDTOList=new ArrayList<>();
        if (requestDTO.getBalanceMethodType()==null ||requestDTO.getBalanceMethodType()==0){
            AgentBillCurrencyDO agentBillCurrencyParam=new AgentBillCurrencyDO();
            agentBillCurrencyParam.setBillId(requestDTO.getBillId());
            List<AgentBillCurrencyDO> agentBillCurrencyDOList=agentBillCurrencyMapper.select(agentBillCurrencyParam);
            for (AgentBillCurrencyDO agentBillCurrencyDO:agentBillCurrencyDOList){
                MultipleCurrencyAmountDTO multipleCurrencyAmountDTO=new MultipleCurrencyAmountDTO();
                BeanUtils.copyProperties(agentBillCurrencyDO,multipleCurrencyAmountDTO);
                multipleCurrencyAmountDTOList.add(multipleCurrencyAmountDTO);
            }
        }else{
            multipleCurrencyAmountDTOList=agentBillOrderMapper.queryAgentBillCurrency(requestDTO);
        }
        return multipleCurrencyAmountDTOList;
    }
}
