package com.travel.common.dto.product.response;

import java.io.Serializable;

/**
 *   2018/2/7.
 */
public class SaleStateResponseDTO implements Serializable{

    private Long stateIdToB;
    private Long stateIdToTaobao;
    private Long stateIdToCtrip;
    private Long pricePlanId;
    /**
     * B2B售卖状态
     */
    private Integer saleStateToB;
    private String saleStateToBValue;
    /**
     *携程售卖状态
     */
    private Integer saleStateToCtrip;
    private String saleStateToCtripValue;
    /**
     * 淘宝售卖状态
     */
    private Integer saleStateToTaobao;
    private String saleStateToTaobaoValue;

    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSaleStateToBValue() {
        return saleStateToBValue;
    }

    public void setSaleStateToBValue(String saleStateToBValue) {
        this.saleStateToBValue = saleStateToBValue;
    }

    public String getSaleStateToCtripValue() {
        return saleStateToCtripValue;
    }

    public void setSaleStateToCtripValue(String saleStateToCtripValue) {
        this.saleStateToCtripValue = saleStateToCtripValue;
    }

    public String getSaleStateToTaobaoValue() {
        return saleStateToTaobaoValue;
    }

    public void setSaleStateToTaobaoValue(String saleStateToTaobaoValue) {
        this.saleStateToTaobaoValue = saleStateToTaobaoValue;
    }

    public Long getStateIdToB() {
        return stateIdToB;
    }

    public void setStateIdToB(Long stateIdToB) {
        this.stateIdToB = stateIdToB;
    }

    public Long getStateIdToTaobao() {
        return stateIdToTaobao;
    }

    public void setStateIdToTaobao(Long stateIdToTaobao) {
        this.stateIdToTaobao = stateIdToTaobao;
    }

    public Long getStateIdToCtrip() {
        return stateIdToCtrip;
    }

    public void setStateIdToCtrip(Long stateIdToCtrip) {
        this.stateIdToCtrip = stateIdToCtrip;
    }

    public Long getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public Integer getSaleStateToB() {
        return saleStateToB;
    }

    public void setSaleStateToB(Integer saleStateToB) {
        this.saleStateToB = saleStateToB;
    }

    public Integer getSaleStateToCtrip() {
        return saleStateToCtrip;
    }

    public void setSaleStateToCtrip(Integer saleStateToCtrip) {
        this.saleStateToCtrip = saleStateToCtrip;
    }

    public Integer getSaleStateToTaobao() {
        return saleStateToTaobao;
    }

    public void setSaleStateToTaobao(Integer saleStateToTaobao) {
        this.saleStateToTaobao = saleStateToTaobao;
    }
}
