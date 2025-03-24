package com.ynov.wordle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordValidator {

	private static final Logger logger = LogManager.getLogger("WordValidator");

	public WordValidator() {}
	
	public static boolean isAlphabetic(String word) {
	       return word.matches("[a-zA-Z]+");
	}
	
	public boolean validateWord(String word) {
		
		if (word.length() != 5 || !isAlphabetic(word)) {
			logger.error("The word must contained 5 letters and only alphabetical characters.");
			return false;
		}
		return true;
	}
}
