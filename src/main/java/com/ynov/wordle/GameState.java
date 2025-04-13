package com.ynov.wordle;

import com.ynov.wordle.inputReader.IInputReader;
import com.ynov.wordle.inputReader.ScannerInputReader;
import com.ynov.wordle.statistics.IGameStatistics;

public class GameState {

	private IInputReader inputReader;
	private WordValidator validator;
	private IGameStatistics data;
	private GameLogic gameLogic;
	
	public GameState() {}
	
	public GameState(IGameStatistics data) {
		this.data = data;
		this.inputReader = new ScannerInputReader();
		this.validator = new WordValidator();
		this.gameLogic = new GameLogic();
	}
	
	public GameState(IInputReader inputReader, WordValidator validator, IGameStatistics data, GameLogic gameLogic) {
		this.inputReader = inputReader;
		this.validator = validator;
		this.data = data;
		this.gameLogic = gameLogic;
	}
	
	private IGameStatistics updateStatsWhenWin(IGameStatistics data) {
		
		data.setNbWins(data.getNbWins() + 1);
		data.setStreaks(data.getStreaks() + 1);
		data.setTotalAttempts(data.getTotalAttempts() + 6 - data.getNbRemainingAttempts());
		data.setTotalGamesPlayed(data.getTotalGamesPlayed() + 1);
		return data;
	}
	
	private IGameStatistics updateStatsWhenLoose(IGameStatistics data) {
		
		data.setStreaks(0);
		data.setTotalAttempts(data.getTotalAttempts() + 6 - data.getNbRemainingAttempts());
		data.setTotalGamesPlayed(data.getTotalGamesPlayed() + 1);
		return data;
	}

	public IGameStatistics makeGuess() {
        
        System.out.println("Let's play !");

        boolean continueGame = true;
        int nbAttempsInvalidWord = 1;
        data.setNbRemainingAttempts(6);
        
        while(continueGame) {
        	
    		System.out.println("Enter a 5 letters word (you have " + data.getNbRemainingAttempts() + " remaining attemps) :");
    		data.setGuess(inputReader.getInput());
    		
    		if (validator.validateWord(data.getGuess()) && continueGame) {
        		data.setNbRemainingAttempts(data.getNbRemainingAttempts() - 1);
        		gameLogic.setData(data);
        		System.out.println(gameLogic.checkGuess());
        		if (data.getCorrectAttempt()) {
        			continueGame = false;
        			data = updateStatsWhenWin(data);
        			System.out.println("Well done ! You found the answer in : " + (6 - data.getNbRemainingAttempts()) + " attemps !");
        		}
        		else if (data.getNbRemainingAttempts() > 0){
        			System.out.println("Try again ! You have : " + data.getNbRemainingAttempts() + " attemps left.");
        		}
        		else {
        			continueGame = false;
        			data = updateStatsWhenLoose(data);
        			System.out.println("Game over, no more attempts left.");
        		}
    		}
    		else if (nbAttempsInvalidWord > 0) {
    			nbAttempsInvalidWord--;
    			System.out.println("Be careful, if you enter another invalid word, you will loose.");
    		}
    		else if (nbAttempsInvalidWord == 0){
    			continueGame = false;
    			data = updateStatsWhenLoose(data);
    			System.out.println("Game over, you entered 2 invalid words.");
    		}
        }
        return data;
	}
}