package com.ynov.wordle;

public class GameData {
	
	private String target;
	//private String guess;
	private int nbRemainingAttempts;
	private boolean correctAttempt;
	
	public GameData() {}
	
	public GameData(String target) {
		this.target = target;
		this.nbRemainingAttempts = 6;
		this.correctAttempt = true;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
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
