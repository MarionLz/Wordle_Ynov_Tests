package com.ynov.wordle.statistics;

import java.util.ArrayList;
import java.util.List;

public class GameData implements IGameStatistics {
	
	private String target;
	private String guess;
	private int nbRemainingAttempts;
	private boolean correctAttempt;
	private int nbWins;
	private int streaks;
	private int totalAttempts;
	private int totalGamesPlayed;
	private int score;
    private List<Integer> scoreHistory;
	
	public GameData() {
		this.nbRemainingAttempts = 6;
		this.correctAttempt = true;
		this.scoreHistory = new ArrayList<>();
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
	public int getScore() {
    	return score;
    }
    
    @Override
	public void setScore(int score) {
    	this.score = score;
    }
    
    @Override
	public void addScore(int points) {
    	this.score += points;
    }

    @Override
    public List<Integer> getScoreHistory() {
        return scoreHistory;
    }
    
    @Override
    public void addScoreToHistory(int score) {
        scoreHistory.add(score);
    }
}
