package com.travel.common.dto;

import com.travel.common.enums.ResultTypeEnum;
import lombok.Data;

/**
 * @Description 通用数据返回对象
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月30日下午3:04:33
 */
@Data
public class ResultDTO<T> extends SerializeDTO {

	private static final long serialVersionUID = 1255091342542787711L;

    public static final Integer SUCCESS = 1;

    public static final Integer FAILURE = 0;

	/**
     * 1：成功；0：失败
     */
    private Integer result;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorReason;

    /**
     * 返回的数据集
     */
    private T obj;

    public ResultDTO() {}

    public ResultDTO(ResultTypeEnum resultTypeEnum) {
        this.result = resultTypeEnum.key;
        this.errorCode = resultTypeEnum.code;
        this.errorReason = resultTypeEnum.desc;
    }

    public ResultDTO(ResultTypeEnum resultTypeEnum, String customerReason) {
        this.result = resultTypeEnum.key;
        this.errorCode = resultTypeEnum.code;
        this.errorReason = customerReason;
    }

    public ResultDTO(ResultTypeEnum resultTypeEnum, String customerReason, T t) {
        this.result = resultTypeEnum.key;
        this.errorCode = resultTypeEnum.code;
        this.errorReason = customerReason;
        this.obj = t;
    }

    public ResultDTO(ResultTypeEnum resultTypeEnum, T t) {
        this.result = resultTypeEnum.key;
        this.errorCode = resultTypeEnum.code;
        this.errorReason = resultTypeEnum.desc;
        this.obj = t;
    }

}
