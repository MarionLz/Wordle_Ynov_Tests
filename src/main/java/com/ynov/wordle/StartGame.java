package com.ynov.wordle;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StartGame {

    private static Scanner scan = new Scanner(System.in);
    private static IGameStatistics data;
    private static GameState gameState;
    
    private static void loadMenu(){
        System.out.println("Please select an option. Simply enter the number to choose an action :");
        System.out.println("1 Start a new game");
        System.out.println("2 Display game statistics");
        System.out.println("3 Exit game");
    }
    
    private static int readSelection() {
        try {
            int input = Integer.parseInt(scan.nextLine());
            return input;
        } catch(Exception e){
            System.out.println("Error reading input. Please enter valid number for proceeding further");
            return -1;
        }
    }
    
    private static String loadDictionary() {
    	
    	String randomWord = "";
    	
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Dictionary dictionary = objectMapper.readValue(new File("dictionaries/french_dictionary.json"), Dictionary.class);

            Random random = new Random();
            randomWord = dictionary.words.get(random.nextInt(dictionary.words.size()));

            System.out.println("Le mot à deviner est : " + randomWord);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomWord;
    }
    
    private static void displayGameStatistics(IGameStatistics data) {
    	
    	int averageAttempts = 0;
    	
    	System.out.println("Game Statistics");
    	System.out.println("Nb wins : " + data.getNbWins());
    	System.out.println("Streaks : " + data.getStreaks());
    	
    	if (data.getTotalGamesPlayed() > 0) {
    		averageAttempts = data.getTotalAttempts() / data.getTotalGamesPlayed();
    	}
    	System.out.println("Average Attempts : " + averageAttempts);
    }
    
	public static void loadGame() {
		
		System.out.print("Welcome to this Wordle Game !");
		
        boolean continueApp = true;
    	data = new GameData();

        while(continueApp){
        	loadMenu();
            int option = readSelection();
            switch(option){
                case 1: {
                	data.setTarget(loadDictionary());
                	gameState = new GameState(data);
                	data = gameState.makeGuess();
                	break;
                }
                case 2: {
                	displayGameStatistics(data);
                	break;
                }
                case 3: {
                    System.out.println("Exiting from the game, see you soon !");
                    continueApp = false;
                    break;
                }
                default: System.out.println("Unsupported option. Please enter a number corresponding to the provided menu");
            }
        }
	}
}
