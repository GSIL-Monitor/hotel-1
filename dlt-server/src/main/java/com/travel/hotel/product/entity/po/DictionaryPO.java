package com.travel.hotel.product.entity.po;

public class DictionaryPO {
    private Long idtDictionary;

    private String dataCode;

    private String dataValue;

    private String dataType;

    private String dataDescription;

    public Long getIdtDictionary() {
        return idtDictionary;
    }

    public void setIdtDictionary(Long idtDictionary) {
        this.idtDictionary = idtDictionary;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription == null ? null : dataDescription.trim();
    }
}