package com.kaleshrikant.drools.cep.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Meeting event type
 *
 * @author Shrikant Kale
 * @Date 16 Sep 2025
 */

public class Meeting extends Event{
	private List<String> attendees;
	private String meetingType;
	private boolean isVirtual;

	public Meeting(String id, String name, LocalDateTime startTime, LocalDateTime endTime) {
		super(id, name, startTime, endTime);
		this.attendees = new ArrayList<>();
		this.meetingType = "GENERAL";
		this.isVirtual = false;
	}

	public List<String> getAttendees() { return attendees; }
	public void addAttendee(String attendee) { attendees.add(attendee); }
	public String getMeetingType() { return meetingType; }
	public void setMeetingType(String meetingType) { this.meetingType = meetingType; }
	public boolean isVirtual() { return isVirtual; }
	public void setVirtual(boolean virtual) { isVirtual = virtual; }

	public int getAttendeeCount() { return attendees.size(); }
}
