package com.calculator.exceptions;

public class InvalidDelimiterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7070760366965072871L;

	public InvalidDelimiterException(String message) {
		super(message);
	}

	public InvalidDelimiterException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
