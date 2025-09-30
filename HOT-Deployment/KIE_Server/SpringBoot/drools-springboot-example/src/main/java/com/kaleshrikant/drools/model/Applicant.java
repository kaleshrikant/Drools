package com.kaleshrikant.drools.model;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */

public class Applicant {
	private String name;
	private int age;
	private double yearsOfExperience;
	private double currentSalary;
	private double expectedSalary;
	private String status;
	private String message;

	// Default constructor (required for JSON deserialization)
	public Applicant() {}

	public Applicant(String name, int age, double yearsOfExperience,
	                 double currentSalary, double expectedSalary) {
		this.name = name;
		this.age = age;
		this.yearsOfExperience = yearsOfExperience;
		this.currentSalary = currentSalary;
		this.expectedSalary = expectedSalary;
	}

	// Getters and Setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public double getYearsOfExperience() { return yearsOfExperience; }
	public void setYearsOfExperience(double yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public double getCurrentSalary() { return currentSalary; }
	public void setCurrentSalary(double currentSalary) {
		this.currentSalary = currentSalary;
	}

	public double getExpectedSalary() { return expectedSalary; }
	public void setExpectedSalary(double expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	@Override
	public String toString() {
		return String.format(
				"Applicant{name='%s', age=%d, experience=%.1f years, " +
						"currentSalary=%.2f, expectedSalary=%.2f, status='%s', message='%s'}",
				name, age, yearsOfExperience, currentSalary, expectedSalary, status, message
		);
	}
}
