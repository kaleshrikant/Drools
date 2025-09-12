package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class DiscountFact {
	private String customerName;
	private double discountAmount;

	public DiscountFact(String customerName, double discountAmount) {
		this.customerName = customerName;
		this.discountAmount = discountAmount;
	}

	public String getCustomerName() { return customerName; }
	public double getDiscountAmount() { return discountAmount; }
}
