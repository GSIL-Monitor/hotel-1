package com.travel.hotel.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.constant.InitData;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.member.query.SupplyQueryDTO;
import com.travel.common.dto.member.request.SupplyRequestDTO;
import com.travel.common.dto.member.response.SupplyResponseDTO;
import com.travel.common.enums.AdditionalTypeEnum;
import com.travel.common.enums.SettlementEnum;
import com.travel.common.exception.ServiceException;
import com.travel.hotel.product.dao.AdditionalChargePOMapper;
import com.travel.hotel.product.dao.HotelPOMapper;
import com.travel.hotel.product.entity.HotelDTO;
import com.travel.hotel.product.entity.po.AdditionalChargePO;
import com.travel.hotel.product.entity.po.HotelPO;
import com.travel.hotel.product.entity.po.SupplyHotelRelationPO;
import com.travel.hotel.product.service.HotelService;
import com.travel.hotel.product.service.SupplyHotelRelationService;
import com.travel.member.entity.Supply;
import com.travel.member.service.SupplierService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *   2017/12/20.
 */
@Service
public class HotelServiceImpl implements HotelService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotelPOMapper hotelPOMapper;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplyHotelRelationService supplyHotelRelationService;
    
    @Autowired
	private AdditionalChargePOMapper additionalChargePOMapper;

    @Override
    public HotelDTO queryByHotelId(Long hotelId,String supplyCode) {
        HotelDTO hotelDTO = new HotelDTO();
        HotelPO queryPO = new HotelPO();
        queryPO.setHotelId(hotelId);
        queryPO.setSupplyCode(supplyCode);
        List<HotelPO> hotelPOList = hotelPOMapper.selectByCondition(queryPO);
        HotelPO hotelPO = hotelPOList.get(0);
        BeanUtils.copyProperties(hotelPO, hotelDTO);
        if (!StringUtils.isEmpty(hotelPO.getStar())){
            hotelDTO.setStarName(InitData.starMap.get(hotelPO.getStar()));
        }
        if (!StringUtils.isEmpty(hotelPO.getCityCode())){
            hotelDTO.setCityName(InitData.cityMap.get(hotelPO.getCityCode()));
        }
        if(null !=hotelPO.getPreHoldQuota()){
            hotelDTO.setPreHoldQuotaName(1 == hotelPO.getPreHoldQuota()?"是":"否");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(hotelPO.getSettlement())) {
            hotelDTO.setSettlementText(SettlementEnum.getDescripByCode(hotelPO.getSettlement()));
        }
        List<AdditionalChargePO> list = this.additionalChargePOMapper.queryAdditionalChargeByHotelId(hotelPO.getHotelId());
        if (CollectionUtils.isNotEmpty(list)) {
        	for (AdditionalChargePO addit : list) {
        		if (StringUtils.isNotBlank(addit.getChargeType())) {
        			if (AdditionalTypeEnum.BED.key.equals(addit.getChargeType())) {
        				hotelDTO.setBedSalePrice(addit.getBasePrice());
        				hotelDTO.setBedSaleCurrency(addit.getBaseCurrency());
        			} else if (AdditionalTypeEnum.BREAKFAST.key.equals(addit.getChargeType())) {
        				hotelDTO.setBreakfastSalePrice(addit.getBasePrice());
        				hotelDTO.setBreakfastSaleCurrency(addit.getBaseCurrency());
        			}
        		}
        	}
        }
        return hotelDTO;
    }

    @Override
    public PaginationDTO<HotelDTO> queryHotelList(HotelPO queryPO) {
    	PaginationDTO<HotelDTO> support = new PaginationDTO<HotelDTO>();
    	List<HotelDTO> hotelDTOList = new ArrayList<HotelDTO>();
        try{
            if(null == queryPO){
                return null;
            }
            PageHelper.startPage(queryPO.getCurrentPage(), queryPO.getPageSize());
            List<HotelPO> hotelInfoList = hotelPOMapper.selectByCondition(queryPO);
            PageInfo<HotelPO> pageInfo = new PageInfo<HotelPO>(hotelInfoList);
            HotelDTO hotelDTO = null;
            if (CollectionUtils.isNotEmpty(pageInfo.getList())){
                for (HotelPO po : hotelInfoList){
                    hotelDTO = new HotelDTO();
                    BeanUtils.copyProperties(po,hotelDTO);
                    hotelDTO.setCityName(InitData.cityMap.get(po.getCityCode()));
                    hotelDTO.setStarName(InitData.starMap.get(po.getStar()));
                    if (null != po.getPreHoldQuota()){
                        hotelDTO.setPreHoldQuotaName(1 == po.getPreHoldQuota() ?"是":"否");
                    }
                    hotelDTOList.add(hotelDTO);
                }
            }
            support.setRecordList(hotelDTOList);
            support.setPageSize(pageInfo.getPageSize());
            support.setCurrentPage(pageInfo.getPageNum());
            support.setTotalCount(Long.valueOf(pageInfo.getTotal()).intValue());
            support.setTotalPages(pageInfo.getPages());
            return support;
        }catch (Exception e){
            logger.error("HotelServiceImpl.queryHotelList. " + queryPO.toString() , e);
            throw new ServiceException("HotelServiceImpl.queryHotelList. " , e);
        }
    }

    @Override
    public List<HotelPO> queryAll() {
        return hotelPOMapper.selectByCondition(null);
    }

    @Transactional
    @Override
    public int addHotel(HotelPO po,Boolean supplyExist) {
        //如果供应商不存在，就新建供应商
        if (!supplyExist){
            Supply s = supplierService.saveSupply(getSupplyRequestDTO(po));
            po.setSupplyCode(s.getSupplyCode());
        }

        //1、保存酒店
        po.setIsactive(1);
        int result = hotelPOMapper.insertSelective(po);
        
        //保存加床附加费
        if (null != po.getBedSalePrice()) {
        	AdditionalChargePO addit = new AdditionalChargePO();
        	addit.setChargeType(AdditionalTypeEnum.BED.key);
        	addit.setChargeName(AdditionalTypeEnum.BED.descrip);
        	addit.setBasePrice(po.getBedSalePrice());
        	addit.setBaseCurrency(po.getBedSaleCurrency());
        	addit.setSaleBPrice(po.getBedSalePrice());
        	addit.setSaleBCurrency(po.getBedSaleCurrency());
        	addit.setSaleCPrice(po.getBedSalePrice());
        	addit.setSaleCCurrency(po.getBedSaleCurrency());
        	addit.setHotelId(po.getHotelId());
        	addit.setCreator(po.getCreator());
        	addit.setCreatedate(new Date());
        	this.additionalChargePOMapper.insertSelective(addit);
        }
        
        //保存加早附加费
        if (null != po.getBreakfastSalePrice()) {
        	AdditionalChargePO addit = new AdditionalChargePO();
        	addit.setChargeType(AdditionalTypeEnum.BREAKFAST.key);
        	addit.setChargeName(AdditionalTypeEnum.BREAKFAST.descrip);
        	addit.setBasePrice(po.getBreakfastSalePrice());
        	addit.setBaseCurrency(po.getBreakfastSaleCurrency());
        	addit.setSaleBPrice(po.getBreakfastSalePrice());
        	addit.setSaleBCurrency(po.getBreakfastSaleCurrency());
        	addit.setSaleCPrice(po.getBreakfastSalePrice());
        	addit.setSaleCCurrency(po.getBreakfastSaleCurrency());
        	addit.setHotelId(po.getHotelId());
        	addit.setCreator(po.getCreator());
        	addit.setCreatedate(new Date());
        	this.additionalChargePOMapper.insertSelective(addit);
        }

        //2、保存酒店和供应商的关系
        SupplyHotelRelationPO shr = new SupplyHotelRelationPO();
        shr.setHotelId(po.getHotelId());
        shr.setSupplyCode(po.getSupplyCode());
        supplyHotelRelationService.addRelation(shr);
        return result;
    }

    @Override
    public Map<String, String> querySupplyMap() {
        Map<String,String> supplyMap = new LinkedHashMap<String,String>();
        SupplyQueryDTO supplyQuery = new SupplyQueryDTO();
        supplyQuery.setIsActive(1);
        List<SupplyResponseDTO> supplyList = supplierService.listSupply(supplyQuery);
        if (CollectionUtils.isEmpty(supplyList)){
            return supplyMap;
        }

        for (SupplyResponseDTO supply : supplyList){
            supplyMap.put(supply.getSupplyCode(),supply.getSupplyName());
        }
        return supplyMap;
    }

    @Transactional
    @Override
    public int updateHotel(HotelPO hotelPO) {

        SupplyResponseDTO supplyResponseDTO = supplierService.getSupplyBySupplyCode(hotelPO.getSupplyCode());
        SupplyRequestDTO supplyRequestDTO = getSupplyRequestDTO(hotelPO);
        supplyRequestDTO.setSupplyId(supplyResponseDTO.getSupplyId());
        supplierService.updateSupply(supplyRequestDTO);

        //供应商编码不同，则说明更换了供应商，更换供应商此时不做修改。
        SupplyHotelRelationPO shr = new SupplyHotelRelationPO();
        shr.setHotelId(hotelPO.getHotelId());
        shr.setSupplyCode(hotelPO.getSupplyCode());
        shr.setRelationId(hotelPO.getRelationId());
        supplyHotelRelationService.updateByPrimaryKeySelective(shr);
        
        List<AdditionalChargePO> list = this.additionalChargePOMapper.queryAdditionalChargeByHotelId(hotelPO.getHotelId());
        if (CollectionUtils.isNotEmpty(list)) {
        	for (AdditionalChargePO addit : list) {
        		if (AdditionalTypeEnum.BED.key.equals(addit.getChargeType())) {
        			if (null != hotelPO.getBedSalePrice()) {
        				AdditionalChargePO addition = new AdditionalChargePO();
        				addition.setChargeId(addit.getChargeId());
        				addition.setBasePrice(hotelPO.getBedSalePrice());
        				addition.setSaleBPrice(hotelPO.getBedSalePrice());
        				addition.setSaleCPrice(hotelPO.getBedSalePrice());
        				addition.setBaseCurrency(hotelPO.getBedSaleCurrency());
        				addition.setSaleBCurrency(hotelPO.getBedSaleCurrency());
        				addition.setSaleCCurrency(hotelPO.getBedSaleCurrency());
        				addition.setHotelId(hotelPO.getHotelId());
        				addition.setModifier(hotelPO.getModifier());
        				addition.setModifydate(new Date());
        				this.additionalChargePOMapper.updateByPrimaryKeySelective(addition);
        			}
        		} else if (AdditionalTypeEnum.BREAKFAST.key.equals(addit.getChargeType())) {
        			if (null != hotelPO.getBreakfastSalePrice()) {
        				AdditionalChargePO addition = new AdditionalChargePO();
        				addition.setChargeId(addit.getChargeId());
        	        	addition.setBasePrice(hotelPO.getBreakfastSalePrice());
        	        	addition.setBaseCurrency(hotelPO.getBreakfastSaleCurrency());
        	        	addition.setSaleBPrice(hotelPO.getBreakfastSalePrice());
        	        	addition.setSaleBCurrency(hotelPO.getBreakfastSaleCurrency());
        	        	addition.setSaleCPrice(hotelPO.getBreakfastSalePrice());
        	        	addition.setSaleCCurrency(hotelPO.getBreakfastSaleCurrency());
        	        	addition.setHotelId(hotelPO.getHotelId());
        	        	addition.setModifier(hotelPO.getModifier());
        	        	addition.setModifydate(new Date());
        	        	this.additionalChargePOMapper.updateByPrimaryKeySelective(addition);
        	        }
        		}
        	}
        }
        return hotelPOMapper.updateByPrimaryKeySelective(hotelPO);
    }

    @Override
    public int delHotel(HotelPO hotelPO) {
        return hotelPOMapper.deleteByPrimaryKey(hotelPO);
    }


    @Override
    public Map<Long, String> queryHotelMap() {
        Map<Long,String> hotelMap = new LinkedHashMap<Long,String>();
        List<HotelPO> hotelPOList = this.queryAll();
        if (CollectionUtils.isEmpty(hotelPOList)){
            return hotelMap;
        }

        for (HotelPO po : hotelPOList){
            hotelMap.put(po.getHotelId(),po.getHotelName());
        }

        return hotelMap;
    }

    private SupplyRequestDTO getSupplyRequestDTO(HotelPO po){
        SupplyRequestDTO supply = new SupplyRequestDTO();
        supply.setSupplyName(po.getHotelName());
        supply.setAddress(po.getAddress());
        supply.setContacts(po.getContacts());
        supply.setTel(po.getTel());
        supply.setSettlement(po.getSettlement());
        supply.setFax(po.getFax());
        supply.setEmail(po.getEmail());
        return supply;
    }
}
