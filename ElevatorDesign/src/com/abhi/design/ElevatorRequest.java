package com.abhi.design;

public class ElevatorRequest {
	
	private static long requestSerialId = 1;

	private int origin;
	private int destination;
	
	private long serial;
	
	public ElevatorRequest(int origin, int destination) {
		this.origin = origin;
		this.destination = destination;
		this.serial = requestSerialId++;
	}

	public int getOrigin() {
		return origin;
	}

	public int getDestination() {
		return destination;
	}

	public long getSerial() {
		return serial;
	}
}
