package com.enigma.test7maret.dto;

import java.math.BigDecimal;

public class AccountPostDto {
	private String account;
	private String name;
	private BigDecimal balance;
	private Double point;
	private int status;
	public AccountPostDto(String account, String namae, BigDecimal balance, double point) {
		super();
		this.account = account;
		this.name = namae;
		this.balance = balance;
		this.point = point;
	}
	public AccountPostDto(String account, String namae, BigDecimal balance, int status) {
		super();
		this.account = account;
		this.name = namae;
		this.balance = balance;
		this.status = status;
	}
	public AccountPostDto(String account, BigDecimal balance, Double point) {
		super();
		this.account = account;
		this.balance = balance;
		this.point = point;
	}
	public AccountPostDto(String account, BigDecimal balance) {
		super();
		this.account = account;
		this.balance = balance;
	}
	public AccountPostDto() {
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String namae) {
		this.name = namae;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	
}
