package com.fangcang.common.enums;

/**
 * Created by Vinney on 2018/5/17.
 */
public enum UploadTypeEnum {

    SUPPLY_CONTRACT_FILE("supplyContract","上传供应商合同附件"),AGENT_CONTRACT_FILE("agentContract","上传分销商合同附件");

    private String typeName;
    private String typeValue;

    UploadTypeEnum(String typeName, String typeValue) {
        this.typeName = typeName;
        this.typeValue = typeValue;
    }
}
