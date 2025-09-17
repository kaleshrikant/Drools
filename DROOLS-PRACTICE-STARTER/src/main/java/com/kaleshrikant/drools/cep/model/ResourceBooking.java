package com.kaleshrikant.drools.cep.model;

import java.time.LocalDateTime;

/**
 * Resource booking event
 *
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class ResourceBooking extends  Event{
	private String resourceType;
	private String resourceId;
	private String bookedBy;
	private boolean isExclusive;

	public ResourceBooking(String id, String name, LocalDateTime startTime, LocalDateTime endTime) {
		super(id, name, startTime, endTime);
		this.isExclusive = true;
	}

	public String getResourceType() { return resourceType; }
	public void setResourceType(String resourceType) { this.resourceType = resourceType; }
	public String getResourceId() { return resourceId; }
	public void setResourceId(String resourceId) { this.resourceId = resourceId; }
	public String getBookedBy() { return bookedBy; }
	public void setBookedBy(String bookedBy) { this.bookedBy = bookedBy; }
	public boolean isExclusive() { return isExclusive; }
	public void setExclusive(boolean exclusive) { isExclusive = exclusive; }
}
