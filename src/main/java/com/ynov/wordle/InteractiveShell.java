package com.ynov.wordle;

import java.util.Scanner;

public class InteractiveShell {

    private static Scanner scan = new Scanner(System.in);
    private static GameData data = new GameData("frais");
    private static GameState gameState = new GameState(data);
    
	public static void loadGame() {
		
		System.out.print("Welcome to this Wordle Game !");
		
        boolean continueApp = true;
        
        while(continueApp){
        	loadMenu();
            int option = readSelection();
            switch(option){
                case 1: {
                	gameState.makeGuess();
                	break;
                }
                case 2: {
                    System.out.println("Exiting from the game, see you soon !");
                    continueApp = false;
                    break;
                }
                default: System.out.println("Unsupported option. Please enter a number corresponding to the provided menu");
            }
        }
	}
	
    private static void loadMenu(){
        System.out.println("Please select an option. Simply enter the number to choose an action :");
        System.out.println("1 Start a new game");
        System.out.println("2 Exit game");
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
}
