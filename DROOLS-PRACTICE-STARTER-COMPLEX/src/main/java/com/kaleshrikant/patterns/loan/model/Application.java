package com.kaleshrikant.patterns.loan.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Application {
	private String customerId;
	private String loanType;

	public Application(String customerId, String loanType) {
		this.customerId = customerId;
		this.loanType = loanType;
	}

	public String getCustomerId() { return customerId; }
	public String getLoanType() { return loanType; }
}
