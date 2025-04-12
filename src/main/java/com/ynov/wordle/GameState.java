package com.ynov.wordle;

import java.util.Scanner;

public class GameState {

	private GameData data;
	
	public GameState() {}

	public GameState(GameData data) {
		this.data = data;
	}

	public void makeGuess() {
		
        Scanner scanner = new Scanner(System.in);
        WordValidator validator = new WordValidator();
        
        System.out.println("Let's play !");

        boolean continueGame = true;
        
        while(continueGame) {
        	
    		System.out.println("Enter a 5 letters word (you have " + data.getNbRemainingAttempts() + " remaining attemps) :");
    		data.setGuess(scanner.nextLine());
    		if (validator.validateWord(data.getGuess()) && continueGame) {
        		data.setNbRemainingAttempts(data.getNbRemainingAttempts() - 1);
        		GameLogic gameLogic = new GameLogic(data);
        		
        		System.out.println(gameLogic.checkGuess());
        		if (data.getCorrectAttempt()) {
        			System.out.println("Well done ! You found the answer in : " + (6 - data.getNbRemainingAttempts()) + " attemps !");
        		}
        		else if (data.getNbRemainingAttempts() > 0){
        			System.out.println("Try again ! You have : " + data.getNbRemainingAttempts() + " attemps left.");
        		}
        		else {
        			continueGame = false;
        			System.out.println("Game over, no more attempts left.");
        		}
    		}
        }
	}
}