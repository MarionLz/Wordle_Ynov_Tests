package com.ynov.wordle;

public class GameData {
	
	private String target;
	private String guess;
	private int nbRemainingAttempts;
	private boolean correctAttempt;
	
	public GameData() {
		this.nbRemainingAttempts = 6;
		this.correctAttempt = true;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getGuess() {
		return guess;
	}
	
	public void setGuess(String guess) {
		this.guess = guess;
	}
	
	public int getNbRemainingAttempts() {
		return nbRemainingAttempts;
	}
	
	public void setNbRemainingAttempts(int nbRemainingAttempts) {
		this.nbRemainingAttempts = nbRemainingAttempts;
	}
	
	public boolean getCorrectAttempt() {
		return correctAttempt;
	}
	
	public void setCorrectAttempt(boolean correctAttempt) {
		this.correctAttempt = correctAttempt;
	}
}
