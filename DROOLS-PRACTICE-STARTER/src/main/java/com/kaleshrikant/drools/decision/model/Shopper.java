package com.kaleshrikant.drools.decision.model;

/**
 * @author Shrikant Kale
 * @Date 25 Sep 2025
 */

public class Shopper {
	private String type;
	private int purchaseAmount;
	private int discount;

	public Shopper(String type, int purchaseAmount) {
		this.type = type;
		this.purchaseAmount = purchaseAmount;
	}

	public String getType() { return type; }
	public int getPurchaseAmount() { return purchaseAmount; }
	public int getDiscount() { return discount; }
	public void setDiscount(int discount) { this.discount = discount; }
}
