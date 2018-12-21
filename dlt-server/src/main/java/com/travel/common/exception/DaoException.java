package com.travel.common.exception;

/**
 * @Description DAO异常
 * @author
 * @date 2018年1月25日 下午17:59:59
 */
public class DaoException extends RuntimeException {


	private static final long serialVersionUID = 7729526086690820276L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
