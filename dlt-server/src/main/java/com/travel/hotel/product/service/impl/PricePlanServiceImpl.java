package com.travel.hotel.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.constant.InitData;
import com.travel.common.dto.DateInfoDTO;
import com.travel.common.dto.PaginationDTO;
import com.travel.common.dto.product.request.PricePlanQueryDTO;
import com.travel.common.dto.product.request.PriceQuotaRestrictRequestDTO;
import com.travel.common.enums.BedTypeEnum;
import com.travel.common.exception.ParameterException;
import com.travel.common.exception.ServiceException;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.PageConvert;
import com.travel.hotel.product.dao.PricePOMapper;
import com.travel.hotel.product.dao.PricePlanPOMapper;
import com.travel.hotel.product.dao.ReserveQuotaPOMapper;
import com.travel.hotel.product.entity.PricePlanDTO;
import com.travel.hotel.product.entity.PricePlanRoom;
import com.travel.hotel.product.entity.po.HtlRestrictPO;
import com.travel.hotel.product.entity.po.PricePO;
import com.travel.hotel.product.entity.po.PricePlanPO;
import com.travel.hotel.product.entity.po.QuotaAccountPO;
import com.travel.hotel.product.entity.po.QuotaPO;
import com.travel.hotel.product.entity.po.ReserveQuotaPO;
import com.travel.hotel.product.entity.po.RoomPO;
import com.travel.hotel.product.service.HotelService;
import com.travel.hotel.product.service.PricePlanService;
import com.travel.hotel.product.service.PriceService;
import com.travel.hotel.product.service.QuotaAccountService;
import com.travel.hotel.product.service.QuotaService;
import com.travel.hotel.product.service.RestrictService;
import com.travel.hotel.product.service.RoomService;
import com.travel.hotel.product.service.SupplyHotelRelationService;
import com.travel.member.service.SupplierService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *   2018/1/20.
 */
