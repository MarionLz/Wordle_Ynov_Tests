package com.ynov.wordle.statistics;

import java.util.List;

public interface IGameStatistics {

	    String getGuess();
	    void setGuess(String guess);
	    
	    String getTarget();
	    void setTarget(String target);
	    
	    int getNbRemainingAttempts();
	    void setNbRemainingAttempts(int nbRemainingAttempts);
	    
	    boolean getCorrectAttempt();
	    void setCorrectAttempt(boolean correctAttempt);
	    
	    int getNbWins();
	    void setNbWins(int nbWins);
	    
	    int getStreaks();
	    void setStreaks(int streaks);
	    
		int getTotalAttempts();
		void setTotalAttempts(int totalAttempts);
		
		int getTotalGamesPlayed();
		void setTotalGamesPlayed(int totalGamesPlayed);
		
		public int getScore();
		public void setScore(int score);
		public void addScore(int points);
		
	    public List<Integer> getScoreHistory();
	    public void addScoreToHistory(int score);
}
