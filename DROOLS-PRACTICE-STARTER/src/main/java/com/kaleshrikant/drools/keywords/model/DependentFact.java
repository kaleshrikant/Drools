package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class DependentFact {
	private String parentName;
	private String childName;

	public DependentFact(String parentName, String childName) {
		this.parentName = parentName;
		this.childName = childName;
	}

	public String getParentName() { return parentName; }
	public String getChildName() { return childName; }
}
