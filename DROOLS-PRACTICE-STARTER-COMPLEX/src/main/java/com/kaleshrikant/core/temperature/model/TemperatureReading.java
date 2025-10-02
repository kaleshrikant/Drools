package com.kaleshrikant.core.temperature.model;

/**
 * @author Shrikant Kale
 * @Date 02 Oct 2025
 */

public class TemperatureReading {
	private String unit;
	private Double value;
	private String status;
	private Double converted;
	private String timestamp;

	public TemperatureReading(String unit, Double value, String timestamp) {
		this.unit = unit;
		this.value = value;
		this.timestamp = timestamp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getConverted() {
		return converted;
	}

	public void setConverted(Double converted) {
		this.converted = converted;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TemperatureReading{" + "unit='" + unit + '\'' + ", value=" + value + ", status='" + status + '\'' + ", converted=" + converted + ", timestamp=" + timestamp + '}';
	}
}
