package com.calculator.exceptions;

public class UnsupportedNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3737134232186488865L;

	public UnsupportedNumberException(String message) {
		super(message);
	}

	public UnsupportedNumberException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
