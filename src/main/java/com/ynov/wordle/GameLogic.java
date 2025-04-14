package com.ynov.wordle;

import com.ynov.wordle.statistics.IGameStatistics;

public class GameLogic {

	private IGameStatistics data;
	private String guess;
	private String target;

	public GameLogic() {}

	public GameLogic(IGameStatistics data) {
		this.data = data;
		this.guess = data.getGuess();
		this.target = data.getTarget();
	}
	
	public void setData(IGameStatistics data) {
		this.data = data;
		this.guess = data.getGuess();
		this.target = data.getTarget();
	}
	
	private int[] countNbLettersOccurrencesInCorrectWord() {
		
		int[] letterCount = new int[26];
		
		for (int i = 0; i < target.length(); i++) {
			letterCount[target.charAt(i) - 'a']++; 
		}
		return letterCount;
	}

	public String checkGuess() {
		
		StringBuilder result = new StringBuilder();
		data.setCorrectAttempt(true);
		int roundScore = 0;
		
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
            
            roundScore += letterStates[i].getScore();
            
            result.append(letterStates[i].getAnsiCode()).append(guessedLetter).append(LetterState.RESET.getAnsiCode());
        }
        
        data.addScore(roundScore);
		return result.toString();
	}
}