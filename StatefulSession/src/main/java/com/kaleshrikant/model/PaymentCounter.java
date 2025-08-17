package com.kaleshrikant.model;

/**
 * @author Shrikant Kale
 * @Date 8/17/25
 */


public class PaymentCounter {
	public String channel;
	public int amount;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
