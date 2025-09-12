package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class PersonaData {
	private String name;
	private int age;
	private boolean birthdayToday;

	public PersonaData(String name, int age, boolean birthdayToday) {
		this.name = name;
		this.age = age;
		this.birthdayToday = birthdayToday;
	}

	public PersonaData(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public boolean isBirthdayToday() { return birthdayToday; }

	public void setAge(int age) { this.age = age; }
	public void setBirthdayToday(boolean birthdayToday) { this.birthdayToday = birthdayToday; }
}