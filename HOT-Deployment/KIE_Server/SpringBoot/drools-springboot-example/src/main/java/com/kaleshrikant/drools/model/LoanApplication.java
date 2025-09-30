package com.kaleshrikant.drools.model;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */

public class LoanApplication {
	private String applicantName;
	private double amount;
	private int durationMonths;
	private double annualIncome;
	private int creditScore;
	private boolean approved;
	private double interestRate;
	private String rejectionReason;

	// Default constructor (required for JSON deserialization)
	public LoanApplication() {}

	public LoanApplication(String applicantName, double amount,
	                       int durationMonths, double annualIncome, int creditScore) {
		this.applicantName = applicantName;
		this.amount = amount;
		this.durationMonths = durationMonths;
		this.annualIncome = annualIncome;
		this.creditScore = creditScore;
	}

	// Getters and Setters
	public String getApplicantName() { return applicantName; }
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public double getAmount() { return amount; }
	public void setAmount(double amount) { this.amount = amount; }

	public int getDurationMonths() { return durationMonths; }
	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}

	public double getAnnualIncome() { return annualIncome; }
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public int getCreditScore() { return creditScore; }
	public void setCreditScore(int creditScore) { this.creditScore = creditScore; }

	public boolean isApproved() { return approved; }
	public void setApproved(boolean approved) { this.approved = approved; }

	public double getInterestRate() { return interestRate; }
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getRejectionReason() { return rejectionReason; }
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	@Override
	public String toString() {
		return String.format(
				"LoanApplication{applicantName='%s', amount=%.2f, duration=%d months, " +
						"annualIncome=%.2f, creditScore=%d, approved=%s, interestRate=%.2f%%, rejectionReason='%s'}",
				applicantName, amount, durationMonths, annualIncome, creditScore,
				approved, interestRate, rejectionReason
		);
	}
}
