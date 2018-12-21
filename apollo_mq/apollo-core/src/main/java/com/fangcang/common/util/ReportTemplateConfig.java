package com.fangcang.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/7/11.
 */
@Component
@ConfigurationProperties(prefix = "apollo.template")
@Data
public class ReportTemplateConfig {

    /**
     * 配额报表模板存放路径
     */
    private String quotaReportUrl;

    /**
     * 应收报表模板存放路径
     */
    private String receivableReportUrl;

    /**
     * 应付报表模板存放路径
     */
    private String payableReportUrl;

    /**
     * 利润报表模板存放路径
     */
    private String profitReportUrl;

    /**
     * 包房报表模板存放路径
     */
    private String prepayContractReportUrl;

    /**
     * 分销商账单模板存放路径
     */
    private String agentBillReportUrl;

    /**
     * 供应商账单模板存放路径
     */
    private String supplyBillReportUrl;
}
