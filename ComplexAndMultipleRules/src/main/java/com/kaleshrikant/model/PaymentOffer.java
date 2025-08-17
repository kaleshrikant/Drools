package com.kaleshrikant.model;

/**
 * @author Shrikant Kale
 * @Date 8/17/25
 */

public class PaymentOffer {
	private String channel;
	private int discount;
	private String festival;
	private boolean firstTimeCustomer;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public boolean isFirstTimeCustomer() {
		return firstTimeCustomer;
	}

	public void setFirstTimeCustomer(boolean firstTimeCustomer) {
		this.firstTimeCustomer = firstTimeCustomer;
	}
}
