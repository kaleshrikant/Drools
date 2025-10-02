package com.kaleshrikant.core.discount.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Campaign {
	private Boolean active;
	private String name;

	public Campaign(String name, Boolean active) {
		this.name = name;
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Campaign{" + "active=" + active + ", name='" + name + '\'' + '}';
	}
}
