package com.kaleshrikant.core.age.model;

import com.kaleshrikant.core.hello.model.Person;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Family {
	private String familyName;
	private Person member;
	private String status;

	public Family(String familyName, Person member) {
		this.familyName = familyName;
		this.member = member;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Person getMember() {
		return member;
	}

	public void setMember(Person member) {
		this.member = member;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Family{" + "familyName='" + familyName + '\'' + ", person=" + member + ", status='" + status + '\'' + '}';
	}
}
