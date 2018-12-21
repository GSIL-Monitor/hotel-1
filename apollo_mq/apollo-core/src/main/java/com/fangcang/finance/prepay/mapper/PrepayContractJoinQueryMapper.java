package com.fangcang.finance.prepay.mapper;

import com.fangcang.finance.prepay.request.QueryPrepayListRequestDTO;
import com.fangcang.finance.prepay.request.QuerySupplyDoneRoomNightRequestDTO;
import com.fangcang.finance.prepay.response.QueryAllPrepayContractResponseDTO;
import com.fangcang.finance.prepay.response.QueryPrepayContractListResponseDTO;

import java.util.List;

/**
 * @author : zhanwang
 * @date : 2018/7/6
 */
public interface PrepayContractJoinQueryMapper {
    /**
     * 预付款合约查询
     *
     * @param requestDTO
     * @return
     */
    List<QueryPrepayContractListResponseDTO> queryPrepayContractList(QueryPrepayListRequestDTO requestDTO);

    /**
     * 查询所有预付款合约
     *
     * @return
     */
    List<QueryAllPrepayContractResponseDTO> queryAllPrepayContract();

    /**
     * 查询供应商成交间夜数
     *
     * @param requestDTO
     * @return
     */
    Integer querySupplyDoneRoomNight(QuerySupplyDoneRoomNightRequestDTO requestDTO);
}
