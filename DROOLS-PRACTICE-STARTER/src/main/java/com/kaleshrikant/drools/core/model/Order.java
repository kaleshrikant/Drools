package com.kaleshrikant.drools.core.model;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class Order {
	private String orderId;
	private double amount;
	private int itemCount;
	private String customerType; // "REGULAR" or "PREMIUM"
	private double discount;

	public Order() {}
	public Order(String orderId, double amount, int itemCount, String customerType) {
		this.orderId = orderId;
		this.amount = amount;
		this.itemCount = itemCount;
		this.customerType = customerType;
		this.discount = 0.0;
	}

	public String getOrderId() { return orderId; }
	public double getAmount() { return amount; }
	public int getItemCount() { return itemCount; }
	public String getCustomerType() { return customerType; }
	public double getDiscount() { return discount; }

	public void setOrderId(String orderId) { this.orderId = orderId; }
	public void setAmount(double amount) { this.amount = amount; }
	public void setItemCount(int itemCount) { this.itemCount = itemCount; }
	public void setCustomerType(String customerType) { this.customerType = customerType; }
	public void setDiscount(double discount) { this.discount = discount; }
}
