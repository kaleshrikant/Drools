package com.kaleshrikant.drools.cep.model;

/**
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class SensorEvent {
	private int value;
	private long time;

	public SensorEvent(int value, long time) {
		this.value = value;
		this.time = time;
	}
	public int getValue() { return value; }
	public long getTime() { return time; }
	@Override
	public String toString() {
		return "SensorEvent{value=" + value + ", time=" + time + "}";
	}
}
