package com.kaleshrikant.core.grade.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class ScholarshipPolicy {
	private String minGrade;
	private String status;

	public ScholarshipPolicy(String minGrade) {
		this.minGrade = minGrade;
	}

	public String getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(String minGrade) {
		this.minGrade = minGrade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ScholarshipPolicy{" + "minGrade='" + minGrade + '\'' + ", status='" + status + '\'' + '}';
	}
}
