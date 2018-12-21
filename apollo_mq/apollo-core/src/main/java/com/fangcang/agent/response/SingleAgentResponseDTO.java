package com.fangcang.agent.response;

import com.fangcang.agent.dto.AgentBankCardDTO;
import com.fangcang.agent.dto.AgentVisitInfoDTO;
import com.fangcang.agent.dto.MasterUserInfoDTO;
import com.fangcang.agent.dto.UserInfoDTO;
import com.fangcang.supplier.dto.ContractFileDTO;
import com.fangcang.supplier.dto.MerchantOPDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 11:04
 * @Description:
 */
@Data
public class SingleAgentResponseDTO implements Serializable {

    private static final long serialVersionUID = 2069650346354933204L;

    /**
     * 分销商ID
     */
    private Long agentId;

    /**
     * 分销商编码
     */
    private String agentCode;

    /**
     * 分销商名称
     */
    private String agentName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 产品经理账号
     */
    private Long merchantPM;

    /**
     * 产品经理姓名
     */
    private String merchantPMName;

    /**
     * 业务经理账号
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
     * 财务员账号
     */
    private Long merchantFinancer;

    /**
     * 财务员姓名
     */
    private String merchantFinancerName;

    /**
     * 用户列表
     */
    private List<UserInfoDTO> userList;

    /**
     * 合同文件列表
     */
    private List<ContractFileDTO> contractFileList;

    /**
     * 拜访记录
     */
    private List<AgentVisitInfoDTO> visitList;

    /**
     * 银行卡信息
     */
    private List<AgentBankCardDTO> bankCardList;

    /**
     * 结算方式
     */
    private Integer billingMethod;

    /**
     * 客户等级
     */
    private Integer customerLevel;

    /**
     * 信用额度
     */
    private BigDecimal amount;

    /**
     * 地址
     */
    private String address;

    /**
     * 结算币种
     */
    private String financeCurrency;

    /**
     * 是否启用
     */
    private Integer isActive;

    /**
     * master账号信息
     */
    private MasterUserInfoDTO masterAccount;

    /**
     * 分销商类别:ENTERPRISE(1, "企业"), TRAVELAGENCY(2, "旅行社"), TOURISTGUIDE(3, "导游"), FLEET(4, "车队"), HOTELSALESMAN(5, "酒店业务员")
     */
    private Integer category;

    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 现金余额
     */
    private BigDecimal cashAmount;



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

}
