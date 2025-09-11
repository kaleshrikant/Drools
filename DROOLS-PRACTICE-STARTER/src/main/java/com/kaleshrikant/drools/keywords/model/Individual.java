package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class Individual {
	private String name;
	private int age;

	public Individual() {}
	public Individual(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() { return name; }
	public int getAge() { return age; }

	public void setName(String name) { this.name = name; }
	public void setAge(int age) { this.age = age; }
}
