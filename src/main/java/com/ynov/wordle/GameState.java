//package com.ynov.wordle;
//
//import java.util.Scanner;
//
//public class GameState {
//
//	private GameData data;
//	
//	public GameState(GameData data) {
//		this.data = data;
//	}
//
//	public void makeGuess() {
//		
//        Scanner scanner = new Scanner(System.in);
//        WordValidator validator = new WordValidator();
//		
//		System.out.print("Let's begin ! Enter a 5 letters word : ");
//		data.setGuess(scanner.nextLine());
//		
//		validator.validateWord(data.getGuess());
//		data.setNbRemainingAttempts(data.getNbRemainingAttempts() - 1);
//		GameLogic gameLogic = new GameLogic(data.getGuess(), data.getTarget());
//
//		if (gameLogic.checkGuess()) {
//			System.out.print("Well done ! You found the answer in : " + data.getNbRemainingAttempts() + " attemps !");
//		}
//		else {
//			System.out.print("Try again ! You have : " + data.getNbRemainingAttempts() + " attemps left");
//		}
//		
//		scanner.close();
//	}
//}