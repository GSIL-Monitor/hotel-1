package com.fangcang.supplier.request;

import com.fangcang.common.BaseDTO;
import com.fangcang.supplier.dto.AccountDTO;
import com.fangcang.supplier.dto.SupplyBankCardDTO;
import com.fangcang.supplier.dto.SupplyConfirmTypeDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/23 15:09
 * @Description: 添加供应商所需请求信息
 */
@Data
public class AddSupplyRequestDTO extends BaseDTO implements Serializable {


    private static final long serialVersionUID = -1157133039668892651L;

    /**
     * 供应商ID
     */
    private Long supplyId;

    /**
     * 供应商名
     */
    @NotEmpty(message = "供应商名不能为空!")
    private String supplyName;

    /**
     * 结算币种Code
     */
    private String baseCurrency;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 地址
     */
    private String address;

    /**
     * 我司产品经理ID
     */
    private Long merchantPM;

    /**
     * 我司业务经理ID
     */
    private Long merchantBM;
    /**
     * 我司运营员ID
     */
    private String [] merchantOPs;

    /**
     * 我司财务经理ID
     */
    private Long merchantFinancer;

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
     * 账号管理列表
     */
    private List<AccountDTO> userList;

    /**
     * 银行卡管理列表
     */
    private List<SupplyBankCardDTO> bankCardList;

    /**
     * 支付方式
     */
    private Integer billingMethod;

    /**
     * 客户等级
     */
    private Integer customerLevel;


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

}
