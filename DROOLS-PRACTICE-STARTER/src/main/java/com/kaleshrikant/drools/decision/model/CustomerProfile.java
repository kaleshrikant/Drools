package com.kaleshrikant.drools.decision.model;

/**
 * @author Shrikant Kale
 * @Date 25 Sep 2025
 */

public class CustomerProfile {
	private String name;
	private String type;
	private int purchaseAmount;
	private int discount;

	public CustomerProfile(String name, String type, int purchaseAmount) {
		this.name = name;
		this.type = type;
		this.purchaseAmount = purchaseAmount;
	}

	public String getName() { return name; }
	public String getType() { return type; }
	public int getPurchaseAmount() { return purchaseAmount; }
	public int getDiscount() { return discount; }
	public void setDiscount(int discount) { this.discount = discount; }
}
