package com.enigma.test7maret.exception;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDetails {
	@ApiModelProperty(notes = "timestamp of error")
	private Date timestamp;
	@ApiModelProperty(notes = "message of error")
	private int status;

	@ApiModelProperty(notes = "message of error")
	private String message;
	
	@ApiModelProperty(notes = "detail of error")
	private String path;

	public ErrorDetails(Date timestamp, int status, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	
}
