package com.lambda.practice;

public class BubbleSort {
	
	static void bubbleSort(int []array)  {
		for(int i=array.length-1;i>=0;i--) {
			for(int j=0;j<i;j++) {
				if(array[j]>array[i]) {
					array[i]=array[i]+array[j];
					array[j]=array[i]-array[j];
					array[i]=array[i]-array[j];
				}
			}
		}
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]);
		}
	}
	
	
	
	public static void main(String []args) {
		int []array = new int[]{4,3,2,5,8,9,1};
		bubbleSort(array);
	}

}
