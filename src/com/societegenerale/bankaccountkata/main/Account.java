package com.societegenerale.bankaccountkata.main;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
	private BigDecimal balance;
	private List<Operation> history;
	
	public Account() {
		this.balance = BigDecimal.ZERO;
		history = new ArrayList<>();
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Operation> getHistory() {
		return this.history;
	}
	public void setHistory(List<Operation> history) {
		this.history = history;
	}
	
	public void deposit(final BigDecimal input) {
		setOperation(input, OperationType.DEPOSIT);
	}
	
	public void withdraw(final BigDecimal input) {
		setOperation(input, OperationType.WITHDRAW);
	}
	
	public void setOperation(final BigDecimal amount, final OperationType type) {
		if (amount == null)
			throw new NumberFormatException("Amount cannot be null");
		else if (amount.compareTo(BigDecimal.ZERO)<=0)
			throw new NumberFormatException("Amount must be greater than zero");
		else if (amount.scale()>2)
			throw new NumberFormatException("Amount cannot have more than two digits");
		
		Operation operation = new Operation();
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
		final LocalDateTime dateTime = LocalDateTime.now();
		operation.setDate(formatter.format(dateTime));
		
		operation.setType(type);
		
		operation.setAmount(amount);
		
		if (type == OperationType.DEPOSIT) {
			setBalance(balance.add(amount));
		} else if (type == OperationType.WITHDRAW) {
			if (amount.compareTo(balance)>0)
				throw new NumberFormatException("Amount cannot be greater than balance");
			setBalance(balance.subtract(amount));
		}
				
		this.history.add(operation);
	}
}
