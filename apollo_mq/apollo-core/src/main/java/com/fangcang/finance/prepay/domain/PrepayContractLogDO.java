package com.fangcang.finance.prepay.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fin_prepay_contract_log")
public class PrepayContractLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 日志类型，1合约日志，2押金
     */
    private Byte type;

    /**
     * 合约id
     */
    @Column(name = "contract_id")
    private Integer contractId;

    /**
     * 供应商id
     */
    @Column(name = "supply_id")
    private Integer supplyId;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    @Column(name = "operator_time")
    private Date operatorTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取日志类型，1合约日志，2押金
     *
     * @return type - 日志类型，1合约日志，2押金
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置日志类型，1合约日志，2押金
     *
     * @param type 日志类型，1合约日志，2押金
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取合约id
     *
     * @return contract_id - 合约id
     */
    public Integer getContractId() {
        return contractId;
    }

    /**
     * 设置合约id
     *
     * @param contractId 合约id
     */
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    /**
     * 获取供应商id
     *
     * @return supply_id - 供应商id
     */
    public Integer getSupplyId() {
        return supplyId;
    }

    /**
     * 设置供应商id
     *
     * @param supplyId 供应商id
     */
    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    /**
     * 获取操作内容
     *
     * @return content - 操作内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置操作内容
     *
     * @param content 操作内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作时间
     *
     * @return operator_time - 操作时间
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     * 设置操作时间
     *
     * @param operatorTime 操作时间
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
}