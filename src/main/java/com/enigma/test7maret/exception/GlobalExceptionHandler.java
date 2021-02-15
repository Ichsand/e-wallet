package com.enigma.test7maret.exception;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(NotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> resourceNotFoundException(BadRequestException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(OkException.class)
	public ResponseEntity<?> resourceNotFoundException(OkException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),HttpStatus.OK.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}
	
}
