package com.abhi.design;

public class ElevatorClient {

	public static void main(String []args) {
		ElevatorController controller = new ElevatorController();
		controller.request(1, 50);
		controller.request(50, 1);
		controller.request(1, 50);
		controller.request(50, 1);
		//controller.request(10, 20);
		//controller.request(20, 10);
	}
}
