package com.societegenerale.bankaccountkata.main;

import java.math.BigDecimal;

public class Operation {
	private String date;
	private OperationType type;
	private BigDecimal amount;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public OperationType getType() {
		return type;
	}
	public void setType(OperationType type) {
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
