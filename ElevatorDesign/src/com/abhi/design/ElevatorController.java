package com.abhi.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ElevatorController {
	private static final int NO_OF_ELEVATOR = 3;
	
	List<Elevator> elevatorList;
	List<ElevatorRequest> elevatorRequestList;
	
	public ElevatorController() {
		elevatorList = new LinkedList<>();
		for(int i = 1; i<= NO_OF_ELEVATOR ; i++) {
			elevatorList.add(new Elevator(String.valueOf(i),i));
		}
		elevatorRequestList = new LinkedList<>();
		Runnable controlElavatorSystem = ()-> {
			assignElevator();
		};
		Executors.newFixedThreadPool(1).execute(controlElavatorSystem);
	}
	
	public void request(int origin, int destination) {
		elevatorRequestList.add(new ElevatorRequest(origin,destination));
		System.out.println("Request Queued "+origin+" --> "+destination);
	}
	
	private void assignElevator() {
		while(true) {
			if(!elevatorRequestList.isEmpty()) {
				Iterator<ElevatorRequest> elevatorRequestListIterator = elevatorRequestList.iterator();
				while(elevatorRequestListIterator.hasNext()) {
					boolean elevatorAssigned = false;
					ElevatorRequest elevatorRequest = elevatorRequestListIterator.next();
					for(int i = 0; i< NO_OF_ELEVATOR; i++) {
						if(!elevatorList.get(i).isMovingDownward() && !elevatorList.get(i).isMovingUpward()) {
							elevatorAssigned = true;
							final int currentElevatorIndex = i;
							Runnable moveElavator = ()-> {
								elevatorList.get(currentElevatorIndex).move(elevatorRequest);
							};			
							Executors.newFixedThreadPool(1).execute(moveElavator);
							break;
						}
					}
					if(elevatorAssigned) {
						elevatorRequestListIterator.remove();
					} else {
						System.out.println("All Elevators engaged, Please wait !!");
						try {
							TimeUnit.MILLISECONDS.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}