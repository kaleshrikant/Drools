package com.kaleshrikant.core.temperature.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class SensorReading {
	private Double value;
	private String timestamp;
	public SensorReading(Double value, String timestamp) {
		this.value = value;
		this.timestamp = timestamp;
	}
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SensorReading{" + "value=" + value + ", timestamp='" + timestamp + '\'' + '}';
	}
}
