package com.kaleshrikant.drools.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

public class Order {
	private Long id;
	private Long customerId;
	private double totalAmount;
	private int discountPercentage;
	private String discountReason;
	private LocalDateTime orderDate;
	private List<String> appliedRules;

	public Order() {
		this.appliedRules = new ArrayList<>();
		this.orderDate = LocalDateTime.now();
		this.discountPercentage = 0; // Explicitly set to 0
	}

	public Order(Long id, Long customerId, double totalAmount) {
		this();
		this.id = id;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
	}

	// Getters and Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Long getCustomerId() { return customerId; }
	public void setCustomerId(Long customerId) { this.customerId = customerId; }

	public double getTotalAmount() { return totalAmount; }
	public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

	public int getDiscountPercentage() { return discountPercentage; }
	public void setDiscountPercentage(int discountPercentage) { this.discountPercentage = discountPercentage; }

	public String getDiscountReason() { return discountReason; }
	public void setDiscountReason(String discountReason) { this.discountReason = discountReason; }

	public LocalDateTime getOrderDate() { return orderDate; }
	public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

	public List<String> getAppliedRules() { return appliedRules; }
	public void setAppliedRules(List<String> appliedRules) { this.appliedRules = appliedRules; }

	public void addAppliedRule(String ruleName) {
		this.appliedRules.add(ruleName);
	}

	public double getFinalAmount() {
		return totalAmount - (totalAmount * discountPercentage / 100.0);
	}

	@Override
	public String toString() {
		return String.format("Order{id=%d, customerId=%d, total=%.2f, discount=%d%%, final=%.2f, reason='%s', rules=%s}",
				id, customerId, totalAmount, discountPercentage, getFinalAmount(), discountReason, appliedRules);
	}
}
