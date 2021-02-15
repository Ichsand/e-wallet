package com.enigma.test7maret.dto;

import java.math.BigDecimal;


public class AccountDto {
	private Long id;
	private String account;
	private String name;
	private BigDecimal balance;
	private Double point;
	private int status;
	public AccountDto() {
		// TODO Auto-generated constructor stub
	}
	public AccountDto(Long id, String account, String name, BigDecimal balance, Double point, int status) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.balance = balance;
		this.point = point;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
