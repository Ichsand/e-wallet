package com.enigma.test7maret.entity;

import java.math.BigDecimal;

public class TransactionPostDto {
	private String account;
	private String to;
	private BigDecimal amount;
	private String description;
	private String hp;
	
	public TransactionPostDto() {
		// TODO Auto-generated constructor stub
	}
	

	public TransactionPostDto(String account,BigDecimal amount) {
		super();
		this.account = account;
		this.amount = amount;
	}


	public TransactionPostDto(String account, String to, BigDecimal amount, String description, String hp) {
		super();
		this.account = account;
		this.to = to;
		this.amount = amount;
		this.description = description;
		this.hp = hp;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}
	
}
