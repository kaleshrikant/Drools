package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class Applicant {
	private String name;
	private int age;

	public Applicant() {}
	public Applicant(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// Getters and setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
}
