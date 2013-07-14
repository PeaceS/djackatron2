package com.test.djackatron2.model;

public class Account {
	private String name;
	private Long id;
	private Double amount;

	public Account(){}
	
	public Account(long id, String name, double amount){
		this.id = id;
		this.name = name;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
