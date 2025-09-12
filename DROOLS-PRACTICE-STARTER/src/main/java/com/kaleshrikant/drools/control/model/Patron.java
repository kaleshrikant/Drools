package com.kaleshrikant.drools.control.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class Patron {
	private String name;
	private boolean premium;
	private double purchaseAmount;

	public Patron(String name, boolean isPremium, double purchaseAmount) {
		this.name = name;
		this.premium = isPremium;
		this.purchaseAmount = purchaseAmount;
	}

	public String getName() { return name; }
	public boolean isPremium() { return premium; }
	public double getPurchaseAmount() { return purchaseAmount; }
}
