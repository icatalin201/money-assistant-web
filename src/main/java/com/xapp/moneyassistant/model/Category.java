package com.xapp.moneyassistant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {
	
	public interface CategoryType {
		String INCOME = "income";
		String EXPENSE = "expense";
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@NotNull(message = "Introduceti numele categoriei!")
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	public Category() {
		super();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
	
}
