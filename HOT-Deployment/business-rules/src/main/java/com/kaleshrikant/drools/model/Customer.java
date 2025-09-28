package com.kaleshrikant.drools.model;

import java.io.Serializable;

/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

	public class Customer implements Serializable {
	private String name;
	private String level; // "NEW", "SILVER", "GOLD", "PLATINUM"
	private int loyaltyPoints;
	private boolean vip;

	public Customer() {}

	public Customer(String name, String level) {
		this.name = name;
		this.level = level;
	}

	public Customer(String name, String level, int loyaltyPoints, boolean vip) {
		this.name = name;
		this.level = level;
		this.loyaltyPoints = loyaltyPoints;
		this.vip = vip;
	}

	// Getters and Setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getLevel() { return level; }
	public void setLevel(String level) { this.level = level; }

	public int getLoyaltyPoints() { return loyaltyPoints; }
	public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }

	public boolean isVip() { return vip; }
	public void setVip(boolean vip) { this.vip = vip; }

	@Override
	public String toString() {
		return "Customer{name='" + name + "', level='" + level +
				"', loyaltyPoints=" + loyaltyPoints + ", vip=" + vip + "}";
	}
}
