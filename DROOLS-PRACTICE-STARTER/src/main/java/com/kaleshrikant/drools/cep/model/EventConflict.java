package com.kaleshrikant.drools.cep.model;

import java.time.LocalDateTime;

/**
 * Conflict detection result
 *
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class EventConflict {
	private Event event1;
	private Event event2;
	private String conflictType;
	private int severity;
	private long overlapMinutes;
	private String resolution;
	private LocalDateTime detectedAt;

	public EventConflict(Event event1, Event event2, String conflictType, int severity) {
		this.event1 = event1;
		this.event2 = event2;
		this.conflictType = conflictType;
		this.severity = severity;
		this.overlapMinutes = event1.getOverlapDuration(event2);
		this.detectedAt = LocalDateTime.now();
	}

	// Getters and setters
	public Event getEvent1() { return event1; }
	public Event getEvent2() { return event2; }
	public String getConflictType() { return conflictType; }
	public int getSeverity() { return severity; }
	public long getOverlapMinutes() { return overlapMinutes; }
	public String getResolution() { return resolution; }
	public void setResolution(String resolution) { this.resolution = resolution; }
	public LocalDateTime getDetectedAt() { return detectedAt; }

	@Override
	public String toString() {
		return String.format("CONFLICT[%s]: %s vs %s - %d min overlap (Severity: %d)",
				conflictType, event1.getName(), event2.getName(), overlapMinutes, severity);
	}
}
