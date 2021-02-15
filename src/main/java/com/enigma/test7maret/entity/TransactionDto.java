package com.enigma.test7maret.entity;

import java.math.BigDecimal;
import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class TransactionDto {
	private Long id;
	private Date transactionDate;
	private String type;
	private String from;
	private String to;
	private BigDecimal amount;
	private String description;

	public TransactionDto() {
		// TODO Auto-generated constructor stub
	}

	public TransactionDto(Long id, Date transactionDate, String type, String from, String to, BigDecimal amount,
			String description) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.type = type;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
