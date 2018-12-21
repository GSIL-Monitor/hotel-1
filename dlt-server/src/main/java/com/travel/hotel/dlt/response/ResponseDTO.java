package com.travel.hotel.dlt.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 6899493106722037546L;

    /**
     * 结果(1：成功 0：失败)
     */
    protected Integer result;

    /**
     * 错误编码
     */
    protected String failCode;

    /**
     * 失败原因
     */
    protected String failReason;

    /**
     * 返回对象
     */
    protected T model;

    public ResponseDTO(){}
    public ResponseDTO(Integer result){
        this.result=result;
    }
    public ResponseDTO(Integer result, String failCode, String failReason){
        this.result=result;
        this.failCode=failCode;
        this.failReason=failReason;
    }

}
