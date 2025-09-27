package com.kaleshrikant.drools.advanced.model;

/**
 * @author Shrikant Kale
 * @Date 26 Sep 2025
 */

public class UserProfile {
	private String name;
	private int age;

	public UserProfile() {}
	public UserProfile(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	@Override
	public String toString() {
		return "UserProfile{name='" + name + "', age=" + age + "}";
	}
}
