package com.kaleshrikant.drools.control.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class Participant {
	private String name;
	private int age;
	private boolean documentsSubmitted;
	private boolean eligible;
	private boolean approved = false;

	public Participant(String name, int age, boolean documentsSubmitted) {
		this.name = name;
		this.age = age;
		this.documentsSubmitted = documentsSubmitted;
	}

	public String getName() { return name; }
	public int getAge() { return age; }
	public boolean isDocumentsSubmitted() { return documentsSubmitted; }
	public boolean isEligible() { return eligible; }
	public boolean isApproved() { return approved; }

	public void setEligible(boolean eligible) { this.eligible = eligible; }
	public void setApproved(boolean approved) { this.approved = approved; }
}
