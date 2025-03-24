package com.ynov.wordle;

public class GameLogic {

	private String guessedWord;
	private String correctWord;

	public GameLogic(String guessedWord, String correctWord) {
		this.guessedWord = guessedWord;
		this.correctWord = correctWord;
	}
	
	private int[] countOccurrencesLettersInCorrectWord() {
		
		int[] letterCount = new int[26];
		
		for (int i = 0; i < correctWord.length(); i++) {
			letterCount[correctWord.charAt(i) - 'a']++; 
		}
		return letterCount;
	}

	public String checkGuessedWord() {
		
		StringBuilder result = new StringBuilder();
		
		int[] letterCount = countOccurrencesLettersInCorrectWord();
		
		LetterState[] letterStates = new LetterState[5];
		
        for (int i = 0; i < 5; i++) {
            char guessedLetter = guessedWord.charAt(i);
            if (guessedLetter == correctWord.charAt(i)) {
                letterStates[i] = LetterState.GREEN;
                letterCount[guessedLetter - 'a']--;
            } else {
                letterStates[i] = LetterState.RESET;
            }
        }
			
        for (int i = 0; i < 5; i++) {
            char guessedLetter = guessedWord.charAt(i);
            
            if (letterStates[i] != LetterState.GREEN && correctWord.contains(String.valueOf(guessedLetter)) && letterCount[guessedLetter - 'a'] > 0) {
                letterStates[i] = LetterState.YELLOW;
                letterCount[guessedLetter - 'a']--;
            }
            
            if (letterStates[i] == LetterState.RESET) {
                letterStates[i] = LetterState.GRAY;
            }
            
            result.append(letterStates[i].getAnsiCode()).append(guessedLetter).append(LetterState.RESET.getAnsiCode());
        }
        
		return result.toString();
	}
}