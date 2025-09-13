package com.kaleshrikant.drools.cep.model;

/**
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class SessionWindow {
	private long startTime;
	private long endTime;

	public SessionWindow(long startTime, long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public long getStartTime() { return startTime; }
	public long getEndTime() { return endTime; }
}
