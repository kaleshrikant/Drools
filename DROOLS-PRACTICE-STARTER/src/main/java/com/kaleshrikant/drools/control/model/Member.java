package com.kaleshrikant.drools.control.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class Member {
	private String name;
	private double loanAmount;
	private String loanTier;

	public Member(String name, double loanAmount) {
		this.name = name;
		this.loanAmount = loanAmount;
	}

	public String getName() { return name; }
	public double getLoanAmount() { return loanAmount; }
	public String getLoanTier() { return loanTier; }

	public void setLoanTier(String loanTier) { this.loanTier = loanTier; }
}
