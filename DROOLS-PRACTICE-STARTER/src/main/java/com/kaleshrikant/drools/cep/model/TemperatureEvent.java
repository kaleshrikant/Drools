package com.kaleshrikant.drools.cep.model;

import java.util.Date;

/**
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class TemperatureEvent {
	private String sensorId;
	private double value;
	private Date timestamp;

	public TemperatureEvent(String sensorId, double value) {
		this.sensorId = sensorId;
		this.value = value;
		this.timestamp = new Date(); // current time
	}

	public String getSensorId() { return sensorId; }
	public double getValue() { return value; }
	public Date getTimestamp() { return timestamp; }

	@Override
	public String toString() {
		return "🌡️ [" + sensorId + "] " + value + "°C at " + timestamp;
	}
}
