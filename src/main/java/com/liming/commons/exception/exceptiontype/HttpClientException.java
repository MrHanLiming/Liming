package com.liming.commons.exception.exceptiontype;

/**
 * 业务错误（常用）
 */
public class HttpClientException extends NestedRuntimeException {

	private static final long serialVersionUID = -4084444984163796577L;

	private int statusCode;

	/**
	 * Construct a new instance of {@code HttpClientException} with the given
	 * message.
	 * 
	 * @param msg
	 *            the message
	 */
	public HttpClientException(int statusCode, String msg) {
		super(msg);
		this.statusCode = statusCode;
	}

	public HttpClientException(String msg) {
		super(msg);
	}

	/**
	 * Construct a new instance of {@code HttpClientException} with the given
	 * message and exception.
	 * 
	 * @param msg
	 *            the message
	 * @param ex
	 *            the exception
	 */
	public HttpClientException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
