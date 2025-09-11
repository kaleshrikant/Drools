package com.kaleshrikant.drools.core.model;

/**
 * @author Shrikant Kale
 * @Date 9/11/25
 */

public class Citizen {
	private String name;
	private int age;
	private String status;

	public Citizen() {}
	public Citizen(String name, int age) {
		this.name = name;
		this.age = age;
		this.status = "UNKNOWN";
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public String getStatus() { return status; }

	public void setName(String name) { this.name = name; }
	public void setAge(int age) { this.age = age; }
	public void setStatus(String status) { this.status = status; }
}
