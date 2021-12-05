package com.societegenerale.bankaccountkata.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.societegenerale.bankaccountkata.main.Account;

public class AccountTest {
	private static Account account;

	@BeforeEach
	public void initialize() {
		account = new Account();
	}
	
	public static Account getAccount() {
		return account;
	}
	
	@Test
	public void givenAnAccountIsInitaialized_thenAmountEqualsZero() {
		assertEquals(BigDecimal.ZERO, account.getBalance());
	}
	
	@Test
	public void givenTheAmountDepositedNullOrNotGreaterThanZeroOrHasMoreThanTwoDigits_thenThrowNumberFormatException() {
		assertThrows(NumberFormatException.class, () -> {
			account.deposit(null);
		});
		assertThrows(NumberFormatException.class, () -> {
			account.deposit(new BigDecimal(-1));
		});
		assertThrows(NumberFormatException.class, () -> {
			account.deposit(new BigDecimal(0));
		});
		assertThrows(NumberFormatException.class, () -> {
			account.deposit(new BigDecimal(1.2345));
		});
	}
	
	@Test
	public void givenAmountWithdrawnNullOrNotGreaterThanZeroOrHasMoreThanTwoDigits_thenThrowNumberFormatException() {
		assertThrows(NumberFormatException.class, () -> {
			account.withdraw(null);
		});
		assertThrows(NumberFormatException.class, () -> {
			account.withdraw(new BigDecimal(-1));
		});
		assertThrows(NumberFormatException.class, () -> {
			account.withdraw(new BigDecimal(0));
		});
		assertThrows(NumberFormatException.class, () -> {
			account.deposit(new BigDecimal(1.2345));
		});
	}
	
	@Test
	public void givenAmountWithdrawnIsGreaterThanBalance_thenThrowNumberFormatException() {
		assertThrows(NumberFormatException.class, () -> {
			account.withdraw(account.getBalance().add(new BigDecimal(1)));
		});
	}
	
	@Test
	public void givenDepositIsFive_ThenBalanceIsIncrementedByFive() {
		BigDecimal oldBalance = account.getBalance();
		account.deposit(new BigDecimal(5));
		assertEquals(oldBalance.add(new BigDecimal(5)), account.getBalance());
	}
	
	@Test
	public void givenWhithdrawalIsFive_ThenBalanceIsDecrementedByFive() {
		account.setBalance(new BigDecimal(12));
		BigDecimal oldBalance = account.getBalance();
		account.withdraw(new BigDecimal(5));
		assertEquals(oldBalance.subtract(new BigDecimal(5)), account.getBalance());
	}

}
