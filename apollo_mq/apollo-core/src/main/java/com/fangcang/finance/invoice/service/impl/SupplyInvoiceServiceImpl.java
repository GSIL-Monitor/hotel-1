package com.fangcang.finance.invoice.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.common.util.excel.ExcelUtils;
import com.fangcang.finance.enums.InvoiceStatusEnum;
import com.fangcang.finance.invoice.domain.SupplyInvoiceDO;
import com.fangcang.finance.invoice.domain.SupplyInvoiceItemDO;
import com.fangcang.finance.invoice.mapper.SupplyInvoiceItemMapper;
import com.fangcang.finance.invoice.mapper.SupplyInvoiceMapper;
import com.fangcang.finance.invoice.request.AddInvoiceItemDTO;
import com.fangcang.finance.invoice.request.CreateInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceSummaryDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceAmountDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.InvoiceSummaryDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceSupplyOrderDTO;
import com.fangcang.finance.invoice.service.SupplyInvoiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplyInvoiceServiceImpl implements SupplyInvoiceService {

    @Autowired
    private SupplyInvoiceMapper supplyInvoiceMapper;

    @Autowired
    private SupplyInvoiceItemMapper supplyInvoiceItemMapper;

    @Override
    public ResponseDTO createSupplyInvoice(@Valid CreateInvoiceDTO request) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        SupplyInvoiceDO supplyInvoiceDO=new SupplyInvoiceDO();
        BeanUtils.copyProperties(request,supplyInvoiceDO);
        supplyInvoiceDO.setInvoiceDate(DateUtil.stringToDateWithHms(request.getInvoiceDate()));
        supplyInvoiceDO.setPostalDate(DateUtil.stringToDateWithHms(request.getPostalDate()));
        supplyInvoiceDO.setCurrency("CNY");
        //更新发票状态
        if (request.getInvoiceType()==null){
            supplyInvoiceDO.setIsInvalid(0);
            supplyInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.UN_FINISH.key);
        }else{
            supplyInvoiceDO.setIsInvalid(1);
            if (StringUtil.isValidString(request.getTitle())
                    && request.getAmount()!=null && request.getAmount().compareTo(BigDecimal.ZERO)!=0){
                supplyInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.FINISH.key);
            }
            if(request.getPostalType()==1){
                //判断快递单号是否有值，有则表示已寄出，否则未寄出
                if(StringUtil.isValidString(request.getExpressNo())){
                    supplyInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.HAS_POSTAL.key);
                }else{
                    supplyInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.NOT_POSTAL.key);
                }
            }else if(request.getPostalType()==2){
                //判断取票时间是否有值，有则表示已取走，否则未取走
                if(StringUtil.isValidString(request.getPostalDate())){
                    supplyInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.HAS_TAKE_AWAY.key);
                }else{
                    supplyInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.NOT_TAKE_AWAY.key);
                }
            }
        }
        supplyInvoiceDO.setCreator(request.getOperator());
        supplyInvoiceDO.setCreateTime(new Date());
        supplyInvoiceMapper.insert(supplyInvoiceDO);
        responseDTO.setModel(supplyInvoiceDO.getId());
        return responseDTO;
    }

    @Override
    public ResponseDTO updateSupplyInvoice(UpdateInvoiceDTO request) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        SupplyInvoiceDO supplyInvoiceUpdate=new SupplyInvoiceDO();
        BeanUtils.copyProperties(request,supplyInvoiceUpdate);
        supplyInvoiceUpdate.setId(request.getInvoiceId());
        supplyInvoiceUpdate.setInvoiceDate(DateUtil.stringToDateWithHms(request.getInvoiceDate()));
        supplyInvoiceUpdate.setPostalDate(DateUtil.stringToDateWithHms(request.getPostalDate()));
        supplyInvoiceUpdate.setModifier(request.getOperator());
        supplyInvoiceUpdate.setModifyTime(new Date());
        supplyInvoiceMapper.updateByPrimaryKeySelective(supplyInvoiceUpdate);

        SupplyInvoiceDO supplyInvoiceDO=supplyInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        SupplyInvoiceDO supplyInvoiceParam=new SupplyInvoiceDO();
        supplyInvoiceParam.setId(request.getInvoiceId());
        //更新发票状态
        if (supplyInvoiceDO.getInvoiceType()==null){
            supplyInvoiceParam.setIsInvalid(0);
            supplyInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.UN_FINISH.key);
        }else{
            supplyInvoiceParam.setIsInvalid(1);
            if (StringUtil.isValidString(supplyInvoiceDO.getTitle())
                    && supplyInvoiceDO.getAmount()!=null && supplyInvoiceDO.getAmount().compareTo(BigDecimal.ZERO)!=0){
                supplyInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.FINISH.key);
            }
            if(supplyInvoiceDO.getPostalType()==1){
                //判断快递单号是否有值，有则表示已寄出，否则未寄出
                if(StringUtil.isValidString(supplyInvoiceDO.getExpressNo())){
                    supplyInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.HAS_POSTAL.key);
                }else{
                    supplyInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.NOT_POSTAL.key);
                }
            }else if(supplyInvoiceDO.getPostalType()==2){
                //判断取票时间是否有值，有则表示已取走，否则未取走
                if(supplyInvoiceDO.getPostalDate()!=null){
                    supplyInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.HAS_TAKE_AWAY.key);
                }else{
                    supplyInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.NOT_TAKE_AWAY.key);
                }
            }
        }
        supplyInvoiceMapper.updateByPrimaryKeySelective(supplyInvoiceParam);
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteSupplyInvoice(DeleteInvoiceDTO request) {
        SupplyInvoiceDO supplyInvoiceDO=supplyInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        if (supplyInvoiceDO==null){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"发票不存在");
        }
        //先更新订单或账单的已开发票金额
        UpdateInvoiceAmountDTO updateInvoiceAmountDTO=new UpdateInvoiceAmountDTO();
        updateInvoiceAmountDTO.setInvoiceId(request.getInvoiceId());
        updateInvoiceAmountDTO.setType(2);
        if (supplyInvoiceDO.getInvoiceMethod()==1){
            supplyInvoiceItemMapper.updateOrderInvoiceAmount(updateInvoiceAmountDTO);
        }else if(supplyInvoiceDO.getInvoiceMethod()==2){
            supplyInvoiceItemMapper.updateBillInvoiceAmount(updateInvoiceAmountDTO);
            supplyInvoiceItemMapper.updateBillOrderInvoiceAmount(updateInvoiceAmountDTO);
        }
        //删除明细
        DeleteInvoiceItemDTO deleteInvoiceItemDTO=new DeleteInvoiceItemDTO();
        deleteInvoiceItemDTO.setInvoiceId(request.getInvoiceId());
        supplyInvoiceItemMapper.deleteSupplyInvoiceItem(deleteInvoiceItemDTO);
        //删除发票
        supplyInvoiceMapper.deleteByPrimaryKey(request.getInvoiceId());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public ResponseDTO addSupplyInvoiceItem(AddInvoiceItemDTO request) {
        SupplyInvoiceDO supplyInvoiceDO=supplyInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        if (supplyInvoiceDO==null){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"发票不存在");
        }
        //如果开票方式发生变化，则先更新开票方式
        if (supplyInvoiceDO.getInvoiceMethod()!=request.getInvoiceMethod()){
            SupplyInvoiceItemDO supplyInvoiceItemParam=new SupplyInvoiceItemDO();
            supplyInvoiceItemParam.setInvoiceId(request.getInvoiceId());
            int count=supplyInvoiceItemMapper.selectCount(supplyInvoiceItemParam);
            if (count > 0){
                return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"请先清空原来的明细");
            }
            SupplyInvoiceDO supplyInvoiceParam=new SupplyInvoiceDO();
            supplyInvoiceParam.setId(request.getInvoiceId());
            supplyInvoiceParam.setInvoiceMethod(request.getInvoiceMethod());
            supplyInvoiceMapper.updateByPrimaryKeySelective(supplyInvoiceParam);
        }
        //先更新订单或账单的已开发票金额
        UpdateInvoiceAmountDTO updateInvoiceAmountDTO=new UpdateInvoiceAmountDTO();
        updateInvoiceAmountDTO.setInvoiceId(request.getInvoiceId());
        updateInvoiceAmountDTO.setOrderCodeList(request.getOrderCodeList());
        updateInvoiceAmountDTO.setType(1);
        if (request.getInvoiceMethod()==1){
            supplyInvoiceItemMapper.saveBatchOrderToInvoice(request);
            supplyInvoiceItemMapper.updateOrderInvoiceAmount(updateInvoiceAmountDTO);
        }else if(request.getInvoiceMethod()==2){
            supplyInvoiceItemMapper.saveBatchBillToInvoice(request);
            supplyInvoiceItemMapper.updateBillInvoiceAmount(updateInvoiceAmountDTO);
            supplyInvoiceItemMapper.updateBillOrderInvoiceAmount(updateInvoiceAmountDTO);
        }
        supplyInvoiceMapper.updateInvoiceAmount(request.getInvoiceId());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public ResponseDTO deleteSupplyInvoiceItem(DeleteInvoiceItemDTO request) {
        SupplyInvoiceDO supplyInvoiceDO=supplyInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        if (supplyInvoiceDO==null){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"发票不存在");
        }
        //先更新订单或账单的已开发票金额
        UpdateInvoiceAmountDTO updateInvoiceAmountDTO=new UpdateInvoiceAmountDTO();
        updateInvoiceAmountDTO.setInvoiceId(request.getInvoiceId());
        updateInvoiceAmountDTO.setInvoiceItemIdList(request.getInvoiceItemIdList());
        updateInvoiceAmountDTO.setType(2);
        if (supplyInvoiceDO.getInvoiceMethod()==1){
            supplyInvoiceItemMapper.updateOrderInvoiceAmount(updateInvoiceAmountDTO);
        }else if(supplyInvoiceDO.getInvoiceMethod()==2){
            supplyInvoiceItemMapper.updateBillInvoiceAmount(updateInvoiceAmountDTO);
            supplyInvoiceItemMapper.updateBillOrderInvoiceAmount(updateInvoiceAmountDTO);
        }
        //删除明细
        supplyInvoiceItemMapper.deleteSupplyInvoiceItem(request);
        supplyInvoiceMapper.updateInvoiceAmount(request.getInvoiceId());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public PaginationSupportDTO<InvoiceDTO> querySupplyInvoice(QueryInvoiceDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<InvoiceDTO> list =supplyInvoiceMapper.querySupplyInvoice(request);
        PageInfo<InvoiceDTO> page = new PageInfo<InvoiceDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<InvoiceItemDTO> querySupplyInvoiceItem(QueryInvoiceItemDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<InvoiceItemDTO> list =supplyInvoiceItemMapper.querySupplyInvoiceItem(request);
        PageInfo<InvoiceItemDTO> page = new PageInfo<InvoiceItemDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<UnInvoiceSupplyOrderDTO> queryUnInvoiceSupplyOrder(QueryUnInvoiceOrderDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<UnInvoiceSupplyOrderDTO> list =supplyInvoiceItemMapper.queryUnInvoiceSupplyOrder(request);
        PageInfo<UnInvoiceSupplyOrderDTO> page = new PageInfo<UnInvoiceSupplyOrderDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<UnInvoiceBillDTO> queryUnInvoiceSupplyBill(QueryUnInvoiceBillDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<UnInvoiceBillDTO> list =supplyInvoiceItemMapper.queryUnInvoiceSupplyBill(request);
        PageInfo<UnInvoiceBillDTO> page = new PageInfo<UnInvoiceBillDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<InvoiceSummaryDTO> querySupplyInvoiceSummary(QueryInvoiceSummaryDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<InvoiceSummaryDTO> list =supplyInvoiceMapper.querySupplyInvoiceSummary(request);
        PageInfo<InvoiceSummaryDTO> page = new PageInfo<InvoiceSummaryDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public ResponseDTO uploadSupplyInvoiceItem(InputStream in,Integer invoiceId,String operator) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        List<SupplyInvoiceItemDO> supplyInvoiceItemDOList=new ArrayList<>();
        ExcelUtils.readExcel(in,SupplyInvoiceItemDO.class,supplyInvoiceItemDOList);
        if (supplyInvoiceItemDOList.size()>0){
            Map<String,SupplyInvoiceItemDO> distinctMap=new HashMap<>();
            for (SupplyInvoiceItemDO supplyInvoiceItemDO:supplyInvoiceItemDOList){
                String key=supplyInvoiceItemDO.getBusinessCode();
                if (distinctMap.containsKey(key)){
                    continue;
                }
                distinctMap.put(key,supplyInvoiceItemDO);
            }
            List<String> list=new ArrayList<>(distinctMap.keySet());
            int size=list.size()%1000==0?list.size()/1000:list.size()/1000+1;
            for(int i=0;i<size;i++) {
                List<String> curlist;
                if (i == list.size() / 1000) {
                    curlist = list.subList(i * 1000, i * 1000 + list.size() % 1000);
                } else {
                    curlist = list.subList(i * 1000, (i + 1) * 1000);
                }
                AddInvoiceItemDTO addInvoiceItemDTO=new AddInvoiceItemDTO();
                addInvoiceItemDTO.setInvoiceId(invoiceId);
                addInvoiceItemDTO.setInvoiceMethod(1);
                addInvoiceItemDTO.setOrderCodeList(curlist);
                addInvoiceItemDTO.setOperator(operator);
                this.addSupplyInvoiceItem(addInvoiceItemDTO);
            }
        }else{
            responseDTO.setResult(ResultCodeEnum.FAILURE.code);
            responseDTO.setFailReason("导入的文件没有订单");
        }
        return responseDTO;
    }
}
