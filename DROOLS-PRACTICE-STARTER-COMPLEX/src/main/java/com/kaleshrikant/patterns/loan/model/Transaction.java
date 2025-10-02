package com.kaleshrikant.patterns.loan.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Transaction {
	private String customerId;
	private String type;
	private double amount;

	public Transaction(String customerId, String type, double amount) {
		this.customerId = customerId;
		this.type = type;
		this.amount = amount;
	}

	public String getCustomerId() { return customerId; }
	public String getType() { return type; }
	public double getAmount() { return amount; }
}
