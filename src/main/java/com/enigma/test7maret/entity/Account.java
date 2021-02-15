package com.enigma.test7maret.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 50)
	private String account;

	@Column(length = 100)
	private String name;

	@Column(name = "balance")
	private BigDecimal balance;

	@Column(name = "point")
	private Double point;
	
	// 0 = Active	
	// 1 = Not Active	
	@Column()
	private int status;

	@OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
	private List<Transaction> list;
	
	@OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
	private List<Transaction> list2;
	
	public Account() {
	}
	public Account(Long id, String account, String name, BigDecimal balance, Double point, int status) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.balance = balance;
		this.point = point;
		this.status = status;
	}
	public Account( String account, String name, BigDecimal balance, Double point, int status) {
		super();
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
	public List<Transaction> getList() {
		return list;
	}
	public void setList(List<Transaction> list) {
		this.list = list;
	}
	public List<Transaction> getList2() {
		return list2;
	}
	public void setList2(List<Transaction> list2) {
		this.list2 = list2;
	}
	
}
