package com.travel.hotel.product.service.impl;

import com.travel.common.constant.InitData;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.response.SaleStateResponseDTO;
import com.travel.common.enums.ChannelEnum;
import com.travel.common.exception.ParameterException;
import com.travel.common.utils.BeanCopy;
import com.travel.common.utils.DateUtil;
import com.travel.hotel.product.dao.SaleStatePOMapper;
import com.travel.hotel.product.entity.SaleStateDTO;
import com.travel.hotel.product.entity.po.SaleStatePO;
import com.travel.hotel.product.service.SaleStateService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *   2018/2/7.
 */
@Service("saleStateService")
public class SaleStateServiceImpl implements SaleStateService {

    @Autowired
    private SaleStatePOMapper saleStatePOMapper;

    @Override
    public List<SaleStateDTO> queryByCondition(SaleStatePO queryPO) {

        List<SaleStateDTO> resultDTOList = new ArrayList<SaleStateDTO>();

        List<SaleStatePO> saleStatePOList = saleStatePOMapper.selectByCondition(queryPO);
        if (CollectionUtils.isEmpty(saleStatePOList)){
            return resultDTOList;
        }

        saleStatePOList.forEach(
                saleStatePO -> {
                    SaleStateDTO saleStateDTO = BeanCopy.copyProperties(saleStatePO,SaleStateDTO.class);
                    saleStateDTO.setChannelName(InitData.channleMap.get(saleStatePO.getChannelCode()));
                    resultDTOList.add(saleStateDTO);
                });

        return resultDTOList;
    }

    @Override
    public SaleStateResponseDTO querySaleStateByPricePlanId(Long pricePlanId) {
        if (null == pricePlanId){
            throw new ParameterException("根据产品查询上下架状态，产品ID为空");
        }
        SaleStatePO queryPO = new SaleStateDTO();
        queryPO.setPriceplanId(pricePlanId);
        List<SaleStatePO> saleStatePOList = saleStatePOMapper.selectByCondition(queryPO);

        SaleStateResponseDTO saleStateResponseDTO = new SaleStateResponseDTO();

        //TODO 添加渠道时，这里要修改
        for (SaleStatePO po : saleStatePOList){
            saleStateResponseDTO.setPricePlanId(po.getPriceplanId());
            if (po.getChannelCode().equals(ChannelEnum.B2B.getKey())){
                saleStateResponseDTO.setStateIdToB(po.getStateId());
                saleStateResponseDTO.setSaleStateToB(po.getSaleState());
            } else if (po.getChannelCode().equals(ChannelEnum.CTRIP.getKey())){
                saleStateResponseDTO.setStateIdToCtrip(po.getStateId());
                saleStateResponseDTO.setSaleStateToCtrip(po.getSaleState());
            } else if (po.getChannelCode().equals(ChannelEnum.FEIZHU.getKey())){
                saleStateResponseDTO.setStateIdToTaobao(po.getStateId());
                saleStateResponseDTO.setSaleStateToTaobao(po.getSaleState());
            }
        }

        //TODO 添加渠道时，这里要修改
        if (null != saleStateResponseDTO.getSaleStateToB() && saleStateResponseDTO.getSaleStateToB() == 1){
            saleStateResponseDTO.setSaleStateToBValue("上架");
        } else{
            saleStateResponseDTO.setSaleStateToBValue("下架");
        }

        if (null != saleStateResponseDTO.getSaleStateToCtrip() && saleStateResponseDTO.getSaleStateToCtrip() == 1){
            saleStateResponseDTO.setSaleStateToCtripValue("上架");
        } else{
            saleStateResponseDTO.setSaleStateToCtripValue("下架");
        }

        if (null != saleStateResponseDTO.getSaleStateToTaobao() && saleStateResponseDTO.getSaleStateToTaobao() == 1){
            saleStateResponseDTO.setSaleStateToTaobaoValue("上架");
        } else{
            saleStateResponseDTO.setSaleStateToTaobaoValue("下架");
        }

        return saleStateResponseDTO;
    }

    @Override
    public int saveSaleState(SaleStateResponseDTO saleStateResponseDTO) {
        //列数据转换成行数据
        List<SaleStatePO> addList = new ArrayList<>();
        List<SaleStatePO> modifyList = new ArrayList<>();

        //TODO 添加渠道时，此处需要修改
        SaleStatePO b2bPO = this.getSaleStatePO(saleStateResponseDTO.getPricePlanId(),ChannelEnum.B2B.key,saleStateResponseDTO.getSaleStateToB(),saleStateResponseDTO.getStateIdToB(),saleStateResponseDTO.getCreator());
        SaleStatePO ctripPO = this.getSaleStatePO(saleStateResponseDTO.getPricePlanId(),ChannelEnum.CTRIP.key,saleStateResponseDTO.getSaleStateToCtrip(),saleStateResponseDTO.getStateIdToCtrip(),saleStateResponseDTO.getCreator());

        this.assortAddOrUpdate(b2bPO,addList,modifyList);
        this.assortAddOrUpdate(ctripPO,addList,modifyList);

        addList.forEach(po -> saleStatePOMapper.insertSelective(po));
        modifyList.forEach(po -> saleStatePOMapper.updateByPrimaryKeySelective(po));
        return 1;
    }

    @Override
    public PaginationDTO<SaleStatePO> querySaleStateByPage(SaleStatePO queryPO) {
        return null;
    }

    @Override
    public int batchSaveSaleState(List<SaleStateResponseDTO> saleStateResponseDTOList,String userName) {
        SaleStateResponseDTO temp = null;
        for (SaleStateResponseDTO dto : saleStateResponseDTOList){
            temp = this.querySaleStateByPricePlanId(dto.getPricePlanId());
            dto.setStateIdToB(temp.getStateIdToB());
            dto.setStateIdToCtrip(temp.getStateIdToCtrip());
            dto.setStateIdToTaobao(temp.getStateIdToTaobao());
            dto.setCreator(userName);
            this.saveSaleState(dto);
        }
        return 1;
    }

    /**
     * 分类整理是新增，还是修改操作
     * @param saleStatePO
     * @param addList
     * @param modifyList
     */
    private void assortAddOrUpdate(SaleStatePO saleStatePO, List<SaleStatePO> addList ,List<SaleStatePO> modifyList){
        if (null != saleStatePO.getStateId() && saleStatePO.getStateId().longValue() > 0){
            modifyList.add(saleStatePO);
        } else{
            addList.add(saleStatePO);
        }
    }

    private SaleStatePO getSaleStatePO(Long pricePlanId,String channelCode,Integer saleState,Long stateId,String userName){
        SaleStatePO saleStatePO = new SaleStatePO();
        saleStatePO.setStateId(stateId);
        saleStatePO.setChannelCode(channelCode);
        saleStatePO.setSaleState(saleState);
        saleStatePO.setPriceplanId(pricePlanId);
        saleStatePO.setCreator(userName);
        saleStatePO.setCreatedate(DateUtil.getCurrentDate());
        saleStatePO.setModifier(userName);
        saleStatePO.setModifydate(DateUtil.getCurrentDate());
        return saleStatePO;
    }

}
