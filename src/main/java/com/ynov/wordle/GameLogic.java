package com.ynov.wordle;

public class GameLogic {

	private String guess;
	private String target;

	public GameLogic(String guess, String target) {
		this.guess = guess;
		this.target = target;
	}
	
	private int[] countNbLettersOccurrencesInCorrectWord() {
		
		int[] letterCount = new int[26];
		
		for (int i = 0; i < target.length(); i++) {
			letterCount[target.charAt(i) - 'a']++; 
		}
		return letterCount;
	}

	public String checkGuess(GameData data) {
		
		StringBuilder result = new StringBuilder();
		data.setCorrectAttempt(true);
		
		int[] letterCount = countNbLettersOccurrencesInCorrectWord();
		
		LetterState[] letterStates = new LetterState[5];
		
        for (int i = 0; i < 5; i++) {
            char guessLetter = guess.charAt(i);
            if (guessLetter == target.charAt(i)) {
                letterStates[i] = LetterState.GREEN;
                letterCount[guessLetter - 'a']--;
            } else {
                letterStates[i] = LetterState.RESET;
                data.setCorrectAttempt(false);
            }
        }
			
        for (int i = 0; i < 5; i++) {
            char guessedLetter = guess.charAt(i);
            
            if (letterStates[i] != LetterState.GREEN && target.contains(String.valueOf(guessedLetter)) && letterCount[guessedLetter - 'a'] > 0) {
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