package com.kaleshrikant.drools.keywords.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class PhraseInput {
	private String phrase;
	private List<AdultCandidate> adults = new ArrayList<>();

	public PhraseInput() {}
	public PhraseInput(String phrase) {
		this.phrase = phrase;
	}

	public String getPhrase() { return phrase; }
	public List<AdultCandidate> getAdults() { return adults; }

	public void setPhrase(String phrase) { this.phrase = phrase; }
	public void setAdults(List<AdultCandidate> adults) { this.adults = adults; }
}
