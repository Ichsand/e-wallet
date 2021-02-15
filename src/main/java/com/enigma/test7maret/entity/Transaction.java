package com.enigma.test7maret.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;



@Entity
@Table(name = "tb_transaction")
@NamedNativeQuery(name = "Transaction.findAllByFromAndDateSQL", query = "SELECT * from tb_transaction t where t.froms = :froms and t.transaction_date = :date", resultClass = Transaction.class)
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@Column(name = "types" , length = 50)
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "froms", nullable = true)
	private Account from;
	
	@ManyToOne
	@JoinColumn()
	private Account to;
	
	@Column()
	private BigDecimal amount;
	
	@Column(length = 255)
	private String description;

	public Transaction() {
	}

	public Transaction(Long id, Date transactionDate, String type, Account from, Account to, BigDecimal amount,
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
	public Transaction( Date transactionDate, String type, Account from, Account to, BigDecimal amount,
			String description) {
		super();
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

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
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
