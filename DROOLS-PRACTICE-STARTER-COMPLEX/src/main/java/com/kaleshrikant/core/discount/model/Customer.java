package com.kaleshrikant.core.discount.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Customer {
	private String name;
	private String type;
	private Integer purchaseAmount;
	private Double discount;
	private Integer loyaltyPoints;
	private String status;

	public Customer(String name, String type, Integer purchaseAmount, Integer loyaltyPoints) {
		this.name = name;
		this.type = type;
		this.purchaseAmount = purchaseAmount;
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Integer purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(Integer loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Customer{" + "name='" + name + '\'' + ", type='" + type + '\'' + ", purchaseAmount=" + purchaseAmount + ", discount=" + discount + ", loyaltyPoints=" + loyaltyPoints + ", status='" + status + '\'' + '}';
	}
}
