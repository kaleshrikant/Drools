package com.kaleshrikant.drools.decision.model;

/**
 * @author Shrikant Kale
 * @Date 25 Sep 2025
 */

public class LoanClient {
	private String name;
	private int creditScore;
	private int income;
	private int debt;
	private boolean loanEligible;

	public LoanClient(String name, int creditScore, int income, int debt) {
		this.name = name;
		this.creditScore = creditScore;
		this.income = income;
		this.debt = debt;
	}

	public String getName() { return name; }
	public int getCreditScore() { return creditScore; }
	public int getIncome() { return income; }
	public int getDebt() { return debt; }
	public boolean isLoanEligible() { return loanEligible; }
	public void setLoanEligible(boolean loanEligible) { this.loanEligible = loanEligible; }
}
