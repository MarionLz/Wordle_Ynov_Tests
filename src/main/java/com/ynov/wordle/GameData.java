package com.ynov.wordle;

public class GameData implements IGameStatistics {
	
	private String target;
	private String guess;
	private int nbRemainingAttempts;
	private boolean correctAttempt;
	
	public GameData() {
		this.nbRemainingAttempts = 6;
		this.correctAttempt = true;
	}
	
    @Override
	public String getTarget() {
		return target;
	}
	
    @Override
	public void setTarget(String target) {
		this.target = target;
	}
	
    @Override
	public String getGuess() {
		return guess;
	}
	
    @Override
	public void setGuess(String guess) {
		this.guess = guess;
	}
	
    @Override
	public int getNbRemainingAttempts() {
		return nbRemainingAttempts;
	}
    
    @Override
	public void setNbRemainingAttempts(int nbRemainingAttempts) {
		this.nbRemainingAttempts = nbRemainingAttempts;
	}
	
    @Override
	public boolean getCorrectAttempt() {
		return correctAttempt;
	}
	
    @Override
	public void setCorrectAttempt(boolean correctAttempt) {
		this.correctAttempt = correctAttempt;
	}
}
