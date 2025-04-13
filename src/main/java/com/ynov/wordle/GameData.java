package com.ynov.wordle;

public class GameData implements IGameStatistics {
	
	private String target;
	private String guess;
	private int nbRemainingAttempts;
	private boolean correctAttempt;
	private int nbWins;
	private int streaks;
	private int totalAttempts;
	private int totalGamesPlayed;
	private int averageAttempts;
	
	public GameData() {
		this.nbRemainingAttempts = 6;
		this.correctAttempt = true;
		this.nbWins = 0;
		this.streaks = 0;
		this.averageAttempts = 0;
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
    
    @Override
    public int getNbWins() {
    	return nbWins;
    }
    
    @Override
    public void setNbWins(int nbWins) {
    	this.nbWins = nbWins;
    }
    
    @Override
    public int getStreaks() {
    	return streaks;
    }
    
    @Override
    public void setStreaks(int streaks) {
    	this.streaks = streaks;
    }
    
    @Override
    public int getTotalAttempts() {
    	return totalAttempts;
    }
    
    @Override
    public void setTotalAttempts(int totalAttempts) {
    	this.totalAttempts = totalAttempts;
    }
	
    @Override
    public int getTotalGamesPlayed() {
    	return totalGamesPlayed;
    }
    
    @Override
    public void setTotalGamesPlayed(int totalGamesPlayed) {
    	this.totalGamesPlayed = totalGamesPlayed;
    }
    
    @Override
    public int getAverageAttempts() {
    	return averageAttempts;
    }
    
    @Override
    public void setAverageAttempts(int averageAttempts) {
    	this.averageAttempts = averageAttempts;
    }
}
