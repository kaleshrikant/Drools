package com.kaleshrikant.drools.model;

/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

public class Customer {
	private Long id;
	private String name;
	private String customerType; // REGULAR, VIP, PREMIUM
	private int yearsWithCompany;

	// Constructors
	public Customer() {}

	public Customer(Long id, String name, String customerType, int yearsWithCompany) {
		this.id = id;
		this.name = name;
		this.customerType = customerType;
		this.yearsWithCompany = yearsWithCompany;
	}

	// Getters and Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCustomerType() { return customerType; }
	public void setCustomerType(String customerType) { this.customerType = customerType; }

	public int getYearsWithCompany() { return yearsWithCompany; }
	public void setYearsWithCompany(int yearsWithCompany) { this.yearsWithCompany = yearsWithCompany; }

	@Override
	public String toString() {
		return String.format("Customer{id=%d, name='%s', type='%s', years=%d}",
				id, name, customerType, yearsWithCompany);
	}
}
