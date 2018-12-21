package com.fangcang.finance.invoice.service.impl;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.common.enums.ResultCodeEnum;
import com.fangcang.common.util.DateUtil;
import com.fangcang.common.util.StringUtil;
import com.fangcang.finance.enums.InvoiceStatusEnum;
import com.fangcang.finance.invoice.domain.AgentInvoiceDO;
import com.fangcang.finance.invoice.domain.AgentInvoiceItemDO;
import com.fangcang.finance.invoice.mapper.AgentInvoiceItemMapper;
import com.fangcang.finance.invoice.mapper.AgentInvoiceMapper;
import com.fangcang.finance.invoice.request.AddInvoiceItemDTO;
import com.fangcang.finance.invoice.request.CreateInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceDTO;
import com.fangcang.finance.invoice.request.DeleteInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceDTO;
import com.fangcang.finance.invoice.request.QueryInvoiceItemDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceBillDTO;
import com.fangcang.finance.invoice.request.QueryUnInvoiceOrderDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceAmountDTO;
import com.fangcang.finance.invoice.request.UpdateInvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceDTO;
import com.fangcang.finance.invoice.response.InvoiceItemDTO;
import com.fangcang.finance.invoice.response.UnInvoiceBillDTO;
import com.fangcang.finance.invoice.response.UnInvoiceOrderDTO;
import com.fangcang.finance.invoice.service.AgentInvoiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AgentInvoiceServiceImpl implements AgentInvoiceService {

    @Autowired
    private AgentInvoiceMapper agentInvoiceMapper;

    @Autowired
    private AgentInvoiceItemMapper agentInvoiceItemMapper;

    @Override
    public ResponseDTO createAgentInvoice(CreateInvoiceDTO request) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        AgentInvoiceDO agentInvoiceDO=new AgentInvoiceDO();
        BeanUtils.copyProperties(request,agentInvoiceDO);
        agentInvoiceDO.setInvoiceDate(DateUtil.stringToDateWithHms(request.getInvoiceDate()));
        agentInvoiceDO.setPostalDate(DateUtil.stringToDateWithHms(request.getPostalDate()));
        agentInvoiceDO.setCurrency("CNY");
        //默认为预开发票
        if (agentInvoiceDO.getInvoiceMethod()==null){
            agentInvoiceDO.setInvoiceMethod(3);
        }
        //更新发票状态
        if (request.getInvoiceType()==null){
            agentInvoiceDO.setIsInvalid(0);
            agentInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.UN_FINISH.key);
        }else{
            agentInvoiceDO.setIsInvalid(1);
            if (StringUtil.isValidString(request.getTitle())
                    && request.getAmount()!=null && request.getAmount().compareTo(BigDecimal.ZERO)!=0){
                agentInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.FINISH.key);
            }
            if(request.getPostalType()==1){
                //判断快递单号是否有值，有则表示已寄出，否则未寄出
                if(StringUtil.isValidString(request.getExpressNo())){
                    agentInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.HAS_POSTAL.key);
                }else{
                    agentInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.NOT_POSTAL.key);
                }
            }else if(request.getPostalType()==2){
                //判断取票时间是否有值，有则表示已取走，否则未取走
                if(StringUtil.isValidString(request.getPostalDate())){
                    agentInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.HAS_TAKE_AWAY.key);
                }else{
                    agentInvoiceDO.setInvoiceStatus(InvoiceStatusEnum.NOT_TAKE_AWAY.key);
                }
            }
        }
        agentInvoiceDO.setCreator(request.getOperator());
        agentInvoiceDO.setCreateTime(new Date());
        agentInvoiceMapper.insert(agentInvoiceDO);
        responseDTO.setModel(agentInvoiceDO.getId());
        return responseDTO;
    }

    @Override
    public ResponseDTO updateAgentInvoice(UpdateInvoiceDTO request) {
        ResponseDTO responseDTO=new ResponseDTO(ResultCodeEnum.SUCCESS.code);
        AgentInvoiceDO agentInvoiceUpdate=new AgentInvoiceDO();
        BeanUtils.copyProperties(request,agentInvoiceUpdate);
        agentInvoiceUpdate.setId(request.getInvoiceId());
        agentInvoiceUpdate.setInvoiceDate(DateUtil.stringToDateWithHms(request.getInvoiceDate()));
        agentInvoiceUpdate.setPostalDate(DateUtil.stringToDateWithHms(request.getPostalDate()));
        agentInvoiceUpdate.setModifier(request.getOperator());
        agentInvoiceUpdate.setModifyTime(new Date());
        agentInvoiceMapper.updateByPrimaryKeySelective(agentInvoiceUpdate);

        AgentInvoiceDO agentInvoiceDO=agentInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        AgentInvoiceDO agentInvoiceParam=new AgentInvoiceDO();
        agentInvoiceParam.setId(request.getInvoiceId());
        //更新发票状态
        if (agentInvoiceDO.getInvoiceType()==null){
            agentInvoiceParam.setIsInvalid(0);
            agentInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.UN_FINISH.key);
        }else{
            agentInvoiceParam.setIsInvalid(1);
            if (StringUtil.isValidString(agentInvoiceDO.getTitle())
                    && agentInvoiceDO.getAmount()!=null && agentInvoiceDO.getAmount().compareTo(BigDecimal.ZERO)!=0){
                agentInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.FINISH.key);
            }
            if(agentInvoiceDO.getPostalType()==1){
                //判断快递单号是否有值，有则表示已寄出，否则未寄出
                if(StringUtil.isValidString(agentInvoiceDO.getExpressNo())){
                    agentInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.HAS_POSTAL.key);
                }else{
                    agentInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.NOT_POSTAL.key);
                }
            }else if(agentInvoiceDO.getPostalType()==2){
                //判断取票时间是否有值，有则表示已取走，否则未取走
                if(agentInvoiceDO.getPostalDate()!=null){
                    agentInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.HAS_TAKE_AWAY.key);
                }else{
                    agentInvoiceParam.setInvoiceStatus(InvoiceStatusEnum.NOT_TAKE_AWAY.key);
                }
            }
        }
        agentInvoiceMapper.updateByPrimaryKeySelective(agentInvoiceParam);
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteAgentInvoice(DeleteInvoiceDTO request) {
        AgentInvoiceDO agentInvoiceDO=agentInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        if (agentInvoiceDO==null){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"发票不存在");
        }
        //先更新订单或账单的已开发票金额
        UpdateInvoiceAmountDTO updateInvoiceAmountDTO=new UpdateInvoiceAmountDTO();
        updateInvoiceAmountDTO.setInvoiceId(request.getInvoiceId());
        updateInvoiceAmountDTO.setType(2);
        if (agentInvoiceDO.getInvoiceMethod()==1){
            agentInvoiceItemMapper.updateOrderInvoiceAmount(updateInvoiceAmountDTO);
        }else if(agentInvoiceDO.getInvoiceMethod()==2){
            agentInvoiceItemMapper.updateBillInvoiceAmount(updateInvoiceAmountDTO);
            agentInvoiceItemMapper.updateBillOrderInvoiceAmount(updateInvoiceAmountDTO);
        }
        //删除明细
        DeleteInvoiceItemDTO deleteInvoiceItemDTO=new DeleteInvoiceItemDTO();
        deleteInvoiceItemDTO.setInvoiceId(request.getInvoiceId());
        agentInvoiceItemMapper.deleteAgentInvoiceItem(deleteInvoiceItemDTO);
        //删除发票
        agentInvoiceMapper.deleteByPrimaryKey(request.getInvoiceId());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public ResponseDTO addAgentInvoiceItem(AddInvoiceItemDTO request) {
        AgentInvoiceDO agentInvoiceDO=agentInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        if (agentInvoiceDO==null){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"发票不存在");
        }
        //如果开票方式发生变化，则先更新开票方式
        if (agentInvoiceDO.getInvoiceMethod()!=request.getInvoiceMethod()){
            AgentInvoiceItemDO agentInvoiceItemParam=new AgentInvoiceItemDO();
            agentInvoiceItemParam.setInvoiceId(request.getInvoiceId());
            int count=agentInvoiceItemMapper.selectCount(agentInvoiceItemParam);
            if (count > 0){
                return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"请先清空原来的明细");
            }
            AgentInvoiceDO agentInvoiceParam=new AgentInvoiceDO();
            agentInvoiceParam.setId(request.getInvoiceId());
            agentInvoiceParam.setInvoiceMethod(request.getInvoiceMethod());
            agentInvoiceMapper.updateByPrimaryKeySelective(agentInvoiceParam);
        }
        //先更新订单或账单的已开发票金额
        UpdateInvoiceAmountDTO updateInvoiceAmountDTO=new UpdateInvoiceAmountDTO();
        updateInvoiceAmountDTO.setInvoiceId(request.getInvoiceId());
        updateInvoiceAmountDTO.setOrderCodeList(request.getOrderCodeList());
        updateInvoiceAmountDTO.setType(1);
        if (request.getInvoiceMethod()==1){
            agentInvoiceItemMapper.saveBatchOrderToInvoice(request);
            agentInvoiceItemMapper.updateOrderInvoiceAmount(updateInvoiceAmountDTO);
        }else if(request.getInvoiceMethod()==2){
            agentInvoiceItemMapper.saveBatchBillToInvoice(request);
            agentInvoiceItemMapper.updateBillInvoiceAmount(updateInvoiceAmountDTO);
            agentInvoiceItemMapper.updateBillOrderInvoiceAmount(updateInvoiceAmountDTO);
        }
        agentInvoiceMapper.updateInvoiceAmount(request.getInvoiceId());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public ResponseDTO deleteAgentInvoiceItem(DeleteInvoiceItemDTO request) {
        AgentInvoiceDO agentInvoiceDO=agentInvoiceMapper.selectByPrimaryKey(request.getInvoiceId());
        if (agentInvoiceDO==null){
            return new ResponseDTO(ResultCodeEnum.FAILURE.code,null,"发票不存在");
        }
        //先更新订单或账单的已开发票金额
        UpdateInvoiceAmountDTO updateInvoiceAmountDTO=new UpdateInvoiceAmountDTO();
        updateInvoiceAmountDTO.setInvoiceId(request.getInvoiceId());
        updateInvoiceAmountDTO.setInvoiceItemIdList(request.getInvoiceItemIdList());
        updateInvoiceAmountDTO.setType(2);
        if (agentInvoiceDO.getInvoiceMethod()==1){
            agentInvoiceItemMapper.updateOrderInvoiceAmount(updateInvoiceAmountDTO);
        }else if(agentInvoiceDO.getInvoiceMethod()==2){
            agentInvoiceItemMapper.updateBillInvoiceAmount(updateInvoiceAmountDTO);
            agentInvoiceItemMapper.updateBillOrderInvoiceAmount(updateInvoiceAmountDTO);
        }
        //删除明细
        agentInvoiceItemMapper.deleteAgentInvoiceItem(request);
        agentInvoiceMapper.updateInvoiceAmount(request.getInvoiceId());
        return new ResponseDTO(ResultCodeEnum.SUCCESS.code);
    }

    @Override
    public PaginationSupportDTO<InvoiceDTO> queryAgentInvoice(QueryInvoiceDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<InvoiceDTO> list =agentInvoiceMapper.queryAgentInvoice(request);
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
    public PaginationSupportDTO<InvoiceItemDTO> queryAgentInvoiceItem(QueryInvoiceItemDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<InvoiceItemDTO> list =agentInvoiceItemMapper.queryAgentInvoiceItem(request);
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
    public PaginationSupportDTO<UnInvoiceOrderDTO> queryUnInvoiceOrder(QueryUnInvoiceOrderDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<UnInvoiceOrderDTO> list =agentInvoiceItemMapper.queryUnInvoiceOrder(request);
        PageInfo<UnInvoiceOrderDTO> page = new PageInfo<UnInvoiceOrderDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }

    @Override
    public PaginationSupportDTO<UnInvoiceBillDTO> queryUnInvoiceAgentBill(QueryUnInvoiceBillDTO request) {
        PageHelper.startPage(request.getCurrentPage(), request.getPageSize());
        List<UnInvoiceBillDTO> list =agentInvoiceItemMapper.queryUnInvoiceAgentBill(request);
        PageInfo<UnInvoiceBillDTO> page = new PageInfo<UnInvoiceBillDTO>(list);

        PaginationSupportDTO paginationSupport=new PaginationSupportDTO();
        paginationSupport.setItemList(list);
        paginationSupport.setPageSize(page.getPageSize());
        paginationSupport.setTotalCount(page.getTotal());
        paginationSupport.setTotalPage(page.getPages());
        paginationSupport.setCurrentPage(page.getPageNum());
        return paginationSupport;
    }
}
