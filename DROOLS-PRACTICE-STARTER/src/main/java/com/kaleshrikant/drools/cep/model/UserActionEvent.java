package com.kaleshrikant.drools.cep.model;

/**
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class UserActionEvent {
	private String userId;
	private long timestamp;

	public UserActionEvent(String userId, long timestamp) {
		this.userId = userId;
		this.timestamp = timestamp;
	}

	public String getUserId() { return userId; }
	public long getTimestamp() { return timestamp; }
}
