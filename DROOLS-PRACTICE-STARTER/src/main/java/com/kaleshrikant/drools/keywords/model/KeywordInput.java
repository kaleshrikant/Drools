package com.kaleshrikant.drools.keywords.model;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class KeywordInput {
	private String keyword;
	private String city;

	public KeywordInput() {}
	public KeywordInput(String keyword) {
		this.keyword = keyword;
		this.city = null;
	}

	public String getKeyword() { return keyword; }
	public String getCity() { return city; }

	public void setKeyword(String keyword) { this.keyword = keyword; }
	public void setCity(String city) { this.city = city; }
}
