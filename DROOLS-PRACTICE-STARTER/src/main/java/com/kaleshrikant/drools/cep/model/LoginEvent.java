package com.kaleshrikant.drools.cep.model;

/**
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class LoginEvent {
	private String userId;
	private long timestamp; // epoch millis

	public LoginEvent(String userId, long timestamp) {
		this.userId = userId;
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "LoginEvent{" + "userId='" + userId + '\'' + ", timestamp=" + timestamp + '}';
	}
}
