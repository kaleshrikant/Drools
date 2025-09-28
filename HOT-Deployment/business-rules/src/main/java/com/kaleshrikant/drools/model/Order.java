package com.kaleshrikant.drools.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

public class Order implements Serializable {
	private String orderId;
	private double amount;
	private double discount;
	private double finalAmount;
	private List<String> appliedDiscounts;

	public Order() {
		this.appliedDiscounts = new ArrayList<>();
	}

	public Order(double amount) {
		this();
		this.amount = amount;
		this.finalAmount = amount;
		this.orderId = "ORD_" + System.currentTimeMillis();
	}

	// Getters and Setters
	public String getOrderId() { return orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public double getAmount() { return amount; }
	public void setAmount(double amount) {
		this.amount = amount;
		calculateFinalAmount();
	}

	public double getDiscount() { return discount; }
	public void setDiscount(double discount) {
		this.discount = discount;
		calculateFinalAmount();
	}

	public double getFinalAmount() { return finalAmount; }
	public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }

	public List<String> getAppliedDiscounts() { return appliedDiscounts; }
	public void setAppliedDiscounts(List<String> appliedDiscounts) {
		this.appliedDiscounts = appliedDiscounts;
	}

	// Helper methods
	private void calculateFinalAmount() {
		this.finalAmount = this.amount * (1 - this.discount);
	}

	public void addDiscount(double discount, String discountReason) {
		this.discount += discount;
		this.appliedDiscounts.add(discountReason);
		calculateFinalAmount();
	}

	@Override
	public String toString() {
		return "Order{orderId='" + orderId + "', amount=" + amount +
				", discount=" + (discount * 100) + "%, finalAmount=" + finalAmount +
				", appliedDiscounts=" + appliedDiscounts + "}";
	}
}
