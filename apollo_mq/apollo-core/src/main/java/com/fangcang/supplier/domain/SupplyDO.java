package com.fangcang.supplier.domain;

import com.fangcang.merchant.domain.UserDO;
import com.fangcang.supplier.dto.ContractFileDTO;
import com.fangcang.supplier.dto.MasterAccountDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 供应商DO
 * @Author: yanming.li@fangcang.com
 * @CreateDate: 2018/5/28 09:48
 */
@Data
@Table(name = "t_agent")
@EqualsAndHashCode(callSuper = false)
public class SupplyDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
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
     * 供应商名
     */
    private String supplyName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 电话
     */
    private String phone;

    private String email;

    /**
     * 产品经理ID
     */
    private Long merchantPM;

    /**
     * 业务经理ID
     */
    private Long merchantBM;

    /**
     * 财务经理ID
     */
    private Long merchantFinancer;

    /**
     * 跟单员ID
     */
    private String [] merchantOPs;

    /**
     * 跟单员ID拼接字符
     */
    private String merchantOPStr;

    private Date createTime;

    private String creator;

    private Date modifyTime;

    private String modifier;

    /**
     * 是否启用
     */
    private Integer isActive;

    /**
     * 地址
     */
    private String address;

    /**
     * 结算币种
     */
    private String baseCurrency;

    /**
     * 是否常用
     */
    private Integer frequentlyUse;

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
     * 结算方式(MONTH(1, "月结"), HALFMONTH(2, "半月结"), WEEK(3, "周结"), SINGLE(4, "单结"), DAY(5, "日结");)
     */
    private Integer billingMethod;

    /**
     * 客户等级(1 一般 2白银 3 黄金 4 铂金 5 钻石)
     */
    private Integer customerLevel;


    /** 以下都是数据库没有的 */

    /**
     * 一对一查询对象 查询列表只用显示masterUser(唯一)
     */
    private SupplyUserDO supplyUserDO;


    /**
     * 一对一查询商家账号信息,列表查询只显示业务经理
     */
    private UserDO userDO;

    /**
     * 查询单个供应商详情 一对多(个供应商账号信息)
     */
    private List<SupplyUserDO> userList;

    /**
     * master账号信息
     */
    private MasterAccountDTO masterAccount;

    /**
     * 一对多个银行卡信息
     */
    private List<SupplyBankCardDO> bankCardList;

    /**
     * 一对多个访问记录
     */
    private List<SupplyVisitDO> visitList;

    /**
     * 产品经理姓名
     */
    private String merchantPMName;

    /**
     * 业务经理姓名
     */
    private String merchantBMName;

    /**
     * 一对多个合同文件
     */
    private List<ContractFileDTO> contractFileList;

    /**
     * 财务员姓名
     */
    private String merchantFinancerName;
    /**
     * 跟单员姓名
     */
    private String merchantOPName;
    /**
     * 押金
     */
    private BigDecimal depositAmount;
    /**
     * 预付款余额
     */
    private BigDecimal prepayBalance;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;


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
}