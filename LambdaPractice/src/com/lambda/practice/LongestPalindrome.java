package com.lambda.practice;

public class LongestPalindrome {
	
	public static String findLongestPalindromeFromString(String str) {
		String palinDromeString = ""; 
		for(int i=0;i<str.length();i++) {
			StringBuffer temp = new StringBuffer(str.substring(i, str.length()));
			if(str.substring(0,i).indexOf(temp.reverse().toString())>=0) {
				if(temp.length()>palinDromeString.length()) {
					palinDromeString = temp.toString();
				}
			}
		}		
		return palinDromeString;
	}
	
	public static void main(String []args) {
		String str = "abcd123456dcba";
		System.out.println(findLongestPalindromeFromString(str));
		
		str = "abcddcba1221abcddcba";
		System.out.println(findLongestPalindromeFromString(str));

		str = "1232xyz31zy";
		System.out.println(findLongestPalindromeFromString(str));
	}
}
