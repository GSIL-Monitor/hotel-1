package com.fangcang.common.exception;

/**
 * Created by ASUS on 2018/5/18.
 */
public class ParameterException extends RuntimeException{

    private static final long serialVersionUID = -2371817378797232156L;

    public ParameterException() {
        super();
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }
}
