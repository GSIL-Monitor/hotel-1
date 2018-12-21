package com.fangcang.finance.prepay.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.CurrencyEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.finance.prepay.domain.PrepayContractDO;
import com.fangcang.finance.prepay.domain.PrepayContractItemDO;
import com.fangcang.finance.prepay.domain.PrepayContractLogDO;
import com.fangcang.finance.prepay.domain.PrepayContractTargetDO;
import com.fangcang.finance.prepay.domain.PrepayRechargeItemAttchDO;
import com.fangcang.finance.prepay.domain.PrepayRechargeItemDO;
import com.fangcang.finance.prepay.dto.PrepayContractItemDTO;
import com.fangcang.finance.prepay.dto.PrepayContractLogDTO;
import com.fangcang.finance.prepay.dto.PrepayContractTargetDTO;
import com.fangcang.finance.prepay.dto.PrepayRechargeItemAttchDTO;
import com.fangcang.finance.prepay.mapper.PrepayContractItemMapper;
import com.fangcang.finance.prepay.mapper.PrepayContractJoinQueryMapper;
import com.fangcang.finance.prepay.mapper.PrepayContractLogMapper;
import com.fangcang.finance.prepay.mapper.PrepayContractMapper;
import com.fangcang.finance.prepay.mapper.PrepayContractTargetMapper;
import com.fangcang.finance.prepay.mapper.PrepayRechargeItemAttchMapper;
import com.fangcang.finance.prepay.mapper.PrepayRechargeItemMapper;
import com.fangcang.finance.prepay.request.AddOrUpdatePrepayContractRequestDTO;
import com.fangcang.finance.prepay.request.DebuctOrRetreatPrepayRequestDTO;
import com.fangcang.finance.prepay.request.PrepayLogInfoRequestDTO;
import com.fangcang.finance.prepay.request.PrepayRechargeRequestDTO;
import com.fangcang.finance.prepay.request.QueryPrepayItemListRequestDTO;
import com.fangcang.finance.prepay.request.QueryPrepayListRequestDTO;
import com.fangcang.finance.prepay.request.QueryRechargeListRequestDTO;
import com.fangcang.finance.prepay.request.QuerySupplyDoneRoomNightRequestDTO;
import com.fangcang.finance.prepay.request.UpdateDepositAmountRequestDTO;
import com.fangcang.finance.prepay.response.QueryAllPrepayContractResponseDTO;
import com.fangcang.finance.prepay.response.QueryPrepayContractListResponseDTO;
import com.fangcang.finance.prepay.response.QueryRechargeListResponseDTO;
import com.fangcang.finance.prepay.service.PrepayContractService;
import com.fangcang.order.domain.SupplyOrderDO;
import com.fangcang.order.mapper.SupplyOrderMapper;
import com.fangcang.supplier.domain.SupplyDO;
import com.fangcang.supplier.mapper.SupplyMapper;
import com.fangcang.supplier.request.SingleUserRequestDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/6
 */
@Service
@Slf4j
public class PrepayContractServiceImpl implements PrepayContractService {
    @Resource
    private PrepayContractMapper prepayContractMapper;
    @Resource
    private PrepayContractItemMapper prepayContractItemMapper;
    @Resource
    private PrepayContractTargetMapper prepayContractTargetMapper;
    @Resource
    private PrepayRechargeItemMapper prepayRechargeItemMapper;
    @Resource
    private PrepayRechargeItemAttchMapper prepayRechargeItemAttchMapper;
    @Resource
    private PrepayContractJoinQueryMapper contractJoinQueryMapper;
    @Resource
    private SupplyMapper supplyMapper;
    @Resource
    private SupplyOrderMapper supplyOrderMapper;
    @Resource
    private PrepayContractLogMapper prepayContractLogMapper;

    @Override
    public PaginationSupportDTO<QueryPrepayContractListResponseDTO> queryPrepayContractList(QueryPrepayListRequestDTO requestDTO) {
        // 1. 分页查询列表
        log.info("queryPrepayContractList param: {}", requestDTO);
        Page<QueryPrepayContractListResponseDTO> page =
                PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize())
                          .doSelectPage(() -> contractJoinQueryMapper.queryPrepayContractList(requestDTO));

