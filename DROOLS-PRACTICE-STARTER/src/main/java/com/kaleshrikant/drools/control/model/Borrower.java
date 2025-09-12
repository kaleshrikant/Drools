package com.kaleshrikant.drools.control.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class Borrower {
	private String name;
	private double invoiceAmount;
	private boolean invoiceValid;
	private boolean discountApplied;
	private boolean billingConfirmed;

	public Borrower(String name, double invoiceAmount) {
		this.name = name;
		this.invoiceAmount = invoiceAmount;
		this.invoiceValid = false;
		this.discountApplied = false;
		this.billingConfirmed = false;
	}

	public String getName() { return name; }
	public double getInvoiceAmount() { return invoiceAmount; }
	public boolean isInvoiceValid() { return invoiceValid; }
	public boolean isDiscountApplied() { return discountApplied; }
	public boolean isBillingConfirmed() { return billingConfirmed; }

	public void setInvoiceValid(boolean invoiceValid) { this.invoiceValid = invoiceValid; }
	public void setDiscountApplied(boolean discountApplied) { this.discountApplied = discountApplied; }
	public void setBillingConfirmed(boolean billingConfirmed) { this.billingConfirmed = billingConfirmed; }
}
