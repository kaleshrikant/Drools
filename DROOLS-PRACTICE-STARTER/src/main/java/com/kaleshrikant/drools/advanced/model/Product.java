package com.kaleshrikant.drools.advanced.model;

/**
 * 🎯 Product model for LogicalFactTMSExampleProgram
 * 🧊 Facts: Product (name, category, discount, applied)
 * ➕ Used for: Demonstrating insertLogical in Drools TMS
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class Product {
	private String name;
	private String category;
	private int discount;      // discount percentage
	private boolean applied;   // tracks if rule already applied

	// Constructor for initial Product (no discount yet)
	public Product(String name, String category) {
		this.name = name;
		this.category = category;
		this.discount = 0;
		this.applied = false;
	}

	// Constructor for logical facts with discount
	public Product(String name, String category, int discount, boolean applied) {
		this.name = name;
		this.category = category;
		this.discount = discount;
		this.applied = applied;
	}

	// Getters and setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	public int getDiscount() { return discount; }
	public void setDiscount(int discount) { this.discount = discount; }

	public boolean isApplied() { return applied; }
	public void setApplied(boolean applied) { this.applied = applied; }

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", category='" + category + '\'' +
				", discount=" + discount + "%" +
				", applied=" + applied +
				'}';
	}
}
