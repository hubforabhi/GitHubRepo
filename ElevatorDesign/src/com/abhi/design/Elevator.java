package com.abhi.design;

import java.util.concurrent.TimeUnit;

public class Elevator {

	private boolean movingDownward;
	private boolean movingUpward;
	
	private int position;	
	private String name;
	private int serial;
	
	public Elevator(String name, int serial) {
		this.name = name;
		this.serial = serial;
	}
	
	public void move(ElevatorRequest elevatorRequest) {
		int origin = elevatorRequest.getOrigin();
		int destination = elevatorRequest.getDestination();
		System.out.println("Elevator "+serial+" Serving Request "+elevatorRequest.getSerial()+" Moving from "+origin+" to "+destination);
		if(origin == destination) {
			throw new RuntimeException("Origin and destination can't be same");
		} else if (origin > destination) {
			movingUpward = true;
			int counter = origin;
			while(counter > destination) {
				try {
					TimeUnit.SECONDS.sleep(3);
					System.out.println("Elevator "+serial+" Serving Request "+elevatorRequest.getSerial()+" Moved from "+counter+" to "+ counter--);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}	
			movingUpward = false;
		}  else if (destination > origin) {
			movingDownward = true;
			int counter = origin;
			while(counter < destination) {
				try {
					TimeUnit.SECONDS.sleep(3);					
					System.out.println("Elevator "+serial+" Serving Request "+elevatorRequest.getSerial()+" Moved from "+counter+" to "+ ++counter);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			movingDownward = false;
		}	
		position = destination;
		System.out.println("Elevator "+serial+" Completed Serving Request "+elevatorRequest.getSerial()+ " Moved from "+origin+" to "+destination);
	}

	public boolean isMovingDownward() {
		return movingDownward;
	}

	public boolean isMovingUpward() {
		return movingUpward;
	}

	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public int getSerial() {
		return serial;
	}
}
