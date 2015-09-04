package com.lambda.practice;

public class MergeSort {
	
	private int []numbers;
	private int []helper;
	private int iteration;
	
	MergeSort(int []values) {
		this.numbers = values;
		this.helper = new int[values.length];
		mergeSort(0,numbers.length-1);
	}
	
	private void mergeSort(int low, int high) {
		if(low<high) {
			mergeSort(low,low+(high-low)/2);
			mergeSort(low+(high-low)/2+1,high);
			merge(low,low+(high-low)/2,high);
		}		
	}
	
	private void merge(int low, int middle, int high) {
		System.out.print("Ieration "+ ++iteration +"\t middle "+middle+"\t");
	    for (int i = low; i <= high; i++) {
	        helper[i] = numbers[i];
	    }
	    
	    int i = low;
	    int j = middle + 1;
	    int k = low;

	    while (i <= middle && j <= high) {
	      if (helper[i] <= helper[j]) {
	        numbers[k] = helper[i];
	        i++;
	      } else {
	        numbers[k] = helper[j];
	        j++;
	      }
	      k++;
	    }
	    printNames(false);
	    while (i <= middle) {
	        numbers[k] = helper[i];
	        k++;
	        i++;
	    }
	    System.out.println("\t");
	    printNames(true);
	}

	private void printNames(boolean flag) {
		for(int number:numbers) {
			System.out.print(number);
		}
		if(flag)
		System.out.println("\n----------");
	}
	
	public static void main(String []args) {
		MergeSort ms = new MergeSort(new int[]{4,2,1,5,6,5,7,8});
		//ms.printNames();
	}
}