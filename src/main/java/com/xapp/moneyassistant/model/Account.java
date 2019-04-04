package com.xapp.moneyassistant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@NotNull(message = "Introduceti numele contului!")
	@Column(name = "name")
	private String name;
	
	@NotNull(message = "Introduceti valoarea actuala a contului!")
	@Column(name = "amount")
	private Double amount;
	
	@Transient
	private Double currentAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private AccountType accountType;
	
	public Account () {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
}
