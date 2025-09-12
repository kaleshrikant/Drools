package com.kaleshrikant.drools.control.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class AccountHolder {
	private String name;
	private int age;
	private String status;

	public AccountHolder(String name, int age, String status) {
		this.name = name;
		this.age = age;
		this.status = status;
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public String getStatus() { return status; }

	public void setStatus(String status) { this.status = status; }
}
