package com.kaleshrikant.drools.advanced.model;

/**
 * @author Shrikant Kale
 * @Date 26 Sep 2025
 */

public class CustomerInfo {
	private String name;
	private int age;
	private boolean premiumMember;

	public CustomerInfo() {}

	public CustomerInfo(String name, int age, boolean premiumMember) {
		this.name = name;
		this.age = age;
		this.premiumMember = premiumMember;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public boolean isPremiumMember() { return premiumMember; }
	public void setPremiumMember(boolean premiumMember) { this.premiumMember = premiumMember; }

	@Override
	public String toString() {
		return "CustomerInfo{" +
				"name='" + name + '\'' +
				", age=" + age +
				", premiumMember=" + premiumMember +
				'}';
	}
}