        PaginationSupportDTO<QueryPrepayContractListResponseDTO> paginationSupport = new PaginationSupportDTO<>();
        paginationSupport.setItemList(page.getResult());
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    @Transactional
    public ResponseDTO<Integer> addOrUpdatePrepayContract(AddOrUpdatePrepayContractRequestDTO requestDTO) {
        log.info("addOrUpdatePrepayContract param: {}", requestDTO);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        // 1. 新增或修改预付款合约
        PrepayContractDO prepayContractDO = PropertyCopyUtil.transfer(requestDTO, PrepayContractDO.class);
        Integer contractId;
        if (requestDTO.getId() == null) {
            // 1.1 新增合约
            prepayContractDO.setSettlementAmount(BigDecimal.ZERO);
            prepayContractDO.setDoneRoomNight(0);
            prepayContractDO.setUnDoneRoomNight(requestDTO.getTargetRoomNightSum());
            prepayContractDO.setPrepayAmount(BigDecimal.ZERO);
            prepayContractMapper.insert(prepayContractDO);
            contractId = prepayContractDO.getId();
            // 1.2 新增合约目标间夜
            List<PrepayContractTargetDO> contractTargetDOList = PropertyCopyUtil.transferArray(requestDTO.getContractTargetDTOList(), PrepayContractTargetDO.class);
            for (PrepayContractTargetDO contractTargetDO : contractTargetDOList) {
                Integer month = Integer.valueOf(contractTargetDO.getMonth());
                contractTargetDO.setMonth(month + "");
                contractTargetDO.setContractId(contractId);
                contractTargetDO.setDoneRoomNight(0);
            }
            prepayContractTargetMapper.insertList(contractTargetDOList);
        } else {
            // 2.1 修改合约
            prepayContractMapper.updateByPrimaryKeySelective(prepayContractDO);
            // 2.2 更新合约目标间夜(先删后加)
            PrepayContractTargetDO contractTargetDelete = new PrepayContractTargetDO();
            contractTargetDelete.setContractId(requestDTO.getId());
            prepayContractTargetMapper.delete(contractTargetDelete);
            List<PrepayContractTargetDO> contractTargetDOList = PropertyCopyUtil.transferArray(requestDTO.getContractTargetDTOList(), PrepayContractTargetDO.class);
            for (PrepayContractTargetDO contractTargetDO : contractTargetDOList) {
                Integer month = Integer.valueOf(contractTargetDO.getMonth());
                contractTargetDO.setMonth(month + "");
                contractTargetDO.setDoneRoomNight(0);
                contractTargetDO.setContractId(requestDTO.getId());
            }
            prepayContractTargetMapper.insertList(contractTargetDOList);
            contractId = requestDTO.getId();

            // 2.3 记日志
            PrepayContractLogDO contractLogDO = new PrepayContractLogDO();
            contractLogDO.setContractId(contractId);
            contractLogDO.setType(Byte.valueOf(1 + ""));
            contractLogDO.setContent("修改合约，合同有效日期：" + (requestDTO.getValidBeginDate() + "~" + requestDTO.getValidEndDate())
                    + "，目标间夜：" + requestDTO.getTargetRoomNightSum() + "，合同金额：" + requestDTO.getContractAmount());
            contractLogDO.setOperator(requestDTO.getModifier());
            contractLogDO.setOperatorTime(requestDTO.getModifyTime());
            prepayContractLogMapper.insert(contractLogDO);
        }

        responseDTO.setResult(Constant.YES);
        responseDTO.setModel(contractId);
        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO recharge(PrepayRechargeRequestDTO requestDTO) {
        log.info("recharge param，充值记录Id：{}", requestDTO.getId());
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 插入预付款充值表
        PrepayRechargeItemDO rechargeItemInsert = PropertyCopyUtil.transfer(requestDTO, PrepayRechargeItemDO.class);
        rechargeItemInsert.setArrivalDate(DateUtil.stringToDateWithHms(requestDTO.getArrivalDate()));
        prepayRechargeItemMapper.insert(rechargeItemInsert);
        Integer rechargeItemId = rechargeItemInsert.getId();

        // 2. 插入预付款充值附件表
        if (CollectionUtils.isNotEmpty(requestDTO.getAttchDTOList())) {
            List<PrepayRechargeItemAttchDO> itemAttchDOList = PropertyCopyUtil.transferArray(requestDTO.getAttchDTOList(), PrepayRechargeItemAttchDO.class);
            for (PrepayRechargeItemAttchDO itemAttchDO : itemAttchDOList) {
                itemAttchDO.setRechargeItemId(rechargeItemId);
                itemAttchDO.setCreator(requestDTO.getCreator());
                itemAttchDO.setCreateTime(DateUtil.stringToDateWithHms(requestDTO.getCreateTime()));
            }
            prepayRechargeItemAttchMapper.insertList(itemAttchDOList);
        }

        // 3. 更新预付款金额
        PrepayContractDO prepayContractDO = prepayContractMapper.selectByPrimaryKey(requestDTO.getContractId());
        BigDecimal prepayAmount = BigDecimal.ZERO;
        prepayAmount = prepayAmount.add(prepayContractDO.getPrepayAmount()).add(requestDTO.getAmount());
        PrepayContractDO prepayContractUpdate = new PrepayContractDO();
        prepayContractUpdate.setId(requestDTO.getContractId());
        prepayContractUpdate.setPrepayAmount(prepayAmount);
        prepayContractMapper.updateByPrimaryKeySelective(prepayContractUpdate);

        // 4. 更新供应商可用余额
        SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(prepayContractDO.getSupplyId().longValue());
        SupplyDO supplyDO = supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
        //供应商可用余额=所有预付款金额-已结算金额-押金
        BigDecimal prepayBalance = BigDecimal.ZERO;
        PrepayContractDO prepayContractQuery = new PrepayContractDO();
        prepayContractQuery.setSupplyId(prepayContractDO.getSupplyId());
        List<PrepayContractDO> prepayContractDOList = prepayContractMapper.select(prepayContractQuery);
        if (CollectionUtils.isNotEmpty(prepayContractDOList)) {
            for (PrepayContractDO contractDO : prepayContractDOList) {
                prepayBalance = prepayBalance.add(contractDO.getPrepayAmount().subtract(contractDO.getSettlementAmount()));
            }
        }
        if (supplyDO.getDepositAmount() != null) {
            prepayBalance = prepayBalance.subtract(supplyDO.getDepositAmount());
        }
        if (prepayBalance.compareTo(BigDecimal.ZERO) < 0) {
            prepayBalance = BigDecimal.ZERO;
        }
        SupplyDO supplyUpdate = new SupplyDO();
        supplyUpdate.setSupplyId(prepayContractDO.getSupplyId().longValue());
        supplyUpdate.setDepositAmount(supplyDO.getDepositAmount() == null ? BigDecimal.ZERO : supplyDO.getDepositAmount());
        supplyUpdate.setPrepayBalance(prepayBalance);
        supplyUpdate.setModifier(requestDTO.getCreator());
        supplyUpdate.setModifyTime(DateUtil.stringToDateWithHms(requestDTO.getCreateTime()));
        supplyMapper.updateSupply(supplyUpdate);

        // 5. 插入交易记录
        PrepayContractItemDO contractItemInsert = new PrepayContractItemDO();
        contractItemInsert.setContractId(requestDTO.getContractId());
        contractItemInsert.setType(1);
        contractItemInsert.setContent("充值");
        contractItemInsert.setAmount(requestDTO.getAmount());
        contractItemInsert.setBalance(prepayAmount.subtract(prepayContractDO.getSettlementAmount()));
        contractItemInsert.setCreator(requestDTO.getCreator());
        contractItemInsert.setCreateTime(DateUtil.stringToDateWithHms(requestDTO.getCreateTime()));
        prepayContractItemMapper.insert(contractItemInsert);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public PaginationSupportDTO<QueryRechargeListResponseDTO> queryRechargeList(QueryRechargeListRequestDTO requestDTO) {
        log.info("queryRechargeList param: {}", requestDTO);
        // 1. 分页查询预付款合约充值记录
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        PrepayRechargeItemDO rechargeItemQuery = new PrepayRechargeItemDO();
        rechargeItemQuery.setContractId(requestDTO.getContractId());
        List<PrepayRechargeItemDO> rechargeItemDOList = prepayRechargeItemMapper.select(rechargeItemQuery);
        List<QueryRechargeListResponseDTO> rechargeItemDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(rechargeItemDOList)) {
            for (PrepayRechargeItemDO rechargeItemDO : rechargeItemDOList) {
                QueryRechargeListResponseDTO rechargeItemDTO = PropertyCopyUtil.transfer(rechargeItemDO, QueryRechargeListResponseDTO.class);
                rechargeItemDTO.setCreateTime(DateUtil.dateToStringWithHms(rechargeItemDO.getCreateTime()));
                // 2. 查附件明细
                PrepayRechargeItemAttchDO attchQuery = new PrepayRechargeItemAttchDO();
                attchQuery.setRechargeItemId(rechargeItemDO.getId());
                List<PrepayRechargeItemAttchDO> itemAttchDOList = prepayRechargeItemAttchMapper.select(attchQuery);
                if (CollectionUtils.isNotEmpty(itemAttchDOList)) {
                    List<PrepayRechargeItemAttchDTO> attchDTOList = PropertyCopyUtil.transferArray(itemAttchDOList, PrepayRechargeItemAttchDTO.class);
                    rechargeItemDTO.setAttchDTOList(attchDTOList);
                }
                rechargeItemDTOList.add(rechargeItemDTO);
            }
        }
        PageInfo<QueryRechargeListResponseDTO> page = new PageInfo<>(rechargeItemDTOList);

        PaginationSupportDTO<QueryRechargeListResponseDTO> paginationSupport = new PaginationSupportDTO<>();
        paginationSupport.setItemList(rechargeItemDTOList);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<PrepayContractItemDTO> queryPrepayItemList(QueryPrepayItemListRequestDTO requestDTO) {
        // 1. 分页查询预付款交易明细
        log.info("queryPrepayItemList param: {}", requestDTO);
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());

        Example example = new Example(PrepayContractItemDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("contractId", requestDTO.getContractId());
        if (StringUtils.isNotEmpty(requestDTO.getSettlementStartDate()) && StringUtils.isNotEmpty(requestDTO.getSettlementEndDate())) {
            criteria.andBetween("createTime", requestDTO.getSettlementStartDate(), requestDTO.getSettlementEndDate().trim() + " 23:59:59");
        }
        if (StringUtils.isNotEmpty(requestDTO.getBillNameOrCode())) {
            if (isNumericOrChar(requestDTO.getBillNameOrCode())) {
                criteria.andLike("orderCode", "%" + requestDTO.getBillNameOrCode() + "%");
            } else {
                criteria.andLike("billName", "%" + requestDTO.getBillNameOrCode() + "%");
            }
        }
        List<PrepayContractItemDO> prepayContractItemDOList = prepayContractItemMapper.selectByExample(example);
        List<PrepayContractItemDTO> contractItemDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(prepayContractItemDOList)) {
            for (PrepayContractItemDO prepayContractItemDO : prepayContractItemDOList) {
                PrepayContractItemDTO contractItemDTO = PropertyCopyUtil.transfer(prepayContractItemDO, PrepayContractItemDTO.class);
                contractItemDTO.setCurrency(CurrencyEnum.CNY.value);
                contractItemDTO.setCreateTime(DateUtil.dateToStringWithHms(prepayContractItemDO.getCreateTime()));
                contractItemDTOList.add(contractItemDTO);
            }
        }
        PageInfo<PrepayContractItemDTO> page = new PageInfo<>(contractItemDTOList);

        PaginationSupportDTO<PrepayContractItemDTO> paginationSupport = new PaginationSupportDTO<>();
        paginationSupport.setItemList(contractItemDTOList);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    /**
     * 判断字符串是否是数字和字母组成
     *
     * @param str
     * @return
     */
    private static boolean isNumericOrChar(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    @Override
    @Transactional
    public ResponseDTO updateDepositAmount(UpdateDepositAmountRequestDTO requestDTO) {
        log.info("updateDepositAmount param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 更新商家在供应商的押金、预付款余额
        SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(requestDTO.getSupplyId().longValue());
        SupplyDO supplyDO = supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
        BigDecimal prepayBalance = BigDecimal.ZERO;
        if (supplyDO.getPrepayBalance() != null) {
            prepayBalance = prepayBalance.add(supplyDO.getPrepayBalance());
        }
        if (supplyDO.getDepositAmount() == null) {
            supplyDO.setDepositAmount(BigDecimal.ZERO);
        }
        // 预付款余额 = 原预付款余额+旧的押金-新的押金
        prepayBalance = prepayBalance.add(supplyDO.getDepositAmount()).subtract(requestDTO.getDepositAmount());
        if (prepayBalance.compareTo(BigDecimal.ZERO) < 0) {
            prepayBalance = BigDecimal.ZERO;
        }
        SupplyDO supplyUpdate = new SupplyDO();
        supplyUpdate.setSupplyId(requestDTO.getSupplyId().longValue());
        supplyUpdate.setDepositAmount(requestDTO.getDepositAmount());
        supplyUpdate.setPrepayBalance(prepayBalance);
        supplyUpdate.setModifier(requestDTO.getModifier());
        supplyUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyMapper.updateSupply(supplyUpdate);
        // 2. 记日志
        PrepayContractLogDO contractLogDO = new PrepayContractLogDO();
        contractLogDO.setSupplyId(requestDTO.getSupplyId());
        contractLogDO.setType(Byte.valueOf(2 + ""));
        contractLogDO.setContent("押金改为" + requestDTO.getDepositAmount());
        contractLogDO.setOperator(requestDTO.getModifier());
        contractLogDO.setOperatorTime(requestDTO.getModifyTime());
        prepayContractLogMapper.insert(contractLogDO);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void statisSupplyDoneRoomNight() {
        // 1. 查询所有预付款合约
        List<QueryAllPrepayContractResponseDTO> prepayContractDTOList = contractJoinQueryMapper.queryAllPrepayContract();
        if (CollectionUtils.isNotEmpty(prepayContractDTOList)) {
            for (QueryAllPrepayContractResponseDTO prepayContractDTO : prepayContractDTOList) {
                // 2. 查询合约已成交间夜， 并更新
                QuerySupplyDoneRoomNightRequestDTO contractRequestDTO = new QuerySupplyDoneRoomNightRequestDTO();
                contractRequestDTO.setSupplyCode(prepayContractDTO.getSupplyCode());
                contractRequestDTO.setStartDate(DateUtil.dateToString(prepayContractDTO.getValidBeginDate()));
                contractRequestDTO.setEndDate(DateUtil.dateToString(prepayContractDTO.getValidEndDate()));
                Integer roomNight = contractJoinQueryMapper.querySupplyDoneRoomNight(contractRequestDTO);
                if (roomNight != null) {
                    PrepayContractDO prepayContractUpdate = new PrepayContractDO();
                    prepayContractUpdate.setId(prepayContractDTO.getContractId());
                    prepayContractUpdate.setDoneRoomNight(roomNight);
                    prepayContractUpdate.setUnDoneRoomNight(prepayContractDTO.getTargetRoomNightSum() - roomNight);
                    prepayContractMapper.updateByPrimaryKeySelective(prepayContractUpdate);
                }
                // 3. 查询合约每月已完成间夜，并更新
                if (CollectionUtils.isNotEmpty(prepayContractDTO.getContractTargetDTOList())) {
                    for (PrepayContractTargetDTO contractTargetDTO : prepayContractDTO.getContractTargetDTOList()) {
                        QuerySupplyDoneRoomNightRequestDTO contractTargetRequestDTO = new QuerySupplyDoneRoomNightRequestDTO();
                        contractTargetRequestDTO.setSupplyCode(prepayContractDTO.getSupplyCode());
                        contractTargetRequestDTO.setStartDate(contractTargetDTO.getYear() + "-" + contractTargetDTO.getMonth() + "-" + "01");
                        contractTargetRequestDTO.setEndDate(contractTargetDTO.getYear() + "-" + contractTargetDTO.getMonth() + "-" + "31");
                        Integer roomNightOfMonth = contractJoinQueryMapper.querySupplyDoneRoomNight(contractTargetRequestDTO);
                        if (roomNightOfMonth != null) {
                            PrepayContractTargetDO contractTargetUpdate = new PrepayContractTargetDO();
                            contractTargetUpdate.setId(contractTargetDTO.getId());
                            contractTargetUpdate.setDoneRoomNight(roomNightOfMonth);
                            prepayContractTargetMapper.updateByPrimaryKeySelective(contractTargetUpdate);
                        }
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public ResponseDTO debuctOrRetreatPrepay(DebuctOrRetreatPrepayRequestDTO requestDTO) {
        log.info("debuctOrRetreatPrepayAmount param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 检验余额是否足够
        PrepayContractDO prepayContractDO = prepayContractMapper.selectByPrimaryKey(requestDTO.getContractId());
        if (prepayContractDO == null) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("操作失败，预付款合约（" + requestDTO.getContractId() + "）不存在");
            return responseDTO;
        }
        SingleUserRequestDTO singleUserRequestDTO = new SingleUserRequestDTO();
        singleUserRequestDTO.setSupplyId(prepayContractDO.getSupplyId().longValue());
        SupplyDO supplyDO = supplyMapper.selectSingleSupplyInfo(singleUserRequestDTO);
        boolean balanceIsEnough = supplyDO.getPrepayBalance().compareTo(requestDTO.getAmount()) >= 0;
        if (!balanceIsEnough) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("预付款（" + prepayContractDO.getId() + "）余额不够，请先充值");
            return responseDTO;
        }
        // 2. 更新预付款合约结算金额
        PrepayContractDO prepayContractUpdate = new PrepayContractDO();
        prepayContractUpdate.setId(prepayContractDO.getId());
        prepayContractUpdate.setSettlementAmount(prepayContractDO.getSettlementAmount().add(requestDTO.getAmount()));
        prepayContractMapper.updateByPrimaryKeySelective(prepayContractUpdate);
        // 3. 更新供应商可用余额
        SupplyDO supplyUpdate = new SupplyDO();
        supplyUpdate.setSupplyId(prepayContractDO.getSupplyId().longValue());
        supplyUpdate.setPrepayBalance(supplyDO.getPrepayBalance().subtract(requestDTO.getAmount()));
        supplyUpdate.setModifier(requestDTO.getModifier());
        supplyUpdate.setModifyTime(requestDTO.getModifyTime());
        supplyMapper.updateSupply(supplyUpdate);
        // 4. 添加预付款交易记录
        PrepayContractItemDO contractItemInsert = PropertyCopyUtil.transfer(requestDTO, PrepayContractItemDO.class);
        contractItemInsert.setFinanceCode(requestDTO.getFinanceOrderId());
        contractItemInsert.setType(requestDTO.getType() == 1 ? 2 : 1);
        contractItemInsert.setBalance(prepayContractDO.getPrepayAmount().subtract(prepayContractUpdate.getSettlementAmount()));
        contractItemInsert.setContent(StringUtils.isEmpty(contractItemInsert.getContent()) ? "预付款" : contractItemInsert.getContent());
        if (requestDTO.getOrderType() != null && requestDTO.getOrderType() == 2 && StringUtils.isNotEmpty(requestDTO.getOrderCode())) {
            SupplyOrderDO supplyOrderQuery = new SupplyOrderDO();
            supplyOrderQuery.setSupplyOrderCode(requestDTO.getOrderCode());
            List<SupplyOrderDO> supplyOrderDOS = supplyOrderMapper.select(supplyOrderQuery);
            contractItemInsert.setOrderId(supplyOrderDOS.get(0).getOrderId());
        }
        prepayContractItemMapper.insert(contractItemInsert);

        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<PrepayContractLogDTO>> prepayLogInfo(PrepayLogInfoRequestDTO requestDTO) {
        ResponseDTO<List<PrepayContractLogDTO>> responseDTO = new ResponseDTO<>();
        if (requestDTO.getContractId() == null && requestDTO.getSupplyId() == null) {
            responseDTO.setResult(Constant.NO);
            responseDTO.setFailReason("请求参数不正确");
            return responseDTO;
        }
        PrepayContractLogDO prepayContractLogQuery = new PrepayContractLogDO();
        prepayContractLogQuery.setType(requestDTO.getType().byteValue());
        prepayContractLogQuery.setContractId(requestDTO.getContractId());
        prepayContractLogQuery.setSupplyId(requestDTO.getSupplyId());
        List<PrepayContractLogDO> logDOS = prepayContractLogMapper.select(prepayContractLogQuery);
        if (CollectionUtils.isNotEmpty(logDOS)) {
            List<PrepayContractLogDTO> prepayContractLogDTOS = new ArrayList<>();
            for (PrepayContractLogDO logDO : logDOS) {
                PrepayContractLogDTO contractLogDTO = PropertyCopyUtil.transfer(logDO, PrepayContractLogDTO.class);
                contractLogDTO.setOperatorTime(DateUtil.dateToStringWithHms(logDO.getOperatorTime()));
                prepayContractLogDTOS.add(contractLogDTO);
            }
            responseDTO.setModel(prepayContractLogDTOS);
        }

        responseDTO.setResult(Constant.YES);
        return responseDTO;

    }
}
