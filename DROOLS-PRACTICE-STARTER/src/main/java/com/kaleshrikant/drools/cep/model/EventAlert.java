package com.kaleshrikant.drools.cep.model;

import java.time.LocalDateTime;

/**
 * Alert for high-priority notifications
 *
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class EventAlert {
	private String alertId;
	private String message;
	private String alertType;
	private int priority;
	private LocalDateTime timestamp;
	private Event relatedEvent;

	public EventAlert(String alertId, String message, String alertType, int priority, Event relatedEvent) {
		this.alertId = alertId;
		this.message = message;
		this.alertType = alertType;
		this.priority = priority;
		this.relatedEvent = relatedEvent;
		this.timestamp = LocalDateTime.now();
	}

	// Getters
	public String getAlertId() { return alertId; }
	public String getMessage() { return message; }
	public String getAlertType() { return alertType; }
	public int getPriority() { return priority; }
	public LocalDateTime getTimestamp() { return timestamp; }
	public Event getRelatedEvent() { return relatedEvent; }

	@Override
	public String toString() {
		return String.format("ALERT[%s]: %s (Priority: %d)", alertType, message, priority);
	}
}
