package com.lambda.practice;

public class LongestRepeatingCharacters {
	
	static void findLongestRepeatingCharacters(String str) {
		StringBuffer buffer = new StringBuffer();
		StringBuffer tempBuffer = new StringBuffer();
		boolean existingCharMatchFound = false;
		int startMatch = -1;
		
		for(int i=0; i< str.length();i++) {			
			if(existingCharMatchFound) {
				tempBuffer.append(str.charAt(i));
				if(str.substring(0, startMatch).indexOf(tempBuffer.toString())<0) {
						tempBuffer.deleteCharAt(tempBuffer.length()-1);
						if(tempBuffer.length()> buffer.length())
							buffer = tempBuffer;						
						existingCharMatchFound = false;
						startMatch = -1;
						
						existingCharMatchFound = str.substring(0,i).indexOf(str.charAt(i)) >= 0;
						if(existingCharMatchFound) {
							startMatch = i;
							tempBuffer = new StringBuffer();
							tempBuffer.append(str.charAt(i));
						}

				}
			} else {
				existingCharMatchFound = str.substring(0,i).indexOf(str.charAt(i)) >= 0;
				if(existingCharMatchFound) {
					startMatch = i;
					tempBuffer = new StringBuffer();
					tempBuffer.append(str.charAt(i));
				}
			}
		}
		if(tempBuffer.length()> buffer.length()) System.out.println(tempBuffer);
		else System.out.println(buffer);
		
	}
	
	public static void main(String []args) {
		findLongestRepeatingCharacters("abcd123abcd1234abcd123456abcd1234abcdabcd12345");	
	}

}
