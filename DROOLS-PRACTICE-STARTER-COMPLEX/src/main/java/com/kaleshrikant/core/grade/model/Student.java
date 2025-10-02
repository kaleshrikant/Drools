package com.kaleshrikant.core.grade.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class Student {
	private String name;
	private Integer marks;
	private String grade;
	private String status;

	public Student(String name, Integer marks) {
		this.name = name;
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Student{" + "name='" + name + '\'' + ", marks=" + marks + ", grade='" + grade + '\'' + ", status='" + status + '\'' + '}';
	}
}
