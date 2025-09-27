package com.kaleshrikant.drools.advanced.model;

/**
 * 📦 BuyerProfile Fact Class
 * Represents customer data for discount evaluation.
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class BuyerProfile {
	private String name;
	private int age;
	private String membershipType;
	private int discount;

	public BuyerProfile(String name, int age, String membershipType) {
		this.name = name;
		this.age = age;
		this.membershipType = membershipType;
		this.discount = 0;
	}

	// Getters & Setters
	public String getName() { return name; }
	public int getAge() { return age; }
	public String getMembershipType() { return membershipType; }
	public int getDiscount() { return discount; }

	public void setDiscount(int discount) { this.discount = discount; }

	@Override
	public String toString() {
		return "BuyerProfile{" +
				"name='" + name + '\'' +
				", age=" + age +
				", membershipType='" + membershipType + '\'' +
				", discount=" + discount +
				"%}";
	}
}
