package com.kaleshrikant.drools.model;

/**
 * @author Shrikant Kale
 * @Date 01 Oct 2025
 */

public class LoanApplication {
	private String applicantName;
	private double loanAmount;
	private int durationYears;
	private double interestRate;
	private boolean approved;
	private String rejectionReason;
	private double monthlyPayment;

	public LoanApplication() {}

	public LoanApplication(String applicantName, double loanAmount, int durationYears) {
		this.applicantName = applicantName;
		this.loanAmount = loanAmount;
		this.durationYears = durationYears;
	}

	// Getters and Setters
	public String getApplicantName() { return applicantName; }
	public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

	public double getLoanAmount() { return loanAmount; }
	public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

	public int getDurationYears() { return durationYears; }
	public void setDurationYears(int durationYears) { this.durationYears = durationYears; }

	public double getInterestRate() { return interestRate; }
	public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

	public boolean isApproved() { return approved; }
	public void setApproved(boolean approved) { this.approved = approved; }

	public String getRejectionReason() { return rejectionReason; }
	public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

	public double getMonthlyPayment() { return monthlyPayment; }
	public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }

	@Override
	public String toString() {
		return "LoanApplication{applicant='" + applicantName + "', amount=" + loanAmount +
				", years=" + durationYears + ", approved=" + approved +
				", interestRate=" + interestRate + ", monthlyPayment=" + monthlyPayment +
				", rejectionReason='" + rejectionReason + "'}";
	}
}
