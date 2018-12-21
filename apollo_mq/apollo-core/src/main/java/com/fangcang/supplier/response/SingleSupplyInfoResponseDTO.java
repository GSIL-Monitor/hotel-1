package com.fangcang.supplier.response;

import com.fangcang.common.BaseDTO;
import com.fangcang.supplier.dto.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 17:02
 * @Description: 单个供应商详情
 */
@Data
public class SingleSupplyInfoResponseDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5721558943206276979L;
    /**
     * 供应商ID
     */
    private Long supplyId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 产品经理账号ID
     */
    private Long merchantPM;

    /**
     * 产品经理姓名
     */
    private String merchantPMName;

    /**
     * 业务经理账号ID
     */
    private Long merchantBM;

    /**
     * 业务经理姓名
     */
    private String merchantBMName;

    /**
     * 订单处理员
     */
    private List<MerchantOPDTO> merchantOPs;

    /**
     * 财务员账号ID
     */
    private Long merchantFinancer;

    /**
     * 财务员姓名
     */
    private String merchantFinancerName;

    /**
     * 用户列表
     */
    private List<AccountDTO> userList;

    /**
     * 合同文件列表
     */
    private List<ContractFileDTO> contractFileList;

    /**
     * 拜访记录
     */
    private List<SupplyVisitInfoDTO> visitList;

    /**
     * 银行卡信息
     */
    private List<SupplyBankCardDTO> bankCardList;

    /**
     * 结算方式
     */
    private Integer billingMethod;

    /**
     * 客户等级
     */
    private Integer customerLevel;

    /**
     * 结算币种
     */
    private String baseCurrency;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 财务电话
     */
    private String financePhone;

    /**
     * 前台电话
     */
    private String frontPhone;

    /**
     * 总机电话
     */
    private String mainPhone;

    /**
     * 副总电话
     */
    private String deputyPhone;

    /**
     * 对接电话
     */
    private String jointPhone;

    /**
     * master账号信息
     */
    private MasterAccountDTO masterAccount;


    /**===================以下为mq新增字段===================**/
    /**
     * 启用预付款
     * 1-启用
     * 0-不启用
     */
    private Integer canUsePrepay;

    /**
     * 财务对账日（1-30的数字）。多个数字用英文逗号隔开
     */
    private String financeDate;

    /**
     * 是否可以管理EBK库存
     * 1-可以
     * 0-不可以
     */
    private Integer canManageProduct;

    /**
     * 合作预警：1-合作，0-不合作
     */
    private Integer cooperationStatus;

    /**
     * 备注
     */
    private String note;

    /**
     * 供应商确认
     */
    private List<SupplyConfirmTypeDTO> supplyConfirmTypeDTOList;


    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;

}
