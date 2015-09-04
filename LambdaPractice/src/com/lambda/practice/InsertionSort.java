package com.lambda.practice;

public class InsertionSort {
	
	static void insertionSort(int []array) {
		for(int i=1;i<array.length;i++) {
			int value = array[i];
			int hole=i;
			while(hole>0 && array[hole-1]>value) {
				array[hole] = array[hole-1];	
				hole--;
			}
			array[hole] = value;
		}
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}
	}
	
	public static void main(String []args) {
		int []array = {6,4,2,6,7,8};
		insertionSort(array);
	}

}
