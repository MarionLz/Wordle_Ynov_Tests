package com.ynov.wordle;

public class WordValidator {

	public WordValidator() {}
	
	public static boolean isAlphabetic(String word) {
	       return word.matches("[a-zA-Z]+");
	}
	
	public boolean validateWord(String word) {
		
		if (word.length() != 5 || !isAlphabetic(word)) {
			System.out.println("ERROR : The word must contained 5 letters and only alphabetical characters.");
			return false;
		}
		return true;
	}
}
