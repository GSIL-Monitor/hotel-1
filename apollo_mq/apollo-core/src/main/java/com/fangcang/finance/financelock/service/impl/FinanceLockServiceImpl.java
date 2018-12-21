package com.fangcang.finance.financelock.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.constant.Constant;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.PropertyCopyUtil;
import com.fangcang.finance.financelock.mapper.FinanceLockMapper;
import com.fangcang.finance.financelock.request.FinanceLockOrderRequestDTO;
import com.fangcang.finance.financelock.request.LockOrderItemRequestDTO;
import com.fangcang.finance.financelock.request.QueryLockLogRequestDTO;
import com.fangcang.finance.financelock.request.UnlockOrderListRequestDTO;
import com.fangcang.finance.financelock.response.FinanceLockLogResponseDTO;
import com.fangcang.finance.financelock.response.UnlockOrderListResponseDTO;
import com.fangcang.finance.financelock.service.FinanceLockService;
import com.fangcang.order.domain.OrderFinanceDO;
import com.fangcang.order.domain.OrderFinanceLockLogDO;
import com.fangcang.order.mapper.OrderFinanceLockLogMapper;
import com.fangcang.order.mapper.OrderFinanceMapper;
import com.fangcang.order.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/4
 */
@Service
@Slf4j
public class FinanceLockServiceImpl implements FinanceLockService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderFinanceMapper orderFinanceMapper;
    @Resource
    private OrderFinanceLockLogMapper financeLockLogMapper;
    @Resource
    private FinanceLockMapper financeLockMapper;

    @Override
    public PaginationSupportDTO<UnlockOrderListResponseDTO> unlockOrderList(UnlockOrderListRequestDTO requestDTO) {
        log.info("unlockOrderList param: {}", requestDTO);
        PageHelper.startPage(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        List<UnlockOrderListResponseDTO> list = financeLockMapper.queryUnlockOrderList(requestDTO);
        PageInfo<UnlockOrderListResponseDTO> page = new PageInfo<>(list);

        PaginationSupportDTO paginationSupport = new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;

    }

    @Override
    public ResponseDTO lockOrder(FinanceLockOrderRequestDTO requestDTO) {
        log.info("lockOrder param: {}", requestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        // 1. 加、解锁订单
        if (!CollectionUtils.isEmpty(requestDTO.getOrderList())) {
            for (LockOrderItemRequestDTO itemRequestDTO : requestDTO.getOrderList()) {
                OrderFinanceDO orderFinanceUpdate = new OrderFinanceDO();
                orderFinanceUpdate.setFinanceLockStatus(itemRequestDTO.getLockType().byteValue());

                Example example = new Example(OrderFinanceDO.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("orderId", itemRequestDTO.getOrderId());
                orderFinanceMapper.updateByExampleSelective(orderFinanceUpdate, example);
                // 2. 记日志
                OrderFinanceLockLogDO financeLockLogInsert = new OrderFinanceLockLogDO();
                financeLockLogInsert.setOrderId(itemRequestDTO.getOrderId());
                financeLockLogInsert.setContent(itemRequestDTO.getLockType() == 1 ? "订单已锁定" : "订单已解锁");
                financeLockLogInsert.setOperator(requestDTO.getModifier());
                financeLockLogInsert.setOperatorTime(requestDTO.getModifyTime());
                financeLockLogMapper.insert(financeLockLogInsert);
            }
        }
        responseDTO.setResult(Constant.YES);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<FinanceLockLogResponseDTO>> queryLockLog(QueryLockLogRequestDTO requestDTO) {
        ResponseDTO<List<FinanceLockLogResponseDTO>> responseDTO = new ResponseDTO<>();
        OrderFinanceLockLogDO financeLockLogQuery = new OrderFinanceLockLogDO();
        financeLockLogQuery.setOrderId(requestDTO.getOrderId());
        List<OrderFinanceLockLogDO> financeLockLogDOList = financeLockLogMapper.select(financeLockLogQuery);
        List<FinanceLockLogResponseDTO> lockLogResponseDTOList = new ArrayList<>();
        for (OrderFinanceLockLogDO logDO : financeLockLogDOList) {
            FinanceLockLogResponseDTO lockLogResponseDTO = PropertyCopyUtil.transfer(logDO, FinanceLockLogResponseDTO.class);
            lockLogResponseDTO.setOperatorTime(DateUtil.dateToStringWithHms(logDO.getOperatorTime()));
            lockLogResponseDTOList.add(lockLogResponseDTO);
        }

        responseDTO.setResult(ResultCodeEnum.SUCCESS.code);
        responseDTO.setModel(lockLogResponseDTOList);
        return responseDTO;
    }
}
