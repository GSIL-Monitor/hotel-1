package com.fangcang.agent.request;

import com.fangcang.agent.dto.AgentBankCardDTO;
import com.fangcang.agent.dto.UserInfoDTO;
import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/5/24 00:58
 * @Description: 保存分销商（添加/编辑时使用）
 */
@Data
public class AddAgentRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8458844266154886296L;

    /**
     * 新增时无,编辑时有
     */
    private Long agentId;

    /**
     * 分销商名称
     */
    @NotEmpty(message = "分销商名不能为null")
    private String agentName;

    /**
     * 分销商结算币种
     */
    private String financeCurrency;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 地址
     */
    private String address;

    /**
     * 我司产品经理账号
     */
    private Long merchantPM;

    /**
     * 我司业务经理账号
     */
    private Long merchantBM;

    /**
     * 我司跟单员账号
     */
    private String [] merchantOPs;

    /**
     * 我司财务经理账号
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
     * 额度
     */
    private BigDecimal amount;

    /**
     * 分销商登录B2B网的账号列表
     */
    List<UserInfoDTO> userList;

    /**
     * 分销商银行卡信息
     */
    List<AgentBankCardDTO> bankCardList;

    /**
     * 商家ID
     */
    private Long merchantId;
    /**
     * 支付方式
     */
    private Integer billingMethod;

    /**
     * 客户等级
     */
    private Integer customerLevel;

    /**
     * 分销商类别:ENTERPRISE(1, "企业"), TRAVELAGENCY(2, "旅行社"), TOURISTGUIDE(3, "导游"), FLEET(4, "车队"), HOTELSALESMAN(5, "酒店业务员")
     */
    private Integer category;


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
