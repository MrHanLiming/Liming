package com.liming.commons.exception.exceptiontype;

public class UnauthorizedException extends NestedRuntimeException {

	public UnauthorizedException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	public UnauthorizedException(String msg) {
		super(msg);
	}
	private static final long serialVersionUID = 1L;

}
