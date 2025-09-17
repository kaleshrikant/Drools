package com.kaleshrikant.drools.cep.model;

import java.util.Date;

/**
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class WebinarEvent {
	private String name;
	private Date startTime;
	private long duration;

	public WebinarEvent(String name, Date startTime, long duration) {
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
	}

	public String getName() { return name; }
	public Date getStartTime() { return startTime; }
	public long getDuration() { return duration; }
}
