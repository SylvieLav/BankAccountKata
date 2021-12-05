package com.societegenerale.bankaccountkata.main;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.societegenerale.bankaccountkata.test.AccountTest;

public class MainUserInterface {
	static Account account;

	public static void main(String[] args) {
		account = new Account();
		System.out.println("Welcome on your bank account."
				+ "\n- to deposit money, please type \"deposit\""
				+ "\n- to withdraw money, please type \"withdraw\""
				+ "\n- to check your history, please type \"history\""
				+ "\n- to exit the application, please type \"exit\"");
		Scanner scanner = new Scanner(System.in);
		String action = scanner.next();
		BigDecimal amount = BigDecimal.ZERO;
		
		while(!"exit".equals(action.toLowerCase())) {
			if ("deposit".equals(action.toLowerCase())) {
				System.out.println("Please type the amount to deposit");
				try {
					amount = new BigDecimal(scanner.next());
					account.deposit(amount);
					System.out.println("Deposit of $" + amount + " successful. Your new balance is $" + account.getBalance() + ". Next action ?");
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage() + ". Next action ?");
				} finally {
					action = scanner.next();
				}
			} else if ("withdraw".equals(action.toLowerCase())) {
				System.out.println("Please type the amount to withdraw");
				try {
					amount = new BigDecimal(scanner.next());
					account.withdraw(amount);
					System.out.println("Withdrawal of $" + amount + " successful. Your new balance is $" + account.getBalance() + ". Next action ?");
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage() + ". Next action ?");
				} finally {
					action = scanner.next();
				}
			} else if ("history".equals(action.toLowerCase())) {
				System.out.println("Here is your account history:");
				printHistory();
				System.out.println("Next action ?");
				action = scanner.next();
			} else {
				System.out.println("Invalid entry. Next action ?");
				action = scanner.next();
			}
		}
		System.out.println("The user has exited the application.");
	}
	
	public static void printHistory() {
		for (Operation operation : account.getHistory()) {
			System.out.println("Date: " + operation.getDate()
			+ ", Type: " + operation.getType()
			+ ", Amount: $" + operation.getAmount());
		}
		System.out.println("Your current balance is $" + account.getBalance());
	}

}
