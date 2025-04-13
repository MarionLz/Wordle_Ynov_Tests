package com.ynov.wordle;

import java.util.Random;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynov.wordle.dictionaryLoader.Dictionary;
import com.ynov.wordle.dictionaryLoader.IDictionaryLoader;
import com.ynov.wordle.inputReader.IInputReader;
import com.ynov.wordle.statistics.GameData;
import com.ynov.wordle.statistics.IGameStatistics;

public class StartGame {

    private IInputReader inputReader;
    
    private IGameStatistics data;
    private GameState gameState;
    private IDictionaryLoader dictionaryLoader;
    
    public StartGame() {}
    
    public StartGame(IInputReader inputReader, IGameStatistics data, IDictionaryLoader dictionaryLoader) {
    	this.inputReader = inputReader;
    	this.data = data;
        this.dictionaryLoader = dictionaryLoader;
    }
    
    private void loadMenu(){
        System.out.println("Please select an option. Simply enter the number to choose an action :");
        System.out.println("1 Start a new game");
        System.out.println("2 Display game statistics");
        System.out.println("3 Exit game");
    }
    
    private int readSelection() {
        try {
            return Integer.parseInt(inputReader.getInput());
        } catch(Exception e){
            System.out.println("Error reading input. Please enter valid number for proceeding further");
            return -1;
        }
    }
    
    private void startNewGame() {
    
        data.setTarget(dictionaryLoader.loadRandomWord());
        gameState = new GameState(data);
    	data = gameState.makeGuess();
    }
    
    private void displayGameStatistics() {
    	
        int averageAttempts = data.getTotalGamesPlayed() > 0 ?
                data.getTotalAttempts() / data.getTotalGamesPlayed() : 0;
        
    	System.out.println("Game Statistics");
    	System.out.println("Nb wins : " + data.getNbWins());
    	System.out.println("Streaks : " + data.getStreaks());
    	System.out.println("Average Attempts : " + averageAttempts);
    }
    
	public void loadGame() {
		
		System.out.print("Welcome to this Wordle Game !");
		
        boolean continueApp = true;
    	data = new GameData();

        while(continueApp){
        	loadMenu();
            int option = readSelection();
            switch(option){
                case 1 -> startNewGame();
                case 2 -> displayGameStatistics();
                case 3 -> {
                	System.out.println("Exiting from the game, see you soon !");
                    continueApp = false;
                }
                default -> System.out.println("Unsupported option. Please enter a number corresponding to the provided menu");
            }
        }
	}
}
