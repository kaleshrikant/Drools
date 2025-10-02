package com.kaleshrikant.patterns.loan.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Account {
	private String customerId;
	private double balance;

	public Account(String customerId, double balance) {
		this.customerId = customerId;
		this.balance = balance;
	}

	public String getCustomerId() { return customerId; }
	public double getBalance() { return balance; }
}
