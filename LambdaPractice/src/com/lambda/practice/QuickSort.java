package com.lambda.practice;


public class QuickSort {
	
	private int []numbers;
	
	QuickSort(int []values) {
		this.numbers = values;
		quicksort(0, numbers.length-1);
	}
	
	private void quicksort(int low, int high) {
		int i = low, j = high;
		int pivot = numbers[low + (high-low)/2];
	
		while (i <= j) {
			while (numbers[i] < pivot) {
		        i++;
		    }
		    while (numbers[j] > pivot) {
		        j--;
		    }
	
		    if (i <= j) {
		    	  exchange(i,j);
		        i++;
		        j--;
		      }
		    }
	
		    if (low < j)
		    	quicksort(low, j);
		    if (i < high)
		    	quicksort(i, high);
	}
	  
	private void exchange(int i, int j) {
		    int temp = numbers[i];
		    numbers[i] = numbers[j];
		    numbers[j] = temp;
	}
	  
	void printNames() {
		for(int number:numbers) {
			System.out.println(number);
		}
	}

	public static void main(String []args) {
		QuickSort qs = new QuickSort(new int[]{4,3,6,5,1,7,8});
		qs.printNames();
	}
}
