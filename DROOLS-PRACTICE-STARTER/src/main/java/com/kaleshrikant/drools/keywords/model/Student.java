package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class Student {
	private String name;
	private int age;
	private boolean hasGuardian;
	private boolean minor;

	public Student() {}
	public Student(String name, int age, boolean hasGuardian) {
		this.name = name;
		this.age = age;
		this.hasGuardian = hasGuardian;
		this.minor = false;
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public boolean hasGuardian() { return hasGuardian; }
	public boolean isMinor() { return minor; }

	public void setName(String name) { this.name = name; }
	public void setAge(int age) { this.age = age; }
	public void setHasGuardian(boolean hasGuardian) { this.hasGuardian = hasGuardian; }
	public void setMinor(boolean minor) { this.minor = minor; }
}
