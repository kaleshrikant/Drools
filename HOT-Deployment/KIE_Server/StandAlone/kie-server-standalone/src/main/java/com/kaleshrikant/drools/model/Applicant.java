package com.kaleshrikant.drools.model;

/**
 * @author Shrikant Kale
 * @Date 01 Oct 2025
 */

public class Applicant {
	private String name;
	private int age;
	private double income;
	private boolean eligible;
	private String validationMessage;

	public Applicant() {}

	public Applicant(String name, int age, double income) {
		this.name = name;
		this.age = age;
		this.income = income;
	}

	// Getters and Setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public double getIncome() { return income; }
	public void setIncome(double income) { this.income = income; }

	public boolean isEligible() { return eligible; }
	public void setEligible(boolean eligible) { this.eligible = eligible; }

	public String getValidationMessage() { return validationMessage; }
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	@Override
	public String toString() {
		return "Applicant{name='" + name + "', age=" + age + ", income=" + income +
				", eligible=" + eligible + ", message='" + validationMessage + "'}";
	}
}
