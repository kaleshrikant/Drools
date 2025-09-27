package com.kaleshrikant.drools.sessions.model;

/**
 * 📌 Domain class representing a Client
 * Fields: name, age, city, category
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class Client {
	private String name;
	private int age;
	private String city;
	private String category;

	public Client(String name, int age, String city) {
		this.name = name;
		this.age = age;
		this.city = city;
		this.category = "";
	}

	// Getters & Setters
	public String getName() { return name; }
	public int getAge() { return age; }
	public String getCity() { return city; }
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	@Override
	public String toString() {
		return "Client{" +
				"name='" + name + '\'' +
				", age=" + age +
				", city='" + city + '\'' +
				", category='" + category + '\'' +
				'}';
	}
}
