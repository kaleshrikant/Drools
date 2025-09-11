package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class AgeCandidate {
	private String name;
	private int age;
	private boolean evenAge;

	public AgeCandidate() {}
	public AgeCandidate(String name, int age) {
		this.name = name;
		this.age = age;
		this.evenAge = false;
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public boolean isEvenAge() { return evenAge; }

	public void setName(String name) { this.name = name; }
	public void setAge(int age) { this.age = age; }
	public void setEvenAge(boolean evenAge) { this.evenAge = evenAge; }
}
