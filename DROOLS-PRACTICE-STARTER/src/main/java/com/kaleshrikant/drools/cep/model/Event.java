package com.kaleshrikant.drools.cep.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Base Event class representing temporal events with overlap detection.
 *
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class Event {
	protected String id;
	protected String name;
	protected LocalDateTime startTime;
	protected LocalDateTime endTime;
	protected String location;
	protected int priority;
	protected String status;
	protected Map<String, Object> attributes;

	public Event(String id, String name, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.priority = 0;
		this.status = "ACTIVE";
		this.attributes = new HashMap<>();
	}
	/**
	 * Check if this event overlaps with another event
	 */
	public boolean overlaps(Event other) {
		return this.startTime.isBefore(other.endTime) &&
				this.endTime.isAfter(other.startTime);
	}
	/**
	 * Get overlap duration in minutes
	 */
	public long getOverlapDuration(Event other) {
		if (!overlaps(other)) return 0;

		LocalDateTime overlapStart = this.startTime.isAfter(other.startTime) ?
				this.startTime : other.startTime;
		LocalDateTime overlapEnd = this.endTime.isBefore(other.endTime) ?
				this.endTime : other.endTime;

		return java.time.Duration.between(overlapStart, overlapEnd).toMinutes();
	}

	public long getDurationMinutes() {
		return java.time.Duration.between(startTime, endTime).toMinutes();
	}

	// Getters and Setters
	public String getId() { return id; }
	public String getName() { return name; }
	public LocalDateTime getStartTime() { return startTime; }
	public LocalDateTime getEndTime() { return endTime; }
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	public int getPriority() { return priority; }
	public void setPriority(int priority) { this.priority = priority; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public Map<String, Object> getAttributes() { return attributes; }

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	@Override
	public String toString() {
		return String.format("%s[%s]: %s - %s (%s)",
				getClass().getSimpleName(), id,
				startTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")),
				endTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")),
				status);
	}
}
