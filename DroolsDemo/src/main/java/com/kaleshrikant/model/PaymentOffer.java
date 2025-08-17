package com.kaleshrikant.model;

/**
 * @author Shrikant Kale
 * @Date 8/16/25
 */

public class PaymentOffer {
	private String channel;
	private int discount;

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
}
