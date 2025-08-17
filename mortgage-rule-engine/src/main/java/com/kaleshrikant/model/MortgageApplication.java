package com.kaleshrikant.model;

/**
 * @author Shrikant Kale
 * @Date 8/17/25
 */

public class MortgageApplication {
	private String applicantName;
	private double loanAmount;
	private int creditScore;
	private String status;

	// Constructors, getters, setters

	public MortgageApplication() {}

	public MortgageApplication(String applicantName, double loanAmount, int creditScore) {
		this.applicantName = applicantName;
		this.loanAmount = loanAmount;
		this.creditScore = creditScore;
		this.status = "PENDING";
	}

	// Getters and setters
	public String getApplicantName() { return applicantName; }
	public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
	public double getLoanAmount() { return loanAmount; }
	public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }
	public int getCreditScore() { return creditScore; }
	public void setCreditScore(int creditScore) { this.creditScore = creditScore; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
}
