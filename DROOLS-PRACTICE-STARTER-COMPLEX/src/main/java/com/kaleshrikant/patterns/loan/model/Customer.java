package com.kaleshrikant.patterns.loan.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Customer {
	private String name;
	private String status;
	private int age;
	private double income;
	private int creditScore;
	private String id;

	public Customer(String name, String status, int age, double income, int creditScore) {
		this.name = name;
		this.status = status;
		this.age = age;
		this.income = income;
		this.creditScore = creditScore;
		this.id = name.toUpperCase(); // Simplified ID
	}

	public String getName() { return name; }
	public String getStatus() { return status; }
	public int getAge() { return age; }
	public double getIncome() { return income; }
	public int getCreditScore() { return creditScore; }
	public String getId() { return id; }

	public void setStatus(String status) { this.status = status; }
}
