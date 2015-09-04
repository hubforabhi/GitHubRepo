package com.lambda.practice;

public class ReverseString {
	
	static String reverseTheString(String str) {
		if(str.length()==1) {
			return str.substring(0);
		}
		return str.charAt(str.length()-1) + reverseTheString(str.substring(0,str.length()-1));
	}
	
	static String reverseTheStringByWord(String str) {
		String words[] = str.split(" ");
		StringBuffer finalString = new StringBuffer();
		for(String word:words) {
			finalString.append(reverseTheString(word));
			finalString.append(" ");
		}
		return finalString.toString();
	}
	
	static String reverseTheStringByWordWithOutRecursion(String str) {
		StringBuffer sBuffer = new StringBuffer();
		for(int i=0;i<str.length()-1;i++) {
			if(str.charAt(i) == ' ') {
				for(int j=i;j<str.length();j++) {
					if(str.charAt(j) == ' ') {
						sBuffer.append(new StringBuffer(str.substring(i,j)).reverse());
						System.out.println(sBuffer);
					}
				}
			}
		}
		return sBuffer.toString();
	}
	
	public static void main(String []args) {
		String str = "I am a java developer";
		System.out.println(reverseTheString(str));
		System.out.println(reverseTheStringByWord(str));
		System.out.println(reverseTheStringByWordWithOutRecursion(str));
	}

}
