package com.travel.common.autocomplete;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *   2018/1/15.
 */

import com.travel.common.dto.AutoCompleteDTO;
import com.travel.common.dto.member.response.SupplyResponseDTO;
import com.travel.hotel.product.entity.po.HotelPO;
import com.travel.hotel.product.service.HotelService;
import com.travel.member.service.SupplierService;

/**
 *   2018/1/15.
 */
@Service("hotelCompleteService")
public class HotelCompleteService extends AbstractAutoComplete {

    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private SupplierService supplierService;
    
    @Override
    protected List<? extends AutoCompleteDTO> getData() {
        List<HotelPO> hotelPOList = hotelService.queryAll();
        AutoCompleteDTO autoCompleteDTO = null;
        List<AutoCompleteDTO> autoCompleteDTOList = new LinkedList<AutoCompleteDTO>();
        for (HotelPO po : hotelPOList){
            autoCompleteDTO = new AutoCompleteDTO();
            autoCompleteDTO.setLabel(po.getHotelName());
            autoCompleteDTO.setValue(String.valueOf(po.getHotelId()));
            List<SupplyResponseDTO> supplyList = this.supplierService.listSupplyByRelation(po.getHotelId());
            if (CollectionUtils.isNotEmpty(supplyList)) {
            	autoCompleteDTO.setSupplyCode(supplyList.get(0).getSupplyCode());
            	autoCompleteDTO.setSupplyName(supplyList.get(0).getSupplyName());
            }
            autoCompleteDTOList.add(autoCompleteDTO);
        }
        return autoCompleteDTOList;
    }
}

