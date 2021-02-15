package com.enigma.test7maret.exception;

@SuppressWarnings("serial")
public class BadRequestException extends Exception {

	public BadRequestException(String message) {
		super(message);
	}
	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