@Service("pricePlanService")
public class PricePlanServiceImpl implements PricePlanService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private PricePOMapper pricePOMapper;

    @Autowired
    private SupplyHotelRelationService supplyHotelRelationService;

    @Autowired
    private PricePlanPOMapper pricePlanPOMapper;

    @Autowired
    private QuotaAccountService quotaAccountService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private QuotaService quotaService;

    @Autowired
    private RestrictService restrictService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReserveQuotaPOMapper reserveQuotaPOMapper;

    @Override
    public String queryAutoCompleteSupplyList() {
        return JSON.toJSONString(supplierService.autocomplete());
    }


    @Transactional
    @Override
    public int addPricePlan(PricePlanPO po) {

//        /* *******************************************
//         * 1查询供应商和酒店的关系是否存在
//         * 2不存在，直接新增关系。存在则直接到3
//         * 3新增价格计划信息
//         * *******************************************/
//        if (!hasRelationHotelAndSupply(po.getHotelId(),po.getSupplyCode())){
//            SupplyHotelRelationPO supplyHotelRelationPO = new SupplyHotelRelationPO();
//            supplyHotelRelationPO.setHotelId(po.getHotelId());
//            supplyHotelRelationPO.setSupplyCode(po.getSupplyCode());
//            supplyHotelRelationService.addRelation(supplyHotelRelationPO);
//        }

        return pricePlanPOMapper.insertSelective(po);
    }

    @Transactional
    @Override
    public int addPriceQuotaRestrict(PriceQuotaRestrictRequestDTO dto) throws ParseException {

        //将所有日期获取出来
        List<DateInfoDTO> dateInfoDTOList = DateUtil.getDateList(dto.getBeginDate(),dto.getEndDate());
        if (CollectionUtils.isEmpty(dateInfoDTOList)){
            logger.error("PricePlanServiceImpl.addPriceQuotaRestrict,{}",dto);
            throw new ServiceException("保存价格房态条款时日期不正确");
        }

        List<PricePO> pricePOList = new LinkedList<PricePO>();
        List<QuotaPO> quotaPOList = new LinkedList<QuotaPO>();
        List<HtlRestrictPO> restrictPOList = new LinkedList<HtlRestrictPO>();
        QuotaPO quotaPO = null;
        HtlRestrictPO restrictPO = null;

        /***
         * 房态有两种：
         * 1-配额账号已存在，直接更新数据。
         *
         * 2-新增的:
         * 2.1在房型下创建一个配额账号
         * 2.2生成每日的配额数据
         * 2.3同1更新价格计划表的配额账号字段
         */
        Long accountId = dto.getAccountId();
        if (null == accountId && dto.getBatchSetQuota()){//自己新建一个配额账号
            QuotaAccountPO quotaAccountPO = new QuotaAccountPO(dto.getRoomTypeId());
            quotaAccountPO.setAccountName(getAccountName(dto));
            accountId = quotaAccountService.addQuotaAccount(quotaAccountPO);
        }
        //就将产品表的配额账号字段更新下即可。
        PricePlanPO pricePlanPO = new PricePlanPO();
        pricePlanPO.setModifier(dto.getModifier());
        pricePlanPO.setModifydate(DateUtil.getCurrentDate());
        pricePlanPO.setAccountId(accountId);
        pricePlanPO.setPriceplanId(dto.getPricePlanId());
        pricePlanPOMapper.updateByPrimaryKeySelective(pricePlanPO);

        //遍历日期，将每天的价格，房态，配额，条款组装出来
        for (DateInfoDTO dateInfoDTO : dateInfoDTOList){

            if (dto.getBatchSetPrice()) {
                pricePOList.add(setPrice(dto, dateInfoDTO));
            }

            if (dto.getBatchSetQuota()){
				quotaPOList.add(setQuota(dto,dateInfoDTO,accountId));
            }

            if (dto.getBatchSetRestrict()){
                restrictPOList.add(setRestrict(dto,dateInfoDTO));
            }
        }

        /**
         * 查询这段日期内的价格、房态、配额、条款
         * 将页面数据，分为两类，一类是新增操作，一类是更新操作
         */
        if (pricePOList.size() > 0){
            Map<String,Long> existPriceMap = getPriceMap(dto);
            List<PricePO> addPriceList = pricePOList.stream().filter(po -> !existPriceMap.containsKey(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"))).collect(Collectors.toList());
            List<PricePO> updatePriceList = pricePOList.stream().filter(po -> existPriceMap.containsKey(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"))).collect(Collectors.toList());
            updatePriceList.forEach(filterPO -> filterPO.setPriceId(existPriceMap.get(DateUtil.dateToString(filterPO.getSaleDate(),"yyyy-MM-dd"))));

            priceService.batchUpdatePriceById(updatePriceList);
            priceService.batchAddPrice(addPriceList);
        }

        if (quotaPOList.size() > 0){
            Map<String,Long> existQuotaMap = getQuotaMap(dto,accountId);
            List<QuotaPO> addQuotaList = quotaPOList.stream().filter(po -> !existQuotaMap.containsKey(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"))).collect(Collectors.toList());
            List<QuotaPO> updateQuotaList = quotaPOList.stream().filter(po -> existQuotaMap.containsKey(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"))).collect(Collectors.toList());
            updateQuotaList.forEach(filterPO -> filterPO.setQuotaId(existQuotaMap.get(DateUtil.dateToString(filterPO.getSaleDate(),"yyyy-MM-dd"))));
            quotaService.batchUpdateQuotaById(updateQuotaList);
            quotaService.batchAddQuota(addQuotaList);
        }

        if (restrictPOList.size() > 0){
            Map<String,Long> existRestrictMap = getRestrictMap(dto);
            List<HtlRestrictPO> addList = restrictPOList.stream().filter(po -> !existRestrictMap.containsKey(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"))).collect(Collectors.toList());
            List<HtlRestrictPO> updateList = restrictPOList.stream().filter(po -> existRestrictMap.containsKey(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"))).collect(Collectors.toList());
            updateList.forEach(filterPO -> filterPO.setRestrictId(existRestrictMap.get(DateUtil.dateToString(filterPO.getSaleDate(),"yyyy-MM-dd"))));

            restrictService.batchUpdateRestrict(updateList);
            restrictService.batchAddRestrict(addList);
        }

        return 1;
    }

    @Override
    public int batchSetPriceQuotaRestrict(List<PriceQuotaRestrictRequestDTO> dtoList,String userName) throws ParseException {

        for (PriceQuotaRestrictRequestDTO dto : dtoList){
            List<QuotaAccountPO> accountList = quotaAccountService.queryQuotaAccountListByRoomId(dto.getRoomTypeId());
            if (!CollectionUtils.isEmpty(accountList) && null != accountList.get(0)){
                dto.setAccountId(accountList.get(0).getAccountId());
            }
            dto.setCreator(userName);
            dto.setModifier(userName);
            dto.setModifyDate(DateUtil.getCurrentDate());
            dto.setCreateDate(DateUtil.getCurrentDate());
            this.addPriceQuotaRestrict(dto);
        }

        return 1;
    }


    private Map<String,Long> getRestrictMap(PriceQuotaRestrictRequestDTO dto){
        Map<String,Long> returnMap = new HashMap<>();
        List<HtlRestrictPO> poList = this.restrictService.queryListByPricePlanIdAndSaleDate(dto.getPricePlanId(),DateUtil.stringToDate(dto.getBeginDate()),DateUtil.stringToDate(dto.getEndDate()));
        poList.forEach(po -> returnMap.put(DateUtil.dateToString(po.getSaleDate()),po.getRestrictId()));
        return returnMap;
    }

    /**
     *
     * @param dto
     * @param accountId
     * @return Map:key=日期，value=quotaId
     */
    private Map<String,Long> getQuotaMap(PriceQuotaRestrictRequestDTO dto, Long accountId) {
        Map<String,Object> param = new HashMap<String,Object>();
        List<Long> accountIdList = new ArrayList<>();
        accountIdList.add(accountId);
        param.put("InAccountId",accountIdList);
        param.put("checkInDate", DateUtil.stringToDate(dto.getBeginDate(),"yyyy-MM-dd"));
        //SQL语句中是不包含结束日期的，所以这里加1天查询
        param.put("checkOutDate",DateUtil.getAfterDate(DateUtil.stringToDate(dto.getEndDate(),"yyyy-MM-dd")));
        List<QuotaPO> quotaList = quotaService.queryByCondition(param);

        Map<String,Long> returnMap = new HashMap<>();
        quotaList.forEach(po -> {returnMap.put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po.getQuotaId());});
        return returnMap;
    }
    /**
     *
     * @param dto
     * @return Map:key=yyyy-MM-dd,value=priceId
     */
    private Map<String,Long> getPriceMap(PriceQuotaRestrictRequestDTO dto) {
        Map<String,Object> param = new HashMap<String,Object>();
        List<Long> pricePlanIdList = new ArrayList<>();
        pricePlanIdList.add(dto.getPricePlanId());
        param.put("InPriceplanId",pricePlanIdList);
        param.put("checkInDate", DateUtil.stringToDate(dto.getBeginDate(),"yyyy-MM-dd"));
        //SQL语句中是不包含结束日期的，所以这里加1天查询
        param.put("checkOutDate",DateUtil.getAfterDate(DateUtil.stringToDate(dto.getEndDate(),"yyyy-MM-dd")));
        List<PricePO> priceList = priceService.queryPriceByCondition(param);

        Map<String,Long> returnMap = new HashMap<>();
        priceList.forEach(po -> {returnMap.put(DateUtil.dateToString(po.getSaleDate(),"yyyy-MM-dd"),po.getPriceId());});

        return returnMap;
    }


    private String getAccountName(PriceQuotaRestrictRequestDTO dto){
        StringBuffer accountName = new StringBuffer();
        if (StringUtils.isEmpty(dto.getRoomTypeName())){
            RoomPO roomPO = roomService.queryRoomById(dto.getRoomTypeId());
            accountName.append(roomPO.getRoomName()).append("-");
        }
        else {
            accountName.append(dto.getRoomTypeName()).append("-");
        }

        if (StringUtils.isEmpty(dto.getPricePlanName())){
            PricePlanPO pricePlanPO = pricePlanPOMapper.selectByPrimaryKey(dto.getPricePlanId());
            accountName.append(pricePlanPO.getPriceplanName());
        }
        else {
            accountName.append(dto.getPricePlanName());
        }

        return  accountName.toString();
    }

    /**
     * 新增的时候可以不用处理复杂了，直接添加数据，不管选择设置为，增加都是这么添加进去。
     * 只有UPDATE的时候才需要注意
     * 调整在售配额（增加、减少），总配额需要变更（增加、减少）。
     * 调整扣留配额（增加、减少），在售配额需要变更（减少、增加）
     * （下单时）调整已用配额（增加、减少），在售配额需要变更（减少，增加）
     *
     * @param dto
     * @param dateInfoDTO
     * @param accountId
     * @return
     */
    private QuotaPO setQuota(PriceQuotaRestrictRequestDTO dto, DateInfoDTO dateInfoDTO, Long accountId){
        QuotaPO quotaPO = new QuotaPO();
        quotaPO.setAccountId(accountId);
        quotaPO.setSaleDate(dateInfoDTO.getSaleDate());
        quotaPO.setQuotaState(dto.getRoomState());
//        if (StringUtils.isNotEmpty(dto.getQuotaNumType())){
//            if ("setQuotaNum".equals(dto.getSetQuotaType())){
//
//            } else if ("setDetQuotaNum".equals(dto.getSetQuotaType())){
//                quotaPO.setDetQuotaNum(null == dto.getQuotaNum() ? 0 : dto.getQuotaNum());
//            }
//        }
        quotaPO.setQuotaNum(dto.getQuotaNum());
        quotaPO.setCreatedate(DateUtil.getCurrentDate());
        quotaPO.setCreator(dto.getCreator());
        quotaPO.setModifydate(DateUtil.getCurrentDate());
        quotaPO.setModifier(dto.getModifier());
        return quotaPO;
    }

    /**
     *
     * @param dto
     * @param dateInfoDTO
     * @return
     */
    private HtlRestrictPO setRestrict(PriceQuotaRestrictRequestDTO dto, DateInfoDTO dateInfoDTO){
        HtlRestrictPO restrictPO = new HtlRestrictPO();
        restrictPO.setPriceplanId(dto.getPricePlanId());
        restrictPO.setSaleDate(dateInfoDTO.getSaleDate());
        restrictPO.setModifier(dto.getModifier());
        restrictPO.setModifydate(DateUtil.getCurrentDate());
        restrictPO.setCreatedate(DateUtil.getCurrentDate());
        restrictPO.setCreator(dto.getCreator());
        //预订条款
        restrictPO.setBookDays(dto.getBookDays());
        restrictPO.setBookTime(dto.getBookTime());
        //间数条款
        restrictPO.setBookRooms(dto.getBookRooms());
        //连住条款
        restrictPO.setOccupancyType(1);//连住：目前只有连住条款
        restrictPO.setOccupancyDays(dto.getOccupancyDays());
        //取消条款
        restrictPO.setCancelType(1);//取消条款：只有一经预订不可取消
        return restrictPO;
    }

    /**
     * 设置价格
     * @param dto
     * @param dateInfoDTO
     * @return
     */
    private PricePO setPrice(PriceQuotaRestrictRequestDTO dto, DateInfoDTO dateInfoDTO){
        PricePO pricePO = new PricePO();
        pricePO.setPriceplanId(dto.getPricePlanId());
        pricePO.setSaleDate(dateInfoDTO.getSaleDate());
        pricePO.setBaseCurrency(dto.getBaseCurrency());
        pricePO.setSaleCCurrency(dto.getToCCurrency());
        pricePO.setSaleChannelCurrency(dto.getToBCurrency());
        pricePO.setBreakfastNum(dto.getBreakfastNum());
        pricePO.setModifier(dto.getModifier());
        pricePO.setCreator(dto.getCreator());
        pricePO.setCreatedate(dto.getCreateDate());
        pricePO.setModifydate(dto.getModifyDate());

        //看看当前日期是周末还是平日，来设置周末价，还是平日价
        if (DateUtil.isWeekend(InitData.weekendList,dateInfoDTO.getWeekDay())){
            //周末价
            this.setWeekendPrice(dto,pricePO);
        } else{
            //平日价
            this.setWeekdayPrice(dto,pricePO);
        }

        return pricePO;
    }


    /**
     * 设置周末价
     * @param dto
     * @param pricePO
     */
    private void setWeekendPrice(PriceQuotaRestrictRequestDTO dto, PricePO pricePO){
        //周末价
        pricePO.setBasePrice(dto.getBasePriceWeekend());
        pricePO.setSaleCPrice(dto.getToCPriceWeekend());

        //所有渠道的售价相同，直接取toB的价格设置各渠道的价格
        if (dto.getSameSalePrice()){
            pricePO.setCtripPrice(dto.getToBPriceWeekend());
            pricePO.setTmPrice(dto.getToBPriceWeekend());
            pricePO.setSaleBPrice(dto.getToBPriceWeekend());
        } else {
            pricePO.setSaleBPrice(dto.getB2BPriceWeekend());
            pricePO.setCtripPrice(dto.getCtripPriceWeekend());
            pricePO.setTmPrice(dto.getTaobaoPriceWeekend());
        }
    }

    /**
     * 设置平时价
     * @param dto
     * @param pricePO
     */
    private void setWeekdayPrice(PriceQuotaRestrictRequestDTO dto, PricePO pricePO){
        //平日价
        pricePO.setBasePrice(dto.getBasePriceWeekday());
        pricePO.setSaleCPrice(dto.getToCPriceWeekday());

        //所有渠道的售价相同，直接取toB的价格设置各渠道的价格
        if (dto.getSameSalePrice()){
            pricePO.setSaleBPrice(dto.getToBPriceWeekday());
            pricePO.setCtripPrice(dto.getToBPriceWeekday());
            pricePO.setTmPrice(dto.getToBPriceWeekday());
        } else {
            pricePO.setSaleBPrice(dto.getToBPriceWeekday());
            pricePO.setCtripPrice(dto.getCtripPriceWeekday());
            pricePO.setTmPrice(dto.getTaobaoPriceWeekday());
        }
    }

    /**
     *
     * @param hotelId
     * @param supplyCode
     * @return true 存在关系。false，不存在关系
     */
    private boolean hasRelationHotelAndSupply(Long hotelId, String supplyCode){
        return null != supplyHotelRelationService.queryByHotelIdAndSupplyCode(hotelId,supplyCode);
    }



	@Override
	public List<PricePO> queryBreakfastByHotelId(Long hotelId) {
		return this.pricePOMapper.queryBreakfastByHotelId(hotelId);
	}

	@Override
	public List<PricePlanRoom> queryPricePlanRoomByHotelId(PricePlanQueryDTO pricePlanQueryDTO) {
		List<PricePlanRoom> list = this.pricePlanPOMapper.queryPricePlanRoomByHotelId(pricePlanQueryDTO);
		if (CollectionUtils.isNotEmpty(list)) {
			for (PricePlanRoom pr : list) {
				if (StringUtils.isNotBlank(pr.getBedType())) {
					StringBuffer sb = new StringBuffer();
					String[] arr = pr.getBedType().split(",");
					for (int i = 0; i < arr.length; i++) {
						if (i != arr.length-1) {
							sb.append(BedTypeEnum.getValueByKey(arr[i]) + "/");
						} else {
							sb.append(BedTypeEnum.getValueByKey(arr[i]));
						}
					}
					pr.setBedTypeText(sb.toString());
				}
			}
		}
		return list;
	}

    @Override
    public PaginationDTO<PricePlanDTO> queryPricePlanList(PricePlanPO queryPO){
        try {
            PageHelper.startPage(queryPO.getCurrentPage(), queryPO.getPageSize());
            List<PricePlanDTO> pricePlanDTOList = pricePlanPOMapper.queryPricePlanList(queryPO);
            for (PricePlanDTO dto : pricePlanDTOList){
                dto.setQuotaTypeName(InitData.quotaTypeMap.get(dto.getQuotaType()));
                dto.setIsActiveName( 1 == dto.getIsActive() ? "有效" : "无效");
            }
            PageInfo<PricePlanDTO> pageInfo = new PageInfo<PricePlanDTO>(pricePlanDTOList);
            return PageConvert.getPaginationSupport(pageInfo);
        } catch (Exception e){
            logger.error("查询价格计划列表出错"+queryPO.toString(),e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PricePlanDTO> queryPricePlanByCondition(PricePlanPO queryPO) {
        List<PricePlanDTO> pricePlanDTOList = pricePlanPOMapper.queryPricePlanList(queryPO);
        for (PricePlanDTO dto : pricePlanDTOList){
            dto.setQuotaTypeName(InitData.quotaTypeMap.get(dto.getQuotaType()));
            dto.setIsActiveName( 1 == dto.getIsActive() ? "有效" : "无效");
            dto.setPreHoldQuotaName(1 == dto.getPreHoldQuota() ? "是" : "否");
        }
        return pricePlanDTOList;
    }

    @Override
    public int updateByCondition(PricePlanPO po) {
        return pricePlanPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public int updateAcvtive(Long pricePlanId, Integer isActive,String modifier) {
        PricePlanPO po = new PricePlanPO();
        po.setPriceplanId(pricePlanId);
        po.setIsActive(isActive);
        po.setModifier(modifier);
        po.setModifydate(DateUtil.getCurrentDate());
        return updateByCondition(po);
    }

    @Override
    public PricePlanDTO queryPrciePlanById(Long pricePlanId) {

        PricePlanPO query = new PricePlanPO();
        query.setPriceplanId(pricePlanId);
        List<PricePlanDTO> pricePlanDTOList = pricePlanPOMapper.queryPricePlanList(query);
        if (CollectionUtils.isEmpty(pricePlanDTOList)){
            return null;
        }
        PricePlanDTO pricePlanDTO = pricePlanDTOList.get(0);
        pricePlanDTO.setQuotaTypeName(InitData.quotaTypeMap.get(pricePlanDTO.getQuotaType()));
        return pricePlanDTO;
    }

    @Override
    public PaginationDTO<ReserveQuotaPO> queryReserveQuotaByCondition(ReserveQuotaPO reserveQuotaPO) {

        if (reserveQuotaPO.getPriceplanId() == null || reserveQuotaPO.getSaleDate() == null){
            logger.error("价格计划或者日期为空：{}", JSONObject.toJSONString(reserveQuotaPO));
            throw new ParameterException("价格计划或者日期为空");
        }

        PageHelper.startPage(reserveQuotaPO.getCurrentPage(), reserveQuotaPO.getPageSize());
        List<ReserveQuotaPO> reserveQuotaPOList = reserveQuotaPOMapper.selectByCondition(reserveQuotaPO);
        reserveQuotaPOList.forEach(po -> po.setEndTime(this.getEndTime(po.getModifyDate())));
        PageInfo<ReserveQuotaPO> pageInfo = new PageInfo<ReserveQuotaPO>(reserveQuotaPOList);
        return PageConvert.getPaginationSupport(pageInfo);
    }

    @Override
    public ReserveQuotaPO queryReserveQuota(Long pricePlanId, Date saleDate, String distributeCode) {
        List<ReserveQuotaPO> reserveQuotaPOList = queryReserveQuotaList(pricePlanId, saleDate, distributeCode);
        if (CollectionUtils.isEmpty(reserveQuotaPOList)) {
            return null;
        }
        return reserveQuotaPOList.get(0);
    }

    @Override
    public Boolean hasReserveQuota(Long pricePlanId, Date saleDate, String distributeCode) {
        List<ReserveQuotaPO> reserveQuotaPOList = queryReserveQuotaList(pricePlanId, saleDate, distributeCode);
        return CollectionUtils.isNotEmpty(reserveQuotaPOList);
    }

    @Override
    public void deductReserveQuota(Long pricePlanId, Date saleDate, String distributeCode, Integer quotaNum) {

        ReserveQuotaPO reserveQuotaPO = this.queryReserveQuota(pricePlanId, saleDate, distributeCode);
        if (null == reserveQuotaPO) {
            throw new ServiceException("查询预留配额失败");
        }

        try {
            ReserveQuotaPO reserveQuotaUpdatePO = new ReserveQuotaPO();
            reserveQuotaUpdatePO.setReserveId(reserveQuotaPO.getReserveId());
            reserveQuotaUpdatePO.setReserveQuotaNum(reserveQuotaPO.getReserveQuotaNum() - quotaNum);
            reserveQuotaPOMapper.updateByPrimaryKeySelective(reserveQuotaUpdatePO);
        } catch (Exception e) {
            logger.error("扣预售配额失败" + pricePlanId + ", " + saleDate + ", " + distributeCode + ", " + quotaNum, e);
            throw new ServiceException("扣预售配额失败" + pricePlanId + ", " + saleDate + ", " + distributeCode + ", " + quotaNum, e);
        }
    }


    private List<ReserveQuotaPO> queryReserveQuotaList(Long pricePlanId, Date saleDate, String distributeCode) {
        if (null == pricePlanId){
            logger.error("价格计划为空：{}", pricePlanId);
            throw new ParameterException("价格计划为空");
        }
        if (null == saleDate){
            logger.error("售卖日期为空：{}", saleDate);
            throw new ParameterException("售卖日期为空");
        }

        if (null == distributeCode){
            logger.error("客户编码为空：{}", distributeCode);
            throw new ParameterException("客户编码为空");
        }
        ReserveQuotaPO reserveQuotaPO = new ReserveQuotaPO();
        reserveQuotaPO.setPriceplanId(pricePlanId);
        reserveQuotaPO.setSaleDate(saleDate);
        reserveQuotaPO.setDistributeCode(distributeCode);

        try {
            return reserveQuotaPOMapper.selectByCondition(reserveQuotaPO);
        } catch (Exception e) {
            logger.error("查询预售配额失败", e);
            throw new ServiceException("查询预售配额失败", e);
        }
    }

    @Override
    public Boolean isReserveQuotaOverDraft(Long quotaAccountId, Date saleDate, Integer addReserveQuotaNum) {

        if (null == quotaAccountId){
            logger.error("配额账号为空：{}", quotaAccountId);
            throw new ParameterException("配额账号为空");
        }

        if (null == saleDate){
            logger.error("售卖日期为空：{}", saleDate);
            throw new ParameterException("售卖日期为空");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountId",quotaAccountId);
        paramMap.put("saleDate",saleDate);
        List<QuotaPO> quotaPOList = quotaService.queryByCondition(paramMap);

        if (CollectionUtils.isEmpty(quotaPOList)){
            logger.info("配额账号{}，售卖日期{}没有设置过配额",quotaAccountId,saleDate);
            return Boolean.FALSE;
        }

        QuotaPO quotaPO = quotaPOList.get(0);

        int quotaNumInDB = getIntNum(quotaPO.getQuotaNum());

        return getIntNum(addReserveQuotaNum) > quotaNumInDB;
    }

    @Transactional
    @Override
    public int addDistributeReserveQuota(ReserveQuotaPO reserveQuotaPO) {

        int insertResult = reserveQuotaPOMapper.insertSelective(reserveQuotaPO);

        if (insertResult <= 0){
            logger.error("给分销商{}设置预留配额失败。{}",reserveQuotaPO.getDistributeCode(),JSONObject.toJSONString(reserveQuotaPO));
            throw new ServiceException("给分销商"+reserveQuotaPO.getDistributeCode()+"设置预留配额失败");
        }
        return updateReserveQuotaNum(reserveQuotaPO);

    }

    @Transactional
    @Override
    public int updateDistributeReserveQuota(ReserveQuotaPO reserveQuotaPO) {
        int updateResult = reserveQuotaPOMapper.updateByPrimaryKeySelective(reserveQuotaPO);
        if (updateResult <= 0){
            logger.error("给分销商{}更新预留配额失败。{}",reserveQuotaPO.getDistributeCode(),JSONObject.toJSONString(reserveQuotaPO));
            throw new ServiceException("给分销商"+reserveQuotaPO.getDistributeCode()+"设置预留配额失败");
        }
        return updateReserveQuotaNum(reserveQuotaPO);
    }

    @Override
    public List<ReserveQuotaPO> queryDaySumByPricePlan(Map param) {
        return reserveQuotaPOMapper.selectDaySumByPricePlan(param);
    }

    private int updateReserveQuotaNum(ReserveQuotaPO reserveQuotaPO) {
        //更新配额表：在售减去-reserveQuotaPO.getReserveQuotaNum()，预留配额+reserveQuotaPO.getReserveQuotaNum()
        QuotaPO po = new QuotaPO();
        po.setAccountId(reserveQuotaPO.getQuotaAccountId());
        po.setReserveQuotaNum(reserveQuotaPO.getAmount());
        po.setModifier(reserveQuotaPO.getModifier());
        po.setModifydate(reserveQuotaPO.getModifyDate());
        po.setSaleDate(reserveQuotaPO.getSaleDate());
        int result = quotaService.addReserveQuota(po);
        return 1;
    }

    /**
     * 获取配额预留剩余时间
     * @param beginTime
     * @return
     */
    private Date getEndTime(Date beginTime){
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localBeginDateTime = beginTime.toInstant().atZone(zoneId).toLocalDateTime();
        LocalDateTime localEndDateTime = localBeginDateTime.plusMinutes(InitData.RESERVE_QUOTA_TIME);
        return Date.from(localEndDateTime.atZone(zoneId).toInstant());
    }

    /**
     * 如果有null的，转为0
     * @param intNum
     * @return
     */
    private Integer getIntNum(Integer intNum){
        return intNum == null ? 0 : intNum.intValue();
    }

}
