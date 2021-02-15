package com.enigma.test7maret.exception;

public class NumberFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberFormatException(String message) {
		super(message);
	}

	public NumberFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
