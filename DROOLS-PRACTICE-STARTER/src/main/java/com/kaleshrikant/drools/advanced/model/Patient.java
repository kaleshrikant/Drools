package com.kaleshrikant.drools.advanced.model;

/**
 * 🧬 Patient information for diagnosis rules
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class Patient {
	private String name;
	private int age;
	private double temperature;
	private boolean hasCough;
	private boolean hasFatigue;
	private String diagnosis;

	public Patient(String name, int age, double temperature, boolean hasCough, boolean hasFatigue) {
		this.name = name;
		this.age = age;
		this.temperature = temperature;
		this.hasCough = hasCough;
		this.hasFatigue = hasFatigue;
	}

	// Getters & Setters
	public String getName() { return name; }
	public int getAge() { return age; }
	public double getTemperature() { return temperature; }
	public boolean isHasCough() { return hasCough; }
	public boolean isHasFatigue() { return hasFatigue; }
	public String getDiagnosis() { return diagnosis; }

	public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

	@Override
	public String toString() {
		return "Patient{" +
				"name='" + name + '\'' +
				", age=" + age +
				", temperature=" + temperature +
				", hasCough=" + hasCough +
				", hasFatigue=" + hasFatigue +
				", diagnosis='" + diagnosis + '\'' +
				'}';
	}
}
