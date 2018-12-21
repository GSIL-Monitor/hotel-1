package com.fangcang.agent.domain;

import com.fangcang.agent.dto.MasterUserInfoDTO;
import com.fangcang.merchant.domain.UserDO;
import com.fangcang.supplier.dto.ContractFileDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 分销商DO
 * @Author: yanming.li@fangcang.com
 * @CreateDate: 2018/5/28 09:48
 */
@Data
@Table(name = "t_agent")
@EqualsAndHashCode(callSuper = false)
public class AgentDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名
     */
    private String agentName;

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
     * 产品经理账号
     */
    private Long merchantPM;

    /**
     * 业务经理账号
     */
    private Long merchantBM;

    /**
     * 财务经理账号
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
     * 结算币种(eg:"CYN")
     */
    private String financeCurrency;

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

    /**
     * 信用额度(总额度)
     */
    private BigDecimal amount;

    /**
     * 剩余额度
     */
    private BigDecimal remainingAmount;

    /**
     * 已用额度
     */
    private BigDecimal usedAmount;

    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;

    /**
     * 分销商类别:ENTERPRISE(1, "企业"), TRAVELAGENCY(2, "旅行社"), TOURISTGUIDE(3, "导游"), FLEET(4, "车队"), HOTELSALESMAN(5, "酒店业务员")
     */
    private Integer category;

    /** 以下是对应关系,数据库不存在 */
    /**
     * 产品经理姓名
     */
    private String merchantPMName;

    /**
     * 业务经理姓名
     */
    private String merchantBMName;

    /**
     * 财务员姓名
     */
    private String merchantFinancerName;
    /**
     * 跟单员姓名
     */
    private String merchantOPName;

    /**
     * 一对一查询商家账号信息
     */
    private UserDO userDO;

    /**
     * 一对一查询对象
     */
    private AgentUserDO agentUserDO;

    /**
     * 查询单个供应商详情 一对多(个供应商账号信息)
     */
    private List<AgentUserDO> userList;

    /**
     * master账号信息
     */
    private MasterUserInfoDTO masterAccount;

    /**
     * 一对多个银行卡信息
     */
    private List<AgentBankCardDO> bankCardList;

    /**
     * 一对多个访问记录
     */
    private List<AgentVisitDO> visitList;

    /**
     * 一对多个合同文件
     */
    private List<ContractFileDTO> contractFileList;

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
     * 每单折扣，或者回扣
     * 页面输入50%。
     * 这里传入的是50
     * 数据库里存0.5
     */
    private Double discount;

    /**
     * 折扣类型：1按份，2按间夜
     */
    private Integer discountType;
}